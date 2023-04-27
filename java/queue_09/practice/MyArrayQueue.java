package Stack.java.queue_09.practice;

/**
 * 固定容量的顺序队列
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2023/4/26 17:05
 */
public class MyArrayQueue<E> {

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
     * 队头
     */
    private int head = 0;

    /**
     * 队尾
     */
    private int tail = 0;

    public MyArrayQueue() {
        this.elementData = new Object[DEFAULT_CAPACITY];
        this.capacity = DEFAULT_CAPACITY;
    }

    public MyArrayQueue(int capacity) {
        this.elementData = new Object[capacity];
        this.capacity = capacity;
    }

    /**
     * 入队
     * @param e  入队元素
     * @return boolean
     * @author Rickshaw
     * @since 2023/4/26 17:17
     */
    public boolean enqueue(E e) {
        /*//假溢出，没有考虑出队空出的容量
        if (this.capacity == this.tail) {
            return false;
        }*/
        //真溢出
        if (this.capacity == this.tail && this.head == 0) {
            return false;
        }
        //数据搬移
        if (this.capacity == this.tail) {
            for (int i = this.head; i < this.tail; i++) {
                this.elementData[i - head] = this.elementData[i];
            }
            //this.head也就是偏移量offset
            this.tail -= this.head;
            this.head = 0;
        }
        this.elementData[tail++] = e;
        return true;
    }

    /**
     * 出队
     * @return E
     * @author Rickshaw
     * @since 2023/4/26 17:19
     */
    public E dequeue() {
        if (this.head == this.tail) {
            return null;
        }
        return this.elementData(head++);
    }

    /**
     * 从队头开始打印队列
     * @author Rickshaw
     * @since 2023/4/27 9:37
     */
    public void printAll() {
        System.out.print("printAll: ");
        for (int i = this.head; i < this.tail; i++) {
            System.out.print(this.elementData[i] + " ");
        }
        System.out.println();
    }

    @SuppressWarnings("unchecked")
    private E elementData(int index) {
        return (E) this.elementData[index];
    }

    public static void main(String[] args) {
        MyArrayQueue<Integer> queue = new MyArrayQueue<>();
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
        queue.printAll();
        queue.enqueue(9);
        queue.enqueue(10);
        queue.printAll();
    }

}
