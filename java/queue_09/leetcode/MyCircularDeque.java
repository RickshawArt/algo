package Stack.java.queue_09.leetcode;


/**
 * 设计循环双端队列
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2024/4/1 11:51
 */
public class MyCircularDeque {

    /**
     * 整型数组
     */
    private final int[] elementData;

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

    public MyCircularDeque(int capacity) {
        // 由于尾指针占一个空位置，所以得加一
        this.capacity = capacity + 1;
        this.elementData = new int[capacity + 1];
    }

    public boolean insertFront(int value) {
        if (this.isFull()) {
            return false;
        }
        this.head = (this.head - 1 + this.capacity) % this.capacity;
        this.elementData[this.head] = value;
        return true;
    }

    public boolean insertLast(int value) {
        if (this.isFull()) {
            return false;
        }
        this.elementData[this.tail] = value;
        this.tail = (this.tail + 1) % this.capacity;
        return true;
    }

    public boolean deleteFront() {
        if (this.isEmpty()) {
            return false;
        }
        this.head = (this.head + 1) % this.capacity;
        return true;
    }

    public boolean deleteLast() {
        if (this.isEmpty()) {
            return false;
        }
        this.tail = (this.tail - 1 + this.capacity) % this.capacity;
        return true;
    }

    public int getFront() {
        if (this.isEmpty()) {
            return -1;
        }
        return this.elementData[this.head];
    }

    public int getRear() {
        if (this.isEmpty()) {
            return -1;
        }
        return this.elementData[(this.tail - 1 + this.capacity) % this.capacity];
    }

    public boolean isEmpty() {
        return this.head == this.tail;
    }

    public boolean isFull() {
        return (this.tail + 1) % this.capacity == this.head;
    }

    public static void main(String[] args) {
        MyCircularDeque circularDeque = new MyCircularDeque(3);
        System.out.println("circularDeque.insertLast(1) = " + circularDeque.insertLast(1));
        System.out.println("circularDeque.insertLast(2) = " + circularDeque.insertLast(2));
        System.out.println("circularDeque.insertFront(3) = " + circularDeque.insertFront(3));
        System.out.println("circularDeque.insertFront(4) = " + circularDeque.insertFront(4));
        System.out.println("circularDeque.getRear() = " + circularDeque.getRear());
        System.out.println("circularDeque.isFull() = " + circularDeque.isFull());
        System.out.println("circularDeque.deleteLast() = " + circularDeque.deleteLast());
        System.out.println("circularDeque.insertFront(4) = " + circularDeque.insertFront(4));
        System.out.println("circularDeque.getFront() = " + circularDeque.getFront());
    }
}
