package com.yt.queue;

/**
 * 环形数组队列
 *
 * 解决ArrayQueue数组不能复用问题
 */
public class CircleArrayQueue {

    /**
     * 队列的头，指向队列头的第一个元素
     */
    private int front;
    /**
     * 队列的尾，指向队列尾的最后一个元素的后一个位置，空出一个空间作为约定
     */
    private int rear;

    private int[] elements;

    /**
     * 队列容量，队列的实际容量只有capacity-1
     */
    private int capacity;
    /**
     * 队列默认容量
     */
    private static final int MIN_INITIAL_CAPACITY = 8;

    public CircleArrayQueue() {
        this(MIN_INITIAL_CAPACITY);
    }

    public CircleArrayQueue(int capacity) {
        elements = new int[capacity];
        this.capacity = capacity;
        this.front = 0;
        this.rear = 0;
    }

    /**
     * 队列是否已满
     *
     * @return
     */
    public boolean isFull() {
        return (rear + 1) % capacity == front;
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
        elements[rear] = element;
        rear = (rear + 1) % capacity;
    }

    /**
     * 从队列头部拉取数据
     */
    public int poll() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，不能拉取数据");
        }
        int element = elements[front];
        front = (front + 1) % capacity;
        return element;
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
        return elements[front];
    }

    /**
     * 显示队列中所有的数据
     */
    public void show() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，不能拉取数据");
        }

        int i = front;
        while (true) {
            System.out.printf("arr[%d]=%d\n", i, elements[i]);
            if (i == rear) {
                break;
            }
            i = i % capacity;
        }
    }

}
