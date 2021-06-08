package com.yt.stack;

import java.util.EmptyStackException;

/**
 * 自定义栈，使用数组完成
 *
 * jdk中有栈的实现Stack类，基于Vector数组实现，使用 synchronized锁比较重量级
 */
public class ArrayStack<E> {

    /**
     * 栈顶指针
     */
    private int top = -1;

    private Object[] elementData;

    public ArrayStack(int maxSize) {
        elementData = new Object[maxSize];
    }

    /**
     * 入栈
     *
     * @param e
     */
    public void push(E e) {
        try {
            elementData[top + 1] = e;
            top++;
        } catch (Exception ex) {
            throw new StackOverflowError();
        }
    }


    /**
     * 出栈
     *
     * @return
     */
    public Object pop() {
        if (top < 0) {
            throw new EmptyStackException();
        }
        return elementData[top--];
    }
}
