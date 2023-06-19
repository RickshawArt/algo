package Stack.java.skiplist_17.practice;


import java.lang.reflect.Array;

/**
 * 跳表实现练习，不存储重复元素
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2023/6/17 13:02
 */
public class MySkipList<E extends Comparable<E>> {

    /**
     * 默认最大层级16
     */
    private static final int DEFAULT_MAX_LEVEL = 1 << 4;

    /**
     * 随机出现概率因子
     */
    private static final double RANDOM_FACTOR = 0.5d;

    /**
     * 哨兵头节点
     */
    private final Node head = new Node();

    /**
     * 当前跳表的最大索引层
     */
    private int maxIndexLevel = 1;

    private class Node {
        /**
         * 当前节点数据
         */
        private final E data;

        /**
         * 当前节点的每一层的next节点数组
         */
        @SuppressWarnings("unchecked")
        private final Node[] nextNodes = (Node[]) Array.newInstance(Node.class, DEFAULT_MAX_LEVEL);

        /**
         * 当前节点的层级
         */
        private final int maxLevel;

        private Node() {
            this.data = null;
            this.maxLevel = 0;
        }

        private Node(E e, int maxLevel) {
            this.data = e;
            this.maxLevel = maxLevel;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", maxLevel=" + maxLevel +
                    '}';
        }
    }

    /**
     * 获取Node[]数组
     * @param length    数组长度
     * @return Stack.java.skiplist_17.practice.MySkipList<E>.Node[]
     * @author Rickshaw
     * @since 2023/6/17 15:40
     */
    @SuppressWarnings("unchecked")
    private MySkipList<E>.Node[] getNodes(int length) {
        return (Node[]) Array.newInstance(Node.class, length);
    }

    /**
     * 插入元素
     * @param e 待插入元素
     * @author Rickshaw
     * @since 2023/6/17 14:39
     */
    public void insert(E e) {
        if (e == null) {
            return;
        }
        int level = randomLevel();
        //level是当前节点的索引层数
        Node newNode = new Node(e, level);

        Node[] preNodes = getPreNodes(e, level);

        //newNode插入操作
        for (int i = level - 1; i >= 0; i--) {
            //在update的node数组插入newNode，先用newNode接上后面的Node节点；
            //如果先接newNode节点会丢失update插入点后面的节点
            newNode.nextNodes[i] = preNodes[i].nextNodes[i];
            preNodes[i].nextNodes[i] = newNode;
        }

        //更新最大索引层
        if (this.maxIndexLevel < level) {
            this.maxIndexLevel = level;
        }
    }

    /**
     * 找到每个索引层要插入的前驱位置（ < e 的最大值位置）
     * @param e     要插入的 e元素
     * @param level    索引层数
     * @return Stack.java.skiplist_17.practice.MySkipList.Node[]
     * @author Rickshaw
     * @since 2023/6/20 1:07
     */
    private Node[] getPreNodes(E e, int level) {
        Node[] preNodes = this.getNodes(level);
        Node iterator = this.head;
        //遍历索引层数
        for (int i = level - 1; i >= 0; i--) {
            //找到每层要插入的前驱位置（ < e 的最大值位置）
            while (iterator.nextNodes[i] != null && e.compareTo(iterator.nextNodes[i].data) > 0) {
                iterator = iterator.nextNodes[i];
            }
            preNodes[i] = iterator;
        }
        return preNodes;
    }

    /**
     * 根据元素查找对应节点，没找到返回null
     * @param e   待查找的元素
     * @return Stack.java.skiplist_17.practice.MySkipList<E>.Node
     * @author Rickshaw
     * @since 2023/6/19 14:50
     */
    public Node find(E e) {
        if (e == null) {
            return null;
        }
        Node ret = this.head;
        //从索引顶层向下检索元素，直到0层并且 >= e 停止
        for (int i = this.maxIndexLevel - 1; i >= 0; i--) {
            while (ret.nextNodes[i] != null && e.compareTo(ret.nextNodes[i].data) > 0) {
                ret = ret.nextNodes[i];
            }
        }
        if (ret.nextNodes[0] != null) {
            assert ret.nextNodes[0].data != null;
            if (e.compareTo(ret.nextNodes[0].data) == 0) {
                return ret.nextNodes[0];
            }
        }
        return null;
    }

    /**
     * 根据元素移除 node节点
     * @param e 待移除元素
     * @author Rickshaw
     * @since 2023/6/19 15:31
     */
    public void remove(E e) {
        //找到每层要插入的前驱位置（ < e 的最大值位置）
        Node[] preNodes = getPreNodes(e, maxIndexLevel);

        //排除多余的循环判断，提高方法执行时间，如果第0层都没有要删除的元素，则直接跳过此部分逻辑
        if (preNodes[0].nextNodes[0].data != null && e.compareTo(preNodes[0].nextNodes[0].data) == 0) {
            for (int i = maxIndexLevel - 1; i >= 0; i--) {
                if (preNodes[i].nextNodes[i] != null && e.compareTo(preNodes[i].nextNodes[i].data) == 0) {
                    //移除操作，update[i].forwards[i]为一个node节点，
                    //node节点里面维护的forwards[i]为对应第i层的下一个元素
                    preNodes[i].nextNodes[i] = preNodes[i].nextNodes[i].nextNodes[i];
                }
            }
        }

        //索引层的首元素如果为空，则证明该层没有元素，可以移除该层
        while (this.maxIndexLevel > 1 && this.head.nextNodes[maxIndexLevel] == null) {
            this.maxIndexLevel--;
        }
    }

    /**
     * 打印全部 node节点
     * @author Rickshaw
     * @since 2023/6/19 16:55
     */
    public void printAll() {
        Node iterator = this.head;
        System.out.println("printAll: ");
        while (iterator.nextNodes[0] != null) {
            System.out.println(iterator.nextNodes[0] + " ");
            iterator = iterator.nextNodes[0];
        }
        System.out.println();
    }

    /**
     * 理论来讲，一级索引中元素个数应该占原始数据的 50%，二级索引中元素个数占 25%，三级索引12.5% ，一直到最顶层。
     * 因为这里每一层的晋升概率是 50%。对于每一个新插入的节点，都需要调用 randomLevel 生成一个合理的层数。
     * 该 randomLevel 方法会随机生成 1~DEFAULT_MAX_LEVEL 之间的数，且 ：
     *     50%的概率返回 1
     *     25%的概率返回 2
     *     12.5%的概率返回 3 ...
     * @return int  随机层数
     * @author Rickshaw
     * @since 2023/6/17 15:00
     */
    private int randomLevel() {
        int level = 1;
        //当RANDOM_FACTOR为0.5d的时候，Math.random() < RANDOM_FACTOR为 50%
        while (Math.random() < RANDOM_FACTOR && level < DEFAULT_MAX_LEVEL) {
            level += 1;
        }
        return level;
    }

    public static void main(String[] args) {
        MySkipList<Integer> mySkipList = new MySkipList<>();
        mySkipList.insert(5);
        mySkipList.insert(8);
        mySkipList.insert(3);
        mySkipList.remove(5);
        mySkipList.insert(11);
        mySkipList.remove(3);
        mySkipList.remove(11);
        MySkipList<Integer>.Node node = mySkipList.find(11);
        System.out.println("node = " + node);
        mySkipList.printAll();
    }

}
