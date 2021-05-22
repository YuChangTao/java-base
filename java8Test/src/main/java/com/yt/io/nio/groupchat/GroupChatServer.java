package com.yt.io.nio.groupchat;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * 群聊系统服务端
 *
 * @author yutyi
 * @date 2020/12/30
 */
public class GroupChatServer {

    private Selector selector;

    private ServerSocketChannel listenChannel;

    private static int PORT = 6667;

    @Before
    public void init() {
        try {
            //得到选择器
            selector = Selector.open();
            //得到ServerSocketChannel
            listenChannel = ServerSocketChannel.open();
            //绑定端口
            listenChannel.bind(new InetSocketAddress(PORT)).socket();
            //设置非阻塞模式
            listenChannel.configureBlocking(false);
            //注册listenChannel到selector
            listenChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void listen() {
        System.out.println("监听线程: " + Thread.currentThread().getName());
        try {
            while (true) {
                //监听事件
                if (selector.select() > 0) {
                    //遍历得到selectionKey 集合
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        //获取SelectionKey
                        SelectionKey key = iterator.next();

                        //监听到OP_ACCEPT事件
                        if (key.isAcceptable()) {
                            SocketChannel socketChannel = listenChannel.accept();
                            //设置为非阻塞模式
                            socketChannel.configureBlocking(false);

                            //注册到selector
                            socketChannel.register(selector, SelectionKey.OP_READ);

                            //上限提示
                            System.out.println(socketChannel.getRemoteAddress() + " 上线 ");
                        }

                        //监听到OP_READ事件
                        if (key.isReadable()) {
                            //读取数据
                            readData(key);
                        }

                        //当前的key 删除，防止重复处理
                        iterator.remove();
                    }
                } else {
                    System.out.println("等待......");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }

    /**
     * 读取客户端数据
     *
     * @param key
     */
    private void readData(SelectionKey key) {
        SocketChannel socketChannel = null;
        try {
            //得到channel
            socketChannel = (SocketChannel) (key.channel());
            //创建buffer
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            int count = socketChannel.read(buffer);

            //根据count的值做处理
            if (count > 0) {
                //把缓存区的数据转成字符串
                String msg = new String(buffer.array());

                //向其它的客户端转发消息(去掉自己), 专门写一个方法来处理
                sendInfoToOtherClients(msg, socketChannel);
            }
        } catch (IOException ie) {
            try {
                System.out.println(socketChannel.getRemoteAddress() + " 离线了..");
                //取消注册
                key.cancel();
                //关闭通道
                socketChannel.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {

        }

    }

    /**
     * 转发消息给其它客户(通道)
     *
     * @param msg
     * @param self
     */
    private void sendInfoToOtherClients(String msg, SocketChannel self) {

        System.out.println("服务器转发消息中...");
        System.out.println("服务器转发数据给客户端线程: " + Thread.currentThread().getName());
        //遍历 所有注册到selector 上的 SocketChannel,并排除 self
        for (SelectionKey key : selector.keys()) {
            //通过 key  取出对应的 SocketChannel
            Channel targetChannel = key.channel();

            //排除自己
            if (targetChannel instanceof SocketChannel && targetChannel != self) {

                //转型
                SocketChannel dest = (SocketChannel) targetChannel;
                //将msg 存储到buffer
                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
                //将buffer 的数据写入 通道
                try {
                    dest.write(buffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
