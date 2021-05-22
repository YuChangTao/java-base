package com.yt.io.nio;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * NIO Channel 和 ByteBuffer测试
 * @author yutyi
 * @date 2020/12/30
 */
public class NioFileChannelTest {

    /**
     * 使用Channel和ByteBuffer将数据写入文件
     *
     * @throws IOException
     */
    @Test
    public void testChannelWrite() throws IOException {

        String str = "hello,java";
        //创建一个输出流->channel
        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\yutyi\\Desktop\\test.txt");

        //通过 fileOutputStream 获取 对应的 FileChannel
        //这个 fileChannel 真实 类型是  FileChannelImpl
        FileChannel fileChannel = fileOutputStream.getChannel();

        //创建一个缓冲区 ByteBuffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        //将 str 放入 byteBuffer
        byteBuffer.put(str.getBytes());

        //对byteBuffer 进行flip
        byteBuffer.flip();

        //将byteBuffer 数据写入到 fileChannel
        fileChannel.write(byteBuffer);
        fileOutputStream.close();
    }

    /**
     * 使用Channel和ByteBuffer将文件读入内存并打印
     *
     * @throws IOException
     */
    @Test
    public void testChannelRead() throws IOException {
        //创建文件的输入流
        File file = new File("C:\\Users\\yutyi\\Desktop\\test.txt");
        FileInputStream fileInputStream = new FileInputStream(file);

        //通过fileInputStream 获取对应的FileChannel -> 实际类型  FileChannelImpl
        FileChannel fileChannel = fileInputStream.getChannel();

        //创建缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());

        //将通道的数据读入到Buffer
        fileChannel.read(byteBuffer);

        //将byteBuffer 的 字节数据 转成String
        System.out.println(new String(byteBuffer.array()));
        fileInputStream.close();
    }

    /**
     * 使用Channel和ByteBuffer复制文件
     *
     * @throws IOException
     */
    @Test
    public void testChannelFileCopy() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\yutyi\\Desktop\\source.txt");
        FileChannel inputStreamChannel = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\yutyi\\Desktop\\target.txt");
        FileChannel outputStreamChannel = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        //循环读取
        while (true) {
            byteBuffer.clear(); //清空buffer
            int read = inputStreamChannel.read(byteBuffer);
            System.out.println("read =" + read);
            //表示读完
            if (read == -1) {
                break;
            }
            //将buffer 中的数据写入到 fileChannel02 -- 2.txt
            byteBuffer.flip();
            outputStreamChannel.write(byteBuffer);
        }

        //关闭相关的流
        fileInputStream.close();
        fileOutputStream.close();
    }

    /**
     * 使用Channel的transferFrom完成拷贝
     * @throws IOException
     */
    @Test
    public void testChannelTransferFrom() throws IOException {

        //创建相关流
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\yutyi\\Desktop\\source.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\yutyi\\Desktop\\target.txt");

        //获取各个流对应的fileChannel
        FileChannel sourceCh = fileInputStream.getChannel();
        FileChannel destCh = fileOutputStream.getChannel();

        //使用transferForm完成拷贝
        destCh.transferFrom(sourceCh, 0, sourceCh.size());

        //关闭相关通道和流
        sourceCh.close();
        destCh.close();
        fileInputStream.close();
        fileOutputStream.close();

    }

}
