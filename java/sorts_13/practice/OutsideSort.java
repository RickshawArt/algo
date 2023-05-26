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
        for (int i = 0; i < right; i++) {
            if (arr[i] < maxCapital) {
                swap(arr, i, right);
                right--;
            } else if (arr[i] > maxCapital) {
                swap(arr, i, left);
                left++;
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
        char[] arr = "D1a4FB2cb0z".toCharArray();
        caseNumPartition(arr);
        for (char c : arr) {
            System.out.print(c);
        }
    }

}
