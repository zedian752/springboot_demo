rabbitmq：
    direct-exchanger
消费者懒生成queue



# 队列-构造(队列名)
# 交换机-构造(无参数)

# 消费机监听(队列名)

# 绑定过程(交换机，队列，[routing_key])

# 调用过程， 交换机与消费者是解耦的，队列和消费者才是耦合的
触发器(交换机名字, routing_key) -> 交换机(routing_key)  -> 队列(routing_key) <-topic_name-> 消费者

direct_exchange 增加死信交换机