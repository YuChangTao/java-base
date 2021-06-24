package com.yt.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 线程池创建方式
 *
 * @see ExecutorService
 */
public class ThreadPoolTest {

    public static void main(String[] args) {

        newCachedThreadPool();

        newFixedThreadPool();

        ScheduledExecutorService scheduledThreadPool = newScheduledThreadPool();

        newSingleThreadExecutor();


        scheduledThreadPool.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("延迟三秒");
            }
        }, 3, TimeUnit.SECONDS);

        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("延迟 1 秒后每三秒执行一次");
            }
        }, 1, 3, TimeUnit.SECONDS);

    }

    /**
     * 创建缓存线程池，空闲60s的线程会被移除
     * new ThreadPoolExecutor(0, Integer.MAX_VALUE,60L, TimeUnit.SECONDS,new SynchronousQueue<Runnable>());
     */
    public static ExecutorService newCachedThreadPool() {
        return Executors.newCachedThreadPool();
    }

    /**
     * 创建固定大小线程池
     * new ThreadPoolExecutor(nThreads, nThreads,0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>());
     */
    public static ExecutorService newFixedThreadPool() {
        return Executors.newFixedThreadPool(5);
    }

    /**
     * 创建定时器线程池
     * new ScheduledThreadPoolExecutor(corePoolSize);
     */
    public static ScheduledExecutorService newScheduledThreadPool() {
        return Executors.newScheduledThreadPool(5);
    }

    /**
     * 创建一个单线程线程池
     * new FinalizableDelegatedExecutorService(new ThreadPoolExecutor(1, 1,0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>()));
     */
    public static ExecutorService newSingleThreadExecutor() {
        return Executors.newSingleThreadExecutor();
    }

}
