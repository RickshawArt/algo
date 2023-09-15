package Stack.java.tree_24.practice.traversal;

import Stack.java.tree_24.practice.domain.TreeNode;

import java.util.*;

/**
 * binaryTree链表遍历
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2023/8/31 17:49
 */
public class LinkedListTraversal<E> implements BinaryTreeTraversal<TreeNode<E>> {

    /**
     * 根节点
     */
    private TreeNode<E> root;

    public LinkedListTraversal(TreeNode<E> root) {
        this.root = root;
    }

    public LinkedListTraversal() {
    }

    @Override
    public void preOrder(TreeNode<E> nodeOrIndex) {
        if (nodeOrIndex == null) {
            return;
        }
        System.out.print(nodeOrIndex.getData() + " ");
        this.preOrder(nodeOrIndex.getLeft());
        this.preOrder(nodeOrIndex.getRight());
    }

    @Override
    public void inOrder(TreeNode<E> nodeOrIndex) {
        if (nodeOrIndex == null) {
            return;
        }
        this.inOrder(nodeOrIndex.getLeft());
        System.out.print(nodeOrIndex.getData() + " ");
        this.inOrder(nodeOrIndex.getRight());
    }

    @Override
    public void postOrder(TreeNode<E> nodeOrIndex) {
        if (nodeOrIndex == null) {
            return;
        }
        this.postOrder(nodeOrIndex.getLeft());
        this.postOrder(nodeOrIndex.getRight());
        System.out.print(nodeOrIndex.getData() + " ");
    }

    @Override
    public void levelOrderBfs(TreeNode<E> nodeOrIndex) {
        //依赖队列FIFO的特点实现, 父节点入队, 出队, 再把子节点依次入队, 循环往复
        Queue<TreeNode<E>> queue = new ArrayDeque<>();
        //用于保存输出节点数据的顺序
        ArrayList<E> outputList = new ArrayList<>();
        //放入root节点
        if (Objects.isNull(nodeOrIndex)) {
            return;
        }
        queue.add(nodeOrIndex);
        while (!queue.isEmpty()) {
            TreeNode<E> remove = queue.remove();
            outputList.add(remove.getData());
            if (Objects.nonNull(remove.getLeft())) {
                queue.add(remove.getLeft());
            }
            if (Objects.nonNull(remove.getRight())) {
                queue.add(remove.getRight());
            }
        }
        outputList.forEach(output -> System.out.print(output + " "));
    }

    @Override
    public void levelOrderDfs(TreeNode<E> nodeOrIndex) {
        if (Objects.isNull(nodeOrIndex)) {
            return;
        }
        List<List<E>> res = new ArrayList<>();
        dfs(nodeOrIndex, 0, res);
        res.stream()
                .flatMap(Collection::stream)
                .forEach(data -> System.out.print(data + " "));
    }

    /**
     * 递归把树节点数据放入
     * @param treeNode  树节点
     * @param depth     深度
     * @param res   对应 binaryTree的二维结构
     * @author Rickshaw
     * @since 2023/9/13 17:08
     */
    private void dfs(TreeNode<E> treeNode, int depth, List<List<E>> res) {
        //res的第二层的list相当于树的每一层 - 1, 此时深度为res的长度，开启新的ArrayList<Integer>用于记录这一层
        if (res.size() == depth) {
            ArrayList<E> depthList = new ArrayList<>();
            res.add(depthList);
        }
        //在当前层追加保存数据
        res.get(depth).add(treeNode.getData());
        if (Objects.nonNull(treeNode.getLeft())) {
            this.dfs(treeNode.getLeft(), depth + 1, res);
        }
        if (Objects.nonNull(treeNode.getRight())) {
            this.dfs(treeNode.getRight(), depth + 1, res);
        }
    }

    public static void main(String[] args) {
        LinkedListTraversal<String> linkedListTraversal = new LinkedListTraversal<>();
        TreeNode<String> bNode = new TreeNode<>("B");
        TreeNode<String> cNode = new TreeNode<>("C");
        TreeNode<String> dNode = new TreeNode<>("D");
        TreeNode<String> eNode = new TreeNode<>("E");
        TreeNode<String> fNode = new TreeNode<>("F");
        TreeNode<String> gNode = new TreeNode<>("G");
        TreeNode<String> mNode = new TreeNode<>("M");
        linkedListTraversal.root = new TreeNode<>("I");
        linkedListTraversal.root.setLeft(bNode);
        linkedListTraversal.root.setRight(cNode);
        bNode.setLeft(dNode);
        bNode.setRight(eNode);
        cNode.setLeft(fNode);
        cNode.setRight(gNode);
        fNode.setLeft(mNode);

        System.out.println("preOrder = ");
        linkedListTraversal.preOrder(linkedListTraversal.root);
        System.out.println();
        System.out.println();

        System.out.println("inOrder = ");
        linkedListTraversal.inOrder(linkedListTraversal.root);
        System.out.println();
        System.out.println();

        System.out.println("postOrder = ");
        linkedListTraversal.postOrder(linkedListTraversal.root);
        System.out.println();
        System.out.println();

        System.out.println("levelOrderBfs = ");
        linkedListTraversal.levelOrderBfs(linkedListTraversal.root);
        System.out.println();
        System.out.println();

        System.out.println("levelOrderDfs = ");
        linkedListTraversal.levelOrderDfs(linkedListTraversal.root);
        System.out.println();
        System.out.println();
    }

}
