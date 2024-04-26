package Stack.java.search_16.leetcode;

/**
 * x 的平方根
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2024/4/25 16:42
 */
public class Sqrt {

    /**
     * 二分查找
     * @param x 整型
     * @return int
     * @author Rickshaw
     * @since 2024/4/25 16:44
     */
    public int execute(int x) {
        int ret = -1;
        if (x < 0) {
            return ret;
        }
        if (x == 0 || x == 1) {
            return x;
        }
        int left = 1, right = x / 2, mid;
        while (left <= right) {
            mid = left + (right - left >> 1);
            if (x / mid >= mid) {
                //当mid * mid < x, mid有可能是要求结果，用变量暂存
                ret = mid;
                left = mid + 1;
            } else if (x / mid < mid) {
                right = mid - 1;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        Sqrt sqrt = new Sqrt();
        int execute = sqrt.execute(51);
        System.out.println("execute = " + execute);
    }

}
