package org.spring_demo.service.dubbo.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.SingleFlight.Result;
import org.SingleFlight.SingleFlight;
import org.SingleFlight.Task;
import org.spring_demo.dao.UserDaoPlus;
import org.spring_demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Future;

@Service
@Slf4j
public class UserApiImpl {

    @Autowired
    UserDaoPlus userDaoPlus;
    public List<User> findAllUsers(){
        return userDaoPlus.getBaseMapper().selectList(new QueryWrapper<User>());
    }
    SingleFlight<String, String> singleFlight = new SingleFlight<>();

    // 定时检查map大小
//    @PostConstruct
//    public void afterPropertiesSet() throws Exception {
//        new Thread(() -> {
//            while(true) {
//                try {
//                    Thread.sleep(5000);
//                    log.info("map 大小为 {}", map.size());
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }).start();
//    }

    // TODO 场景，锁结果 多线程同时进来的时候，如果key不在map里，会产生多次计算
    Task<String> supplier = new Task<String>() {
        public String get() {
            try {
                Thread.sleep(1000);
                return "生产完成";
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    };


    public String addUser(String userId) {
        Result<String> res = null;
        try {
            res = singleFlight.setResult(userId, supplier);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return res.getVal();
    }

    @Async("asyncHistoryServiceExecutor")
    public Future<Void> asyncTest() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        throw new NullPointerException("");
//        log.info("异步任务完成");
    }
    @Async("asyncHistoryServiceExecutor")
    public void asyncTest(Integer args) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        throw new NullPointerException("");
//        log.info("异步任务完成");
    }
}
