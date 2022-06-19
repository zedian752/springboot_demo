package org.spring_demo.Mq.rabbitMq.Topic_exchanger.Service;


import org.spring_demo.Mq.rabbitMq.Topic_exchanger.configuration.MqAttrConfiguration;
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

@Service("topicService")
public class MqService {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public boolean send_dot(String msg) {
        String msgId = UUID.randomUUID().toString();
        String dateTime = sdf.format(new Date());

        Message message = new Message((msgId + dateTime + msg).getBytes(StandardCharsets.UTF_8), new MessageProperties());
        Map<String, Object> map = new HashMap<>();
        map.put("msgId", msgId);
        map.put("sendTime", dateTime);
        map.put("msg", msg);

        rabbitTemplate.convertAndSend(MqAttrConfiguration.RABBITMQ_TOPIC_EXCHANGE, "topic.email", map);
        return true;
    }

    public boolean send_shape(String msg) {
        String msgId = UUID.randomUUID().toString();
        String dateTime = sdf.format(new Date());

        Map<String, Object> map = new HashMap<>();
        map.put("msgId", msgId);
        map.put("sendTime", dateTime);
        map.put("msg", msg);

        rabbitTemplate.convertAndSend(MqAttrConfiguration.RABBITMQ_TOPIC_EXCHANGE, "topic.email.a", map);
        rabbitTemplate.convertAndSend(MqAttrConfiguration.RABBITMQ_TOPIC_EXCHANGE, "topic.cd.b", map);
        return true;
    }

    public boolean send_topic(String topic) {
        String msgId = UUID.randomUUID().toString();
        String dateTime = sdf.format(new Date());

        Map<String, Object> map = new HashMap<>();
        map.put("msgId", msgId);
        map.put("sendTime", dateTime);
        map.put("msg", String.format("topic is %s", topic));

        rabbitTemplate.convertAndSend(MqAttrConfiguration.RABBITMQ_TOPIC_EXCHANGE, topic, map);
        return true;
    }


}
