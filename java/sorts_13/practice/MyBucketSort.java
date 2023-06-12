package Stack.java.sorts_13.practice;

import Stack.java.service.SortAlgo;
import Stack.java.sorts_12.practice.MyMergeSort;

/**
 * 桶排序
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2023/5/15 14:38
 */
public class MyBucketSort implements SortAlgo {

    /**
     * 桶内排序算法
     */
    private final SortAlgo sortAlgo;

    public MyBucketSort(SortAlgo sortAlgo) {
        this.sortAlgo = sortAlgo;
    }

    @Override
    public void sort(long[] arr, int length) {
        if (length < 2) {
            return;
        }
        long min = arr[0], max = arr[1];
        for (long j : arr) {
            if (j > max) {
                max = j;
            } else if (j < min) {
                min = j;
            }
        }
        //桶的容量
        //（由于桶的数量越接近n，数据均匀分布，每个桶的元素数量相对平衡，桶排序的时间复杂度越接近O(n)，所以要除以length）
        int bucketCapacity = Math.toIntExact((max - min) / length);
        //桶的数量
        //（+ 1是因为(arr[i] - min) / bucketCapacity）索引桶的时候，最大值不会超出数组下标，因为下标从0开始
        int bucketCount = Math.toIntExact((max - min) / bucketCapacity + 1);
        //一维为桶的数量，二维展开为每个桶的容量（存放的数据）
        long[][] buckets = new long[bucketCount][bucketCapacity];
        //长度为桶数量的索引数组，每个位置记录二维度数组的索引
        int[] indexArr = new int[bucketCount];

        //将数组中值分配到各个桶里
        for (long j : arr) {
            //判断放入哪个桶
            int bucketIndex = Math.toIntExact((j - min) / bucketCapacity);
            //判断是否需要扩容
            if (indexArr[bucketIndex] == buckets[bucketIndex].length) {
                ensureCapacity(buckets, bucketIndex);
            }
            buckets[bucketIndex][indexArr[bucketIndex]++] = j;
        }
        //对每个桶进行排序，放回到原数组
        int k = 0;
        for (int i = 0; i < buckets.length; i++) {
            //桶里面没有数据
            if (indexArr[i] == 0) {
                continue;
            }
            //使用传入的排序算法进行排序
            sortAlgo.sort(buckets[i], indexArr[i]);
            for (int j = 0; j < indexArr[i]; j++) {
                arr[k++] = buckets[i][j];
            }
        }
    }

    @Override
    public void sort(long[] arr) {
        this.sort(arr, arr.length);
    }

    /**
     * 扩容二维度数组的容量
     * @param buckets   二维数组
     * @param bucketIndex   桶的索引
     * @author Rickshaw
     * @since 2023/5/17 14:34
     */
    private void ensureCapacity(long[][] buckets, int bucketIndex) {
        long[] newArr = new long[buckets[bucketIndex].length * 2];
        System.arraycopy(buckets[bucketIndex], 0, newArr, 0, buckets[bucketIndex].length);
        buckets[bucketIndex] = newArr;
    }

    public static void main(String[] args) {
        SortAlgo bucketSort = new MyBucketSort(new MyMergeSort());
        long[] arr = {22, 5, 11, 41, 45, 26, 29, 10, 7, 8, 30, 27, 42, 43, 40, 7};
        bucketSort.sort(arr);
        bucketSort.printArr(arr);
    }
}
