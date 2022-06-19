package org.spring_demo.Mq.rabbitMq.Topic_exchanger.trigger;

import org.spring_demo.Mq.rabbitMq.Topic_exchanger.Service.MqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("topicController")
public class MqController {

    @Autowired
    @Qualifier("topicService")
    MqService mqService;

    @GetMapping("/mq3/sendMsgDot")
    public void sendMsgDot() {
        mqService.send_dot("dot");
    }

    @GetMapping("/mq3/sendMsgShape")
    public void sendMsgShape() {
        mqService.send_shape("shape");
    }

    @GetMapping("/mq3/send_topic")
    public void sendMsgTopic(String topicName) {
        mqService.send_topic(topicName);
    }
}
