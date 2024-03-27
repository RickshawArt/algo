package Stack.java.recursion_10.practice;

import java.util.*;

/**
 * 编程实现一组数据集合的全排列
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2024/3/25 11:02
 */
public class FullArrangement {

    /**
     * 全排列的入口方法
     * @param beArrangedList    待全排列的集合
     * @return java.util.List<java.util.List<java.lang.Integer>>
     * @author Rickshaw
     * @since 2024/3/26 11:14
     */
    public List<List<Integer>> execute(List<Integer> beArrangedList) {
        // 返回的结果
        List<List<Integer>> res = new ArrayList<>();
        if (Objects.isNull(beArrangedList) || beArrangedList.isEmpty()) {
            return res;
        }
        // 位置标记是否在使用
        boolean[] used = new boolean[beArrangedList.size()];
        // 当前的某个排列的栈
        Deque<Integer> permutationDeque = new ArrayDeque<>(beArrangedList.size());
        this.backtrack(used, permutationDeque, res, beArrangedList);
        return res;
    }

    /**
     * 递归回溯
     * <p>
     * 1. 在递归函数 `backtrack` 中，首先进行基本情况的判断：如果当前排列的长度等于原始数组的长度，则说明已经生成了一个完整的排列，将其加入结果列表中，并返回。这是递归的结束条件。
     * <p>
     * 2. 接下来是递归的主体部分，使用一个循环来尝试每个可选的数字。在循环中，如果当前数字还未被使用过，则将其加入排列中，并标记为已使用。然后递归调用下一层递归，继续生成下一个位置的排列。
     * <p>
     * 3. 在递归返回后，进行回溯操作：撤销当前选择。这里的回溯包括两个步骤：一是将最后一个加入排列的数字移除，即 `permutationDeque.pop()`；
     *      二是将该数字的使用状态标记为未使用，即 `used[i] = false`。这样就回到了上一层递归的状态，继续尝试其他的选择。
     * <p>
     * 这种回溯的方式保证了程序可以穷举所有可能的排列，同时在生成每个排列时保证了其合法性
     *
     * @param used    位置标记数组
     * @param permutationDeque   当前的某个排列的栈
     * @param res     返回的结果
     * @param beArrangedList    待全排列的集合
     * @author Rickshaw
     * @since 2024/3/26 11:31
     */
    private void backtrack(boolean[] used, Deque<Integer> permutationDeque, List<List<Integer>> res, List<Integer> beArrangedList) {
        int size = beArrangedList.size();
        if (size == permutationDeque.size()) {
            res.add(new ArrayList<>(permutationDeque));
            return;
        }
        for (int i = 0; i < size; i++) {
            if (!used[i]) {
                used[i] = true;
                permutationDeque.push(beArrangedList.get(i));
                System.out.println("递归之前 => " + permutationDeque);
                this.backtrack(used, permutationDeque, res, beArrangedList);
                used[i] = false;
                permutationDeque.pop();
                System.out.println("递归之后 => " + permutationDeque);
            }
        }
    }

    public static void main(String[] args) {
        FullArrangement fullArrangement = new FullArrangement();
        List<List<Integer>> execute = fullArrangement.execute(Arrays.asList(1, 2, 3));
        System.out.println("execute = " + execute);
    }

}
