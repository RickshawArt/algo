package Stack.java.tree_24.practice;

import Stack.java.tree_24.practice.domain.TreeNode;
import Stack.java.tree_24.practice.traversal.BinaryTreeTraversal;
import Stack.java.tree_24.practice.traversal.LinkedListTraversal;

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
     * 用于遍历 binarySearchTree
     */
    private final BinaryTreeTraversal<TreeNode<E>> traversal = new LinkedListTraversal<>();

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

    /**
     * 删除
     * @param e 要删除的元素
     * @author Rickshaw
     * @since 2023/9/20 14:46
     */
    public void delete(E e) {
        if (Objects.isNull(e)) {
            return;
        }
        //要删除的元素
        TreeNode<E> p = this.root;
        //要删除元素的父节点
        TreeNode<E> parent = null;
        //找到要删除的元素
        while (Objects.nonNull(p) && e.compareTo(p.getData()) != 0) {
            parent = p;
            if (e.compareTo(p.getData()) > 0) {
                p = p.getRight();
            } else if (e.compareTo(p.getData()) < 0) {
                p = p.getLeft();
            }
        }
        //没有找到要删除的元素, 直接返回
        if (Objects.isNull(p)) {
            return;
        }
        //要删除的节点有两个子节点的情况
        if (Objects.nonNull(p.getLeft()) && Objects.nonNull(p.getRight())) {
            //找到这个节点的右子树中的最小节点
            TreeNode<E> minP = p.getRight();
            TreeNode<E> minParent = p;
            while (minP.getLeft() != null) {
                minParent = minP;
                minP = minP.getLeft();
            }
            //把要删除的元素替换成p右子树中的最小节点
            p.setData(minP.getData());
            //问题转换成删除p右子树中的最小节点, 因为p右子树中的最小节点的data已经替换p节点的data了
            p = minP;
            parent = minParent;
        }

        //用于未来替换删除节点p的
        TreeNode<E> child = null;
        //根据p只有一个子节点的情况, 填充child
        if (Objects.nonNull(p.getLeft())) {
            child = p.getLeft();
        } else if (Objects.nonNull(p.getRight())) {
            child = p.getRight();
        }

        //真正删除的操作
        if (Objects.isNull(parent)) {
            //删除根节点的情况
            this.root = child;
        } else if (p.equals(parent.getLeft())) {
            parent.setLeft(child);
        } else if (p.equals(parent.getRight())) {
            parent.setRight(child);
        }
    }

    /**
     * 查找最小值
     * @return Stack.java.tree_24.practice.domain.TreeNode<E>
     * @author Rickshaw
     * @since 2023/10/12 8:39
     */
    public TreeNode<E> findMin() {
        if (Objects.isNull(this.root)) {
            return null;
        }
        TreeNode<E> p = this.root;
        while (Objects.nonNull(p.getLeft())) {
            p = p.getLeft();
        }
        return p;
    }

    /**
     * 查找最大值
     * @return Stack.java.tree_24.practice.domain.TreeNode<E>
     * @author Rickshaw
     * @since 2023/10/12 8:39
     */
    public TreeNode<E> findMax() {
        if (Objects.isNull(this.root)) {
            return null;
        }
        TreeNode<E> p = this.root;
        while (Objects.nonNull(p.getRight())) {
            p = p.getRight();
        }
        return p;
    }

    /**
     * 顺序输出 binarySearchTree 的元素
     * @author Rickshaw
     * @since 2023/10/12 16:18
     */
    public void printAsc() {
        traversal.inOrder(this.root);
    }

    /**
     * 获取 binarySearchTree的高度, 树的根节点为 0
     * @return int
     * @author Rickshaw
     * @since 2023/10/12 17:27
     */
    public int getHeight() {
        return this.getHeightRecursive(this.root) - 1;
    }

    /**
     * 递归获取左右子树中最大的高度
     * @param treeNode  树节点
     * @return int
     * @author Rickshaw
     * @since 2023/10/12 17:28
     */
    private int getHeightRecursive(TreeNode<E> treeNode) {
        if (Objects.isNull(treeNode)) {
            return 0;
        }
        int leftHeight = this.getHeightRecursive(treeNode.getLeft());
        int rightHeight = this.getHeightRecursive(treeNode.getRight());
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static void main(String[] args) {
        CustomBinarySearchTree<Integer> binarySearchTree = new CustomBinarySearchTree<>();
        binarySearchTree.insert(10);
        binarySearchTree.insert(4);
        binarySearchTree.insert(20);
        binarySearchTree.insert(1);
        binarySearchTree.insert(3);
        binarySearchTree.printAsc();
        System.out.println("binarySearchTree.getHeight() = " + binarySearchTree.getHeight());
        System.out.println();
        TreeNode<Integer> find = binarySearchTree.find(3);
        System.out.println("find = " + find);
        binarySearchTree.delete(10);
        binarySearchTree.delete(20);
        binarySearchTree.delete(1);
        System.out.println("binarySearchTree.findMin() = " + binarySearchTree.findMin().getData());
        System.out.println("binarySearchTree.findMax() = " + binarySearchTree.findMax().getData());
    }

}
