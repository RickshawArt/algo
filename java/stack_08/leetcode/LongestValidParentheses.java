package Stack.java.stack_08.leetcode;


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

/**
 * Longest Valid Parentheses（最长有效的括号）
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2024/3/26 15:38
 */
public class LongestValidParentheses {

    /**
     * 始终保持栈底元素为最后一个没有被匹配的右括号的下标，这样的做法主要是考虑了边界条件的处理，栈里其他元素维护左括号的下标。
     * <p>
     * 当遇到左括号(时，将它的下标放入栈中;
     * 当遇到右括号)时先弹出栈顶元素表示匹配了当前右括号。
     * 如果栈为空,存入最后一个没有被匹配的右括号的下标。
     * 如果栈不空，当前下标i减去栈顶元素的值就是以右括号结尾的最长有效括号的长度。
     *
     * @param str   需要校验的字符串
     * @return int
     * @author Rickshaw
     * @since 2024/3/27 14:29
     */
    public int execute(String str) {
        if (Objects.isNull(str) || str.isEmpty()) {
            return 0;
        }
        // 用于存放未匹配
        Deque<Integer> deque = new ArrayDeque<>();
        // 里面先存入一个 -1，方便后面计算有效子串长度，我们栈中的栈顶元素是为后面计算有效长度做标记
        // 由于索引从 0开始，某一对括号匹配后，用对应 index - 栈顶元素即为当前的有效长度
        deque.push(-1);
        char leftParenthesis = '(';
        int longestValidLength = 0;
        for (int i = 0; i < str.length(); i++) {
            if (leftParenthesis == str.charAt(i)) {
                deque.push(i);
                continue;
            }
            deque.pop();
            if (deque.isEmpty()) {
                deque.push(i);
            } else {
                longestValidLength = Math.max(longestValidLength, i - deque.peek());
            }
        }
        return longestValidLength;
    }

    public static void main(String[] args) {
        LongestValidParentheses longestValidParentheses = new LongestValidParentheses();
        int execute = longestValidParentheses.execute("");
        System.out.println("execute = " + execute);
    }

}
