package Stack.java.sorts_11.practice;

import Stack.java.service.SortAlgo;

/**
 * 冒泡排序
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2023/5/15 15:05
 */
public class MyBubbleSort implements SortAlgo {
    @Override
    public void sort(int[] arr) {
        this.sort(arr, arr.length);
    }

    @Override
    public void sort(int[] arr, int length) {
        if (length <= 1) {
            return;
        }
        //用于交换变量
        int exchange = 0;
        for (int i = 0; i < length - 1; i++) {
            //如果下一次没有进行比较交换逻辑，则证明已排序好
            boolean isSorted = true;
            for (int j = 0; j < length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    exchange = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = exchange;
                    isSorted = false;
                }
            }
            if (isSorted) {
                return;
            }
        }
    }


    public static void main(String[] args) {
        int[] arr = {1, 5, 4, 7, 2, 6};
        SortAlgo bubbleSort = new MyBubbleSort();
        bubbleSort.sort(arr);
        bubbleSort.printArr(arr);
    }
}
