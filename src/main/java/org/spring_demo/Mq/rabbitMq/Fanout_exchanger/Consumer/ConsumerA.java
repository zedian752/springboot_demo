package org.spring_demo.Mq.rabbitMq.Fanout_exchanger.Consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;

import java.util.Map;

@Slf4j
//@Component
//@RabbitListener(queuesToDeclare = @Queue(MqAttrConfiguration.RABBITMQ_QUEUE_TOPIC_A))
public class ConsumerA {


    @RabbitHandler
    public void handler(Map<String, Object> msg) { // 进行消费
        log.info(msg.toString());
    }
}
