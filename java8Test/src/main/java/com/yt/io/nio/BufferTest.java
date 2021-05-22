package com.yt.io.nio;

import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 * Buffer测试
 *
 * @author yutyi
 */
public class BufferTest {

    /**
     * 举例说明Buffer 的使用 (简单说明)
     * <p>
     * capacity: buffer的容量大小，实际上是buffer中数组的大小
     * position：buffer下次读/写的索引位置
     * limit: buffer读/写的索引上限（position小于limit方可操作）
     * mark: 标记position的索引位置，使用reset()可将position重置到标记位置，主要用于重复读
     */
    @Test
    public void intBuffer() {
        //创建一个Buffer, 大小为 5, 即可以存放5个int
        IntBuffer intBuffer = IntBuffer.allocate(5);

        //向buffer 存放数据
        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(i * 2);
        }

        //读写切换，limit=position，position=0
        intBuffer.flip();

        //循环读出，每次get操作position++
        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get());
        }
    }

    /**
     * byteBuffer读写int、long、char、short
     */
    @Test
    public void byteBuffer() {
        //创建一个Buffer
        ByteBuffer buffer = ByteBuffer.allocate(64);

        //类型化方式放入数据
        buffer.putInt(100);
        buffer.putLong(9);
        buffer.putChar('Y');
        buffer.putShort((short) 4);

        //切换读写
        buffer.flip();

        System.out.println();

        System.out.println(buffer.getInt());
        System.out.println(buffer.getLong());
        System.out.println(buffer.getChar());
        System.out.println(buffer.getShort());

    }

    /**
     * 只读Buffer
     */
    @Test
    public void readOnlyBuffer() {
        //创建一个buffer
        ByteBuffer buffer = ByteBuffer.allocate(64);

        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte) i);
        }

        //读取
        buffer.flip();

        //得到一个只读的Buffer
        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();
        System.out.println(readOnlyBuffer.getClass());

        //读取
        while (readOnlyBuffer.hasRemaining()) {
            System.out.println(readOnlyBuffer.get());
        }

        //ReadOnlyBufferException
        readOnlyBuffer.put((byte) 100);
    }

}
