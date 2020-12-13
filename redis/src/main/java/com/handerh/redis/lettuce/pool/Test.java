package com.handerh.redis.lettuce.pool;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.RedisURI;
import io.lettuce.core.SetArgs;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.sync.RedisCommands;

import java.util.concurrent.ExecutionException;

/**
 * @author handerh
 * @date 2020/12/13
 */
public class Test {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // Syntax: redis://[password@]host[:port][/databaseNumber]
        // Syntax: redis://[username:password@]host[:port][/databaseNumber]
        // 1.直接使用RedisClient建立redis的连接
        RedisClient redisClient = RedisClient.create("redis://localhost:6379/0");
        StatefulRedisConnection<String, String> connect = redisClient.connect();

        //2. 使用RedisURI建造者模式
//        RedisURI localhost = RedisURI.builder().withHost("localhost").withPort(6379).withDatabase(0).build();
//        RedisClient redisClient1 = RedisClient.create(localhost);
//        StatefulRedisConnection<String, String> connect1 = redisClient1.connect();

        /**
         * 同步方式
         */
        RedisCommands<String, String> syncCmmonands = connect.sync();

        SetArgs setArgs = SetArgs.Builder.nx().ex(5);
        String set = syncCmmonands.set("name", "throwable", setArgs);
        String value = syncCmmonands.get("name");
        System.out.println(value);

        /**
         * 异步方式
         */
        RedisAsyncCommands<String, String> asyncCommands = connect.async();

        RedisFuture<String> future = asyncCommands.set("name", "throwable", setArgs);

        future.thenAccept(x -> System.out.println(x));

        System.out.println(future.get());

    }
}
