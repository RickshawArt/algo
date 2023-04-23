package Stack.java.array_05.practice;


/**
 * 固定大小的数组容器实现
 * @author Rickshaw
 * @since 2023/4/10 16:32
 * @version 1.0
 */
public class MyArray {

    /**
     * 数组容器
     */
    private int[] data;

    /**
     * 数组容量
     */
    private final int capacity;

    /**
     * 数组当前下标计数 (从1开始)
     */
    private int count;

    public MyArray(int capacity) {
        this.data = new int[capacity];
        this.capacity = capacity;
        this.count = 0;
    }


    /**
     * 随机访问数据
     * @param index  数组下标
     * @return int
     * @author Rickshaw
     * @since 2023/4/10 16:34
     */
    public int find(int index) {
        if (index < 0 || index >= this.capacity) {
            throw new IllegalArgumentException("The parameter is invalid");
        }
        return this.data[index];
    }


    /**
     * 根据index插入数据
     * @param index 数组下标
     * @param element   数据
     * @return boolean
     * @author Rickshaw
     * @since 2023/4/10 16:38
     */
    public boolean insert(int index, int element) {
        //固定容量，数组已满拒绝插入
        if (this.count == this.capacity) {
            throw new RuntimeException("数组已满，请先移除元素再插入");
        }
        if (index < 0 || index > this.count) {
            throw new IllegalArgumentException("The parameter is invalid");
        }
        //从数组的末尾往后顺延一位（因为从index开始顺延会覆盖后面的元素）
        for (int i = this.count; i > index; i--) {
            this.data[i] = this.data[i - 1];
        }
        this.data[index] = element;
        this.count++;
        return true;
    }

    /**
     * 根据index删除元素
     * @param index 数组下标
     * @return boolean
     * @author Rickshaw
     * @since 2023/4/10 16:39
     */
    public boolean delete(int index) {
        if (index < 0 || index >= this.count) {
            throw new IllegalArgumentException("The parameter is invalid");
        }
        //从数组index元素往前顺延一位（从最后面元素开始的话会覆盖掉前一位的元素）
        for (int i = index; i < this.count - 1; i++) {
            data[i] = data[i + 1];
        }
        //删除数组末尾元素
        int[] newData = new int[this.count - 1];
        for (int i = 0; i < newData.length; i++) {
            newData[i] = this.data[i];
        }
        this.data = newData;
        this.count--;
        return true;
    }

    /**
     * 打印数组
     * @author Rickshaw
     * @since 2023/4/10 16:40
     */
    public void printAll() {
        System.out.print("printAll: ");
        for (int i = 0; i < this.data.length; i++) {
            System.out.print(this.data[i] + " ");
        }
        System.out.println();
    }


    public static void main(String[] args) {
        MyArray array = new MyArray(5);
        array.printAll();
        array.insert(0, 3);
        array.insert(0, 4);
        array.insert(1, 5);
        array.insert(3, 9);
        array.insert(2, 11);
        array.printAll();
        array.delete(2);
//        array.insert(3, 11);
        array.printAll();
    }
}
