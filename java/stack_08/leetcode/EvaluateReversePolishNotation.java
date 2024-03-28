package Stack.java.stack_08.leetcode;

import java.util.*;

/**
 * Evaluate Reverse Polish Notation（逆波兰表达式求值）
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2024/3/27 17:07
 */
public class EvaluateReversePolishNotation {

    /**
     * 逆波兰表达式求值
     * @param tokens    逆波兰表达式数组
     * @return int
     * @author Rickshaw
     * @since 2024/3/27 17:13
     */
    public int execute(String[] tokens) {
        if (Objects.isNull(tokens) || tokens.length == 0) {
            return 0;
        }
        tokens = Arrays.stream(tokens).map(String::toLowerCase).toArray(String[]::new);
        // 用于存放表达式token的栈
        Deque<String> deque = new ArrayDeque<>();
        String addition = "+", subtraction = "-", multiplication = "*", division = "/";
        List<String> operators = new ArrayList<>(Arrays.asList(addition, subtraction, multiplication, division));
        for (String token : tokens) {
            //数字直接压栈
            if (!operators.contains(token)) {
                deque.push(token);
                continue;
            }
            //遇到运算符，则计算入栈
            int o1 = Integer.parseInt(deque.pop());
            int o2 = Integer.parseInt(deque.pop());
            int result;
            if (addition.equals(token)) {
                result = o2 + o1;
            } else if (subtraction.equals(token)) {
                result = o2 - o1;
            } else if (multiplication.equals(token)) {
                result = o2 * o1;
            } else {
                result = o2 / o1;
            }
            deque.push(String.valueOf(result));
        }
        return Integer.parseInt(deque.pop());
    }


    public static void main(String[] args) {
        String[] tokens = new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        EvaluateReversePolishNotation reversePolishNotation = new EvaluateReversePolishNotation();
        int execute = reversePolishNotation.execute(tokens);
        System.out.println("execute = " + execute);
    }


}
