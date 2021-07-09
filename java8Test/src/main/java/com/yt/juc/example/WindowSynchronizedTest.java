package com.yt.juc.example;

/**
 * 例子：创建三个窗口卖票，总票数为100张.使用继承Thread类和实现Runnable接口的方式
 *
 * 在Java中，我们通过同步机制，来解决线程的安全问题。
 *  方式一：同步代码块
 *      synchronized(同步监视器){
 *          //需要被同步的代码
 *      }
 *      说明：1.操作共享数据的代码，即为需要被同步的代码。  -->不能包含代码多了，也不能包含代码少了。
 *            2.共享数据：多个线程共同操作的变量。比如：ticket就是共享数据。
 *            3.同步监视器，俗称：锁。任何一个类的对象，都可以充当锁。
 *      要求：多个线程必须要共用同一把锁。
 *      补充：在实现Runnable接口创建多线程的方式中，我们可以考虑使用this充当同步监视器。
 *  方式二：同步方法。
 *     如果操作共享数据的代码完整的声明在一个方法中，我们不妨将此方法声明同步的。
 *同步的方式，解决了线程的安全问题。---好处
 *    操作同步代码时，只能有一个线程参与，其他线程等待。相当于是一个单线程的过程，效率低。 ---局限性
 *
 *  关于同步方法的总结：
 *  1. 同步方法仍然涉及到同步监视器，只是不需要我们显式的声明。
 *  2. 非静态的同步方法，同步监视器是：this
 *     静态的同步方法，同步监视器是：当前类本身
 */
public class WindowSynchronizedTest {
    public static void main(String[] args) {
        Window t1 = new Window();
        Window t2 = new Window();
        Window t3 = new Window();
        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");
        t1.start();
        t2.start();
        t3.start();

//        Window1 window1 = new Window1();
//        Thread thread1 = new Thread(window1, "窗口1");
//        Thread thread2 = new Thread(window1, "窗口2");
//        Thread thread3 = new Thread(window1, "窗口3");
//        thread1.start();
//        thread2.start();
//        thread3.start();
    }

    /**
     * 继承Thread类，同步类或同步静态对象
     */
    static class Window extends Thread {

        private static int ticket = 100;

        private static Object obj = new Object();

        @Override
        public void run() {
            while (true) {
//                synchronized (Window.class) {   //对于静态变量，需要同步类锁，不建议使用，比较重且类被锁时静态变量无法被其它线程读取
                synchronized (obj) {    //同步对象锁，建议使用
                    if (ticket > 0) {
                        System.out.println(getName() + ":卖票，票号为：" + ticket);
                        ticket--;
                        try {
                            Thread.sleep(100);//不会释放锁
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        break;
                    }
                }
            }

        }
    }


    /**
     * 实现Runnable接口，同步对象
     */
    static class Window1 implements Runnable {

        private int ticket = 100;

        @Override
        public void run() {
            while (true) {
                synchronized (this) { //同步对象锁
                    if (ticket > 0) {
                        System.out.println(Thread.currentThread().getName() + ":卖票，票号为：" + ticket);
                        ticket--;
                        try {
                            Thread.sleep(100);//不会释放锁
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        break;
                    }
                }
            }
        }
    }

    /**
     * 继承Thread类，同步类或同步静态对象
     */
    static class Window2 extends Thread {

        private static int ticket = 100;

        private static Object obj = new Object();

        @Override
        public void run() {
            while (true) {
                show();
            }

        }
        /**
         * 等同于synchronized (Window2.class){}
         */
        private static synchronized void show(){//同步监视器：Window2.class
            if (ticket > 0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "：卖票，票号为：" + ticket);
                ticket--;
            }
        }
    }

    /**
     * 实现Runnable接口，同步方法
     */
    class Window3 implements Runnable {

        private int ticket = 100;

        @Override
        public void run() {
            while (true) {
                show();
            }
        }

        /**
         * 等同于synchronized (this){}
         */
        private synchronized void show(){//同步监视器：this
            //synchronized (this){
            if (ticket > 0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ":卖票，票号为：" + ticket);
                ticket--;
            }
            //}
        }
    }
}
