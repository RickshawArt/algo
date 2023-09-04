package Stack.java.tree_24.practice.traversal;

import java.lang.reflect.Array;

/**
 * binaryTree 数组遍历
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2023/9/1 8:34
 */
public class ArrayListTraversal<E> implements BinaryTreeTraversal<Integer> {

    /**
     * 默认数组容量为 8
     */
    private static final int DEFAULT_CAPACITY = 1 << 3;

    /**
     * 数组容器
     */
    private final Object[] container;

    public ArrayListTraversal() {
        container = (Object[]) Array.newInstance(Object.class, DEFAULT_CAPACITY);
    }

    public ArrayListTraversal(int initialCapacity) {
        container = (Object[]) Array.newInstance(Object.class, initialCapacity);
    }

    /**
     * 转换为对应的泛型返回
     * @param index 数组下标
     * @return E
     * @author Rickshaw
     * @since 2023/9/1 09:59
     */
    @SuppressWarnings("unchecked")
    private E elementData(int index) {
        return (E) this.container[index];
    }

    /**
     * 是否数组越界
     * @param index  数组索引
     * @return boolean
     * @author Rickshaw
     * @since 2023/9/1 10:58
     */
    private boolean isOutOfBounds(int index) {
        return index >= this.container.length;
    }

    @Override
    public void preOrder(Integer nodeOrIndex) {
        if (isOutOfBounds(nodeOrIndex)) {
            return;
        }
        E e = this.elementData(nodeOrIndex);
        if (e == null) {
            return;
        }
        System.out.print(e + " ");
        this.preOrder(nodeOrIndex * 2);
        this.preOrder(nodeOrIndex * 2 + 1);
    }

    @Override
    public void inOrder(Integer nodeOrIndex) {
        if (isOutOfBounds(nodeOrIndex)) {
            return;
        }
        E e = this.elementData(nodeOrIndex);
        if (e == null) {
            return;
        }
        this.inOrder(nodeOrIndex * 2);
        System.out.print(e + " ");
        this.inOrder(nodeOrIndex * 2 + 1);
    }

    @Override
    public void postOrder(Integer nodeOrIndex) {
        if (isOutOfBounds(nodeOrIndex)) {
            return;
        }
        E e = this.elementData(nodeOrIndex);
        if (e == null) {
            return;
        }
        this.postOrder(nodeOrIndex * 2);
        this.postOrder(nodeOrIndex * 2 + 1);
        System.out.print(e + " ");
    }

    public static void main(String[] args) {
//        ArrayListTraversal<Character> arrayListTraversal = new ArrayListTraversal<>();
        ArrayListTraversal<Character> arrayListTraversal = new ArrayListTraversal<>(11);
        Object[] container = arrayListTraversal.container;

        //index从1开始, 为了满足左节点为2n, 右节点为2n + 1的规则, n为父节点的index
        char c = 'A';
        for (int i = 1; i < container.length; i++) {
            container[i] = c;
            c++;
        }

        int rootIndex = 1;
        System.out.println("preOrder = ");
        arrayListTraversal.preOrder(rootIndex);
        System.out.println();
        System.out.println();

        System.out.println("inOrder = ");
        arrayListTraversal.inOrder(rootIndex);
        System.out.println();
        System.out.println();

        System.out.println("postOrder = ");
        arrayListTraversal.postOrder(rootIndex);
        System.out.println();
        System.out.println();
    }
}
