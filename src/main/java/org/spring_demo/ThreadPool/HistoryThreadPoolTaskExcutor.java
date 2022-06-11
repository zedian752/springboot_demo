package org.spring_demo.ThreadPool;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.*;

/**
 * @since 2022-04-18 20:23:50
 * @description 专门做插入历史记录异步任务
 */
@Configuration
@EnableAsync
public class HistoryThreadPoolTaskExcutor {

    @Bean("asyncHistoryServiceExecutor")
    public Executor asyncHistoryServiceExecutor() {
        return new ThreadPoolExecutor(5, 5, 1000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(),Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
    }
}
