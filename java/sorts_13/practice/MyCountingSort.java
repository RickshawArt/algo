package Stack.java.sorts_13.practice;

import Stack.java.service.SortAlgo;

/**
 * 计数排序
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2023/5/25 10:21
 */
public class MyCountingSort implements SortAlgo {


    @Override
    public void sort(int[] arr) {
        this.sort(arr, arr.length);
    }

    @Override
    public void sort(int[] arr, int length) {
        //校验长度
        if (length < 2) {
            return;
        }
        //找出数据里面的最大值
        int max = arr[0];
        for (int i = 1; i < length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        //声明一个计数数组index
        int[] countArr = new int[max + 1];
        //把arr的数据在计数数组countArr对应索引处计数
        for (int i = 0; i < length; i++) {
            countArr[arr[i]]++;
        }
        //将countArr计数数组的前一个索引与后一个索引依次累加
        for (int i = 1; i < countArr.length; i++) {
            countArr[i] = countArr[i - 1] + countArr[i];
        }
        //从arr倒序遍历，在countArr计数数组对应的索引位，取出进行-1，
        //就是排序后数组的位置，直到arr遍历完
        int[] tempArr = new int[length];
        for (int i = length - 1; i >= 0; i--) {
            int index = --countArr[arr[i]];
            tempArr[index] = arr[i];
        }
        //将排序好的临时数组拷贝到原数组
        System.arraycopy(tempArr, 0, arr, 0, length);
    }

    public static void main(String[] args) {
        SortAlgo countingSort = new MyCountingSort();
        int[] arr = {2, 5, 3, 0, 2, 3, 0, 3};
        countingSort.sort(arr);
        countingSort.printArr(arr);
    }


}
