package Stack.java.sorts_13.practice;

import Stack.java.service.SortAlgo;

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
    public void sort(int[] arr) {
        int length = arr.length;
        if (length < 2) {
            return;
        }
        int min = 0, max = 0;
        for (int j : arr) {
            if (j > max) {
                max = j;
            } else if (j < min) {
                min = j;
            }
        }
        //桶的容量
        int bucketCapacity = (max - min) / length;
        //桶的数量
        int bucketCount = (max - min) / bucketCapacity;
        //一维为桶的数量，二维展开为每个桶的容量（存放的数据）
        int[][] buckets = new int[bucketCount][bucketCapacity];
        //长度为桶数量的索引数组，每个位置记录二维的索引
        int[] indexArr = new int[bucketCount];

        //将数组中值分配到各个桶里
        for (int i = 0; i < length; i++) {
            //判断放入哪个桶
            int bucketIndex = (arr[i] - min) / bucketCapacity;
            //判断是否需要扩容
            if (indexArr[bucketIndex] == buckets[bucketIndex].length) {
                ensureCapacity(buckets, bucketIndex);
            }
        }
    }

    private void ensureCapacity(int[][] buckets, int bucketIndex) {
    }

    public static void main(String[] args) {

    }
}
