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
    public void sort(long[] arr) {
        this.sort(arr, arr.length);
    }

    @Override
    public void sort(long[] arr, int length) {
        if (length <= 1) {
            return;
        }
        // 用于交换两个变量
        long temp;
        for (int i = 0; i < length - 1; i++) {
            // 假设本次冒泡没有出现元素交换
            boolean exchange = false;
            for (int j = 0; j < length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    exchange = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            // 如果某次冒泡没有元素交换，则证明排序提前完成，返回
            if (!exchange) {
                return;
            }
        }
    }


    public static void main(String[] args) {
        long[] arr = {1, 5, 4, 7, 2, 6};
        SortAlgo bubbleSort = new MyBubbleSort();
        bubbleSort.sort(arr);
        bubbleSort.printArr(arr);
    }
}
