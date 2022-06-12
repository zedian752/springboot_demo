package org.spring_demo.service.dubbo.impl;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.spring_demo.entity.DataResponse;
import org.spring_demo.utils.TimerUtils;
import utils.HttpClientUtils;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


//@SpringBootTest
//@RunWith(SpringRunner.class)
//@AutoConfigureMockMvc
public class UserApiImplTest {
    private final static int threadCount = 100;
//    private final static ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
    private final static ExecutorService executorService = new ThreadPoolExecutor(5, 5, 1000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(),Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
    private final String host = "http://127.0.0.1";
    private final int port = 9001;
    private final String target = String.format("%s:%d", host, port);

    // singleFlight单元测试
    @Test
    public void addUserController() throws Exception {
        int count = 10; // 请求次数
        String url = target + "/addUser9999";
        List<String> resList = new CopyOnWriteArrayList<>(); // 多并发下出现的null元素问题

        // 计数器
        CountDownLatch countDownLatch = new CountDownLatch(count);
        int round = 1;
        System.out.printf("请求次数为%d, mod为 %d, 请求线程池大小为%d%n", count, round, threadCount);
        for (int i = 0 ; i < count; ++i) {
            int finalI = i;
            Runnable task = () -> {
            HashMap<String, String> params = new HashMap<String, String>(){{
                put("userId", String.valueOf(finalI % round));
            }};
                System.out.println("发出请求" + (finalI));
                String res = null;
                try {
                    res = HttpClientUtils.get(url, params);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                DataResponse<String> dataResponse = JSONObject.parseObject(res, DataResponse.class);
//                JSONObject jsonObject = dataResponse.getData();
//                singleFlight<String> singleFlight = new singleFlight<>();
//                singleFlight.setType(jsonObject.getString("type"));
//                singleFlight.setRes(jsonObject.getString("res"));
                resList.add(dataResponse.getData());
                // 减数操作一定要放在Collection的modify后面，否则会junit出现ConcurrentModificationException，因为在对Collection循环的时候，有可能会出现add元素的操作
                countDownLatch.countDown();
            };
            executorService.execute(task);
        }
        countDownLatch.await();
//        Map<String, List<singleFlight<String>>> groups = resList.stream().collect(Collectors.groupingBy(i -> ))
        // 生产次数与线程池大小相关，如果线程不充足，在单元测试时，下一批线程只能推迟发送，从而引起多次生
//        for (DataResponse<singleFlight<String>> s : resList) {
//
////            JSONObject.toJavaObject(dataResponse, singleFlight.class);
//        }
    }


    // 每一个字符串id作为一把锁
    @Test
    public void stringLock() throws Exception {
        int count = 10;
        String url = target + "/addUser9999";
        List<String> resList = new ArrayList<>();

        Long start = System.currentTimeMillis();

        // 计数器
        CountDownLatch countDownLatch = new CountDownLatch(count);
        int round = 5;
        for (int i = 0 ; i < count; ++i) {
            int finalI = i;
            Callable<String> task = () -> {
                String userId = String.valueOf(finalI % round);
                HashMap<String, String> params = new HashMap<String, String>(){{
//                put("userId", String.valueOf(finalI % round));
                    put("userId", userId);
                }};
                String res = call(userId, url, params);
                System.out.println("当前count为" + countDownLatch.getCount());
                resList.add(res);
                // 减数操作一定要放在Collection的modify后面，否则会出现ConcurrentModificationException，因为在对Collection循环的时候，有可能会出现add元素的操作
                countDownLatch.countDown();
                return res;
            };
            executorService.submit(task);
        }
        countDownLatch.await();
        // 输出结果
        for (String s : resList) {
            System.out.println(s);
        }
        TimerUtils.getAndPrintEndTime(start);
    }
    String call(String userId, String url, HashMap<String, String> map) throws URISyntaxException, InterruptedException {
        synchronized (userId.intern()) {
            Thread.sleep(500);
            return userId;
        }
    }
    // 测试条件变量
    @Test
    public void testExecutor() {
        ReentrantLock lock = new ReentrantLock();
        AtomicReference<String> res = new AtomicReference<>();
        Condition condition = lock.newCondition();
        Thread t1 =  new Thread(() -> {
            lock.lock();
            try {
                condition.await();
                System.out.println(res.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        Thread t2 = new Thread(() -> {
                try {
                    lock.lock();
                    Thread.sleep(2000);
                    res.set("caonima");
                    condition.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }


        });
        try {
            t1.start();
            t2.start();
            t1.join();
        } catch (InterruptedException e) {
            System.out.println("被打断了" );
            e.printStackTrace();
        }
    }


}