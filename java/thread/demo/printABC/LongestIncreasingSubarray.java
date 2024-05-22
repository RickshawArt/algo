package Stack.java.thread.demo.printABC;

import java.util.Arrays;

/**
 * 从数组里找出最长升序的数组
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2024/5/12 15:57
 */
public class LongestIncreasingSubarray {

    public int[] execute(int[] nums) {
        //startIndex为最长升序数组的首索引
        //currentLength为当前正在计算的有序数组的长度
        //maxLength为最长升序数组的长度
        int startIndex = 0, currentLength = 1, maxLength = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                currentLength++;
            } else {
                //如果出现了next < prev，则把currentLength赋值给maxLength，currentLength重置；更新startIndex
                if (currentLength > maxLength) {
                    maxLength = currentLength;
                    startIndex = i - maxLength;
                }
                currentLength = 1;
            }
        }
        //待数组遍历完之后，再次校验currentLength和maxLength，看看最后的有序数组长度是否是最大的
        if (currentLength > maxLength) {
            maxLength = currentLength;
            startIndex = nums.length - maxLength;
        }
        //收集最长的有序数组并返回
        int[] ret = new int[maxLength];
        for (int i = 0; i < maxLength; i++) {
            ret[i] = nums[startIndex + i];
        }
        return ret;
    }

    public static void main(String[] args) {
        LongestIncreasingSubarray longestIncreasingSubarray = new LongestIncreasingSubarray();
        int[] execute = longestIncreasingSubarray.execute(new int[]{1, 3, 5, 2, 4, 7, 3, 10});
        System.out.println(Arrays.toString(execute));
    }

}
