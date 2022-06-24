package org.spring_demo.Mq.rabbitMq.Direct_exchanger.trigger;

import org.spring_demo.Mq.rabbitMq.Direct_exchanger.Service.MqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("directController")
public class MqController {

    @Autowired
    @Qualifier("directService")
    MqService mqService;

    @GetMapping("/mq1/sendMsg")
    public void sendMsg(String bool) {
        mqService.send(bool);
    }
}
