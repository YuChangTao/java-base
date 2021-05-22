package com.yt.linkedlist;

import org.junit.Before;
import org.junit.Test;

import java.util.Stack;

public class LinkedListTest {

    SingleLinkedList<Integer> singleLinkedList = new SingleLinkedList<>();

    @Before
    public void testAdd() {
        singleLinkedList.addLast(3);
        singleLinkedList.addLast(4);
        singleLinkedList.addFirst(1);
        singleLinkedList.addBefore(1, 2);
        System.out.println("单链表原始数据~~");
        singleLinkedList.list();
    }

    @Test
    public void testRemove() {
        singleLinkedList.remove(1);
        System.out.println("删除后~~");
        singleLinkedList.list();
    }

    /**
     * 单链表反向输出
     * <p>
     * 方式：
     * 1.单链表反转，见上面
     * 2.可以利用栈这个数据结构，将各个节点压入到栈中，然后利用栈的先进后出的特点，就实现了逆序打印的效果
     */
    @Test
    public void reverseList() {
        //方式一：
        System.out.println("单链表反转输出列表~~");
        SingleLinkedList<Integer> reverseList = singleLinkedList.reverseList();
        reverseList.list();

        //方式二：
        //创建要给一个栈，将各个节点压入栈
        Stack<SingleLinkedList.Node> stack = new Stack<>();
        SingleLinkedList.Node<Integer> node = singleLinkedList.getFirst();
        //将链表的所有节点压入栈
        while (true) {
            if (node == null) {
                break;
            }
            stack.push(node);
            node = node.getNext();
        }
        //将栈中的节点进行打印,pop 出栈
        while (stack.size() > 0) {
            System.out.println(stack.pop()); //stack的特点是先进后出
        }
    }
}
