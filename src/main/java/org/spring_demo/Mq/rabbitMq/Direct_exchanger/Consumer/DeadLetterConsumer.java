package org.spring_demo.Mq.rabbitMq.Direct_exchanger.Consumer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;

import java.util.Map;

@Slf4j
//@Component
//@RabbitListener(queues =  DirectExchangeBeanConfiguration.DEAD_LETTER_QUEUE_NAME)
public class DeadLetterConsumer {

    @RabbitHandler
    public void handler(Map<String, String> msg, Channel channel, Message message) { //
        try {
            log.info(msg.toString());
//            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}