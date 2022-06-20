package org.spring_demo.Mq.rabbitMq.Topic_exchanger.configuration;


import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class TopicExchangeBeanConfiguration {
    @Bean(MqAttrConfiguration.RABBITMQ_QUEUE_TOPIC_A)
    public Queue rabbitmqTopicQueueA(){
        return new Queue(MqAttrConfiguration.RABBITMQ_QUEUE_TOPIC_A, true, false, false);
    }

    @Bean(MqAttrConfiguration.RABBITMQ_QUEUE_TOPIC_B)
    public Queue rabbitmqTopicQueueB(){
        return new Queue(MqAttrConfiguration.RABBITMQ_QUEUE_TOPIC_B, true, false, false);
    }

    @Bean
    public TopicExchange rabbitmqTopicExchange(){
        return new TopicExchange(MqAttrConfiguration.RABBITMQ_TOPIC_EXCHANGE, true, false);
    }

    @Bean
    public Binding bindingTopicA(TopicExchange TopicExchange,
            @Autowired
            @Qualifier(MqAttrConfiguration.RABBITMQ_QUEUE_TOPIC_A)
            Queue queue)
    {
        return BindingBuilder.bind(queue).to(TopicExchange).with(MqAttrConfiguration.RABBITMQ_QUEUE_TOPIC_ROUTING_KEY_DOT);
    }

    @Bean
    public Binding bindingTopicB(TopicExchange TopicExchange,
                            @Autowired
                            @Qualifier(MqAttrConfiguration.RABBITMQ_QUEUE_TOPIC_B)
                                    Queue queue)
    {
        return BindingBuilder.bind(queue).to(TopicExchange).with(MqAttrConfiguration.RABBITMQ_QUEUE_TOPIC_ROUTING_KEY_SHAPE);
    }
}
