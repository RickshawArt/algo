package Stack.java.array_05.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 三数之和
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，
 * 同时还满足 nums[i] + nums[j] + nums[k] == 0 。请你返回所有和为 0 且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2024/3/5 14:39
 */
public class ThreeSum {

    /**
     * 返回三数之和为0的不重复三元组
     * @param nums  整数数组
     * @return java.util.List<java.util.List<java.lang.Integer>>
     * @author Rickshaw
     * @since 2024/3/5 14:42
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int minLength = 3;
        if (Objects.isNull(nums) || nums.length < minLength) {
            return result;
        }
        int length = nums.length;
        Arrays.sort(nums);
        //排序后第一个元素大于0，则三数之和不可能为0
        if (nums[0] > 0) {
            return result;
        }
        //此层循环以nums[i]为基准，进行循环
        for (int i = 0; i < length; i++) {
            //去重，因为已经将 nums[i - 1] 的所有组合加入到结果中，本次双指针搜索只会得到重复组合
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = length - 1;
            //此层循环是移动左，右指针的
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum < 0) {
                    left = leftDistinctMove(nums, left, right);
                } else if (sum > 0) {
                    right = rightDistinctMove(nums, left, right);
                } else {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    //跳过所有重复的 nums[left] 和 nums[right]，防止记录到重复组合
                    left = leftDistinctMove(nums, left, right);
                    right = rightDistinctMove(nums, left, right);
                }
            }
        }
        return result;
    }

    /**
     * 左指针去重并向右移动一位
     * @param nums   数组
     * @param left   左指针
     * @param right  右指针
     * @return int
     * @author Rickshaw
     * @since 2024/3/8 9:17
     */
    private static int leftDistinctMove(int[] nums, int left, int right) {
        while (left < right && nums[left] == nums[left + 1]) {
            left++;
        }
        return ++left;
    }

    /**
     * 右指针去重并向左移动一位
     * @param nums   数组
     * @param left   左指针
     * @param right  右指针
     * @return int
     * @author Rickshaw
     * @since 2024/3/8 9:17
     */
    private static int rightDistinctMove(int[] nums, int left, int right) {
        while (left < right && nums[right] == nums[right - 1]) {
            right--;
        }
        return --right;
    }

    public static void main(String[] args) {
        ThreeSum obj = new ThreeSum();
        List<List<Integer>> result = obj.threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        System.out.println("result = " + result);
    }

}
