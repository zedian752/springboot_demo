package org.spring_demo.utils;

import redis.clients.jedis.Jedis;

import java.io.*;

public class RedisUtils {

    // redis key默认只能为string
    public static <T> T getObject(Jedis jedis, String key, Class<T> clz) {
         byte[] bytes = jedis.get(key.getBytes());
         T res = (T) deserialize(bytes);
         return res;
    }

    public static String setObject(Jedis jedis, String key, Object object) {
        return jedis.set(key.getBytes(), serialize(object));
    }

    // 序列化对象
    private static byte[] serialize(Object object) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static Object deserialize(byte[] bytes) {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;

    }
}
