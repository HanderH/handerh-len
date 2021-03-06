package com.handerh.distrublock.reids;

import redis.clients.jedis.Jedis;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author handerh
 * @date 2020/12/06
 */
public class TestLock {

//    private static final Jedis jedis = new Jedis("localhost");

    private static Integer total = 1000;

    private static Integer THREOLD = 1000;

    private final  static  LinkedBlockingQueue blockingQueue  = new LinkedBlockingQueue();

    private final static String LOCK = "lock";

    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        // 统计获取到锁的线程数
         AtomicReference<Integer> sum = new AtomicReference<>(0);
        //线程数为1000
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(THREOLD, THREOLD, 10, TimeUnit.SECONDS, blockingQueue);

        CountDownLatch countDownLatch = new CountDownLatch(THREOLD);

        for (int i=0;i<THREOLD;i++){
            threadPoolExecutor.execute(()->{

                String requestId = String.valueOf(Thread.currentThread().getId());

                boolean lock = RedisLock.tryGetDistributedLock(new Jedis("localhost"), LOCK, requestId, 600);
                // 如果获取到锁
                if (lock){
                    sum.getAndSet(sum.get() + 1);
                    System.out.println(Thread.currentThread().getName()+"Now,total is: "+total);
                    if (total>0){
                        total--;
                    }
                }
                RedisLock.releaseDistributedLock(new Jedis("localhost"),LOCK,requestId);
                countDownLatch.countDown();
            });
        }
        threadPoolExecutor.shutdown();
        countDownLatch.await();

        System.out.println("get lock sum is　"+sum.get()+ " The End Total is :"+ total);
    }
}
