package com.handerh.redis.jedis.pool;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author handerh
 * @date 2020/12/13
 */
public class JedisPoolUtil {

    private static JedisPool pool = null;//jedis连接池

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        InputStream resourceAsStream = JedisPoolUtil.class.getClassLoader().getResourceAsStream("redis.properties");
        Properties props = new Properties();
        try {
            props.load(resourceAsStream);
            //设置池配置项值

            // 最大连接池
            config.setMaxTotal(Integer.valueOf(props.getProperty("jedis.pool.maxActive")));
            // 最大空闲数
            config.setMaxIdle(Integer.valueOf(props.getProperty("jedis.pool.minIdle")));
            // 最小空闲数
            config.setMinIdle(Integer.valueOf(props.getProperty("jedis.pool.maxIdle")));
            // 获取连接时的最大等待时间
            config.setMaxWaitMillis(Long.valueOf(props.getProperty("jedis.pool.maxWait")));
            //设置的逐出策略类名, 默认DefaultEvictionPolicy(当连接超过最大空闲时间,或连接数超过最大空闲连接数)
            config.setEvictionPolicyClassName("org.apache.commons.pool2.impl.DefaultEvictionPolicy");
            //逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
            config.setMinEvictableIdleTimeMillis(Long.valueOf(props.getProperty("jedis.pool.minEvictableIdleTimeMillis")));
            pool = new JedisPool(config, props.getProperty("jedis.pool.host"),Integer.valueOf(props.getProperty("jedis.pool.port")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JedisPool getPool(){
        return pool;
    }

    public static Jedis getRedisClient(){
        return getPool().getResource();
    }
}
