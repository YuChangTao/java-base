package com.yt.stack;

import java.util.EmptyStackException;

/**
 * 自定义栈，使用链表完成
 */
public class LinkedListStack<E> {

    /**
     * 栈大小
     */
    private int size;
    /**
     * 栈顶节点
     */
    private StackNode top;


    public LinkedListStack() {

    }

    /**
     * 入栈（使用头插法）
     *
     * @param e
     */
    public void push(E e) {
        try {
            StackNode stackNode = new StackNode(e, null);
            if (top != null) {
                stackNode.next = top;
            }
            top = stackNode;
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
        StackNode temp = top;
        if (temp == null) {
            throw new EmptyStackException();
        }
        top = temp.next;

        //GC
        temp.next = null;

        return temp.o;
    }

    private static class StackNode {
        private Object o;
        private StackNode next;

        public StackNode(Object o, StackNode next) {
            this.o = o;
            this.next = next;
        }
    }
}
