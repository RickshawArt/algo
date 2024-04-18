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
    public void sort(long[] arr, int length) {
        if (length <= 1) {
            return;
        }
        this.sortRecursion(arr, 0, length - 1);
    }

    @Override
    public void sort(long[] arr) {
        this.sort(arr, arr.length);
    }

    /**
     * 递归分解直到不可再分，再重新有序合并
     * @param arr   待排序数组
     * @param left  左区间
     * @param right 右区间
     * @author Rickshaw
     * @since 2023/5/10 9:19
     */
    private void sortRecursion(long[] arr, int left, int right) {
        //递归结束的条件，区间不可再分（区间只剩一个元素）
        if (left >= right) {
            return;
        }
        //增量方式取中点
        int mid = left + (right - left >> 1);
        //一分二，二分四...
        this.sortRecursion(arr, left, mid);
        this.sortRecursion(arr, mid + 1, right);
        this.merge(arr, left, mid, right);
//        this.sentinelMerge(arr, left, mid, right);
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
    private void sentinelMerge(long[] arr, int left, int mid, int right) {
        // 由于索引是从0开始的，所以先+1，然后为哨兵留个位置，再+1
        long[] leftArr = new long[mid + 1 - left + 1];
        for (int i = 0; i < leftArr.length - 1; i++) {
            leftArr[i] = arr[left + i];
        }
        leftArr[leftArr.length - 1] = Long.MAX_VALUE;
        long[] rightArr = new long[right + 1 - mid];
        for (int i = 0; i < rightArr.length - 1; i++) {
            rightArr[i] = arr[mid + 1 + i];
        }
        rightArr[rightArr.length - 1] = Long.MAX_VALUE;
        // 注意此处k从left开始，并不是从0开始
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
    private void merge(long[] arr, int left, int mid, int right) {
        // 申请一个临时新数组存放合并的结果，后续再copy到arr
        long[] newArr = new long[right - left + 1];
        // i：左有序数组指针；j：右有序数组指针；k：newArr指针
        int i = left;
        int j = mid + 1;
        int k = 0;
        // 先迁移一部分数据，直到其中一个有序数组的数据被全部迁移
        for (; i <= mid && j <= right; k++) {
            // 等于的情况取left区间的先放进去，保证稳定排序
            if (arr[i] <= arr[j]) {
                newArr[k] = arr[i++];
            } else {
                newArr[k] = arr[j++];
            }
        }
        // 获取剩余数据的起止索引
        int start = i, end = mid;
        if (j <= right) {
            start = j;
            end = right;
        }
        // 把剩余数据放进新数组后面
        while (start <= end) {
            newArr[k++] = arr[start++];
        }
        // 把新数组合并后的有序数组替换原数组arr对应位置的数据
        for (int l = 0; l < newArr.length; l++) {
            arr[left + l] = newArr[l];
        }
    }

    public static void main(String[] args) {
        SortAlgo myMergeSort = new MyMergeSort();
        long[] arr = {1, 5, 4, 7, 2, 6};
//        arr = new long[]{2, 4, 6, 1, 5, 7};
//        MyMergeSort myMergeSort1 = new MyMergeSort();
//        myMergeSort1.sentinelMerge(arr, 0, 2, 5);
//        myMergeSort1.merge(arr, 1, 2, 5);
        myMergeSort.sort(arr);
        myMergeSort.printArr(arr);
    }

}
