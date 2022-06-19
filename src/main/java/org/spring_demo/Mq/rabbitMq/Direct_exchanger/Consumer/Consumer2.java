package org.spring_demo.Mq.rabbitMq.Direct_exchanger.Consumer;

import lombok.extern.slf4j.Slf4j;
import org.spring_demo.Mq.rabbitMq.Direct_exchanger.configuration.DirectExchangeBeanConfiguration;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
//@Component
//@RabbitListener(queuesToDeclare = @Queue(DirectExchangeBeanConfiguration.RABBITMQ_QUEUE_NAME))
public class Consumer2 {

    @RabbitHandler
    public void handler2(Map msg) { // 进行消费
        log.info(msg.toString());
    }
}
