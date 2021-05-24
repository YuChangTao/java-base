package com.yt.linkedlist;

/**
 * 约瑟夫问题：
 * 设编号为1,2,3....,n的n个人围坐成一圈，约定编号为k（1<=k<=n）的人从1开始报数，数到m的那个人出列，它的下一位又从1开始报数，
 * 数到m的那个人又出列，知道所有人出列为止，由此产生一个出队编号的序列
 * <p>
 * 约瑟夫环形单向列表
 */
public class JosephCircleSingleLinkedList<E> {

    /**
     * 头节点
     */
    private Node<E> first;
    /**
     * 辅助节点
     */
    private Node<E> currentNode;
    private int size;

    /**
     * 添加节点
     *
     * @param e
     */
    public void addLast(E e) {
        Node<E> node = new Node<>(null, e);
        if (first == null) {
            first = node;
            first.next = first;
            currentNode = first;
        } else {
            node.next = first;
            currentNode.next = node;
            currentNode = node;
        }
        size++;
    }

    /**
     * 遍历链表
     */
    public void list() {
        Node<E> temp = this.first;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp.item);
            temp = temp.next;
            if (temp == first) {
                break;
            }
        }
    }

    public void joseph(E k, int m) {
        //获取开始报数的上一个节点
        Node<E> kNode = this.first;
        while (true) {
            if (kNode.next.item == k) {
                break;
            }
            kNode = kNode.next;
        }

        while (true) {
            //开始循环报数,获取报数为m-1的节点
            for (int i = 1; i < m; i++) {
                kNode = kNode.next;
            }
            System.out.printf("%d\t", kNode.next.item);
            //刪除下个节点
            kNode.next = kNode.next.next;

            //只剩最后一个节点
            if (kNode.next == kNode) {
                System.out.printf("%d\t", kNode.item);
                kNode.next = null;
                kNode.item = null;
                break;
            }
        }
    }

    public static class Node<E> {
        private Node<E> next;
        private E item;

        public Node(Node<E> next, E item) {
            this.next = next;
            this.item = item;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "item=" + item +
                    '}';
        }
    }

}
