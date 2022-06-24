package org.spring_demo.Mq.rabbitMq.Fanout_exchanger.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

//@Configuration
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
