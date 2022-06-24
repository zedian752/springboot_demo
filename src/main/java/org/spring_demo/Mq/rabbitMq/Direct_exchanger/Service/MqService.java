package org.spring_demo.Mq.rabbitMq.Direct_exchanger.Service;

import org.spring_demo.Mq.rabbitMq.Direct_exchanger.configuration.DirectExchangeBeanConfiguration;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service("directService")
public class MqService {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public boolean send(String msg) {
        String msgId = UUID.randomUUID().toString();
        String dateTime = sdf.format(new Date());

        Message message = new Message((msgId + dateTime + msg).getBytes(StandardCharsets.UTF_8), new MessageProperties());
        Map<String, Object> map = new HashMap<>();
        map.put("msgId", msgId);
        map.put("sendTime", dateTime);
        map.put("msg", msg);
        rabbitTemplate.convertAndSend(DirectExchangeBeanConfiguration.RABBITMQ_DIRECT_EXCHANGE, DirectExchangeBeanConfiguration.RABBITMQ_DIRECT_ROUTING_KEY, map);
        return true;
    }


}
