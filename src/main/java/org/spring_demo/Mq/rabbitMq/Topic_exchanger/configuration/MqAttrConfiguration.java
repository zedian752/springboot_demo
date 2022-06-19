package org.spring_demo.Mq.rabbitMq.Topic_exchanger.configuration;

public class MqAttrConfiguration {

    public static final String RABBITMQ_TOPIC_EXCHANGE = "rabbitmqTopicExchange";

    //
    public static final String RABBITMQ_QUEUE_TOPIC_A = "topic.A";
    public static final String RABBITMQ_QUEUE_TOPIC_B = "topic.B";

    // routing_key
    public static final String RABBITMQ_QUEUE_TOPIC_ROUTING_KEY_DOT = "topic.*";
    public static final String RABBITMQ_QUEUE_TOPIC_ROUTING_KEY_SHAPE = "topic.#";
    public static final String RABBITMQ_QUEUE_TOPIC_ROUTING_KEY_SPECIFIC = "topic.specific";
}
