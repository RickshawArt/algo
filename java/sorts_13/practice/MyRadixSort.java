package Stack.java.sorts_13.practice;

import Stack.java.service.SortAlgo;

/**
 * 基数排序(使用计数排序对每个位数进行排序)
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2023/5/25 15:03
 */
public class MyRadixSort implements SortAlgo{

    @Override
    public void sort(long[] arr) {
        this.sort(arr, arr.length);
    }

    @Override
    public void sort(long[] arr, int length) {
        if (length < 2) {
            return;
        }
        //找出最大值
        long max = getMaxVal(arr, length);
        //exp代表每个位数，从低位到高位
        for (int exp = 1; max / exp > 0; exp *= 10) {
            //使用计数排序对每个位数进行排序
            countingSort(arr, length, exp);
        }
    }

    /**
     * 使用计数排序根据位数上的值进行排序
     * @param arr  待排序数组
     * @param exp  位数
     * @author Rickshaw
     * @since 2023/5/26 9:18
     */
    private void countingSort(long[] arr, int length, int exp) {
        //计数数组
        int[] countArr = new int[10];
        long[] tempArr = new long[length];
        //把每个位数取余10后放入计数数组计数
        for (int i = 0; i < length; i++) {
            int index = Math.toIntExact(arr[i] / exp % 10);
            countArr[index]++;
        }
        //把计数数组countArr的前一位和后一位累加
        for (int i = 1; i < countArr.length; i++) {
            countArr[i] += countArr[i - 1];
        }
        //将排序好的数据放入临时数组tempArr
        for (int i = length - 1; i >= 0; i--) {
            int index = Math.toIntExact(arr[i] / exp % 10);
            tempArr[--countArr[index]] = arr[i];
        }
        //把排序后的数据赋值回原数组
        System.arraycopy(tempArr, 0, arr, 0, length);
    }

    /**
     * 获取数组中的最大值
     * @param arr 数组
     * @param length   数组长度
     * @return int
     * @author Rickshaw
     * @since 2023/5/26 9:16
     */
    private long getMaxVal(long[] arr, int length) {
        long max = arr[0];
        for (int i = 1; i < length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        SortAlgo radixSort = new MyRadixSort();
        long[] arr = {170, 45, 75, 90, 802, 24, 2, 66};
        radixSort.sort(arr);
        radixSort.printArr(arr);
    }
}
