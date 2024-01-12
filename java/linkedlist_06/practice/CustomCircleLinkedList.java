package Stack.java.linkedlist_06.practice;

import java.util.Objects;

/**
 * 循环单链表实现
 * 存在的问题: 与单链表类似, 头插法时, 需要遍历到尾节点, 然后接到头节点, O(n)
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2024/1/10 9:43
 */
public class CustomCircleLinkedList<E> {

    /**
     * 头节点
     */
    private Node<E> head;

    /**
     * 元素数量
     */
    private int size;

    private static class Node<E> {
        /**
         * 存放的数据
         */
        E item;
        /**
         * 后驱节点
         */
        Node<E> next;

        Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }
    }

    public CustomCircleLinkedList() {
        this.head = null;
        this.size = 0;
    }

    /**
     * 从指定位置插入元素
     * @param index     插入的位置
     * @param element     插入的元素
     * @author Rickshaw
     * @since 2024/1/10 9:54
     */
    public void add(int index, E element) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        if (Objects.isNull(element)) {
            throw new IllegalArgumentException("The element is empty");
        }
        Node<E> newNode = this.createNode(element);
        Node<E> p;
        if (this.head == null) {
            //链表没有元素
            this.head = newNode;
            newNode.next = this.head;
        } else if (0 == index) {
            //链表存在元素, 且插入位置在头节点的位置
            //尾节点
            p = getLastNode();
            newNode.next = this.head;
            p.next = newNode;
            this.head = newNode;
        } else {
            //其他位置插入元素
            p = getPreNode(index);
            newNode.next = p.next;
            p.next = newNode;
        }
        this.size++;
    }

    /**
     * 移除
     * @param index   要移除的元素下标
     * @return E
     * @author Rickshaw
     * @since 2024/1/10 16:11
     */
    public E remove(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        Node<E> p;
        E ret;
        if (this.head.next == this.head) {
            ret = this.head.item;
            this.head = null;
        } else if (0 == index) {
            //尾元素
            p = getLastNode();
            ret = p.next.item;
            p.next = p.next.next;
            this.head = p.next;
        } else {
            p = getPreNode(index);
            ret = p.next.item;
            p.next = p.next.next;
        }
        this.size--;
        return ret;
    }

    /**
     * 移除尾元素
     * @return E
     * @author Rickshaw
     * @since 2024/1/10 17:15
     */
    public E removeLast() {
        return this.remove(this.size - 1);
    }

    /**
     * 移除首元素
     * @return E
     * @author Rickshaw
     * @since 2024/1/10 17:14
     */
    public E removeFirst() {
        return this.remove(0);
    }

    /**
     * 从头部新增
     * @param e     插入的元素
     * @author Rickshaw
     * @since 2024/1/10 11:37
     */
    public void addFirst(E e) {
        this.add(0, e);
    }

    /**
     * 从尾部新增
     * @param e     插入的元素
     * @author Rickshaw
     * @since 2024/1/10 11:39
     */
    public void addLast(E e) {
        this.add(this.size, e);
    }

    /**
     * 获取索引的前一个元素
     * @param index     索引
     * @return Stack.java.linkedlist_06.practice.CustomCircleLinkedList.Node<E>
     * @author Rickshaw
     * @since 2024/1/12 10:22
     */
    private Node<E> getPreNode(int index) {
        Node<E> p = this.head;
        for (int i = 0; i < index - 1; i++) {
            p = p.next;
        }
        return p;
    }

    /**
     * 获取尾节点
     * @return Stack.java.linkedlist_06.practice.CustomCircleLinkedList.Node<E>
     * @author Rickshaw
     * @since 2024/1/12 10:13
     */
    private Node<E> getLastNode() {
        Node<E> p = this.head;
        while (p.next != this.head) {
            p = p.next;
        }
        return p;
    }

    /**
     * 创建一个 Node节点
     * @param e    元素
     * @return Stack.java.linkedlist_06.practice.CustomCircleLinkedList.Node<E>
     * @author Rickshaw
     * @since 2024/1/11 11:00
     */
    private Node<E> createNode(E e) {
        return new Node<>(e, null);
    }

    public static void main(String[] args) {
        CustomCircleLinkedList<Integer> circleLinkedList = new CustomCircleLinkedList<>();
        circleLinkedList.addLast(1);
        circleLinkedList.addLast(2);
        circleLinkedList.addLast(4);
        circleLinkedList.addFirst(0);
        circleLinkedList.add(3, 3);
        circleLinkedList.addLast(5);
        circleLinkedList.addLast(6);
        circleLinkedList.removeLast();
        circleLinkedList.removeLast();
        circleLinkedList.removeFirst();
        circleLinkedList.removeFirst();
        circleLinkedList.remove(1);
        circleLinkedList.remove(1);
        circleLinkedList.remove(0);
        System.out.println("circleLinkedList = " + circleLinkedList);
    }

}
