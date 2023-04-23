package Stack.java.linkedlist_06.practice;

/**
 * 基于数组实现 Lru缓存淘汰策略
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2023/4/21 9:19
 */
public class MyLruBaseArray<E> {

    /**
     * 默认数组容量
     */
    private static final int DEFAULT_CAPACITY = 1 << 3;

    /**
     * 存储数组
     */
    private final Object[] elementData;

    /**
     * 数组容量
     */
    private final int capacity;

    /**
     * The size of the MyGenericArray (the number of elements it contains).
     */
    private int size;

    public MyLruBaseArray() {
        this.elementData = new Object[DEFAULT_CAPACITY];
        this.capacity = DEFAULT_CAPACITY;
        this.size = 0;
    }

    public MyLruBaseArray(int capacity) {
        this.elementData = new Object[capacity];
        this.capacity = capacity;
        this.size = 0;
    }

    /**
     * 缓存
     * @param e   要缓存的元素
     * @author Rickshaw
     * @since 2023/4/21 14:10
     */
    public void cache(E e) {
        //数组为空时直接首元素赋值
        if (this.size == 0) {
            this.elementData[0] = e;
            this.size++;
            return;
        }
        int index = findIndex(e);
        //找到元素，移除元素，新增在末尾
        if (index != -1) {
            remove(index);
        }
        //数组已满，而且数组不存在 e元素
        if (index == -1 && isFull()) {
            //移除首元素，在尾部新增 e元素
            removeFirst();
        }
        //在数组后面新增元素
        setLast(e);
    }

    /**
     * 在数组末端新增元素
     * @param e  要新增的元素
     * @author Rickshaw
     * @since 2023/4/21 14:24
     */
    private void setLast(E e) {
        this.set(this.size, e);
    }

    /**
     * 修改 index位置的值
     * @param index 数组下标
     * @param e   要set的值
     * @author Rickshaw
     * @since 2023/4/21 14:25
     */
    private void set(int index, E e) {
        if (isFull()) {
            throw new RuntimeException("数组已满，请先释放空间再新增");
        }
        this.elementData[index] = e;
        this.size++;
    }

    /**
     * 移除数组首元素
     * @author Rickshaw
     * @since 2023/4/21 14:26
     */
    private void removeFirst() {
        remove(0);
    }

    /**
     * 移除index位置上的元素
     * @param index  数组下标
     * @author Rickshaw
     * @since 2023/4/21 14:27
     */
    private void remove(int index) {
        for (int i = index; i < this.size - 1; i++) {
            this.elementData[i] = this.elementData[i + 1];
        }
        this.elementData[this.size - 1] = null;
        this.size--;
    }

    /**
     * 判断数组是否已经满了
     * @return boolean
     * @author Rickshaw
     * @since 2023/4/21 14:27
     */
    private boolean isFull() {
        return this.size == this.capacity;
    }

    /**
     * 根据元素查找查找index
     * @param e   元素
     * @return int
     * @author Rickshaw
     * @since 2023/4/21 9:53
     */
    private int findIndex(E e) {
        int i = this.size - 1;
        while (i >= 0 && !e.equals(this.elementData[i])) {
            i--;
        }
        return i;
    }

    /**
     * 根据index获取对应元素
     * @param index  数组下标
     * @return E
     * @author Rickshaw
     * @since 2023/4/21 14:39
     */
    private E get(int index) {
        if (index < 0 || index >= this.size) {
            throw new IllegalArgumentException("数组下标异常");
        }
        E ret = elementData(index);
        remove(index);
        setLast(ret);
        return ret;
    }

    @SuppressWarnings("unchecked")
    private E elementData(int index) {
        return (E) this.elementData[index];
    }

    /**
     * 打印数组
     * @author Rickshaw
     * @since 2023/4/21 15:43
     */
    public void printAll() {
        System.out.print("printAll: ");
        for (int i = 0; i < this.size; i++) {
            System.out.print(this.elementData[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MyLruBaseArray<Integer> array = new MyLruBaseArray<>();
        for (int i = 0; i < 20; i++) {
            if (i == 11) {
                System.out.println("array.get(1) = " + array.get(1));
                System.out.println("=========");
                array.printAll();
                System.out.println("=========");
            }
            array.cache(i);
            array.printAll();
        }
    }

}
