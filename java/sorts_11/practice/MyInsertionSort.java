package Stack.java.sorts_11.practice;

import Stack.java.service.SortAlgo;

/**
 * 插入排序
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2023/5/15 15:11
 */
public class MyInsertionSort implements SortAlgo {

    @Override
    public void sort(long[] arr, int length) {
//        sortWithoutSentinel(arr, length);
        sortWithSentinel(arr, length);
    }

    /**
     * 哨兵模式的插入排序
     * @param arr  待排序数组
     * @param length   排序长度
     * @author Rickshaw
     * @since 2023/5/30 14:11
     */
    private void sortWithSentinel(long[] arr, int length) {
        if (length < 2) {
            return;
        }
        //找出最小值，与arr[0]交换，作为哨兵元素，方便最后还原
        int minIndex = this.getMinIndex(arr, length);
        this.swap(arr, minIndex, 0);
        long min = arr[0];
        //arr[0]作为哨兵，暂存待排序元素
        for (int i = 2; i < length; i++) {
            arr[0] = arr[i];
            //j是有序区的尾部index
            int j = i - 1;
            //此处哨兵省略了j >= 0的判断，因为j = 0，arr[j] == arr[0]就会跳出循环
            while (arr[j] > arr[0]) {
                //依次往后挪位置
                arr[j + 1] = arr[j];
                j--;
            }
            //插入待排序元素
            arr[j + 1] = arr[0];
        }
        //移除哨兵元素，把原始元素还原
        arr[0] = min;
    }

    /**
     * 交换数组的两个值
     * @param arr 数组
     * @param i 要交换的索引
     * @param j  要交换的索引
     * @author Rickshaw
     * @since 2023/6/1 14:27
     */
    private void swap(long[] arr, int i, int j) {
        if (arr[i] == arr[j]) {
            return;
        }
        long temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 获取数组中最小值的index
     * @param arr 数组
     * @param length 排序长度
     * @return int
     * @author Rickshaw
     * @since 2023/6/1 14:21
     */
    private int getMinIndex(long[] arr, int length) {
        int minIndex = 0;
        for (int i = 1; i < length; i++) {
            if (arr[minIndex] > arr[i]) {
                minIndex = i;
            }
        }
        return minIndex;
    }

    /**
     * 非哨兵模式的插入排序
     * @param arr  待排序数组
     * @param length    排序长度
     * @author Rickshaw
     * @since 2023/5/30 14:12
     */
    private void sortWithoutSentinel(int[] arr, int length) {
        if (length <= 1) {
            return;
        }
        //i: 未排序序号，首次arr[0]作为已排序区
        for (int i = 1; i < length; i++) {
            //未排序区中要排序的值
            int value = arr[i];
            //j: 已排序序号
            int j = i - 1;
            for (; j >= 0; j--) {
                if (arr[j] <= value) {
                    break;
                }
                arr[j + 1] = arr[j];
            }
            //因为判断j >= 0的时候j--了，所以要补1
            arr[j + 1] = value;
        }
    }

    @Override
    public void sort(long[] arr) {
        this.sort(arr, arr.length);
    }

    public static void main(String[] args) {
        long[] arr = {7, 5, 4, 1, 2, 6};
        SortAlgo bubbleSort = new MyInsertionSort();
        bubbleSort.sort(arr);
        bubbleSort.printArr(arr);
    }
}
