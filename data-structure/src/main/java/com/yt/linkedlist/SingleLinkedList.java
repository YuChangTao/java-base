package com.yt.linkedlist;


/**
 * 自定义单向链表，单向链表的基本思路：从头节点向下遍历
 * <p>
 */
public class SingleLinkedList<E> {

    /**
     * 单向链表的大小
     */
    private int size;

    /**
     * 头节点
     */
    private Node<E> first;

    public SingleLinkedList() {
    }

    /**
     * 链表的节点
     */
    public static class Node<E> {
        private Node<E> next;
        private E item;

        public Node(Node<E> next, E item) {
            this.next = next;
            this.item = item;
        }

        public Node<E> getNext() {
            return next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "item=" + item +
                    '}';
        }
    }

    /**
     * 添加节点到头部，头插法t
     */
    public void addFirst(E e) {
        Node<E> node = new Node<>(null, e);
        if (first != null) {
            node.next = first;
        }
        first = node;
        size++;
    }

    /**
     * 添加节点到尾部，尾插法
     */
    public void addLast(E e) {
        Node<E> nextNode = new Node<>(null, e);
        if (first != null) {
            Node<E> x = first;
            for (; x.next != null; x = x.next) {
            }
            x.next = nextNode;
        } else {
            first = nextNode;
        }
        size++;
    }

    /**
     * 添加到某个节点后面
     *
     * @param index
     * @param e
     */
    public void addAfter(E index, E e) {
        Node<E> temp = first;
        boolean flag = false;
        //遍历获取指定节点
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.item == index) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //如果节点存在
        if (flag) {
            Node<E> node = new Node<>(temp.next, e);
            temp.next = node;
            size++;
        }
    }

    /**
     * 删除节点
     */
    public void remove(E e) {
        Node<E> temp = first;
        //判断是否是头节点
        if (temp != null && temp.item == e) {
            if (temp.next != null) {
                first = temp.next;
            }
            //GC
            temp.next = null;
            temp.item = null;
        } else if (temp != null) {
            boolean isExist = false;
            while (true) {
                if (temp.next == null) { //已到链表尾部
                    break;
                }
                //找到待删除节点的前一个节点
                if (temp.next.item == e) {
                    isExist = true;
                    break;
                }
                temp = temp.next;
            }
            if (isExist) {

                Node<E> delNode = temp.next;
                //刪除节点
                temp.next = temp.next.next;
                size--;

                //GC
                delNode.next = null;
                delNode.item = null;
            } else {
                System.out.printf("要删除的 %d 节点不存在\n", e);
            }
        } else {
            System.out.printf("要删除的 %d 节点不存在\n", e);
        }
    }

    public Node<E> getFirst() {
        return first;
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
        }
    }

    /**
     * 返回反转列表
     *
     * @return
     */
    public SingleLinkedList<E> reverseList() {
        SingleLinkedList<E> reverseList = new SingleLinkedList<>();
        Node<E> temp = this.first;
        while (true) {
            if (temp == null) {
                break;
            }
            reverseList.addFirst(temp.item);
            temp = temp.next;
        }
        return reverseList;
    }
}
