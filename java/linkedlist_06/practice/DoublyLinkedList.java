package Stack.java.linkedlist_06.practice;

import java.util.Objects;

/**
 * 双向链表
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2024/1/19 10:38
 */
public class DoublyLinkedList<E> {

    /**
     * 头节点
     */
    private Node<E> head = null;

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
        if (this.head == null) {
            //链表为空
            this.head = newNode;
        } else if (0 == index) {
            //从头部插入
            newNode.next = this.head;
            this.head.prev = newNode;
            this.head = newNode;
        } else if (this.size == index) {
            //从尾部插入
            Node<E> node = this.getLastNode();
            node.next = newNode;
            newNode.prev = node;
        } else {
            //从其他位置插入
            Node<E> node = this.getNode(index);
            newNode.prev = node.prev;
            node.prev.next = newNode;
            node.prev = newNode;
            newNode.next = node;
        }
        this.size++;
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
        Node<E> node = this.getNode(index);
        return this.remove(node);
    }

    /**
     * 移除
     * @param element   要移除的元素
     * @return boolean
     * @author Rickshaw
     * @since 2024/1/19 16:59
     */
    public boolean removeElement(E element) {
        Node<E> p = this.head;
        int i = 0;
        while (p.next != null && p.item != element) {
            p = p.next;
            i++;
        }
        if (!p.item.equals(element)) {
            return false;
        }
        this.remove(p);
        return true;
    }

    /**
     * 移除
     * @param node  要删除的节点
     * @return E
     * @author Rickshaw
     * @since 2024/1/19 16:36
     */
    private E remove(Node<E> node) {
        if (node.prev == null) {
            //移除头元素
            node.next.prev = null;
            this.head = node.next;
        } else if (node.next == null) {
            //移除尾元素
            node.prev.next = null;
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        this.size--;
        return node.item;
    }

    /**
     * 获取尾节点
     * @return Stack.java.linkedlist_06.practice.CustomCircleLinkedList.Node<E>
     * @author Rickshaw
     * @since 2024/1/12 10:13
     */
    private Node<E> getLastNode() {
        Node<E> p = this.head;
        while (p.next != null) {
            p = p.next;
        }
        return p;
    }

    /**
     * 获取索引的那个元素
     * @param index     索引
     * @return Stack.java.linkedlist_06.practice.CustomCircleLinkedList.Node<E>
     * @author Rickshaw
     * @since 2024/1/12 10:22
     */
    private Node<E> getNode(int index) {
        Node<E> p = this.head;
        for (int i = 0; i < index; i++) {
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
        return new Node<>(e, null, null);
    }

    public static void main(String[] args) {
        DoublyLinkedList<Integer> doublyLinkedList = new DoublyLinkedList<>();
        doublyLinkedList.addFirst(2);
        doublyLinkedList.addLast(3);
        doublyLinkedList.addLast(4);
        doublyLinkedList.add(1, 5);
        doublyLinkedList.addFirst( 6);
        doublyLinkedList.add(3, 10);
        doublyLinkedList.removeFirst();
        doublyLinkedList.removeLast();
        doublyLinkedList.remove(2);
        doublyLinkedList.removeElement(3);
        doublyLinkedList.removeElement(null);
        doublyLinkedList.removeElement(20);
        doublyLinkedList.removeElement(2);
        System.out.println("doublyLinkedList = " + doublyLinkedList);
    }

}
