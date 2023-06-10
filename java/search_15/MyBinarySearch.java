package Stack.java.search_15;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 二分查找（上）
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

    /**
     * 求一个数的平方根
     * @param cal 计算数
     * @param precision 精度
     * @return double
     * @author Rickshaw
     * @since 2023/6/6 20:36
     */
    public BigDecimal sqrt(int cal, int precision) {
        if (cal < 0) {
            throw new IllegalArgumentException("cal must be greater than or equal to 0");
        }
        //直接去掉多余的小数点
        BigDecimal operand = BigDecimal.valueOf(cal).setScale(6, RoundingMode.DOWN);
        if (cal == 0 || cal == 1) {
            return operand;
        }
        BigDecimal low = BigDecimal.valueOf(0);
        BigDecimal high = operand;
        BigDecimal mid = null;
        BigDecimal gap = BigDecimal.valueOf(0.000001);
        while (high.compareTo(low) >= 0) {
            mid = low.add(high.subtract(low).divide(BigDecimal.valueOf(2), 6, RoundingMode.DOWN));
            //使用除法运算，防止溢出
            BigDecimal midCompare = operand.divide(mid, 6, RoundingMode.DOWN);
            boolean isInRange = mid.subtract(midCompare).abs().compareTo(gap) <= 0;
            //如果中点相等，或者误差 <= 0.000001，都证明找到了mid
            if (mid.equals(midCompare) || isInRange) {
                break;
            } else if (midCompare.compareTo(mid) > 0) {
                low = mid;
            } else {
                high = mid;
            }
        }
        assert mid != null;
        return mid;
    }

    public static void main(String[] args) {
        MyBinarySearch binarySearch = new MyBinarySearch();
        int[] arr = {8, 11, 19, 23, 27, 33, 45, 55, 67, 98};
//        int index = binarySearch.simpleSearch(arr, arr.length, 45);
        int index = binarySearch.searchRecursion(arr, 0, arr.length - 1, 57);
        System.out.println("index = " + index);
        BigDecimal sqrt = binarySearch.sqrt(53891, 6);
        System.out.println("sqrt.doubleValue() = " + sqrt.doubleValue());
        System.out.println("sqrt.multiply(sqrt) = " + sqrt.multiply(sqrt));
    }

}
