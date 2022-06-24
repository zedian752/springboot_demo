package org.spring_demo.Mq.rabbitMq.Direct_exchanger.configuration;

public class DirectExchangeBeanConfiguration {
    public static final String RABBITMQ_QUEUE_NAME = "rabbitmq_queue1";
    public static final String DEAD_LETTER_QUEUE_NAME = "rabbitmq_direct_dead_queue";


    public static final String RABBITMQ_DIRECT_EXCHANGE = "rabbitmqDirectExchange";

    public static final String DEAD_LETTER_EXCHANGE_NAME = "rabbitmqDirectDeadLetterExchange";

    public static final String RABBITMQ_DIRECT_ROUTING_KEY = "rabbitDirectRouting";
}
