rabbitmq：
    fanout-exchanger
消费者懒生成queue

# 调用过程， 交换机与消费者是解耦的，队列和消费者才是耦合的
# 交换机与队列提前全绑定好，收到消息广播所有队列，不再需要routing_key做二次校验
触发器(交换机名字) -> 交换机()  -> 队列() <-topic_name-> 消费者