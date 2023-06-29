package Stack.java.hashtable_18.practice;


/**
 * 散列表实现
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2023/6/29 10:23
 */
public class MyHashTable<K, V> {
    /**
     * 默认的初始容量为 16，必须是 2的幂次方
     */
    private static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;

    /**
     * 2的幂次方的最大值
     */
    private static final int MAXIMUM_CAPACITY = 1 << 30;

    /**
     * 散列表
     */
    private MyHashTable.Node<K,V>[] table;

    /**
     * 散列表的容量
     */
    private final int tableCapacity;

    /**
     * 默认的加载因子
     */
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    /**
     * 加载因子，实际元素数量 / 数组容量
     */
    private final float loadFactor;

    /**
     * 该散列表中包含的键值对映射的数量
     */
    private int size;

    /**
     * 该散列表中使用到的索引数量
     */
    private int indexCount;

    public MyHashTable(int initialCapacity, float loadFactor) {
        //初始容量
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
        }
        if (initialCapacity > MAXIMUM_CAPACITY) {
            initialCapacity = MAXIMUM_CAPACITY;
        }
        if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
            throw new IllegalArgumentException("Illegal load factor: " + loadFactor);
        }
        this.loadFactor = loadFactor;
        this.tableCapacity = tableSizeFor(initialCapacity);
    }

    public MyHashTable(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    public MyHashTable() {
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        this.tableCapacity = DEFAULT_INITIAL_CAPACITY;
    }


    static class Node<K, V> {
        /**
         * 键
         */
        K key;

        /**
         * 值
         */
        V value;

        /**
         * 链表，用于解决 hash冲突的情况
         */
        Node<K, V> next;

        private Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    /**
     * 新增
     * @param key   键
     * @param value 值
     * @return V    值
     * @author Rickshaw
     * @since 2023/6/29 11:31
     */
    public V put(K key, V value) {
        //懒加载数组
        resize();
        // TODO: 2023/6/29  
        return value;
    }

    /**
     * 初始化或扩容散列表
     * @author Rickshaw
     * @since 2023/6/29 16:01
     */
    private void resize() {
        if (this.tableCapacity == 0) {
            adjustSize(DEFAULT_INITIAL_CAPACITY);
            return;
        }
        // TODO: 2023/6/29  
    }

    /**
     * 重新调整散列表的容量大小
     * @param capacity  要调整的容量
     * @author Rickshaw
     * @since 2023/6/29 15:57
     */
    @SuppressWarnings("unchecked")
    private void adjustSize(int capacity) {
        this.table = (Node<K,V>[]) new Node[capacity];
    }

    /**
     * 将给定的初始化容量转化为不小于初始化容量的，最接近的2的幂次方的整数
     * @param capacity   散列表要初始化的容量
     * @return int
     * @author Rickshaw
     * @since 2023/6/29 17:16
     */
    static int tableSizeFor(int capacity) {
        //为了将capacity转换为 2的幂次方减去 1表示的数，如11111这样的二进制
        int n = capacity - 1;
        //位或操作符|：有1即为1
        //以下每一行操作都会逐步把左侧的高位依次设置为1（从左往右，最高位 -> 次高位）
        //二分之一，四分之一，八分之一，十六分之一，三十二分之一
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    public static void main(String[] args) {

    }


}
