package Stack.java.queue_09.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Sliding Window Maximum（滑动窗口最大值）
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2024/4/8 10:53
 */
public class SlidingWindowMaximum {

    /**
     * 1. 初始化： 双端队列 deque ，结果列表 res ，数组长度 n ；
     * 2. 滑动窗口： 左边界范围 i∈[1−k,n−k] ，右边界范围 j∈[0,n−1] ；
     *      a. 若 i>0 且 队首元素 deque[0] === 被删除元素 nums[i−1] ：则队首元素出队；
     *      b. 删除 deque 内所有 <nums[j] 的元素，以保持 deque 递减；
     *      c. 将 nums[j] 添加至 deque 尾部；
     *      d. 若已形成窗口（即 i≥0 ）：将窗口最大值（即队首元素 deque[0] ）添加至列表 res ；
     * 3. 返回值： 返回结果列表 res
     *
     * @param nums  整型数组
     * @param k     k 个滑动窗口
     * @return int[]
     * @author Rickshaw
     * @since 2024/4/8 14:13
     */
    public int[] execute(int[] nums, int k) {
        if (nums.length == 0 || k == 0) {
            return new int[0];
        }
        Deque<Integer> deque = new ArrayDeque<>();
        int[] res = new int[nums.length - k + 1];
        // 形成首个滑动窗口
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && nums[i] > deque.getFirst()) {
                deque.removeLast();
            }
            deque.addLast(nums[i]);
        }
        res[0] = deque.getFirst();
        // 开始滑动窗口
        for (int i = k; i < nums.length; i++) {
            int slideIn = nums[i];
            int slideOut = nums[i - k];
            // 如果最大值为滑出元素，则需要移除
            if (slideOut == deque.getFirst()) {
                deque.removeFirst();
            }
            // 保持双端队列的单调性，非严格递减
            while (!deque.isEmpty() && slideIn > deque.getLast()) {
                deque.removeLast();
            }
            deque.addLast(nums[i]);
            res[i - k + 1] = deque.getFirst();
        }
        return res;
    }

    public static void main(String[] args) {
        SlidingWindowMaximum slidingWindowMaximum = new SlidingWindowMaximum();
        int[] execute = slidingWindowMaximum.execute(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
        System.out.println("execute = " + Arrays.toString(execute));
    }


}
