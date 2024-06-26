package Stack.java.sorts_12.practice;

import Stack.java.service.SortAlgo;

/**
 * 快速排序
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2023/5/12 8:45
 */
public class MyQuickSort implements SortAlgo {

    @Override
    public void sort(long[] arr) {
        this.sort(arr, arr.length);
    }

    @Override
    public void sort(long[] arr, int length) {
        if (length <= 1) {
            return;
        }
        this.quickSortRecursion(arr, 0, length - 1);
    }

    /**
     * 递归分区排序，直到不能再分，即排序完成
     * @param arr   待排序数组
     * @param left  左区间
     * @param right 右区间
     * @author Rickshaw
     * @since 2023/5/12 10:57
     */
    private void quickSortRecursion(long[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int partitionIndex = this.partition(arr, left, right);
        this.quickSortRecursion(arr, left, partitionIndex - 1);
        this.quickSortRecursion(arr, partitionIndex + 1, right);
    }

    /**
     * 分区，返回分区支点索引
     * @param arr 待分区的数组
     * @param left 左区间
     * @param right   右区间
     * @return int
     * @author Rickshaw
     * @since 2023/5/12 10:52
     */
    private int partition(long[] arr, int left, int right) {
        //选取数组尾部元素作为分区元素
        long pivot = arr[right];
        // 以i为分界点，左边 <= pivot，右边 > pivot；j用于遍历数组，跟pivot进行比较
        // i为已处理区间的为尾部索引，j为未处理区间的头部索引
        int i = left;
        for (int j = left; j < right; j++) {
            if (arr[j] <= pivot) {
                swap(arr, i++, j);
            }
        }
        // 最后把pivot放在对应合适的位置
        swap(arr, i, right);
        return i;
    }

    /**
     * 交换数组的两个数
     * @param arr  数组
     * @param i  要交换索引
     * @param j  要交换索引
     * @author Rickshaw
     * @since 2023/5/12 10:46
     */
    private static void swap(long[] arr, int i, int j) {
        long temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    public static void main(String[] args) {
        SortAlgo myQuickSort = new MyQuickSort();
        long[] arr = {8, 10, 2, 3, 6, 1, 5};
//        arr = new long[]{8, 1, 3, 9, 4, 5};
//        MyQuickSort myQuickSort1 = new MyQuickSort();
//        int partition = myQuickSort1.partition(arr, 1, 4);
        myQuickSort.sort(arr);
        myQuickSort.printArr(arr);
    }
}
