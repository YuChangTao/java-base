package com.yt.juc;

import java.util.concurrent.*;

/**
 * 多线程的创建方式：
 * 1.继承Thread类
 * 2.实现Runnable接口
 * 3.实现Callable接口（带返回值），结合ExecutorService和Future使用
 * 4.基于线程池创建
 */
public class ThreadCreateTest {


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //第一种方式
        class MyThread extends Thread {
            @Override
            public void run() {
                System.out.println("MyThread run");
            }
        }
        MyThread myThread = new MyThread();
        myThread.start();

        //第二种方式
        class MyRunnable implements Runnable {

            @Override
            public void run() {
                System.out.println("MyRunnable run");
            }
        }
        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);
        thread.start();

        //第三种方式
        class MyCallable implements Callable<String> {

            @Override
            public String call() throws Exception {
                System.out.println("MyCallable run");
                return "MyCallable done";
            }
        }
        MyCallable myCallable = new MyCallable();//创建Callable接口实现类的对象
        FutureTask<String> futureTask = new FutureTask<>(myCallable);//将此Callable接口实现类的对象作为传递到FutureTask构造器中，创建FutureTask的对象
        new Thread(futureTask).start();//将FutureTask的对象作为参数传递到Thread类的构造器中，创建Thread对象，并调用start()

        while (!futureTask.isDone()) {
            System.out.println("res：" + futureTask.get());
        }

        //第四种方式
        // 创建线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        while (true) {
            // 提交多个线程任务，并执行
            threadPool.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " is running ..");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}