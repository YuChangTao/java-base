package com.yt.juc;

import java.util.concurrent.Semaphore;

/**
 * Semaphore 是一种基于计数的信号量。它可以设定一个阈值，基于此，多个线程竞争获取许可信
 * 号，做完自己的申请后归还，超过阈值后，线程申请许可信号将会被阻塞。Semaphore 可以用来
 * 构建一些对象池，资源池之类的，比如数据库连接池
 */
public class SemaphoreTest {

    public static void main(String[] args) {
        SemaphoreRunnable semaphoreRunnable = new SemaphoreRunnable();
        for (int i = 0; i < 6; i++) {
            new Thread(semaphoreRunnable).start();
        }
    }

    static class SemaphoreRunnable implements Runnable {

        private Semaphore semaphore = new Semaphore(2);

        @Override
        public void run() {
            try {
                //申请许可信号第一种方式，可轮询锁，定时锁，与ReentrantLock.tryLock()作用效果一致
//                semaphore.tryAcquire(1000, TimeUnit.MILLISECONDS);

                //申请许可信号第二种方式，默认实现可中断锁，与ReentrantLock.lockInterruptibly()作用效果一致
                semaphore.acquire();

                System.out.println("ThreadName=" + Thread.currentThread().getName());

                System.out.println("可用的许可证数量：" + semaphore.availablePermits());
                System.out.println("立即可用的许可证数量：" + semaphore.drainPermits());
                System.out.println("等待获取锁的线程估计数：" + semaphore.getQueueLength());
                System.out.println("是否有线程等待此锁：" + semaphore.hasQueuedThreads());
                System.out.println("是否为公平锁：" + semaphore.isFair());

                Thread.sleep(10000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }
    }

}
