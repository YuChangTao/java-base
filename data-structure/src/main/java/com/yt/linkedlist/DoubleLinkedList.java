package com.yt.linkedlist;

/**
 * 自定义双向链表
 * <p>
 * jdk中有双向链表的实现LinkedList
 */
public class DoubleLinkedList<E> {
    /**
     * 链表的大小
     */
    private int size;

    /**
     * 头节点
     */
    private Node<E> first;

    /**
     * 尾节点
     */
    private Node<E> last;


    /**
     * 添加到链表尾部
     *
     * @param e
     */
    public void addLast(E e) {
        Node<E> node = new Node<E>(e, null, null);
        if (first == null) {
            first = node;
        } else {
            last.next = node;
            node.prev = last;
        }
        last = node;
        size++;
    }

    /**
     * 添加到链表头部
     *
     * @param e
     */
    public void addFirst(E e) {
        Node<E> node = new Node<E>(e, null, null);
        if (last == null) {
            last = node;
        } else {
            first.prev = node;
            node.next = first;
        }
        first = node;
        size++;
    }

    /**
     * 添加到某个节点前面
     *
     * @param index
     * @param e
     */
    public void addBefore(E index, E e) {
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
            Node<E> node = new Node<E>(e, temp.prev, temp);
            temp.prev.next = node;
            temp.prev = node;
            size++;
        }
    }

    /**
     * 得到指定索引处节点
     *
     * @param index
     */
    public E get(int index) {
        if (index >= 0 && index < size) {
            if (index < size >> 2) {
                Node<E> x = first;
                for (int i = 0; i < index; i++) {
                    x = x.next;
                }
                return x.item;
            } else {
                Node<E> x = last;
                for (int i = size - 1; i > index; i--) {
                    x = x.prev;
                }
                return x.item;
            }
        }
        return null;
    }

    /**
     * 移除某个节点
     *
     * @param e
     */
    public void remove(E e) {
        Node<E> temp = first;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.item == e) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            //防止删除最后一个或第一个出现空指针
            Node<E> prev = temp.prev;
            Node<E> next = temp.next;

            if (prev == null) {
                first = next;
            } else {
                temp.prev.next = temp.next;
            }
            if (next == null) {
                last = prev;
            } else {
                temp.next.prev = temp.prev;
            }
            temp.item = null;
            size--;
        }
    }

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
     * 双向链表节点
     */
    private static class Node<E> {
        private E item;

        /**
         * 前一个节点
         */
        private Node<E> prev;

        /**
         * 后一个节点
         */
        private Node<E> next;

        public Node(E item, Node<E> prev, Node<E> next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }
}
