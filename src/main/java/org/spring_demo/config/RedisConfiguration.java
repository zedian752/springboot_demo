package org.spring_demo.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

@EnableConfigurationProperties(myJedisProperties.class)
@Configuration
public class RedisConfiguration {
    @Autowired
    private myJedisProperties myRedisProperties;

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private Integer port;

    @Value("${spring.redis.password}")
    private String password;


    @Bean
    public JedisPool getJedis(){
//        return new JedisPool(new GenericObjectPoolConfig(), myRedisProperties.getHost(), Integer.parseInt(myRedisProperties.getPort()), 20000, myRedisProperties.getPassword());
        return new JedisPool(new GenericObjectPoolConfig(), host, port, 20000, password);
    }
}

