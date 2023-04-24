package Stack.java.stack_08.practice;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * 动态顺序栈实现
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2023/4/24 11:32
 */
public class MyStackBasedOnArray<E> {

    /**
     * 默认容量
     */
    public static final int DEFAULT_CAPACITY = 1 << 3;

    /**
     * 数据数组
     */
    private Object[] elementData;

    /**
     * 实际的数据大小
     */
    private int elementCount;

    /**
     * 数组容量
     */
    private final int capacity;

    public MyStackBasedOnArray() {
        this.elementData = new Object[DEFAULT_CAPACITY];
        this.capacity = DEFAULT_CAPACITY;
        this.elementCount = 0;
    }

    public MyStackBasedOnArray(int capacity) {
        this.elementData = new Object[capacity];
        this.capacity = capacity;
        this.elementCount = 0;
    }

    /**
     * 入栈
     * @param item  入栈元素
     * @return E
     * @author Rickshaw
     * @since 2023/4/24 15:01
     */
    public E push(E item) {
        if (this.capacity == this.elementCount) {
            resize(2 * this.elementData.length);
        }
        this.elementData[this.elementCount++] = item;
        return item;
    }

    /**
     * 出栈
     * @return E
     * @author Rickshaw
     * @since 2023/4/24 15:02
     */
    public E pop() {
        if (this.elementData.length / 4 == this.elementCount && this.elementData.length / 2 != 0 ) {
            resize(this.elementData.length / 2);
        }
        E ret = this.peek();
        this.elementCount--;
        return ret;
    }

    /**
     * 查看栈顶元素，而不将其删除
     * @return E
     * @author Rickshaw
     * @since 2023/4/24 15:03
     */
    public E peek() {
        if (this.elementCount == 0) {
            throw new EmptyStackException();
        }
        return elementData(this.elementCount - 1);
    }

    /**
     * 打印顺序栈
     * @author Rickshaw
     * @since 2023/4/24 16:21
     */
    public void printAll() {
        System.out.print("printAll: ");
        for (int i = this.elementCount; i > 0; i--) {
            System.out.print(this.elementData[i - 1] + " ");
        }
        System.out.println();
    }

    @SuppressWarnings("unchecked")
    private E elementData(int index) {
        return (E) this.elementData[index];
    }

    private void resize(int capacity) {
        this.elementData = Arrays.copyOf(this.elementData, capacity);
    }

    public static void main(String[] args) {
        MyStackBasedOnArray<String> stack = new MyStackBasedOnArray<>();
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
