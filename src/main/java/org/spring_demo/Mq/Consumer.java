package org.spring_demo.Mq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

//@Component
@Slf4j
public class Consumer {
    @Service
    @RocketMQMessageListener(consumerGroup = "consumer-group-1", topic = "rocket-topic-2")
    public class Consumer1 implements RocketMQListener<String> {

        @Override
        public void onMessage(String s) {
            log.info("consumer1 rocket 收到信息：{}", s);
        }
    }

    @Service
    @RocketMQMessageListener(consumerGroup = "consumer-group-2", topic = "rocket-topic-2",
    selectorExpression = "tag2", messageModel = MessageModel.BROADCASTING
    )
    public class Consumer2 implements RocketMQListener<String> {

        @Override
        public void onMessage(String s) {
            log.info("consumer2 rocket 收到信息：{}", s);
        }
    }


}
