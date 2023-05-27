package Stack.java.sorts_13.practice;

/**
 * 区域外有序，区域内无序
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2023/5/26 14:24
 */
public class OutsideSort {

    /**
     * 大小写字母分区，区内无序
     * @param arr   待分区数组
     * @author Rickshaw
     * @since 2023/5/26 14:32
     */
    public static void casePartition(char[] arr) {
        int maxCapital = 90;
        //小写的放left，大写的放right
        int left = 0, right = arr.length - 1;
        int i = 0;
        while (i < right) {
            if (arr[i] < maxCapital) {
                swap(arr, i, right);
                right--;
            } else if (arr[i] > maxCapital) {
                swap(arr, i, left);
                left++;
                i++;
            }
        }
    }

    /**
     * 大小写字母，数字分区，区内无序（小写，数字，大写）
     * @param arr   待分区数组
     * @author Rickshaw
     * @since 2023/5/26 14:32
     */
    public static void caseNumPartition(char[] arr) {
        //使用三路快排思路实现
        //[0, left) -> 存放小写字母
        //[left, i) -> 存放数字
        //[right, arr.length -1] -> 存放大写字母
        int maxNum = 57;
        int maxCapital = 90;
//        int maxLowerCase = 122;
        //left：小写字母的最右边界，right：大写字母的最左边界
        int left = 0, right = arr.length - 1;
        //遍历指针，数字的最右开边界
        int i = 0;
        while (i <= right) {
            if (arr[i] < maxNum) {
                i++;
            } else if (arr[i] <= maxCapital) {
                //因为交换后的arr[i]不清楚是什么范围，所以i不能++，需要再此对新的arr[i]进行判断
                swap(arr, i, right);
                right--;
            } else {
                /*
                1、i从左往右开始扫描，如果扫到大写字母都被放到right去了，
                   所以i扫到小写字母，必然是小写字母和小写字母的交换和小写字母与数字的交换，所以i和left都要++
                2、因为[left, i) -> 存放数字，意味着i >= left，所以left++，i也要++
                */
                swap(arr, i, left);
                left++;
                i++;
            }
        }
    }

    /**
     * 交换数组中的两个数据
     * @param arr   数组
     * @param i 要交换的数组索引
     * @param j 要交换的数组索引
     * @author Rickshaw
     * @since 2023/5/26 15:56
     */
    private static void swap(char[] arr, int i, int j) {
        if (arr[i] == arr[j]) {
            return;
        }
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }



    public static void main(String[] args) {
        char[] arr = ("t1a4FB2cb0Z").toCharArray();
//        arr = "GqwUIaYlKp".toCharArray();
//        casePartition(arr);
        caseNumPartition(arr);
        for (char c : arr) {
            System.out.print(c);
        }
    }

}
