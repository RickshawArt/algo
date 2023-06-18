package Stack.java.skiplist_17.practice;

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

    private class Node {
        /**
         * 当前节点数据
         */
        private E data;

        /**
         * 当前节点的每一层的next节点数组
         */
        @SuppressWarnings({"unchecked", "DataFlowIssue"})
        private final Node[] nextNodes = (Node[]) new Object[DEFAULT_MAX_LEVEL];

        /**
         * 当前节点的层级
         */
        private int maxLevel;

        private Node() {
            this.data = null;
            this.maxLevel = 0;
        }

        private Node(E e, int maxLevel) {
            this.data = e;
            this.maxLevel = maxLevel;
        }

    }

    /**
     * 获取Node[]数组
     * @param length    数组长度
     * @return Stack.java.skiplist_17.practice.MySkipList<E>.Node[]
     * @author Rickshaw
     * @since 2023/6/17 15:40
     */
    @SuppressWarnings({"unchecked", "DataFlowIssue"})
    private MySkipList<E>.Node[] getNodes(int length) {
        return (Node[]) new Object[length];
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

        Node[] preNodes = this.getNodes(level);
        //找到每层要插入的位置（ > e 的位置）
        Node iterator = this.head;
        for (int i = level; i > 0; i--) {
            while (iterator.nextNodes[i] != null && e.compareTo(iterator.nextNodes[i].data) < 0) {
                iterator = iterator.nextNodes[i];
            }
            // TODO: 2023/6/19   
        }
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

}
