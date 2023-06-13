package Stack.java.search_16;

/**
 * 旋转有序数组的二分查找
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2023/6/13 14:31
 */
public class RotateSortedArray {

    /**
     * 旋转有序数组的二分查找，并返回数组索引
     * （旋转点：普通有序数组的首位元素）
     * @param arr 旋转有序数组
     * @param val 要查找的元素
     * @return int
     * @author Rickshaw
     * @since 2023/6/13 14:34
     */
    public int binarySearch(long[] arr, long val) {
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low >> 1);
            if (arr[mid] == val) {
                return mid;
            }

            /*
                arr[mid] >= arr[low]，为什么这里加等号
                1、arr[mid] == arr[low]的情况分两种
                    （1）mid == low，旋转点在右侧
                    （2）存在重复元素，旋转点也在右侧
                    arr旋转点在mid右侧的情况，都必须进入arr[mid] > arr[low]的分支，
                    也就是左半边有序区间，所以要加上等号
                2、如果不加等号，mid在区间只有两个数的时候一定是0，那么a[0]<mid]一定不成立，这时，
                   在else的条件下，右指针会向左偏移一位，从而失去 第二个数 ，导致无法被查询。
                   比如[2,1]，target = 1，可以查询得到2，但是查不到1
             */
            if (arr[mid] >= arr[low]) {
                //旋转点在mid的右侧，左半边[low, mid]是有序区间
                if (val >= arr[low] && val < arr[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                //旋转点在mid的左侧，右半边[mid, high]是有序区间
                if (val > arr[mid] && val <= arr[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        RotateSortedArray rotateSortedArray = new RotateSortedArray();
//        long[] arr = {5, 6, 1, 2, 3, 4};
        long[] arr = {2, 1};
        int index = rotateSortedArray.binarySearch(arr, 1);
        System.out.println("index = " + index);
    }

}
