package org.spring_demo.Mq.rabbitMq.Fanout_exchanger.trigger;

import org.spring_demo.Mq.rabbitMq.Fanout_exchanger.Service.MqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("fanoutController")
public class MqController {

    @Autowired
    @Qualifier("fanoutService")
    MqService mqService;

    @GetMapping("/mq2/sendMsg")
    public void sendMsg() {
        mqService.send("hel");
    }
}
