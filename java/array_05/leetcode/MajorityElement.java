package Stack.java.array_05.leetcode;

/**
 * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2024/3/11 15:27
 */
public class MajorityElement {

    /**
     * 求众数（适用于众数的出现次数大于 ⌊ n/2 ⌋ 的元素的情况）
     * @param arr   整型数组
     * @return int
     * @author Rickshaw
     * @since 2024/3/11 15:45
     */
    public int execute(int[] arr) {
        int candidate = arr[0];
        int count = 1;
        for (int i = 1; i < arr.length; i++) {
            if (candidate == arr[i]) {
                count++;
            } else if (--count == 0){
                candidate = arr[i];
                count++;
            }
        }
        return candidate;
    }

    public static void main(String[] args) {
        MajorityElement majorityElement = new MajorityElement();
        int execute = majorityElement.execute(new int[]{2, 2, 2, 2, 2, 1, 4, 3});
        System.out.println("execute = " + execute);
    }

}
