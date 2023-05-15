package Stack.java.sorts_12.practice;

import Stack.java.service.SortAlgo;

/**
 * 归并排序
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2023/5/10 9:09
 */
public class MyMergeSort implements SortAlgo {
    @Override
    public void sort(int[] arr) {
        int length = arr.length;
        if (length <= 1) {
            return;
        }
        sortRecursion(arr, 0, length - 1);
    }

    /**
     * 递归分解直到不可再分，再重新有序合并
     * @param arr   待排序数组
     * @param left  左区间
     * @param right 右区间
     * @author Rickshaw
     * @since 2023/5/10 9:19
     */
    private void sortRecursion(int[] arr, int left, int right) {
        //递归结束的条件，区间不可再分（区间只剩一个元素）
        if (left >= right) {
            return;
        }
        //增量方式取中点
        int mid = left + (right - left >> 1);
        //一分二，二分四...
        sortRecursion(arr, left, mid);
        sortRecursion(arr, mid + 1, right);
//        merge(arr, left, mid, right);
        sentinelMerge(arr, left, mid, right);
    }


    /**
     * 通过两个哨兵节点，合并两个有序数组
     * @param arr   待排序的数组
     * @param left  左索引
     * @param mid   中点索引
     * @param right 右索引
     * @author Rickshaw
     * @since 2023/5/10 10:11
     */
    private void sentinelMerge(int[] arr, int left, int mid, int right) {
        //因为索引从0开始，而且还要预留一个哨兵节点，所以是+2
        int[] leftArr = new int[mid - left + 2];
        for (int i = 0; i < mid - left + 1; i++) {
            leftArr[i] = arr[left + i];
        }
        leftArr[mid - left + 1] = Integer.MAX_VALUE;

        int[] rightArr = new int[right - mid + 1];
        for (int i = 0; i < right - mid; i++) {
            rightArr[i] = arr[mid + 1 + i];
        }
        rightArr[right - mid] = Integer.MAX_VALUE;

        for (int i = 0, j = 0, k = left; k < right + 1; k++) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i++];
            } else {
                arr[k] = rightArr[j++];
            }
        }
    }

    /**
     * 合并两个有序数组
     * @param arr   待排序的数组
     * @param left  左索引
     * @param mid   中点索引
     * @param right 右索引
     * @author Rickshaw
     * @since 2023/5/10 10:11
     */
    private void merge(int[] arr, int left, int mid, int right) {
        //申请一个临时数组存放合并的结果，后续再copy到arr
        int[] temp = new int[right - left + 1];
        //i：左有序数组指针；j：右有序数组指针；k：temp指针
        int i = left;
        int j = mid + 1;
        int k = 0;
        for (; i <= mid && j <= right; k++) {
            //等于的情况取left区间的先放进去，保持稳定
            if (arr[i] <= arr[j]) {
                temp[k] = arr[i++];
            } else {
                temp[k] = arr[j++];
            }
        }
        //获取剩余子数组start, end索引
        int start = i;
        int end = mid;
        if (j <= right) {
            start = j;
            end = right;
        }

        //把剩余子数组的数据接在temp后面
        while (start <= end) {
            temp[k++] = arr[start++];
        }

        //把temp合并后的有序数组替换arr对应位置的数据
        for (int l = 0; l < temp.length; l++) {
            arr[left + l] = temp[l];
        }

    }

    public static void main(String[] args) {
        SortAlgo myMergeSort = new MyMergeSort();
        int[] arr = {1, 5, 4, 7, 2, 6};
        myMergeSort.sort(arr);
        myMergeSort.printArr(arr);
    }

}
