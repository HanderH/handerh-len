package com.handerh.redis.lettuce.pool;

import com.handerh.redis.jedis.pool.JedisPoolUtil;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.support.ConnectionPoolSupport;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Properties;

/**
 * @author handerh
 * @date 2020/12/13
 */
public class LettucePool {

    private static  GenericObjectPool<StatefulRedisConnection<String, String>> pool;

    static {

        Properties props = new Properties();
        RedisURI redisUri = RedisURI.builder()
                .withHost("localhost")
                .withPort(6379)
                .withTimeout(Duration.of(10, ChronoUnit.SECONDS))
                .build();
        RedisClient redisClient = RedisClient.create(redisUri);
        InputStream resourceAsStream = LettucePool.class.getClassLoader().getResourceAsStream("redis.properties");

        GenericObjectPoolConfig config = new GenericObjectPoolConfig();

        try {
            props.load(resourceAsStream);
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

            pool= ConnectionPoolSupport.createGenericObjectPool(redisClient::connect, config);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static GenericObjectPool getPool(){
        return pool;
    }

    public static RedisCommands getCommand() throws Exception {

        StatefulRedisConnection<String, String> stringStringStatefulRedisConnection = pool.borrowObject();

        RedisCommands<String, String> sync = stringStringStatefulRedisConnection.sync();
        return sync;
    }
}
