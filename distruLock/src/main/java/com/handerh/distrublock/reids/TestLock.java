package com.handerh.distrublock.reids;

import redis.clients.jedis.Jedis;

import java.util.concurrent.*;

/**
 * @author handerh
 * @date 2020/12/06
 */
public class TestLock {

    private static final Jedis jedis = new Jedis("localhost:6379");

    private static Integer total = 1001;

    private static Integer THREOLD = 1000;

    private final  static  LinkedBlockingQueue blockingQueue  = new LinkedBlockingQueue();

    private final static String LOCK = "lock";
    public static void main(String[] args) throws InterruptedException {

        //线程数为1000
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1000, 1000, 10, TimeUnit.SECONDS, blockingQueue);

        CountDownLatch countDownLatch = new CountDownLatch(THREOLD);

        for (int i=0;i<THREOLD;i++){
            threadPoolExecutor.submit(()->{
                String requestId = String.valueOf(Thread.currentThread().getId());
                RedisLock.tryGetDistributedLock(jedis,LOCK,requestId,10);
                if (total>0){
                    total--;
                }
                System.out.println("Now,total is: "+total);
                RedisLock.releaseDistributedLock(jedis,LOCK,requestId);

                countDownLatch.countDown();
            });
        }

        threadPoolExecutor.shutdown();
        countDownLatch.await();

    }
}
