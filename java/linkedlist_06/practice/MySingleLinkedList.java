package Stack.java.linkedlist_06.practice;


/**
 * 单链表的插入、删除、查找操作
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2023/4/12 9:20
 */
public class MySingleLinkedList<E> {

    private Node<E> head = null;

    public static class Node<E> {
        /**
         * 存放的数据
         */
        E item;
        /**
         * 后驱节点
         */
        MySingleLinkedList.Node<E> next;

        Node(E item, MySingleLinkedList.Node<E> next) {
            this.item = item;
            this.next = next;
        }
    }

    /**
     * 根据元素找到对应的节点
     * @param e 元素
     * @return Stack.java.linkedlist_06.practice.MySingleLinkedList.Node<E>
     * @author Rickshaw
     * @since 2023/4/12 9:30
     */
    public Node<E> findByElement(E e) {
        Node<E> ret = this.head;
        while (ret != null
                && !e.equals(ret.item)) {
            ret = ret.next;
        }
        return ret;
    }

    /**
     * 根据index找到对应的节点
     * @param index   链表下标
     * @return Stack.java.linkedlist_06.practice.MySingleLinkedList.Node<E>
     * @author Rickshaw
     * @since 2023/4/12 9:47
     */
    public Node<E> findByIndex(int index) {
        Node<E> ret = this.head;
        int pos = 0;
        while (ret != null
                && index != pos) {
            ret = ret.next;
            pos++;
        }
        return ret;
    }
    
    /**
     * 无头结点，头插法（这种操作将于输入的顺序相反，逆序）
     * @param e   要插入的元素
     * @author Rickshaw
     * @since 2023/4/12 9:58
     */
    public void insertToHead(E e) {
        insertToHead(this.createNode(e));
    }

    /**
     * 无头结点，头插法（这种操作将于输入的顺序相反，逆序）
     * @param newNode   要插入的节点
     * @author Rickshaw
     * @since 2023/4/12 11:05
     */
    public void insertToHead(Node<E> newNode) {
        if (this.head == null) {
            this.head = newNode;
            return;
        }
        //把newNode的后继节点接到head的后继节点
        newNode.next = this.head;
        //把head的后继节点接到newNode，顺序不能搞反，否则原本head的后继节点会丢失
        this.head = newNode;
    }

    /**
     * 顺序插入, 链表尾部插入
     * @param e   要插入的数据
     * @author Rickshaw
     * @since 2023/4/12 11:10
     */
    public void insertToTail(E e) {
        Node<E> newNode = this.createNode(e);
        if (this.head == null) {
            this.head = newNode;
            return;
        }
        Node<E> ret = this.head;
        while (ret.next != null) {
            ret = ret.next;
        }
        newNode.next = ret.next;
        ret.next = newNode;
    }

    /**
     * 在after之后插入element
     * @param after 在after之后插入
     * @param e 要插入的新元素
     * @author Rickshaw
     * @since 2023/4/12 11:20
     */
    public void insertAfter(Node<E> after, E e) {
        insertAfter(after, this.createNode(e));
    }

    /**
     * 在after之后插入newNode
     * @param after 在after之后插入
     * @param newNode 要插入的新节点
     * @author Rickshaw
     * @since 2023/4/12 11:20
     */
    public void insertAfter(Node<E> after, Node<E> newNode) {
        if (after == null) {
            return;
        }
        newNode.next = after.next;
        after.next = newNode;
    }

    /**
     * 在before之前插入element
     * @param before 在before之前插入
     * @param e 要插入的新元素
     * @author Rickshaw
     * @since 2023/4/12 14:19
     */
    public void insertBefore(Node<E> before, E e) {
        insertBefore(before, this.createNode(e));
    }

    /**
     * 在before之前插入newNode
     * @param before    在before之前插入
     * @param newNode   要插入的新节点
     * @author Rickshaw
     * @since 2023/4/12 14:23
     */
    public void insertBefore(Node<E> before, Node<E> newNode) {
        if (before == null) {
            return;
        }
        //链表只存在一个节点的时候，等于头插法插入
        if (before == this.head) {
            insertToHead(newNode);
            return;
        }
        //找到before的前驱节点
        Node<E> ret = this.head;
        while (ret != null && ret.next != before) {
            ret = ret.next;
        }
        if (ret == null) {
            return;
        }
        newNode.next = before;
        ret.next = newNode;
    }

    /**
     * 删除 node节点
     * @param node  要删除的节点
     * @author Rickshaw
     * @since 2023/4/12 14:51
     */
    public void deleteByNode(Node<E> node) {
        if (node == null || this.head == null) {
            return;
        }
        if (this.head == node) {
            //只能证明node是头节点，不能证明后继节点是空，所以this.head = null错误
            this.head = this.head.next;
            return;
        }
        Node<E> before = this.head;
        while (before != null && before.next != node) {
            before = before.next;
        }
        if (before == null) {
            return;
        }
//        before.next = node.next;
        before.next = before.next.next;
    }

    /**
     * 删除元素e对应的节点
     * @param e  要删除的元素
     * @author Rickshaw
     * @since 2023/4/13 9:11
     */
    public void deleteByElement(E e) {
        if (this.head == null) {
            return;
        }
        //要删除的节点
        Node<E> delete = this.head;
        //要删除节点的前驱节点
        Node<E> prevDelete = null;
        while (delete != null && !e.equals(delete.item)) {
            prevDelete = delete;
            delete = delete.next;
        }
        if (delete == null) {
            return;
        }
        if (prevDelete == null) {
            this.head = this.head.next;
        } else {
            prevDelete.next = prevDelete.next.next;
        }
    }

    /**
     * 打印链表
     * @author Rickshaw
     * @since 2023/4/13 10:22
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

    public Node<E> createNode(E e) {
        return new Node<>(e, null);
    }

    /**
     * 判断是否回文链表
     * @return boolean
     * @author Rickshaw
     * @since 2023/4/18 22:30
     */
    public boolean palindrome() {
        if (this.head == null) {
            return false;
        }
        //只有一个Node节点，也算回文
        if (this.head.next == null) {
            return true;
        }
        //快慢指针找中点
        Node<E> fast = this.head;
        Node<E> slow = this.head;
        //此处fast指针要走两步，所以要两步的空，防止出现fast==null的空指针异常
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Node<E> rightNode = null;
        Node<E> leftNode = null;
        //证明链表长度为奇数，只有一个中点，就是slow节点
        if (fast.next == null) {
            rightNode = slow.next;
            leftNode = sentinelInverse(slow).next;
        } else {
            rightNode = slow.next;
            leftNode = sentinelInverse(slow);
        }
        return compareLeftRight(leftNode, rightNode);
    }

    /**
     * 依次比较左右节点的 item是否相等
     * @param leftNode 左节点
     * @param rightNode  右节点
     * @return boolean
     * @author Rickshaw
     * @since 2023/4/18 23:13
     */
    private boolean compareLeftRight(Node<E> leftNode, Node<E> rightNode) {
        Node<E> left = leftNode;
        Node<E> right = rightNode;
        while (left != null && right != null && left.item == right.item) {
            left = left.next;
            right = right.next;
        }
        return left == null && right == null;
    }

    /**
     * 不带头节点的链表反转（从 head到 until为止）
     * @param until  需要反转的链表的尾区间
     * @return Stack.java.linkedlist_06.practice.MySingleLinkedList.Node<E>
     * @author Rickshaw
     * @since 2023/4/18 22:54
     */
    public Node<E> inverse(Node<E> until) {
        Node<E> prev = null;
        Node<E> cur = null;
        Node<E> next = this.head;
        //cur节点反转指向prev节点，接着next，cur，prev往后顺延一位
        while (next != until) {
            cur = next;
            next = next.next;
            //当前节点的指针反转，指向前节点
            cur.next = prev;
            prev = cur;
        }
        next.next = cur;
        return next;
    }

    /**
     * 带头节点的链表反转（从 head到 until为止）
     * @param until   需要反转的链表的尾区间
     * @return Stack.java.linkedlist_06.practice.MySingleLinkedList.Node<E>
     * @author Rickshaw
     * @since 2023/4/20 10:01
     */
    public Node<E> sentinelInverse(Node<E> until) {
        //声明一个头节点（哨兵节点）
        Node<E> sentinel = this.createNode(null);
        Node<E> iterator = this.head;
        Node<E> cur = null;
        //使用哨兵节点 + 头插法实现
        while (iterator != until) {
            cur = iterator;
            //这个时候cur和iterator指向的是不同的内存地址
            iterator = iterator.next;
            cur.next = sentinel.next;
            sentinel.next = cur;

            /*//这个时候，cur跟iterator指向同一个内存地址
            cur = iterator;
            //修改cur的next指针，iterator也会受到影响
            cur.next = sentinel.next;
            sentinel.next = cur;
            iterator = iterator.next;*/
        }
        iterator.next = sentinel.next;
        sentinel.next = iterator;
        return sentinel.next;
    }

    public static void main(String[] args) {
        MySingleLinkedList<String> linkedList = new MySingleLinkedList<>();
        linkedList.insertToTail("上");
        linkedList.insertToTail("海");
        linkedList.insertToTail("自");
        linkedList.insertToTail("来");
//        linkedList.insertToTail("水");
        linkedList.insertToTail("来");
        linkedList.insertToTail("自");
        linkedList.insertToTail("海");
        linkedList.insertToTail("上");
        linkedList.printAll();
        System.out.println("linkedList.palindrome() = " + linkedList.palindrome());


    }

}
