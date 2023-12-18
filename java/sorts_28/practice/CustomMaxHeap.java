package Stack.java.sorts_28.practice;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

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
     * @return E
     * @author Rickshaw
     * @since 2023/10/25 11:50
     */
    public E removeMax() {
        if (this.size == 0) {
            return null;
        }
        int i = 1;
        E ret = this.elementData(i);
        //把根节点换成最新节点, 删除最新节点
        this.elementData[i] = this.elementData(this.size--);
        //往下堆化
        heapify(i, this.size);
        return ret;
    }

    /**
     * 堆排序
     * @param e  要转换成的泛型数组
     * @return E[]
     * @author Rickshaw
     * @since 2023/12/18 10:47
     */
    @SuppressWarnings("unchecked")
    public E[] sort(E[] e) {
        this.buildHeap(this.size);
        for (int i = this.size; i > 1; i--) {
            this.swap(this.elementData, i, 1);
            heapify(1, i - 1);
        }
        return (E[]) Arrays.copyOf(this.elementData, this.size, e.getClass());
    }

    /**
     * 从上往下建堆
     * @param size   建堆的范围大小
     * @author Rickshaw
     * @since 2023/12/18 10:14
     */
    public void buildHeap(int size) {
        int heapIndex = size / 2;
        for (int i = heapIndex; i >= 1; i--) {
            this.heapify(i, size);
        }
    }

    /**
     * 堆化, 保持堆顶是最值
     * @param index   索引
     * @param size   堆化的范围大小
     * @author Rickshaw
     * @since 2023/12/15 17:16
     */
    private void heapify(int index, int size) {
        while (true) {
            int curMaxIndex = this.getCurMaxIndex(index, size);
            //父节点都比其左右子节点大, 堆化结束
            if (index == curMaxIndex) {
                break;
            }
            this.swap(this.elementData, index, curMaxIndex);
            index = curMaxIndex;
        }
    }

    /**
     * 从该索引节点的左右子树获取最大节点的索引
     * @param i   索引
     * @param size   比较的范围大小
     * @return int
     * @author Rickshaw
     * @since 2023/12/15 15:03
     */
    private int getCurMaxIndex(int i, int size) {
        int leftNodeIndex = 2 * i;
        int rightNodeIndex = 2 * i + 1;
        //比较当前节点与其左右子节点大小, 把最大的节点作为父节点
        int curMaxIndex = i;
        if (size >= leftNodeIndex && this.elementData(curMaxIndex).compareTo(this.elementData(leftNodeIndex))  < 0) {
            curMaxIndex = leftNodeIndex;
        }
        if (size >= rightNodeIndex && this.elementData(curMaxIndex).compareTo(this.elementData(rightNodeIndex))  < 0) {
            curMaxIndex = rightNodeIndex;
        }
        return curMaxIndex;
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

    public static void main(String[] args) {
        CustomMaxHeap<Integer> customMaxHeap = new CustomMaxHeap<>();
//        customMaxHeap.add(8);
//        customMaxHeap.add(2);
//        customMaxHeap.add(10);
//        customMaxHeap.add(5);
//        customMaxHeap.add(12);
//        customMaxHeap.add(9);
//        customMaxHeap.add(0);
//        customMaxHeap.add(6);
//        System.out.println("customMaxHeap.elementData = " + Arrays.toString(customMaxHeap.elementData));
//        System.out.println("customMaxHeap.removeMax() = " + customMaxHeap.removeMax());
//        System.out.println("customMaxHeap.elementData = " + Arrays.toString(customMaxHeap.elementData));

        customMaxHeap.size = 0;
        for (int i = 1; i < customMaxHeap.elementData.length; i++) {
            customMaxHeap.size++;
            int random = new Random().nextInt(50);
            customMaxHeap.elementData[i] = random;
            if (i == 9) {
                break;
            }
        }
        Set<Object> collect = Arrays.stream(customMaxHeap.elementData).collect(Collectors.toSet());
        if (collect.size() != customMaxHeap.size + 1) {
            return;
        }
        Integer[] sort = customMaxHeap.sort(new Integer[0]);
        System.out.println("sort = " + Arrays.toString(sort));
    }

}
