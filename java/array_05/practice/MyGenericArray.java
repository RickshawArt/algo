package Stack.java.array_05.practice;


/**
 * 动态数组实现
 * @author Rickshaw
 * @since 2023/4/10 16:42
 * @version 1.0
 */
public class MyGenericArray<E> {

    private Object[] elementData;

    /**
     * The size of the MyGenericArray (the number of elements it contains).
     */
    private int size;

    public MyGenericArray(int capacity) {
        this.elementData = new Object[capacity];
        this.size = 0;
    }

    /**
     * 无参构造方法，默认数组容量为10
     */
    public MyGenericArray() {
        this(10);
    }


    /**
     * 获取数组容量
     * @return int
     * @author Rickshaw
     * @since 2023/4/10 16:44
     */
    public int getCapacity() {
        return this.elementData.length;
    }

    /**
     * 获取当前元素个数
     * @return int
     * @author Rickshaw
     * @since 2023/4/10 16:44
     */
    public int getSize() {
        return this.size;
    }

    /**
     * 判断数组是否为空
     * @return boolean
     * @author Rickshaw
     * @since 2023/4/10 16:44
     */
    public boolean isEmpty() {
        return 0 == this.size;
    }


    /**
     * 修改 index 位置的元素, 含 index = 0 的位置
     * @param index     链表下标
     * @param element    数据
     * @author Rickshaw
     * @since 2023/4/10 16:44
     */
    public void set(int index, E element) {
        checkIndex(index);
        this.elementData[index] = element;
    }

    /**
     * 获取对应 index 位置的元素
     * @param index 链表下标
     * @return E
     * @author Rickshaw
     * @since 2023/4/10 16:46
     */
    public E get(int index) {
        checkIndex(index);
        return elementData(index);
    }


    /**
     * 查看数组是否包含元素element
     * @param element    要查找的数据
     * @return boolean
     * @author Rickshaw
     * @since 2023/4/10 16:46
     */
    public boolean contain(E element) {
        for (int i = 0; i < this.size; i++) {
            if (element.equals(this.elementData[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取对应元素的下标, 未找到，返回 -1
     * @param element   要查找的数据
     * @return int
     * @author Rickshaw
     * @since 2023/4/10 16:47
     */
    public int find(E element) {
        for (int i = 0; i < this.size; i++) {
            if (element.equals(this.elementData[i])) {
                return i;
            }
        }
        return -1;
    }


    /**
     * 在 index 位置，插入元素element, 时间复杂度 O(m+n)
     * @param index 链表下标
     * @param element   要插入的数据
     * @author Rickshaw
     * @since 2023/4/10 16:55
     */
    public void add(int index, E element) {
        //由于存在动态扩容机制，所以接收index == this.size的情况
        checkIndexForAdd(index);
        if (this.size == this.elementData.length) {
            resize(2 * this.elementData.length);
        }
        for (int i = this.size; i > index; i--) {
            this.elementData[i] = this.elementData[i - 1];
        }
        this.elementData[index] = element;
        this.size++;
    }

    /**
     * 向数组头插入元素
     * @param element  要插入的元素
     * @author Rickshaw
     * @since 2023/4/10 16:56
     */
    public void addFirst(E element) {
        this.add(0, element);
    }


    /**
     * 向数组尾插入元素
     * @param element  要插入的元素
     * @author Rickshaw
     * @since 2023/4/10 16:56
     */
    public void addLast(E element) {
        this.add(size, element);
    }


    /**
     * 删除 index 位置的元素，并返回
     * @param index 要删除的链表索引
     * @return E
     * @author Rickshaw
     * @since 2023/4/10 16:57
     */
    public E remove(int index) {
        checkIndex(index);
        E ret = elementData(index);
        for (int i = index; i < this.size; i++) {
            this.elementData[i] = this.elementData[i + 1];
        }
        this.size--;
        this.elementData[size] = null;
        //缩容
        if (this.size == this.elementData.length / 4 && this.elementData.length / 2 != 0) {
            resize(this.elementData.length / 2);
        }
        return ret;
    }


    /**
     * 删除第一个元素
     * @return E
     * @author Rickshaw
     * @since 2023/4/10 16:58
     */
    public E removeFirst() {
        return this.remove(0);
    }

    /**
     * 删除末尾元素
     * @return E
     * @author Rickshaw
     * @since 2023/4/10 16:58
     */
    public E removeLast() {
        return this.remove(this.size - 1);
    }

    /**
     * 从数组中删除指定元素
     * @param element   要删除的元素
     * @author Rickshaw
     * @since 2023/4/10 16:58
     */
    public void remove(E element) {
        int index = this.find(element);
        if (-1 != index) {
            this.remove(index);
        }
    }

    /**
     * 打印数组所有元素
     * @author Rickshaw
     * @since 2023/4/12 8:49
     */
    public void printAll() {
        System.out.print("printAll: ");
        for (int i = 0; i < this.size; i++) {
            System.out.print(this.elementData[i] + " ");
        }
    }

    /**
     * 扩容方法，时间复杂度 O(n)
     * @param capacity  新数组的容量
     * @author Rickshaw
     * @since 2023/4/10 16:59
     */
    private void resize(int capacity) {
        Object[] newElementData = new Object[capacity];
        if (this.size >= 0) {
            System.arraycopy(this.elementData, 0, newElementData, 0, this.size);
        }
        this.elementData = newElementData;
    }

    /**
     * 转换为对应的泛型返回
     * @param index 链表下标
     * @return E
     * @author Rickshaw
     * @since 2023/4/10 16:59
     */
    @SuppressWarnings("unchecked")
    private E elementData(int index) {
        return (E) this.elementData[index];
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= this.size) {
            throw new IllegalArgumentException("Add failed! Require index >=0 and index < size.");
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > this.size) {
            throw new IllegalArgumentException("Add failed! Require index >=0 and index <= size.");
        }
    }


    public static void main(String[] args) {
        MyGenericArray<String> genericArray = new MyGenericArray<>();
        genericArray.addFirst("John");
        genericArray.addFirst("Bob");
        genericArray.addLast("Juice");
        genericArray.add(1, "Rickshaw");
        genericArray.addLast("Mercy");
        genericArray.addLast("Lisa");
        genericArray.addLast("Kathe");
        genericArray.addLast("Jane");
        genericArray.addLast("Alan");
        genericArray.addLast("Bard");
        System.out.println("genericArray.getCapacity() = " + genericArray.getCapacity());
        genericArray.addLast("Cash");
        System.out.println("genericArray.getCapacity() = " + genericArray.getCapacity());
        System.out.println("genericArray.find(\"Jane\") = " + genericArray.find("Jane"));
        System.out.println("genericArray.get(5) = " + genericArray.get(5));
        genericArray.set(3, "Edison");
        System.out.println("genericArray.contain(\"Rickshaw\") = " + genericArray.contain("Rickshaw"));
        genericArray.remove("Alan");
        genericArray.remove(2);
        genericArray.removeLast();
        genericArray.removeFirst();
        genericArray.removeFirst();
        genericArray.removeFirst();
        System.out.println("genericArray.getCapacity() = " + genericArray.getCapacity());
        genericArray.printAll();
    }


}
