package com.yt.linkedlist;

import org.junit.Test;

public class LinkedListTest {

    @Test
    public void testSingleLinkedList() {
        SingleLinkedList<Integer> singleLinkedList = new SingleLinkedList<>();
        singleLinkedList.addLast(3);
        singleLinkedList.addFirst(1);
        singleLinkedList.addBefore(1,2);

        singleLinkedList.list();
    }
}
