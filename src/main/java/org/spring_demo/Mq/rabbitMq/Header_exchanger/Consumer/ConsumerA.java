package org.spring_demo.Mq.rabbitMq.Header_exchanger.Consumer;

import lombok.extern.slf4j.Slf4j;
import org.spring_demo.Mq.rabbitMq.Header_exchanger.configuration.MqAttrConfiguration;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
@Slf4j

//@Component("ConsumerHeaderA")
public class ConsumerA {

    @RabbitListener(queuesToDeclare = @Queue(MqAttrConfiguration.RABBITMQ_QUEUE_HEADER_A))
    public void handler(Message  msg) { // 进行消费

        log.info(new String(msg.getBody()));
    }

}
