package Stack.java.linkedlist_07.practice;

import java.util.Objects;

/**
 * 1、单链表反转
 * 2、链表中环的检测
 * 3、两个有序的链表合并
 * 4、删除链表倒数第n个结点
 * 5、求链表的中间结点
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2023/4/21 15:53
 */
public class MyLinkedListAlgo<E> {

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
     * 单链表反转
     * @param head  需要反转的链表头节点
     * @return Stack.java.linkedlist_07.practice.MyLinkedListAlgo.Node<E>
     * @author Rickshaw
     * @since 2023/4/21 17:03
     */
    public Node<E> reverse(Node<E> head) {
        Node<E> sentinel = null;
        //迭代节点
        Node<E> p = head;
        //用于把当前节点通过头茶法插入sentinel
        Node<E> cur;
        while (p != null) {
            cur = p;
            p = p.next;
            cur.next = sentinel;
            sentinel = cur;
        }
        return sentinel;
    }

    /**
     * 链表中环的检测
     * @param head  需要检测链表的头节点
     * @return boolean
     * @author Rickshaw
     * @since 2023/4/23 0:01
     */
    public boolean hasCycle(Node<E> head) {
        if (head == null) {
            return false;
        }
        Node<E> fast = head.next;
        Node<E> slow = head;
        while (fast != null && fast.next != null) {
            if (fast == slow) {
                return true;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        return false;
    }

    /**
     * 两个正序的有序链表合并
     * @param o1 有序链表1
     * @param o2 有序链表2
     * @return Stack.java.linkedlist_07.practice.MyLinkedListAlgo.Node<java.lang.Integer>
     * @author Rickshaw
     * @since 2023/4/23 9:10
     */
    public Node<Integer> mergeLinkedList(Node<Integer> o1, Node<Integer> o2) {
        Node<Integer> sentinel = new Node<>(Integer.MIN_VALUE, null);
        Node<Integer> p = sentinel;
        while (o1 != null && o2 != null) {
            if (o1.item.compareTo(o2.item) > 0) {
                p.next = o2;
                o2 = o2.next;
            } else {
                p.next = o1;
                o1 = o1.next;
            }
            //用于存储下一个需要合并的节点
            p = p.next;
        }
        //把剩余的链表放在新链表的结尾
        p.next = Objects.isNull(o1) ? o2 : o1;
        return sentinel.next;
    }

    /**
     * 删除链表倒数第 n个节点
     * @param head  链表的头节点
     * @param n 要删除的倒数第 n个节点
     * @return Stack.java.linkedlist_07.practice.MyLinkedListAlgo.Node<E>
     * @author Rickshaw
     * @since 2023/4/23 10:13
     */
    public Node<E> lastRemoveOf(Node<E> head, int n) {
        if (head == null) {
            throw new IllegalArgumentException("The LinkedList is null");
        }
        if (n <= 0) {
            throw new IllegalArgumentException("n should be > 0");
        }
        //快指针先走 n步
        Node<E> fast = head;
        int i = 1;
        while (fast != null && i < n) {
            fast = fast.next;
            i++;
        }
        //链表长度不够 n
        if (fast == null) {
            throw new IllegalArgumentException("The LinkedList's size should be >= n");
        }
        //慢指针刚好就是要删除的节点
        Node<E> slow = head;
        //单链表要通过要删除的节点的前驱节点来删除
        Node<E> prevRemove = null;
        while (fast.next != null) {
            prevRemove = slow;
            slow = slow.next;
            fast = fast.next;
        }
        //证明要删除的是首节点
        if (prevRemove == null) {
            head = head.next;
            return head;
        }
        prevRemove.next = prevRemove.next.next;
        return head;
    }

    /**
     * 求链表的中间结点
     * @param head  链表的头节点
     * @return Stack.java.linkedlist_07.practice.MyLinkedListAlgo.Node<E>
     * @author Rickshaw
     * @since 2023/4/23 15:03
     */
    public Node<E> findMiddleNode(Node<E> head) {
        //快指针
        Node<E> p = head;
        //慢指针
        Node<E> q = head;
        while (p.next != null && p.next.next != null) {
            q = q.next;
            p = p.next.next;
        }
        return q;
    }

    /**
     * 打印传进来的链表
     * @param head   需要打印的链表头节点
     * @author Rickshaw
     * @since 2023/4/21 17:13
     */
    public void printAll(Node<E> head) {
        System.out.print("printAll: ");
        Node<E> iterator = head;
        while (iterator != null) {
            System.out.print(iterator.item + " ");
            iterator = iterator.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        //测试单链表反转
        /*MyLinkedListAlgo<String> algo = new MyLinkedListAlgo<>();
        Node<String> aNode = algo.createNode("a");
        Node<String> bNode = new Node<>("b", aNode);
        Node<String> cNode = new Node<>("c", bNode);
        Node<String> dNode = new Node<>("d", cNode);
        Node<String> eNode = new Node<>("e", dNode);
        algo.printAll(eNode);
        Node<String> reverseNode = algo.reverse(eNode);
        algo.printAll(reverseNode);*/

        //测试链表中环的检测
        /*MyLinkedListAlgo<String> algo = new MyLinkedListAlgo<>();
        Node<String> aNode = algo.createNode("a");
        Node<String> bNode = new Node<>("b", aNode);
        Node<String> cNode = new Node<>("c", bNode);
        Node<String> dNode = new Node<>("d", cNode);
        Node<String> eNode = new Node<>("e", dNode);
        Node<String> fNode = new Node<>("f", eNode);
        Node<String> gNode = new Node<>("g", fNode);
        aNode.next = fNode;
        System.out.println("algo.hasCycle(eNode) = " + algo.hasCycle(eNode));*/

        //测试两个正序的有序链表合并
        /*MyLinkedListAlgo<Integer> algo = new MyLinkedListAlgo<>();
        Node<Integer> node9 = algo.createNode(9);
        Node<Integer> node5 = new Node<>(5, node9);
        Node<Integer> node0 = new Node<>(0, node5);
        algo.printAll(node0);

        Node<Integer> node7 = algo.createNode(7);
        Node<Integer> node6 = new Node<>(6, node7);
        Node<Integer> node3 = new Node<>(3, node6);
        Node<Integer> node1 = new Node<>(1, node3);
        algo.printAll(node1);

        Node<Integer> mergedNode = algo.mergeLinkedList(node1, node0);
        algo.printAll(mergedNode);*/

        //测试删除链表倒数第 n个节点
        /*MyLinkedListAlgo<Integer> algo = new MyLinkedListAlgo<>();
        Node<Integer> node7 = algo.createNode(7);
        Node<Integer> node6 = new Node<>(6, node7);
        Node<Integer> node3 = new Node<>(3, node6);
        Node<Integer> node1 = new Node<>(1, node3);
        algo.printAll(node1);
        Node<Integer> head = algo.lastRemoveOf(node1, 0);
        algo.printAll(head);*/

        //测试求链表的中间结点
        MyLinkedListAlgo<Integer> algo = new MyLinkedListAlgo<>();
//        Node<Integer> node7 = algo.createNode(7);
        Node<Integer> node6 = new Node<>(6, null);
        Node<Integer> node5 = new Node<>(5, node6);
        Node<Integer> node4 = new Node<>(4, node5);
        Node<Integer> node3 = new Node<>(3, node4);
        Node<Integer> node2 = new Node<>(2, node3);
        Node<Integer> node1 = new Node<>(1, node2);
        algo.printAll(node1);
        Node<Integer> head = algo.findMiddleNode(node1);
        System.out.println("head.item = " + head.item);
    }

}
