package Stack.java.search_16;

/**
 * 二分查找（下）
 * 技巧：逐步逼近直到边界（条件不成立），则证明找到了该元素
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
        int low = 0, high = length - 1, mid;
        while (low <= high) {
            mid = low + (high - low >> 1);
            if (arr[mid] < val) {
                low = mid + 1;
            } else if (arr[mid] > val) {
                high = mid - 1;
            } else {
                //当mid == 0，或者arr[mid - 1] != val，即arr[mid]为第一个元素
                if (mid == 0 || arr[mid - 1] != val) {
                    return mid;
                }
                //不是第一个元素，就继续缩减区间
                high = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 变体二：查找最后一个值等于给定值的元素
     * @param arr 有序数组
     * @param length 有序数组长度
     * @param val    要查找的元素
     * @return int
     * @author Rickshaw
     * @since 2023/6/12 10:14
     */
    public int findLast(int[]arr, int length, int val) {
        int low = 0, high = length - 1, mid;
        while (low <= high) {
            mid = low + (high - low >> 1);
            if (arr[mid] < val) {
                low = mid + 1;
            } else if (arr[mid] > val) {
                high = mid - 1;
            } else {
                //当mid == length - 1，或者arr[mid + 1] != val，即arr[mid]为最后一个元素
                if (mid == length - 1 || arr[mid + 1] != val) {
                    return mid;
                }
                //不是第一个元素，就继续缩减区间
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 变体三：查找第一个大于等于给定值的元素
     * @param arr  有序数组
     * @param length  有序数组长度
     * @param val  要查找的元素
     * @return int
     * @author Rickshaw
     * @since 2023/6/12 10:56
     */
    public int findGreaterEqual(int[] arr, int length, int val) {
        int low = 0, high = length - 1, mid;
        while (low <= high) {
            mid = low + (high - low >> 1);
            if (arr[mid] >= val) {
                //如果mid == 0, 前一个元素小于val，证明找到了，否则压缩区间
                if (mid == 0 || arr[mid - 1] < val) {
                    return mid;
                }
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 变体四：查找最后一个小于等于给定值的元素
     * @param arr   有序数组
     * @param length    有序数组长度
     * @param val   要查找的元素
     * @return int
     * @author Rickshaw
     * @since 2023/6/12 11:42
     */
    public int findLessEqual(int[] arr, int length, int val) {
        int low = 0, high = length - 1, mid;
        while (low <= high) {
            mid = low + (high - low >> 1);
            //arr[mid] < val 走 low = mid + 1
            if (arr[mid] <= val) {
                //arr[mid] = val, 如果arr[mid]为最后一个元素，或者下一个元素大于val，则证明找到了，不然继续压缩区间，二分
                if (mid == length - 1 || arr[mid + 1] > val) {
                    return mid;
                }
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        MyBinarySearch2 search2 = new MyBinarySearch2();
        int[] arr = {1, 3, 4, 5, 6, 8, 8, 8, 11, 18};
        int firstIndex = search2.findFirst(arr, arr.length, 1);
        int lastIndex = search2.findLast(arr, arr.length, 1);
        int greaterEqualIndex = search2.findGreaterEqual(arr, arr.length, 11);
        int lessEqualIndex = search2.findLessEqual(arr, arr.length, 7);
        System.out.println("firstIndex = " + firstIndex);
        System.out.println("lastIndex = " + lastIndex);
        System.out.println("greaterEqualIndex = " + greaterEqualIndex);
        System.out.println("lessEqualIndex = " + lessEqualIndex);
    }

}
