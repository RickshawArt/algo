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
        //如果是首元素，队头，队尾指针要指向首元素
        if (this.head == null) {
            this.head = newNode;
            this.tail = newNode;
            return;
        }
        //队尾指针指向newNode的位置
        this.tail.next = newNode;
        this.tail = this.tail.next;
    }

    /**
     * 出队
     * @return E
     * @author Rickshaw
     * @since 2023/4/28 8:50
     */
    public E dequeue() {
        //队列为空
        if (this.head == null) {
            return null;
        }
        E ret = this.head.item;
        this.head = this.head.next;
        //如果出队的是最后一个元素，要把队尾设null，不然再入队会形成环，造成死循环
        if (this.head == null) {
            this.tail = null;
        }
        return ret;
    }

    /**
     * 从队头打印链表
     * @author Rickshaw
     * @since 2023/4/28 9:03
     */
    public void printAll() {
        System.out.print("printAll: ");
        Node<E> iterator = this.head;
        while (iterator != null) {
            System.out.print(iterator.item + " ");
            iterator = iterator.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MyQueueBasedOnLinkedList<Integer> queue = new MyQueueBasedOnLinkedList<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.printAll();
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.dequeue();
        queue.dequeue();
        queue.enqueue(6);
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.enqueue(7);
        queue.printAll();
    }



}
