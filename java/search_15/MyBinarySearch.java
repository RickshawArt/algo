package Stack.java.search_15;

/**
 * 二分查找
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2023/6/1 15:55
 */
public class MyBinarySearch {

    /**
     * 简单的二分查找
     * @param arr 有序数组
     * @param length 数组长度
     * @param value 要查找的值
     * @return int  找到元素的index
     * @author Rickshaw
     * @since 2023/6/1 15:58
     */
    private int simpleSearch(int[] arr, int length, int value) {
        int low = 0, high = length - 1;
        //数组中点
        int mid;
        //要包含 = ，因为 = 是只剩一个元素，如果还不是要找的value，才能说明找不到
        while (low <= high) {
            //根据中点每次缩窄区间，知道区间剩一个元素
            mid = low + ((high - low) >> 1);
            if (arr[mid] > value) {
                high = mid - 1;
            } else if (arr[mid] < value) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 二分查找的递归实现
     * @param arr   有序数组
     * @param low   数组startIndex
     * @param high  数组endIndex
     * @param value 要查找的元素
     * @return int
     * @author Rickshaw
     * @since 2023/6/1 17:14
     */
    private int searchRecursion(int[] arr, int low, int high, int value) {
        //二分查找的结束条件，1、找不到返回-1；2、找到返回对应的index
        if (low > high) {
            return -1;
        }
        int mid = low + ((high - low) >> 1);
        if (arr[mid] > value) {
            return searchRecursion(arr, low, mid - 1, value);
        } else if (arr[mid] < value) {
            return searchRecursion(arr, mid + 1, high, value);
        } else {
            return mid;
        }
    }

    public static void main(String[] args) {
        MyBinarySearch binarySearch = new MyBinarySearch();
        int[] arr = {8, 11, 19, 23, 27, 33, 45, 55, 67, 98};
//        int index = binarySearch.simpleSearch(arr, arr.length, 45);
        int index = binarySearch.searchRecursion(arr, 0, arr.length - 1, 57);
        System.out.println("index = " + index);
    }

}
