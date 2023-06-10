package Stack.java.search_16;

/**
 * 二分查找（下）
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2023/6/10 12:56
 */
public class MyBinarySearch2 {

    /**
     * 变体一：查找第一个值等于给定值的元素
     * @param arr 有序数组
     * @param length 有序数组长度
     * @param val    要查找的元素
     * @return int
     * @author Rickshaw
     * @since 2023/6/10 14:32
     */
    public int findFirst(int[]arr, int length, int val) {
        int low = 0, high = length - 1;
        while (low <= high) {
            int mid = low + (high - low >> 1);
            if (arr[mid] < val) {
                low = mid + 1;
            } else if (arr[mid] > val) {
                high = mid - 1;
            } else {
                //当mid == 0，或者arr[mid - 1] != val，即arr[mid]为第一个元素
                if (mid == 0 || arr[mid - 1] != val) {
                    return mid;
                } else {
                    //不是第一个元素，就继续缩减区间
                    high = mid - 1;
                }
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        MyBinarySearch2 search2 = new MyBinarySearch2();
        int[] arr = {1, 3, 4, 5, 6, 8, 8, 8, 11, 18};
        System.out.println("search2.findFirst(arr, arr.length, 8) = "
                + search2.findFirst(arr, arr.length, 8));
    }

}
