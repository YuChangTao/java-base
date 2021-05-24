package com.yt.linkedlist;

import org.junit.Before;
import org.junit.Test;

import java.util.Stack;

public class LinkedListTest {

    SingleLinkedList<Integer> singleLinkedList = new SingleLinkedList<>();

    /**
     * 单向链表添加
     */
//    @Before
    public void testSingleLinkedListAdd() {
        singleLinkedList.addLast(3);
        singleLinkedList.addLast(4);
        singleLinkedList.addFirst(1);
        singleLinkedList.addAfter(1, 2);
        System.out.println("单链表原始数据~~");
        singleLinkedList.list();
    }

    /**
     * 单向链表删除
     */
    @Test
    public void testSingleLinkedListRemove() {
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
    public void reverseSingleLinkedList() {
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


    /**
     * 测试双向链表
     */
    @Test
    public void testDoubleLinkedList() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addLast(3);
        list.addBefore(3, 4);
        list.list();

        list.remove(3);
        System.out.println("删除后");
        list.list();
    }

    @Test
    public void testJoseph() {
        JosephCircleSingleLinkedList josephCircleSingleLinkedList = new JosephCircleSingleLinkedList();
        int num = 10;
        for (int i = 0; i < num; i++) {
            josephCircleSingleLinkedList.addLast(i);
        }
        josephCircleSingleLinkedList.list();

        //约瑟夫问题
        System.out.println("测试约瑟夫问题：");
        josephCircleSingleLinkedList.joseph(3, 6);
    }
}
