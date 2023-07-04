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
    }

    /**
     * 默认的初始化容量为8
     */
    private static final int DEFAULT_INITIAL_CAPACITY = 1 << 3;

    /**
     * 头节点
     */
    private Entry<K, V> head;

    /**
     * 尾节点
     */
    private Entry<K, V> tail;

    /**
     * 散列表，用于存放数据
     */
    private MyHashTable<K, Entry<K, V>> table;

    /**
     * 存放entry的数量
     */
    private int size;

    /**
     * 双向链表容器的容量
     */
    private int capacity;

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
     * 将节点移动到双向链表的尾部
     * @param entry 要移动的节点
     * @author Rickshaw
     * @since 2023/7/4 11:18
     */
    private void moveToTail(Entry<K,V> entry) {
        removeNode(entry);
        linkNodeLast(entry);
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

}
