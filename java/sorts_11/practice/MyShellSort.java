package Stack.java.sorts_11.practice;

import Stack.java.service.SortAlgo;

/**
 * 希尔排序（对插入排序优化）
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2023/5/15 15:16
 */
public class MyShellSort implements SortAlgo {

    @Override
    public void sort(long[] arr, int length) {
        if (length <= 1) {
            return;
        }
        //初始步长为数组长度的一半，而且每次为原来的一半；直到step == 1，直接对整一个数组进行插入排序
        for (int step = length / 2; step > 0; step /= 2) {
            //从第step元素，对其所在组的元素进行插入排序
            for (int i = step; i < length; i++) {
                int j = i;
                //待排序元素
                long value = arr[j];
                //如果比组内前一个元素小，则进行交换
                while (j - step >= 0 && value < arr[j - step]) {
                    //组内插入排序
                    arr[j] = arr[j - step];
                    j -= step;
                }
                arr[j] = value;
            }
        }
    }

    @Override
    public void sort(long[] arr) {
        this.sort(arr, arr.length);
    }

    public static void main(String[] args) {
        long[] arr = {1, 5, 4, 7, 2, 6};
        SortAlgo bubbleSort = new MyShellSort();
        bubbleSort.sort(arr);
        bubbleSort.printArr(arr);
    }
}
