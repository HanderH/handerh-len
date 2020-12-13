package com.handerh.distrublock.reids;

import redis.clients.jedis.Jedis;

import java.util.Collections;

/**
 * @author handerh
 * @date 2020/12/06
 */
public class RedisLock {

    private static final String LOCK_SUCCESS = "OK";

    private static final Long RELEASE_SUCCESS = 1L;

    private static final String SET_IF_NOT_EXIST = "NX";

    private static final String SET_WITH_EXPIRE_TIME = "EX";

    // 获取锁的等待时间 10s
    private static long timeout = 1000*10;

    /**
     *
     * @param jedis Redis客户端
     * @param lockKey 锁
     * @param requestId 请求标识
     * @param expireTime 超期时间
     * @return
     */
    public static boolean tryGetDistributedLock(Jedis jedis, String lockKey, String requestId, int expireTime){

        long start = System.currentTimeMillis();
        /**
         * 1. nx 即当key不存在时，我们进行set操作；若key已经存在，则不做任何操作
         * 2. ex 给这个key加一个过期的设置
         *
         * 执行上面的set()方法就只会导致两种结果：
         *      1. 当前没有锁（key不存在），那么就进行加锁操作，并对锁设置个有效期，同时value表示加锁的客户端。
         *      2. 已有锁存在，不做任何操作。
         *在timeout时间内一直尝试获取锁
         */
        while(System.currentTimeMillis()-start<timeout){
            String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
            // jedis.set(lockKey, requestId, new SetParams().nx().ex(expireTime));
            if (LOCK_SUCCESS.equals(result)){
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param jedis jedis客户端
     * @param lockKey　锁
     * @param requestId　客户端id
     */
    public static boolean releaseDistributedLock(Jedis jedis, String lockKey, String requestId){

        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

        // 将lockKey赋值给KEYS[1] 将requestId赋值给ARGV[1] ,获取lockKey对应的value，检查是否与requestId相等，如果相等则删除锁（解锁）
        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));

        if (RELEASE_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }
}
