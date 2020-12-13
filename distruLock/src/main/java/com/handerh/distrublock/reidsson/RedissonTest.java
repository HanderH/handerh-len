package com.handerh.distrublock.reidsson;

import org.redisson.Redisson;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * @author handerh
 * @date 2020/12/13
 */
public class RedissonTest {

    public static void main(String[] args) {
        Config config = new Config();

        config.useSingleServer().setAddress("redis://127.0.0.1:6379");

        RedissonClient client = Redisson.create(config);

    }
}
