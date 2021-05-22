package com.yt.io.nio;

import org.junit.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 直接缓冲区
 *
 * MappedByteBuffer可以让文件之间在堆外内存修改，避免了内核缓冲区和用户缓冲区之间的数据拷贝
 *
 * @author yutyi
 * @date 2020/12/30
 */
public class MappedByteBufferTest {

    @Test
    public void testMappedByteBuffer() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("C:\\Users\\yutyi\\Desktop\\test.txt", "rw");

        //获取对应的通道
        FileChannel channel = randomAccessFile.getChannel();

        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);

        mappedByteBuffer.put(0, (byte) 'J');
        mappedByteBuffer.put(3, (byte) 'D');
        mappedByteBuffer.put(4, (byte) 'A');

        randomAccessFile.close();

    }

}
