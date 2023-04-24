package Stack.java.stack_08.practice;

import java.util.EmptyStackException;

/**
 * 链式栈实现
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2023/4/24 16:03
 */
public class MyStackBasedOnLinkedList<E> {

    /**
     * 哨兵节点（头节点）
     */
    private final Node<E> sentinel = this.createNode(null);

    public static class Node<E> {

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

    public Node<E> createNode(E e) {
        return new Node<>(e, null);
    }

    public void clear() {
        this.sentinel.next = null;
    }

    /**
     * 入栈
     * @param item  入栈元素
     * @return E
     * @author Rickshaw
     * @since 2023/4/24 16:13
     */
    public E push(E item) {
        Node<E> node = this.createNode(item);
        //使用sentinel进行头插法，避免遍历
        node.next = sentinel.next;
        sentinel.next = node;
        return item;
    }

    /**
     * 出栈
     * @return E
     * @author Rickshaw
     * @since 2023/4/24 16:18
     */
    public E pop() {
        if (this.sentinel.next == null) {
            throw new EmptyStackException();
        }
        E ret = sentinel.next.item;
        //从sentinel移除栈顶元素
        sentinel.next = sentinel.next.next;
        return ret;
    }

    /**
     * 偷看栈顶元素
     * @return E
     * @author Rickshaw
     * @since 2023/4/24 16:14
     */
    public E peek() {
        if (this.sentinel.next == null) {
            throw new EmptyStackException();
        }
        return sentinel.next.item;
    }

    /**
     * 打印链式栈
     * @author Rickshaw
     * @since 2023/4/24 16:21
     */
    public void printAll() {
        System.out.print("printAll: ");
        Node<E> iterator = this.sentinel;
        while (iterator.next != null) {
            System.out.print(iterator.next.item +" ");
            iterator = iterator.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MyStackBasedOnLinkedList<String> stack = new MyStackBasedOnLinkedList<>();
        stack.push("C");
        stack.push("F");
        stack.push("J");
        stack.push("G");
        stack.push("H");
        stack.push("R");
        stack.push("B");
        stack.push("P");
        stack.push("Z");
        stack.printAll();
        System.out.println("stack.peek() = " + stack.peek());
        stack.printAll();
        System.out.println("stack.pop() = " + stack.pop());
        stack.printAll();
        System.out.println("stack.pop() = " + stack.pop());
        stack.printAll();
        System.out.println("stack.pop() = " + stack.pop());
        stack.printAll();
    }

}
