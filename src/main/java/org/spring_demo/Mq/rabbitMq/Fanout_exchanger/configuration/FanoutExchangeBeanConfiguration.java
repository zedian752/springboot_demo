package org.spring_demo.Mq.rabbitMq.Fanout_exchanger.configuration;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Configuration
public class FanoutExchangeBeanConfiguration {
    @Bean(MqAttrConfiguration.RABBITMQ_QUEUE_TOPIC_A)
    public Queue rabbitmqFanoutQueueA(){
        return new Queue(MqAttrConfiguration.RABBITMQ_QUEUE_TOPIC_A, true, false, false);
    }

    @Bean(MqAttrConfiguration.RABBITMQ_QUEUE_TOPIC_B)
    public Queue rabbitmqFanoutQueueB(){
        return new Queue(MqAttrConfiguration.RABBITMQ_QUEUE_TOPIC_B, true, false, false);
    }

    @Bean
    public FanoutExchange rabbitmqFanoutExchange(){
        return new FanoutExchange(MqAttrConfiguration.RABBITMQ_FANOUT_EXCHANGE, true, false);
    }

    @Bean
    public Binding bindingA(FanoutExchange fanoutExchange,
            @Autowired
            @Qualifier(MqAttrConfiguration.RABBITMQ_QUEUE_TOPIC_A)
            Queue queue)
    {
            return BindingBuilder.bind(queue).to(fanoutExchange);
    }

    @Bean
    public Binding bindingB(FanoutExchange fanoutExchange,
                            @Autowired
                            @Qualifier(MqAttrConfiguration.RABBITMQ_QUEUE_TOPIC_B)
                                    Queue queue)
    {
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }
}
