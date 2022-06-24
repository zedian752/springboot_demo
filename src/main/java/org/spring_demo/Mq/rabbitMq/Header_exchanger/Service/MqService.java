package org.spring_demo.Mq.rabbitMq.Header_exchanger.Service;


import org.spring_demo.Mq.rabbitMq.Header_exchanger.configuration.MqAttrConfiguration;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Map;

@Service("headerService")
public class MqService {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private RabbitTemplate rabbitTemplate;



    public boolean send_header(String msg, Map<String, Object> map) {

        MessageProperties messageProperties = new MessageProperties();
        //消息持久化
        messageProperties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
        messageProperties.setContentType("UTF-8");
        //添加消息
        messageProperties.getHeaders().putAll(map);
        Message message = new Message(msg.getBytes(), messageProperties);
        rabbitTemplate.convertAndSend(MqAttrConfiguration.RABBITMQ_HEADER_EXCHANGE, null, message);


        return true;
    }


}
