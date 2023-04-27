package Stack.java.queue_09.practice;

/**
 * 链式队列的实现
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2023/4/27 11:26
 */
public class MyQueueBasedOnLinkedList<E> {

    /**
     * 队头指针
     */
    private Node<E> head = null;
    /**
     * 队尾指针
     */
    private Node<E> tail = null;

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

    /**
     * 入队
     * @param e   入队元素
     * @author Rickshaw
     * @since 2023/4/27 14:32
     */
    public void enqueue(E e) {
        Node<E> newNode = this.createNode(e);
        //如果是首元素，队头指针要指向将要出队的元素
        if (this.head == null) {
            this.head = newNode;
        }
        //队尾指针指向新元素将要入队的位置
        newNode.next = tail;
        tail = newNode;
    }





}
