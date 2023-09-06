package Stack.java.tree_24.practice.traversal;

/**
 * binaryTree 遍历接口
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2023/8/31 17:20
 */
public interface BinaryTreeTraversal<T> {

    /**
     * 前序遍历
     * @param nodeOrIndex   树节点或索引
     * @author Rickshaw
     * @since 2023/8/31 17:26
     */
    void preOrder(T nodeOrIndex);

    /**
     * 中序遍历
     * @param nodeOrIndex   树节点或索引
     * @author Rickshaw
     * @since 2023/8/31 17:26
     */
    void inOrder(T nodeOrIndex);

    /**
     * 后序遍历
     * @param nodeOrIndex   树节点或索引
     * @author Rickshaw
     * @since 2023/8/31 17:26
     */
    void postOrder(T nodeOrIndex);

    /**
     * 层序遍历(广度优先)
     * @param nodeOrIndex   树节点或索引
     * @author Rickshaw
     * @since 2023/8/31 17:26
     */
    void levelOrderBfs(T nodeOrIndex);

}
