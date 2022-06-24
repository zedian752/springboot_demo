package org.spring_demo.Mq.rabbitMq.Header_exchanger.configuration;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

//@Configuration
public class HeaderExchangeBeanConfiguration {
    @Bean(MqAttrConfiguration.RABBITMQ_QUEUE_HEADER_A)
    public Queue rabbitmqHeaderQueueA(){
        return new Queue(MqAttrConfiguration.RABBITMQ_QUEUE_HEADER_A, true, false, false);
    }

    @Bean(MqAttrConfiguration.RABBITMQ_QUEUE_HEADER_B)
    public Queue rabbitmqHeaderQueueB(){
        return new Queue(MqAttrConfiguration.RABBITMQ_QUEUE_HEADER_B, true, false, false);
    }

    @Bean
    public HeadersExchange rabbitmqHeadersExchange(){
        return new HeadersExchange(MqAttrConfiguration.RABBITMQ_HEADER_EXCHANGE, true, false);
    }

    @Bean
    public Binding bindingHeaderA(HeadersExchange headerExchanger,
            @Autowired
            @Qualifier(MqAttrConfiguration.RABBITMQ_QUEUE_HEADER_A)
            Queue queue)
    {
        Map<String, Object> map = new HashMap<>();
        map.put("key_one", "java");
        map.put("key_two", "rabbit");
            return BindingBuilder.bind(queue).to(headerExchanger).whereAll(map).match();
    }

    @Bean
    public Binding bindingHeaderB(HeadersExchange headerExchanger,
                            @Autowired
                            @Qualifier(MqAttrConfiguration.RABBITMQ_QUEUE_HEADER_B)
                                    Queue queue)
    {
        Map<String, Object> map = new HashMap<>();
        map.put("headers_A", "coke");
        map.put("headers_B", "sky");
        return BindingBuilder.bind(queue).to(headerExchanger).whereAny(map).match();
    }
}
