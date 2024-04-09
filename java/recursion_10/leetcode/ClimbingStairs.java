package Stack.java.recursion_10.leetcode;

/**
 * Climbing Stairs（爬楼梯）
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2024/4/9 14:17
 */
public class ClimbingStairs {

    /**
     * 本问题其实常规解法可以分成多个子问题，爬第n阶楼梯的方法数量，等于 2 部分之和
     * <p>
     * 爬上 n−1 阶楼梯的方法数量。因为再爬1阶就能到第n阶 爬上 n−2 阶楼梯的方法数量，因为再爬2阶就能到第n阶
     * 这种说法可能会导致初学者进入思维误区，让初学者钻牛角尖。 可以适当改成： 爬第n阶楼梯的方法数量，等于 2 部分之和： 1、先爬一步，加上后面的n-1阶楼梯爬法 2、先爬两步，加上后面的n-2阶楼梯爬法。
     * <p>
     * 虽然上面两种解释都是一个意思，但是下面这种说法更能让人理解。
     *
     * @param n 阶梯
     * @return int
     * @author Rickshaw
     * @since 2024/4/9 15:50
     */
    public int recursivelyExecute(int n) {
        if (n <= 2) {
            return n;
        }
        return this.recursivelyExecute(n - 1) + this.recursivelyExecute(n - 2);
    }

    /**
     * 本问题其实常规解法可以分成多个子问题，爬第n阶楼梯的方法数量，等于 2 部分之和
     * <p>
     * 爬上 n−1 阶楼梯的方法数量。因为再爬1阶就能到第n阶 爬上 n−2 阶楼梯的方法数量，因为再爬2阶就能到第n阶
     * 这种说法可能会导致初学者进入思维误区，让初学者钻牛角尖。 可以适当改成： 爬第n阶楼梯的方法数量，等于 2 部分之和： 1、先爬一步，加上后面的n-1阶楼梯爬法 2、先爬两步，加上后面的n-2阶楼梯爬法。
     * <p>
     * 虽然上面两种解释都是一个意思，但是下面这种说法更能让人理解。
     *
     * @param n 阶梯
     * @return int
     * @author Rickshaw
     * @since 2024/4/9 15:50
     */
    public int execute(int n) {
        if (n <= 2) {
            return n;
        }
        // f(1) = 1, f(2) = 2
        int prev = 1, next = 2, ret = 0;
        for (int i = 3; i <= n; i++) {
            // f(n) = f(n - 1) + f(n - 2), 此处反过来，从小到大
            ret = prev + next;
            prev = next;
            next = ret;
        }
        return ret;
    }

    public static void main(String[] args) {
        ClimbingStairs climbingStairs = new ClimbingStairs();
        int recursivelyExecute = climbingStairs.recursivelyExecute(6);
        System.out.println("recursivelyExecute = " + recursivelyExecute);
        int execute = climbingStairs.execute(6);
        System.out.println("execute = " + execute);
    }

}
