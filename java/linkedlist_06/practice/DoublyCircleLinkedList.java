package Stack.java.linkedlist_06.practice;

import java.util.Objects;

/**
 * 双向循环链表
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2024/1/23 14:09
 */
public class DoublyCircleLinkedList<E> {

    /**
     * 头节点
     */
    private Node<E> head;

    /**
     * 元素数量
     */
    private int size;

    public DoublyCircleLinkedList() {
        this.head = null;
        this.size = 0;
    }

    private static class Node<E> {
        /**
         * 存放的数据
         */
        E item;
        /**
         * 前驱节点
         */
        Node<E> prev;
        /**
         * 后驱节点
         */
        Node<E> next;

        Node(E item, Node<E> prev, Node<E> next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    /**
     * 移除头元素
     * @return E
     * @author Rickshaw
     * @since 2024/1/24 16:20
     */
    public E removeFirst() {
        return this.remove(0);
    }

    /**
     * 移除尾元素
     * @return E
     * @author Rickshaw
     * @since 2024/1/24 16:20
     */
    public E removeLast() {
        return this.remove(this.size - 1);
    }

    /**
     * 移除元素
     * @param element     要移除的元素
     * @return boolean
     * @author Rickshaw
     * @since 2024/1/24 16:23
     */
    public boolean removeElement(E element) {
        Node<E> p = this.head;
        int i = 0;
        while (i < this.size && !p.item.equals(element)) {
            p = p.next;
            i++;
        }
        if (i == this.size) {
            return false;
        }
        this.remove(p);
        return true;
    }

    /**
     * 移除对应位置的元素
     * @param index     索引
     * @return E
     * @author Rickshaw
     * @since 2024/1/24 15:30
     */
    public E remove(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        //需要移除的节点
        Node<E> p = this.getNode(index);
        return this.remove(p);
    }

    /**
     * 从指定位置插入元素
     * @param index     索引
     * @param element   元素
     * @author Rickshaw
     * @since 2024/1/23 14:33
     */
    public void add(int index, E element) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("Invalid Index");
        }
        if (Objects.isNull(element)) {
            throw new NullPointerException("The element is empty");
        }
        Node<E> newNode = this.createNode(element);
        //要插入位置的现有节点
        Node<E> p;
        //链表为空的情况
        if (this.head == null) {
            //前驱节点, 后驱节点都指向自己
            newNode.prev = newNode.next = newNode;
            this.head = newNode;
            this.size++;
            return;
        }
        //由于是循环链表的原因, 头插和尾插, 插入的位置都是一样的
        if (0 == index || this.size == index) {
            p = this.head;
        } else {
            p = this.getNode(index);
        }
        p.prev.next = newNode;
        newNode.prev = p.prev;
        newNode.next = p;
        p.prev = newNode;
        //头插需要把头节点指向新节点
        if (0 == index) {
            this.head = newNode;
        }
        this.size++;
    }

    /**
     * 头插
     * @param element   元素
     * @author Rickshaw
     * @since 2024/1/24 10:48
     */
    public void addFirst(E element) {
        this.add(0, element);
    }

    /**
     * 尾插
     * @param element   元素
     * @author Rickshaw
     * @since 2024/1/24 10:50
     */
    public void addLast(E element) {
        this.add(this.size, element);
    }

    /**
     * 移除
     * @param node  要移除的节点
     * @return E
     * @author Rickshaw
     * @since 2024/1/24 17:24
     */
    private E remove(Node<E> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        //如果是移除头节点, 则需要把头节点往下移一位
        if (this.head == node) {
            this.head = node.next;
        }
        this.size--;
        return node.item;
    }

    /**
     * 获取index对应的节点
     * @param index     索引
     * @return Stack.java.linkedlist_06.practice.DoublyCircleLinkedList.Node<E>
     * @author Rickshaw
     * @since 2024/1/24 9:34
     */
    private Node<E> getNode(int index) {
        Node<E> p = this.head;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p;
    }

    /**
     * 创建一个节点
     * @param element   元素
     * @return Stack.java.linkedlist_06.practice.DoublyCircleLinkedList.Node<E>
     * @author Rickshaw
     * @since 2024/1/23 14:43
     */
    private Node<E> createNode(E element) {
        return new Node<>(element, null, null);
    }

    public static void main(String[] args) {
        DoublyCircleLinkedList<Integer> doublyCircleLinkedList = new DoublyCircleLinkedList<>();
        doublyCircleLinkedList.addLast(5);
        doublyCircleLinkedList.addFirst(3);
        doublyCircleLinkedList.addLast(9);
        doublyCircleLinkedList.add(1, 8);
        doublyCircleLinkedList.removeFirst();
        doublyCircleLinkedList.removeLast();
        doublyCircleLinkedList.addFirst(10);
        doublyCircleLinkedList.addLast(15);
        doublyCircleLinkedList.addLast(17);
        doublyCircleLinkedList.removeElement(8);
        doublyCircleLinkedList.remove(2);
        doublyCircleLinkedList.removeElement(20);
        doublyCircleLinkedList.removeElement(null);
        System.out.println("doublyCircleLinkedList = " + doublyCircleLinkedList);
    }

}
