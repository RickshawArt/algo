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
    private Node<K,V>[] table;

    /**
     * 散列表的容量
     */
    private int tableCapacity;

    /**
     * 默认的加载因子
     */
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    /**
     * 加载因子 = 实际元素数量 / 数组容量
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
        if (this.table == null) {
            this.table = this.getNodeArr(this.tableCapacity);
        }
        int index = hash(key) & (this.tableCapacity - 1);
        if (this.table[index] == null) {
            //设置哨兵节点
            this.table[index] = new Node<>(null, null, null);
        }
        //通过hash散列出来的HashTable上的节点
        Node<K, V> p = this.table[index];
        //只有哨兵节点，则直接插入
        if (p.next == null) {
            p.next = new Node<>(key, value, null);
            this.size++;
            this.indexCount++;
            //超过加载因子，则需要扩容
            if (this.indexCount > this.loadFactor * this.tableCapacity) {
                this.resize();
            }
            return value;
        }
        //遍历链表，发现key相同则替换value
        do {
            p = p.next;
            if (p.key.equals(key)) {
                p.value = value;
                return value;
            }
        } while (p.next != null);
        //尾插法
        p.next = new Node<>(key, value, null);
        this.size++;
        return value;
    }

    /**
     * 获取
     * @param key   键
     * @return  值
     */
    public V get(Object key) {
        int index = hash(key) & (this.tableCapacity - 1);
        Node<K, V> p = this.table[index];
        if (p == null || p.next == null) {
            return null;
        }
        while (p.next != null) {
            p = p.next;
            if (p.key.equals(key)) {
                return p.value;
            }
        }
        return null;
    }

    /**
     * 移除
     * @param key   键
     * @return V    值
     * @author Rickshaw
     * @since 2023/7/1 13:11
     */
    public V remove(Object key) {
        int index = hash(key) & (this.tableCapacity - 1);
        Node<K, V> p = this.table[index];
        if (p == null || p.next == null) {
            return null;
        }

        Node<K, V> pre;
        Node<K, V> head = this.table[index];
        do {
            pre = p;
            p = p.next;
            if (p.key.equals(key)) {
                V value = p.value;
                //pre节点用于删除元素的作用
                pre.next = p.next;
                this.size--;
                //如果哨兵节点没有后继节点，则索引要减1
                if (head.next == null) {
                    this.indexCount--;
                }
                return value;
            }
        } while (p.next != null);
        return null;
    }

    /**
     * 返回此映射中键值映射的数量
     * @return int  该映射中键值映射的数量
     * @author Rickshaw
     * @since 2023/6/30 15:25
     */
    public int size() {
        return this.size;
    }

    /**
     * 计算 key.hashCode() 并将散列的高位散布到低位，
     * 使得 hash & (n-1)既同时具有高位和低位的特性，散列更随机均匀
     * （^异或操作：只有 1个 1，才为 1）
     * @param key   键
     * @return int  散列值
     * @author Rickshaw
     * @since 2023/6/30 9:26
     */
    private static int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    /**
     * 扩容散列表
     * @author Rickshaw
     * @since 2023/6/29 16:01
     */
    private void resize() {
        Node<K, V>[] oldTable = this.table;
        this.tableCapacity *= 2;
        this.table = this.getNodeArr(this.tableCapacity);
        this.indexCount = 0;
        for (Node<K, V> kvNode : oldTable) {
            //将要插入新HashTable的元素
            Node<K, V> p = kvNode;
            //如果该元素为空，或者只有哨兵元素则下一个
            if (p == null || p.next == null) {
                continue;
            }
            while (p.next != null) {
                p = p.next;
                int index = hash(p.key) & (this.tableCapacity - 1);
                //如果散列表对应的index没有元素，则新增哨兵元素
                if (this.table[index] == null) {
                    this.table[index] = new Node<>(null, null, null);
                    this.indexCount++;
                }
                //头插入法插入
                this.table[index].next = new Node<>(p.key, p.value, this.table[index].next);
            }

        }
    }

    /**
     * 重新调整散列表的容量大小
     * @param capacity  要调整的容量
     * @author Rickshaw
     * @since 2023/6/29 15:57
     */
    @SuppressWarnings("unchecked")
    private Node<K,V>[] getNodeArr(int capacity) {
       return (Node<K,V>[]) new Node[capacity];
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
        MyHashTable<Integer, String> hashTable = new MyHashTable<>(3);
        hashTable.put(1, "one");
        hashTable.put(10, "ten");
        hashTable.put(3, "three");
        hashTable.put(12, "twelve");
        hashTable.put(2, "two");
        hashTable.put(6, "six");
        System.out.println("hashTable.get(9) = " + hashTable.get(9));
        System.out.println("hashTable.get(10) = " + hashTable.get(10));
        hashTable.remove(10);
        System.out.println("hashTable.get(10) = " + hashTable.get(10));

    }
}
