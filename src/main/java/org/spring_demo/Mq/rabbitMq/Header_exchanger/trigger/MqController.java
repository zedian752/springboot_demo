package org.spring_demo.Mq.rabbitMq.Header_exchanger.trigger;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.spring_demo.Mq.rabbitMq.Header_exchanger.Service.MqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController("headerController")
public class MqController {

    @Autowired
    @Qualifier("headerService")
    MqService mqService;


    // {"key_one":"java","key_two":"rabbit"}
    // {\"headers_A\":\"coke\",\"headers_B\":\"sky\"}
    @PostMapping("/mq4/send_header")
    public void sendMsgHeader(@RequestParam(name = "msg") String msg, @RequestParam(name = "json") String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(json, Map.class);

        mqService.send_header(msg, map);
    }
}
