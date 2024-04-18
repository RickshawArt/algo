package Stack.java.sorts_11.practice;

import Stack.java.service.SortAlgo;

/**
 * 选择排序
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2023/5/15 15:14
 */
public class MySelectionSort implements SortAlgo {
    @Override
    public void sort(long[] arr, int length) {
        if (length <= 1) {
            return;
        }
        //未排序区遍历找到的最小值的索引
        int minIndex;
        //用于交换数据的变量
        long exchange;
        //i 为将要成为有序区的索引
        for (int i = 0; i < length - 1; i++) {
            minIndex = i;
            //用于交换数据
            //j 为无序区的索引，在无序区寻找最小值的索引
            for (int j = i + 1; j < length; j++) {
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }
            //把无序区的最小值放入有序区的尾部
            if (minIndex != i) {
                exchange = arr[minIndex];
                arr[minIndex] = arr[i];
                arr[i] = exchange;
            }
        }
    }

    @Override
    public void sort(long[] arr) {
        this.sort(arr, arr.length);
    }

    public static void main(String[] args) {
        long[] arr = {1, 5, 4, 7, 2, 6};
        SortAlgo bubbleSort = new MySelectionSort();
        bubbleSort.sort(arr);
        bubbleSort.printArr(arr);
    }
}
