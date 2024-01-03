package Stack.java.greedy_algorithm_37;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 课后思考
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2024/1/2 16:27
 */
public class ThoughtsAfterClass {


    /**
     * 在一个非负整数 a 中，我们希望从中移除 k 个数字，让剩下的数字值最小，如何选择移除哪 k 个数字呢？
     * @param a     非负整数
     * @param k     移除数量
     * @return java.lang.String
     * @author Rickshaw
     * @since 2024/1/2 16:28
     */
    public String getMin(String a, int k) {
        if (Integer.parseInt(a) <= 0) {
            throw new IllegalArgumentException("a需要为非负整数");
        }
        if (k <= 0 && a.length() <= k) {
            throw new IllegalArgumentException("k为非负整数且需要小于a的长度");
        }
        List<String> originList = Arrays.stream(a.split("")).collect(Collectors.toList());
        return String.join("", this.remove(originList, k));
    }

                               /**
     * 递归移除k个数, 使得originList最小
     * @param originList    原始集合
     * @param k    移除数量
     * @return java.util.List<java.lang.String>
     * @author Rickshaw
     * @since 2024/1/3 9:47
     */
    private List<String> remove(List<String> originList, int k) {
        if (k == 0) {
            return originList;
        }
        int size = originList.size();
        List<String> targetList = new ArrayList<>(originList);
        for (int i = 0; i < size; i++) {
            //如果遍历到尾部还没移除元素, 则移除最后一位元素
            if (i == size - 1) {
                targetList.remove(targetList.size() - 1);
                return this.remove(targetList, --k);
            }
            //因为一个数左边的位数(权重)大于右边, 如果左数比右数大, 则移除左数
            int left = Integer.parseInt(originList.get(i));
            int right = Integer.parseInt(originList.get(i + 1));
            if (left > right) {
                targetList.remove(i);
                return this.remove(targetList, --k);
            }
        }
        return targetList;
    }

    public static void main(String[] args) {
        ThoughtsAfterClass thoughtsAfterClass = new ThoughtsAfterClass();
        String min = thoughtsAfterClass.getMin("2893146", 2);
        System.out.println("min = " + min);
    }

}
