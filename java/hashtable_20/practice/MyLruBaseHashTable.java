package Stack.java.hashtable_20.practice;

import Stack.java.hashtable_18.practice.MyHashTable;

/**
 * 天然的Lru算法实现，模拟LinkedHashMap
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2023/7/4 10:07
 */
public class MyLruBaseHashTable<K, V> {

    /**
     * 双向链表结构
     *
     * @author Rickshaw
     * @version 1.0
     * @since 2023/7/4 10:07
     */
    static class Entry<K, V> {
        /**
         * 键
         */
        private K key;
        /**
         * 值
         */
        private V value;
        /**
         * 前驱节点
         */
        private Entry<K, V> before;
        /**
         * 后驱节点
         */
        private Entry<K, V> after;

        public Entry() {

        }

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }

    /**
     * 默认的初始化容量为8
     */
    private static final int DEFAULT_INITIAL_CAPACITY = 1 << 3;

    /**
     * 头节点（最老）
     */
    private final Entry<K, V> head;

    /**
     * 尾节点（最年轻）
     */
    private final Entry<K, V> tail;

    /**
     * 散列表，用于存放数据
     */
    private final MyHashTable<K, Entry<K, V>> table;

    /**
     * 存放entry的数量
     */
    private int size;

    /**
     * 双向链表容器的容量
     */
    private final int capacity;

    public MyLruBaseHashTable(int initialCapacity) {
        this.head = new Entry<>();
        this.tail = new Entry<>();
        this.head.after = this.tail;
        this.tail.before = this.head;
        this.table = new MyHashTable<>();
        this.size = 0;
        this.capacity = initialCapacity;
    }

    public MyLruBaseHashTable() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    /**
     * 获取
     * @param key   键
     * @return V    值
     * @author Rickshaw
     * @since 2023/7/4 11:07
     */
    public V get(Object key) {
        Entry<K, V> entry = this.table.get(key);
        if (entry == null) {
            return null;
        }
        moveToTail(entry);
        return entry.value;
    }

    /**
     * 新增
     * @param key   键
     * @param value 值
     * @return V    值
     * @author Rickshaw
     * @since 2023/7/4 20:45
     */
    public V put(K key, V value) {
        Entry<K, V> entry = this.table.get(key);
        Entry<K, V> newNode = new Entry<>(key, value);
        //第一次新增，直接放双向链表尾部
        if (entry == null) {
            linkNodeLast(newNode);
            this.table.put(key, newNode);
            //如果超出容器容量，则移除首元素
            if (++this.size > this.capacity) {
                Entry<K, V> pop = this.popHead();
                this.table.remove(pop.key);
                this.size--;
            }
            return value;
        }
        this.table.put(key, newNode);
        moveToTail(newNode);
        return value;
    }

    /**
     * 移除
     * @param key   键
     * @return V    值
     * @author Rickshaw
     * @since 2023/7/4 21:46
     */
    public V remove(Object key) {
        Entry<K, V> entry = this.table.get(key);
        if (entry == null) {
            return null;
        }
        removeNode(entry);
        this.size--;
        this.table.remove(key);
        return entry.value;
    }

    /**
     * 按照LRU算法打印节点数据
     * @author Rickshaw
     * @since 2023/7/4 21:59
     */
    public void printAll() {
        Entry<K, V> p = this.tail;
        while (p.before != null && p.before.before != null) {
            p = p.before;
            System.out.println(p);
        }
        System.out.println();
    }

    /**
     * 弹出头部数据节点
     * @return Stack.java.hashtable_20.practice.MyLruBaseHashTable.Entry<K,V>
     * @author Rickshaw
     * @since 2023/7/4 21:36
     */
    private Entry<K,V> popHead() {
        Entry<K, V> entry = this.head.after;
        this.removeNode(entry);
        return entry;
    }

    /**
     * 将节点移动到双向链表的尾部
     * @param entry 要移动的节点
     * @author Rickshaw
     * @since 2023/7/4 11:18
     */
    private void moveToTail(Entry<K,V> entry) {
        this.removeNode(entry);
        this.linkNodeLast(entry);
    }


    /**
     * 移除节点
     * @param entry   待移除的节点
     * @author Rickshaw
     * @since 2023/7/4 11:20
     */
    private void removeNode(Entry<K, V> entry) {
        entry.before.after = entry.after;
        entry.after.before = entry.before;
    }

    /**
     * 把节点插入双向链表的尾部
     * @param entry    待插入的元素
     * @author Rickshaw
     * @since 2023/7/4 11:12
     */
    private void linkNodeLast(Entry<K, V> entry) {
        this.tail.before.after = entry;
        entry.before = this.tail.before;

        entry.after = this.tail;
        this.tail.before = entry;

    }

    public static void main(String[] args) {
        MyLruBaseHashTable<Integer, String> hashTable = new MyLruBaseHashTable<>();
        hashTable.put(1, "one");
        hashTable.put(2, "two");
        hashTable.put(3, "three");
        hashTable.get(1);
        hashTable.put(4, "four");
        hashTable.put(5, "five");
        hashTable.put(6, "six");
        hashTable.put(7, "seven");
        hashTable.put(0, "zero");
        hashTable.put(8, "eight");
        hashTable.put(9, "nine");
        hashTable.remove(7);
        hashTable.get(0);
        hashTable.printAll();
    }

}
