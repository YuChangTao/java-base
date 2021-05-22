package com.yt.queue;

/**
 * 队列是一个有序列表，可以使用数组或链表来实现
 * 遵循 先入先出（FIFO） 的原则
 * <p>
 * jdk中有Queue接口的是实现，以下是使用数组自定义实现的队列
 *
 */
public class ArrayQueue {

    /**
     * 队列的头，指向队列头的前一个位置
     */
    private int front;
    /**
     * 队列的尾，指向队列尾的最后一个元素位置
     */
    private int rear;

    private int[] elements;

    /**
     * 队列容量
     */
    private int capacity;
    /**
     * 队列默认容量
     */
    private static final int MIN_INITIAL_CAPACITY = 8;

    public ArrayQueue() {
        this(MIN_INITIAL_CAPACITY);
    }

    public ArrayQueue(int capacity) {
        elements = new int[capacity];
        this.capacity = capacity;
        this.front = -1;        //指向队列头的前一个位置
        this.rear = -1;         //指向队列尾的最后一个数据
    }

    /**
     * 队列是否已满
     *
     * @return
     */
    public boolean isFull() {
        return capacity - 1 == rear;
    }

    /**
     * 队列是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return front == rear;
    }

    /**
     * 从队列尾部添加数据
     *
     * @param element
     */
    public void push(int element) {
        if (isFull()) {
            throw new RuntimeException("队列已满，不能加入数据");
        }
        elements[++rear] = element;
    }

    /**
     * 从队列头部拉取数据
     */
    public int poll() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，不能拉取数据");
        }
        return elements[++front];
    }

    /**
     * 获取头部数据，不是拉取数据
     *
     * @return
     */
    public int getFirst() {
        // 判断
        if (isEmpty()) {
            throw new RuntimeException("队列空的，没有数据~~");
        }
        return elements[front + 1];
    }

    /**
     * 显示队列中所有的数据
     */
    public void show() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，不能拉取数据");
        }
        for (int i = front + 1; i < rear+1; i++) {
            System.out.printf("arr[%d]=%d\n", i, elements[i]);
        }
    }

}

