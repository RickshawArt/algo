package Stack.java.tree_24.practice.domain;

import java.util.StringJoiner;

/**
 * binaryTree链式节点对象
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2023/9/4 10:51
 */
public class TreeNode<E> {
    private final E data;
    private TreeNode<E> left;
    private TreeNode<E> right;

    public TreeNode(E data) {
        this.data = data;
    }

    public E getData() {
        return data;
    }

    public TreeNode<E> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<E> left) {
        this.left = left;
    }

    public TreeNode<E> getRight() {
        return right;
    }

    public void setRight(TreeNode<E> right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TreeNode.class.getSimpleName() + "[", "]")
                .add("data=" + data)
                .add("left=" + left)
                .add("right=" + right)
                .toString();
    }
}
