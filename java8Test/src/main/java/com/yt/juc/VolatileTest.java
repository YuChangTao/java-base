package com.yt.juc;

/**
 * volatile
 * <p>
 * 变量可见性:其一是保证该变量对所有线程可见，这里的可见性指的是当一个线程修改了变量的值，那么新的值对于其他线程是可以立即获取的。
 * 禁止重排序:禁止了指令重排
 * <p>
 * 在访问 volatile 变量时不会执行加锁操作，因此也就不会使执行线程阻塞，因此 volatile 变量是一
 * 种比 sychronized 关键字更轻量级的同步机制。volatile 适合这种场景：一个变量被多个线程共
 * 享，线程直接给这个变量赋值。
 * <p>
 * <p>
 * 可见性方式：
 * 1.加锁：当一个线程进入 synchronizer 代码块后，线程获取到锁，会清空本地内存，然后从主内存中拷贝共享变量的最新值到本地内存作为副本，
 * 执行代码，又将修改后的副本值刷新到主内存中，最后线程释放锁。
 * 2.volatile：使用 volatile 修饰共享变量后，每个线程要操作变量时会从主内存中将变量拷贝到本地内存作为副本，当线程操作变量副本并写回主内存后，
 * 会通过 CPU 总线嗅探机制告知其他线程该变量副本已经失效，需要重新从主内存中读取。
 * 3.final
 */
public class VolatileTest {
    public static void main(String[] args) {
//        noVisibility();
//        lockVisibility();
        volatileVisibility();
    }

    /**
     * 不可见
     */
    public static void noVisibility() {
        MyThread myThread = new MyThread();
        // 开启线程
        myThread.start();

        // 主线程执行
        for (; ; ) {
            if (myThread.isFlag()) {
                //永远不会被执行
                System.out.println("主线程访问到 flag 变量");
            }
        }
    }

    /**
     * 加锁
     */
    public static void lockVisibility() {
        MyThread myThread = new MyThread();
        // 开启线程
        myThread.start();

        // 主线程执行
        for (; ; ) {
            synchronized (myThread) {
                if (myThread.isFlag()) {
                    System.out.println("主线程访问到 flag 变量");
                }
            }
        }
    }

    public static void volatileVisibility() {
        MyThread myThread = new MyThread();
        // 开启线程
        myThread.start();

        // 主线程执行
        for (; ; ) {
            if (myThread.isVolatileFlag()) {
                System.out.println("主线程访问到 volatileFlag 变量");
            }
        }
    }


    /**
     * 子线程类
     */
    static class MyThread extends Thread {

        private boolean flag = false;
        private volatile boolean volatileFlag = false;

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 修改变量值
            flag = true;
            volatileFlag = true;
            System.out.println("flag = " + flag);
            System.out.println("volatileFlag = " + volatileFlag);
        }

        public boolean isFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }

        public boolean isVolatileFlag() {
            return volatileFlag;
        }

        public void setVolatileFlag(boolean volatileFlag) {
            this.volatileFlag = volatileFlag;
        }
    }
}
