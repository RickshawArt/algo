package Stack.java.tree_24.practice.traversal;

/**
 * binaryTree链表遍历
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2023/8/31 17:49
 */
public class LinkedListTraversal<E> implements BinaryTreeTraversal<LinkedListTraversal.TreeNode<E>> {

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
        System.out.print(nodeOrIndex.data + " ");
        this.preOrder(nodeOrIndex.left);
        this.preOrder(nodeOrIndex.right);
    }

    @Override
    public void inOrder(TreeNode<E> nodeOrIndex) {
        if (nodeOrIndex == null) {
            return;
        }
        this.inOrder(nodeOrIndex.left);
        System.out.print(nodeOrIndex.data + " ");
        this.inOrder(nodeOrIndex.right);
    }

    @Override
    public void postOrder(TreeNode<E> nodeOrIndex) {
        if (nodeOrIndex == null) {
            return;
        }
        this.postOrder(nodeOrIndex.left);
        this.postOrder(nodeOrIndex.right);
        System.out.print(nodeOrIndex.data + " ");
    }

    public static class TreeNode<E> {
        private final E data;
        private LinkedListTraversal.TreeNode<E> left;
        private LinkedListTraversal.TreeNode<E> right;

        private TreeNode(E data) {
            this.data = data;
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
        linkedListTraversal.root.left = bNode;
        linkedListTraversal.root.right = cNode;
        bNode.left = dNode;
        bNode.right = eNode;
        cNode.left = fNode;
        cNode.right = gNode;
        fNode.left = mNode;

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
