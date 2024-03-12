package Stack.java.array_05.leetcode;

/**
 * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数
 * 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2024/3/12 9:58
 */
public class FirstMissingPositive {

    /**
     * 思路：假设数组长度为 n
     * 1、隐藏条件：最小正整数出现区间为 [1, n + 1]
     * 2、选取 nums[i]，n >= nums[i] > 0，并且 nums[i] != nums[nums[i] - 1]，就需要交换数据，把 nums[i] 放回正确的位置，nums[i] - 1为正确的索引
     *      循环再把新的nums[i]的值放在正确的位置，直到nums[i]不符合规则被丢弃；例如nums[0] = 1，nums[1] = 2.......
     * 3、遍历 [0, n]，第一个不符合nums[i] == i + 1, i + 1即为所求，没遍历到，则为 n + 1
     * @param nums  无序整型数组
     * @return int
     * @author Rickshaw
     * @since 2024/3/12 10:28
     */
    public int execute(int[] nums) {
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            while (nums[i] > 0 && nums[i] <= length && nums[i] != nums[nums[i] - 1]) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }
        for (int i = 0; i < length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return length + 1;
    }

    public static void main(String[] args) {
        FirstMissingPositive firstMissingPositive = new FirstMissingPositive();
        int execute = firstMissingPositive.execute(new int[]{7, 8, 9, 11, 12});
        System.out.println("execute = " + execute);
    }

}
