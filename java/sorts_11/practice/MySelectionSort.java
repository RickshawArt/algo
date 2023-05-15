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
    public void sort(int[] arr) {
        int length = arr.length;
        if (length <= 1) {
            return;
        }
        //未排序区遍历找到的最小值
        int minIndex;
        //用于交换数据的变量
        int exchange;
        //i: 已排序区序号
        for (int i = 0; i < length - 1; i++) {
            minIndex = i;
            //j: 待排序区序号
            for (int j = i + 1; j < length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            //如果当前位置就是最小值，无需进行交换
            if (minIndex != i) {
                exchange = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = exchange;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 5, 4, 7, 2, 6};
        SortAlgo bubbleSort = new MySelectionSort();
        bubbleSort.sort(arr);
        bubbleSort.printArr(arr);
    }
}
