package org.spring_demo.Mq.rabbitMq.Direct_exchanger.Consumer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;

import java.util.Map;

@Slf4j
//@Component
//@RabbitListener(queuesToDeclare = @Queue(DirectExchangeBeanConfiguration.RABBITMQ_QUEUE_NAME))
//@RabbitListener(queues =  DirectExchangeBeanConfiguration.RABBITMQ_QUEUE_NAME)
public class Consumer {

    @RabbitHandler
    public void handler(Map<String, String> msg, Channel channel, Message message) { // 进行消费
        log.info("begin consumer");
        int a = 1 / 0;
//        try {
//            String s_msg = msg.get("msg");
//            if ("true".equals(s_msg)) {
//                throw new Exception();
//            }
//            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//        } catch (Exception e) {
//            try {
//                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
//            } catch (IOException ioException) {
//                log.warn("basicNack error");
//            }
//        }
    }
}
