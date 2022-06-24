package org.spring_demo.Mq.rabbitMq.Direct_exchanger.Consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;

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
