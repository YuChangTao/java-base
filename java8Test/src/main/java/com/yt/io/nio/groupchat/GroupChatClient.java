package com.yt.io.nio.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * 群聊系統客戶端
 *
 * @author yutyi
 * @date 2020/12/30
 */
public class GroupChatClient {

    private final String HOST = "127.0.0.1";
    private final int PORT = 6667;
    private Selector selector;
    private SocketChannel socketChannel;
    private String username;

    public GroupChatClient() {
        try {
            selector = Selector.open();
            //连接服务器
            socketChannel = SocketChannel.open(new InetSocketAddress(HOST, PORT));
            //设置非阻塞
            socketChannel.configureBlocking(false);
            //将channel 注册到selector
            socketChannel.register(selector, SelectionKey.OP_READ);
            //得到username
            username = socketChannel.getLocalAddress().toString().substring(1);
            System.out.println(username + " is ok...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用main方法，否则Scanner无法使用
     *
     * @param args
     */
    public static void main(String[] args) {
        GroupChatClient client = new GroupChatClient();
        //启动一个线程, 每个3秒，读取从服务器发送数据
        new Thread(() -> {
            while (true) {
                //读取数据
                client.readInfo();
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        //发送数据给服务器端
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            client.sendInfo(s);
        }
    }

    /**
     * 向服务器发送消息
     *
     * @param info
     */
    private void sendInfo(String info) {

        info = username + "说：" + info;

        try {
            socketChannel.write(ByteBuffer.wrap(info.getBytes()));
        } catch (IOException e) {
            System.out.println(username + "发送消息失败");
        }

    }

    /**
     * 从服务器读取数据
     */
    private void readInfo() {
        try {
            int selects = selector.select();
            if (selects > 0) {
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (key.isReadable()) {
                        //得到相关的通道
                        SocketChannel sc = (SocketChannel) key.channel();
                        //得到一个Buffer
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        //读取
                        sc.read(buffer);
                        //把读到的缓冲区的数据转成字符串
                        String msg = new String(buffer.array());
                        System.out.println(msg.trim());
                    }
                    //删除当前的selectionKey, 防止重复操作
                    iterator.remove();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
