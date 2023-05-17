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
    public void sort(int[] arr, int length) {
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
    public void sort(int[] arr) {
        this.sort(arr, arr.length);
    }

    public static void main(String[] args) {
        int[] arr = {1, 5, 4, 7, 2, 6};
        SortAlgo bubbleSort = new MyInsertionSort();
        bubbleSort.sort(arr);
        bubbleSort.printArr(arr);
    }
}
