package org.spring_demo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class JedisResPool {
    @Autowired
    JedisPool jedisPool;
    public synchronized Jedis getJedis() {
            Jedis resource = jedisPool.getResource();
            return resource;
    }
}
