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
    public void sort(int[] arr) {
        this.sort(arr, arr.length);
    }

    @Override
    public void sort(int[] arr, int length) {
        if (length < 2) {
            return;
        }

    }
}
