package Stack.java.tree_24.practice.traversal;

import Stack.java.tree_24.practice.domain.TreeNode;

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
    }

}
