package com.yt.juc;

/**
 * 线程终止
 * <p>
 * 1.正常结束
 * 2.使用标识符结束
 * 3.使用interrupt()结束
 * 4.使用stop()结束，不安全不可使用
 */
public class ThreadStopTest {

    public static void main(String[] args) {
        ThreadIdentifierSafe threadIdentifierSafe = new ThreadIdentifierSafe();
        threadIdentifierSafe.start();

        ThreadInterruptSafe threadInterruptSafe = new ThreadInterruptSafe();
        threadInterruptSafe.start();

        try {
            Thread.sleep(10000);
            threadIdentifierSafe.exit = true;   //设置标识符中断
            threadInterruptSafe.interrupt();    //中断
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main end");

    }

    /**
     * 标识符来中断线程
     */
    static class ThreadIdentifierSafe extends Thread {
        public volatile boolean exit = false;

        public void run() {
            while (!exit) {
                //do something
                System.out.println("ThreadIdentifier running");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("ThreadIdentifier exit");
        }
    }

    /**
     * 用 interrupt()方法来中断线程（通过捕获InterruptException异常和isInterrupted状态）
     */
    public static class ThreadInterruptSafe extends Thread {
        public void run() {
            while (!isInterrupted()) { //非阻塞过程中通过判断中断标志来退出
                try {
                    Thread.sleep(2 * 1000);//阻塞过程捕获中断异常来退出
                    System.out.println("ThreadInterrupt running");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;//捕获到异常之后，执行 break 跳出循环
                }
            }
            System.out.println("ThreadInterrupt exit");
        }
    }
}
