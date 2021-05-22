package com.yt.io.bio;

import org.junit.Test;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * BIO通信方式
 * 服务器监听一个端口，每当客户端发起TCP三次握手成功，则创建一个线程和对应的Socket绑定，这样会产生二个问题：
 * 1.线程的数量是有限且宝贵的，过多的线程也会造成cpu切换耗时
 * 2.{@link InputStream#read()}操作是阻塞的，导致大量线程阻塞等待数据准备，造成线程资源浪费
 *
 * @author yutyi
 * @date 2020/12/30
 */
public class BioServerTest {

    /**
     * 思路：
     * 1. 创建一个线程池
     * 2. 如果有客户端连接，就创建一个线程，与之通讯(单独写一个方法)
     *
     * @throws Exception
     */
    @Test
    public void server() throws Exception {

        LinkedBlockingQueue queue = new LinkedBlockingQueue(200);
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(10, 20, 30, TimeUnit.SECONDS, queue);

        //创建ServerSocket
        ServerSocket serverSocket = new ServerSocket(6666);

        System.out.println("服务器启动了");

        while (true) {

            System.out.println("线程信息 id =" + Thread.currentThread().getId() + " 名字=" + Thread.currentThread().getName());
            //监听，等待客户端连接
            System.out.println("等待连接....");
            final Socket socket = serverSocket.accept();
            System.out.println("连接到一个客户端");

            //就创建一个线程，与之通讯(单独写一个方法)
            poolExecutor.execute(new Runnable() {
                @Override
                public void run() { //我们重写
                    //可以和客户端通讯
                    handler(socket);
                }
            });
        }
    }

    /**
     * 编写一个handler方法，和客户端通讯
     *
     * @param socket
     */
    public static void handler(Socket socket) {

        try {
            System.out.println("线程信息 id =" + Thread.currentThread().getId() + " 名字=" + Thread.currentThread().getName());
            byte[] bytes = new byte[1024];
            //通过socket 获取输入流
            InputStream inputStream = socket.getInputStream();

            //循环的读取客户端发送的数据
            while (true) {

                System.out.println("线程信息 id =" + Thread.currentThread().getId() + " 名字=" + Thread.currentThread().getName());
                System.out.println("read....");
                //read操作阻塞线程
                int read = inputStream.read(bytes);
                if (read != -1) {
                    //输出客户端发送的数据
                    System.out.println(new String(bytes, 0, read));
                } else {
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("关闭和client的连接");
            try {
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
