package org.spring_demo.Mq.rabbitMq.Direct_exchanger.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

//@Configuration
public class RabbitmqDirect {
    // 设置业务队列
    @Bean
    public Queue rabbitmqDirectQueue(){
        Map<String, Object> args = new HashMap<>(4);
        args.put("x-message-ttl", 100000); // 业务队列最大超时时间
        args.put("x-max-length", 3); // 业务队列最大长度
        args.put("x-dead-letter-routing-key", DirectExchangeBeanConfiguration.RABBITMQ_DIRECT_ROUTING_KEY);
        args.put("x-dead-letter-exchange", DirectExchangeBeanConfiguration.DEAD_LETTER_EXCHANGE_NAME);
        // 设置业务队列的死信交换机
        return new Queue(DirectExchangeBeanConfiguration.RABBITMQ_QUEUE_NAME, true, false, false, args);
    }



    @Bean
    public DirectExchange rabbitmqDirectExchange(){
        return new DirectExchange(DirectExchangeBeanConfiguration.RABBITMQ_DIRECT_EXCHANGE, true, false);
    }

    // 声明死信队列
    @Bean
    public Queue deadLetterQueue(){
        return new Queue(DirectExchangeBeanConfiguration.DEAD_LETTER_QUEUE_NAME);
    }

    // 声明死信交换机
    @Bean
    public DirectExchange deadLetterExchange(){
        return new DirectExchange(DirectExchangeBeanConfiguration.DEAD_LETTER_EXCHANGE_NAME, true, false);
    }

    // 绑定死信交换机与死信队列，使用业务组的routing_key
    @Bean
    public Binding bindDeadLetterQueue(){
        return BindingBuilder
                .bind(deadLetterQueue())
                .to(deadLetterExchange())
                .with(DirectExchangeBeanConfiguration.RABBITMQ_DIRECT_ROUTING_KEY);
    }


    @Bean
    public Binding bingDirect() {
        return BindingBuilder
                .bind(rabbitmqDirectQueue()) // 绑定队列
                .to(rabbitmqDirectExchange()) // 设置交换机
                .with(DirectExchangeBeanConfiguration.RABBITMQ_DIRECT_ROUTING_KEY); // 设置精确routing_key
    }
}
