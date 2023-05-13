package Stack.java.sorts_12.practice;

/**
 * O(n) 时间复杂度内求无序数组中的第 K 大元素
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2023/5/12 11:17
 */
public class MyKthElement {

    /**
     * 从无序数组中找到第 K 大元素
     * @param arr  无序数组
     * @param k 第 k 大的索引
     * @return int
     * @author Rickshaw
     * @since 2023/5/12 11:20
     */
    public int getKthElement(int[] arr, int k) {
        int length = arr.length;
        if (k < 1 || length < k) {
            throw new IllegalArgumentException("The k must be greater than or equal to 1 and " +
                    "less than or equal to the array length");
        }
        return partition(arr, 0, length - 1, k);
    }

    /**
     * 递归分区，每次缩小分区区间，直到 i + 1 = k返回
     * @param arr   待分区的数组
     * @param left  左区间
     * @param right 右区间
     * @param k 第 k 大的索引
     * @return int
     * @author Rickshaw
     * @since 2023/5/12 14:20
     */
    private int partition(int[] arr, int left, int right, int k) {
        int pivot = arr[right];
        int i = left, j = left;
        for (; j < right; j++) {
            if (arr[j] >= pivot) {
                this.swap(arr, i, j);
                i++;
            }
        }
        this.swap(arr, i, j);
        if (i + 1 > k) {
            //这两处递归调用要return，才能保证找到结果得时候层层往上返回；
            //不然找到结果返回上一层的话会继续往下执行代码，58行代码被多次执行，得到错误结果
            return partition(arr, left, i - 1, k);
        } else if (i + 1 < k) {
            //以i为分区点，左侧都比i大，右侧都比i小；
            //数组下标从0开始，所以i + 1就是第i + 1大的元素，得出i + 1 与 k的关系
            return partition(arr, i + 1, right, k);
        }
        //i + 1 == k就是结果，直接层层往上返回
        return arr[i];
    }

    /**
     * 交换数组的两个数
     * @param arr  数组
     * @param i  要交换索引
     * @param j  要交换索引
     * @author Rickshaw
     * @since 2023/5/12 10:46
     */
    private void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    public static void main(String[] args) {
        MyKthElement myKthElement = new MyKthElement();
        int[] arr = {4, 2, 5, 12, 3};
        int kthElement = myKthElement.getKthElement(arr, 5);
        System.out.println("kthElement = " + kthElement);
    }

}
