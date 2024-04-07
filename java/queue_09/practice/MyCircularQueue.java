package Stack.java.queue_09.practice;

/**
 * 循环队列实现
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2023/4/28 9:42
 */
public class MyCircularQueue<E> {

    /**
     * 默认数组容量
     */
    public static final int DEFAULT_CAPACITY = 1 << 3;

    /**
     * 存放元素的数组
     */
    private final Object[] elementData;

    /**
     * 数组容量
     */
    private final int capacity;

    /**
     * 队头元素对应的数组的索引
     */
    private int head = 0;

    /**
     * 队尾元素对应的索引的下一个索引
     */
    private int tail = 0;

    public MyCircularQueue() {
        // 由于尾指针占一个空位置，所以得加一
        this.elementData = new Object[DEFAULT_CAPACITY + 1];
        this.capacity = DEFAULT_CAPACITY + 1;
    }

    public MyCircularQueue(int capacity) {
        // 由于尾指针占一个空位置，所以得加一
        this.elementData = new Object[capacity + 1];
        this.capacity = capacity + 1;
    }

    @SuppressWarnings("unchecked")
    private E elementData(int index) {
        return (E) this.elementData[index];
    }

    /**
     * 入队
     * @param e  入队元素
     * @return boolean
     * @author Rickshaw
     * @since 2023/4/28 9:47
     */
    public boolean enqueue(E e) {
        //队列已满
        if ((this.tail + 1) % this.capacity == this.head) {
            return false;
        }
        this.elementData[this.tail] = e;
        this.tail = (this.tail + 1) % this.capacity;
        return true;
    }

    /**
     * 出队
     * @return E
     * @author Rickshaw
     * @since 2023/4/28 10:03
     */
    public E dequeue() {
        //队列为空
        if (this.head == this.tail) {
            return null;
        }
        E ret = elementData(this.head);
        this.head = (this.head + 1) % this.capacity;
        return ret;
    }

    /**
     * 从队头打印队列
     * @author Rickshaw
     * @since 2023/4/28 10:21
     */
    public void printAll() {
        System.out.print("printAll: ");
        for (int i = this.head; i != this.tail; i = (i + 1) % this.capacity) {
            System.out.print(this.elementData[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MyCircularQueue<Integer> queue = new MyCircularQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);
        queue.enqueue(7);
        queue.enqueue(8);
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.enqueue(9);
        queue.enqueue(10);
        queue.enqueue(11);
        queue.enqueue(12);
        queue.printAll();
    }

}
