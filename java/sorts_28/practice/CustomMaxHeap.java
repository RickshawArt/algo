package Stack.java.sorts_28.practice;

import java.util.Arrays;
import java.util.Objects;

/**
 * 自定义最大堆实现
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2023/10/25 10:23
 */
public class CustomMaxHeap<E extends Comparable<E>> {

    /**
     * 存放数据的容器, 从index = 1开始存放
     */
    private final Object[] elementData;

    /**
     * 当前容器存放数据的数量
     */
    private int size;

    /**
     * 默认容量, 刚好属于满二叉树
     */
    private static final int DEFAULT_CAPACITY = (1 << 4) - 1;

    public CustomMaxHeap() {
        this.elementData = new Object[DEFAULT_CAPACITY + 1];
        this.size = 0;
    }

    public CustomMaxHeap(int capacity) {
        this.elementData = new Object[capacity + 1];
        this.size = 0;
    }

    /**
     * 获取泛型元素
     * @param index    数组索引
     * @return E
     * @author Rickshaw
     * @since 2023/10/25 11:38
     */
    @SuppressWarnings("unchecked")
    private E elementData(int index) {
        return (E) this.elementData[index];
    }

    /**
     * 交换数组的两个值
     * @param arr 数组
     * @param i 要交换的索引
     * @param j  要交换的索引
     * @author Rickshaw
     * @since 2023/10/25 11:00
     */
    private void swap(Object[] arr, int i, int j) {
        Object temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 插入
     * @param e   要插入的元素
     * @return boolean
     * @author Rickshaw
     * @since 2023/10/25 11:39
     */
    public boolean add(E e) {
        if (Objects.isNull(e)) {
            return false;
        }
        if (this.size >= this.elementData.length - 1) {
            return false;
        }
        this.elementData[++this.size] = e;
        int i = this.size;
        int parentIndex = i / 2;
        //往上堆化
        while (parentIndex > 0 && this.elementData(i).compareTo(this.elementData(parentIndex)) > 0) {
            this.swap(this.elementData, i, parentIndex);
            i = parentIndex;
            parentIndex = i / 2;
        }
        return true;
    }

    /**
     * 移除最大的元素
     * @author Rickshaw
     * @since 2023/10/25 11:50
     */
    public void removeMax() {
        if (this.size == 0) {
            return;
        }
        int i = 1;
        //把根节点换成最新节点, 删除最新节点
        this.elementData[i] = this.elementData(this.size--);
        //往下堆化
        while (true) {
            int leftNodeIndex = 2 * i;
            int rightNodeIndex = 2 * i + 1;
            //比较当前节点与其左右子节点大小, 把最大的节点作为父节点
            int curMaxIndex = i;
            if (this.size >= leftNodeIndex && this.elementData(curMaxIndex).compareTo(this.elementData(leftNodeIndex))  < 0) {
                curMaxIndex = leftNodeIndex;
            }
            if (this.size >= rightNodeIndex && this.elementData(curMaxIndex).compareTo(this.elementData(rightNodeIndex))  < 0) {
                curMaxIndex = rightNodeIndex;
            }
            //父节点都比其左右子节点大, 堆化结束
            if (i == curMaxIndex) {
                break;
            }
            this.swap(this.elementData, i, curMaxIndex);
            i = curMaxIndex;
        }
    }

    public static void main(String[] args) {
        CustomMaxHeap<Integer> customMaxHeap = new CustomMaxHeap<>();
        customMaxHeap.add(8);
        customMaxHeap.add(2);
        customMaxHeap.add(10);
        customMaxHeap.add(5);
        customMaxHeap.add(12);
        customMaxHeap.add(9);
        customMaxHeap.add(0);
        customMaxHeap.add(6);
        customMaxHeap.removeMax();
        System.out.println("customMaxHeap.elementData = " + Arrays.toString(customMaxHeap.elementData));
    }

}
