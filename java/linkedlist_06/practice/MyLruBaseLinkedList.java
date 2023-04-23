package Stack.java.linkedlist_06.practice;

/**
 * 固定容量单链表实现Lru算法
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2023/4/20 16:05
 */
public class MyLruBaseLinkedList<E> {

    public static final int DEFAULT_CAPACITY = 10;

    /**
     * 声明头节点（哨兵节点）
     */
    private final Node<E> head;

    /**
     * 链表容量
     */
    private final int capacity;

    /**
     * 链表长度
     */
    private int size;

    public MyLruBaseLinkedList() {
        this.head = createNode(null);
        this.capacity = DEFAULT_CAPACITY;
        this.size = 0;
    }

    public MyLruBaseLinkedList(int capacity) {
        this.head = createNode(null);
        this.capacity = capacity;
        this.size = 0;
    }

    public static class Node<E> {
        /**
         * 存放的数据
         */
        E item;
        /**
         * 后驱节点
         */
        Node<E> next;

        Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }
    }

    public Node<E> createNode(E e) {
        return new Node<>(e, null);
    }

    /**
     * 新增
     * @param e   要添加的元素
     * @author Rickshaw
     * @since 2023/4/20 16:18
     */
    public void add(E e) {
        Node<E> toAdd = this.createNode(e);
        //链表满了，移除链表最后的元素，再头插法
        if (this.capacity == this.size) {
            removeLast();
        }
        //链表未满，直接头插法
        addFirst(toAdd);
    }

    /**
     * 缓存元素 e
     * @param e  要缓存的元素
     * @author Rickshaw
     * @since 2023/4/20 22:24
     */
    public void cache(E e) {
        //如果在链表里面存在，则移除，重新头插法
        Node<E> toAdd = this.createNode(e);
        Node<E> prevRemove = this.head;
        while (prevRemove.next != null && !e.equals(prevRemove.next.item)) {
            prevRemove = prevRemove.next;
        }
        //当前链表不存在此元素
        if (prevRemove.next == null) {
            add(e);
            return;
        }
        prevRemove.next = prevRemove.next.next;
        this.size--;
        addFirst(toAdd);
    }

    /**
     * 移除最后的元素
     * @author Rickshaw
     * @since 2023/4/20 22:10
     */
    private void removeLast() {
        Node<E> prevRemove = this.head;
        //找到倒数第二个元素
        while (prevRemove.next.next != null) {
            prevRemove = prevRemove.next;
        }
        prevRemove.next = null;
        this.size--;
    }

    /**
     * 在头节点新增元素
     * @param toAdd   要新增的元素
     * @author Rickshaw
     * @since 2023/4/20 22:10
     */
    private void addFirst(Node<E> toAdd) {
        toAdd.next = this.head.next;
        this.head.next = toAdd;
        this.size++;
    }

    /**
     * 打印链表元素
     * @author Rickshaw
     * @since 2023/4/20 22:18
     */
    public void printAll() {
        System.out.print("printAll: ");
        Node<E> iterator = this.head;
        while (iterator != null) {
            System.out.print(iterator.item + " ");
            iterator = iterator.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MyLruBaseLinkedList<Integer> linkedList = new MyLruBaseLinkedList<>();
        for (int i = 0; i < 20; i++) {
            linkedList.add(i);
            if (i == 9) {
                linkedList.cache(5);
            }
            linkedList.printAll();
        }
    }


}
