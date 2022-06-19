package org.spring_demo.Mq.rabbitMq.Topic_exchanger.Consumer;

import lombok.extern.slf4j.Slf4j;

import org.spring_demo.Mq.rabbitMq.Topic_exchanger.configuration.MqAttrConfiguration;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component("ConsumerTopicB")
@RabbitListener(queuesToDeclare = @Queue(MqAttrConfiguration.RABBITMQ_QUEUE_TOPIC_B))
public class ConsumerB {


    @RabbitHandler
    public void handler(Map<String, Object> msg) { // 进行消费
        log.info(msg.toString());
    }
}
