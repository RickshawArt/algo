package Stack.java.tree_24.practice;

import Stack.java.tree_24.practice.domain.TreeNode;

import java.util.Objects;

/**
 * 自定义 binarySearchTree实现, 暂不支持重复数据
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2023/9/5 9:09
 */
public class CustomBinarySearchTree<E extends Comparable<E>> {

    /**
     * 树的根节点
     */
    private TreeNode<E> root;

    /**
     * 插入
     * @param e 要插入的元素
     * @author Rickshaw
     * @since 2023/9/19 14:17
     */
    public void insert(E e) {
        if (Objects.isNull(e)) {
            return;
        }
        TreeNode<E> currentNode = new TreeNode<>(e);
        if (Objects.isNull(this.root)) {
            this.root = currentNode;
            return;
        }
        TreeNode<E> p = this.root;
        while (true) {
            TreeNode<E> left = p.getLeft();
            TreeNode<E> right = p.getRight();
            //如果e < p节点, 则插入p节点的左节点
            if (e.compareTo(p.getData()) < 0) {
                if (Objects.isNull(left)) {
                    p.setLeft(currentNode);
                    return;
                }
                p = left;
            //如果e > p节点, 则插入p节点的右节点
            } else if (e.compareTo(p.getData()) > 0) {
                if (Objects.isNull(right)) {
                    p.setRight(currentNode);
                    return;
                }
                p = right;
            }
        }
    }

    /**
     * 查找
     * @param e   要查找的元素
     * @return Stack.java.tree_24.practice.domain.TreeNode<E>
     * @author Rickshaw
     * @since 2023/9/19 16:35
     */
    public TreeNode<E> find(E e) {
        if (Objects.isNull(e)) {
            return null;
        }
        TreeNode<E> p = this.root;
        TreeNode<E> ret = null;
        while (p != null) {
            E data = p.getData();
            if (e.compareTo(data) > 0) {
                p = p.getRight();
            } else if (e.compareTo(data) < 0) {
                p = p.getLeft();
            } else if (e.compareTo(data) == 0) {
                ret = p;
                break;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        CustomBinarySearchTree<Integer> binarySearchTree = new CustomBinarySearchTree<>();
        binarySearchTree.insert(10);
        binarySearchTree.insert(4);
        binarySearchTree.insert(20);
        binarySearchTree.insert(1);
        binarySearchTree.insert(3);
        TreeNode<Integer> find = binarySearchTree.find(0);
        System.out.println("find = " + find);
    }

}
