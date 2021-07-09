package com.yt.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 继承接口 Lock 并实现了接口中定义的方法，他是一种可重入锁，除了能完
 * 成 synchronized 所能完成的所有工作外，还提供了诸如可响应中断锁、可轮询锁请求、定时锁等
 * 避免多线程死锁的方法。
 */
public class ReentrantLockTest {


    public static void main(String[] args) throws InterruptedException {
        LockRunnable lockRunnable = new LockRunnable();
        Thread t1 = new Thread(lockRunnable);
        Thread t2 = new Thread(lockRunnable);
        Thread t3 = new Thread(lockRunnable);

        t1.start();
        t2.start();
        t3.start();

        t1.interrupt();
        t2.interrupt();
        t3.interrupt();
    }

    static class LockRunnable implements Runnable {
        private ReentrantLock lock = new ReentrantLock();
        //Lock lock=new ReentrantLock(true);//公平锁
        //Lock lock=new ReentrantLock(false);//非公平锁

        //条件对象，获取等待通知组件
        private Condition condition = lock.newCondition();

        /**
         * 1. tryLock 能获得锁就返回 true，不能就立即返回 false，tryLock(long timeout,TimeUnit
         * unit)，可以增加时间限制，如果超过该时间段还没获得锁，返回 false
         * 2. lock 能获得锁就返回 true，不能的话一直等待获得锁
         * 3. lock 和 lockInterruptibly区别：
         * Lock阻塞式地获取锁，只有在获取到锁后才处理interrupt信息
         * lockInterruptibly阻塞式地获取锁，立即处理interrupt信息，并抛出异常
         */
        @Override
        public void run() {
            try {
                //第一种方式获取锁，可轮询锁，定时锁
//                if (!lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
//                    System.out.println("ThreadName=" + Thread.currentThread().getName() + "尝试获取锁失败");
//                    return;
//                }
                //第二种方式获取锁
                lock.lock();
                //第三种方式获取锁，可响应中断锁
//                lock.lockInterruptibly();

                //signal 方法唤醒 wait 线程
                condition.signal();

                System.out.println("ThreadName=" + Thread.currentThread().getName());

                System.out.println("等待获取锁的线程估计数：" + lock.getQueueLength());
                System.out.println("当前线程保持此锁的次数：" + lock.getHoldCount());
                System.out.println("处于wait队列的线程数：" + lock.getWaitQueueLength(condition));
                System.out.println("是否有处于wait队列的线程：" + lock.hasWaiters(condition));
                System.out.println("是否有线程等待此锁：" + lock.hasQueuedThreads());
                System.out.println("是否为公平锁：" + lock.isFair());
                System.out.println("锁是否被占用：" + lock.isLocked());

                Thread.sleep(2000);

                //wait方法等待，必须先获取到锁才可调用
                condition.await();

                System.out.println("ThreadName=" + Thread.currentThread().getName() + "wait end");

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}

