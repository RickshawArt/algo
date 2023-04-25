package Stack.java.stack_08.practice;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 利用栈实现表达式求值
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2023/4/24 23:53
 */
public class ExpressionCalculation {

    /**
     * 运算符栈
     */
    private final MyStackBasedOnLinkedList<String> operatorStack;

    /**
     * 操作数栈
     */
    private final MyStackBasedOnLinkedList<String> operandStack;

    /**
     * 校验数字的正则
     */
    public static final Pattern PATTERN = Pattern.compile("\\d");

    /**
     * 操作符优先级, 数值越大，优先级越高
     */
    private final Map<String, Integer> priority;

    public ExpressionCalculation(Map<String, Integer> priority) {
        this.operatorStack = new MyStackBasedOnLinkedList<>();
        this.operandStack = new MyStackBasedOnLinkedList<>();
        this.priority = priority;
    }

    /**
     * 解析并计算
     * @param expression  需要计算的表达式
     * @return int
     * @author Rickshaw
     * @since 2023/4/25 16:34
     */
    private int parseAndCalculate(String expression) {
        //表达式字符数组
        String[] charArr = expression.split("");
        StringBuilder numBuf = new  StringBuilder();
        for (String c : charArr) {
            boolean isNum = this.isNumber(c);
            if (isNum) {
                numBuf.append(c);
                continue;
            }
            this.operandStack.push(numBuf.toString());
            numBuf.setLength(0);
            //是否需要优先计算
            boolean priorCalculate = needPriorCalculate(c);
            if (priorCalculate) {
                int result = calculate();
                if (result == Integer.MIN_VALUE) {
                    continue;
                }
                this.operandStack.push(result + "");
            } else {
                this.operatorStack.push(c);
            }
        }
        return Integer.parseInt(this.operandStack.peek());
    }

    /**
     * 计算
     * @return int
     * @author Rickshaw
     * @since 2023/4/25 15:14
     */
    private int calculate() {
        if (this.operatorStack.size() == 1) {
            return Integer.MIN_VALUE;
        }
        int right = Integer.parseInt(this.operandStack.pop());
        int left = Integer.parseInt(this.operandStack.pop());
        String operator = this.operatorStack.pop();
        int result = 0;
        //由于做不到运算符字符串，真正的算数运算符一一映射，所以目前先写死加减乘除规则，Just play
        if (operator.equals("+")) {
            result = left + right;
        } else if (operator.equals("-")) {
            result = left - right;
        } else if (operator.equals("*")) {
            result = left * right;
        } else if (operator.equals("/")) {
            result = left / right;
        }
        return result;
    }

    /**
     * 判断是否需要优先计算
     * @param operator  运算符
     * @return boolean
     * @author Rickshaw
     * @since 2023/4/25 14:45
     */
    private boolean needPriorCalculate(String operator) {
        //校验运算符优先级是否有效
        Integer operatorPriority = priority.get(operator);
        if (Objects.isNull(operatorPriority)) {
            throw new IllegalArgumentException("没有找到[" + operator + "]的运算符优先级, 请先确保它已经在priority中");
        }
//        this.operatorStack.push(operator);
        //栈顶为空, 直接push
        if (this.operatorStack.isEmpty()) {
            return false;
        }
        //栈顶不为空，需要比较运算符的优先级
        Integer roofPriority = priority.get(this.operatorStack.peek());
        return operatorPriority <= roofPriority;
    }

    /**
     * 判断是否数字
     * @param input 需要校验的输入串
     * @return boolean
     * @author Rickshaw
     * @since 2023/4/25 14:34
     */
    private boolean isNumber(String input) {
        Matcher matcher = PATTERN.matcher(input);
        return matcher.matches();
    }

    public static void main(String[] args) {
        String expression = "3+5*8-6";
//        expression = "34+13*9+44-12/3";
        Map<String, Integer> priority = new HashMap<>();
        priority.putIfAbsent("+", 1);
        priority.putIfAbsent("-", 1);
        priority.putIfAbsent("*", 2);
        priority.putIfAbsent("/", 2);
        ExpressionCalculation expressionCalculation = new ExpressionCalculation(priority);
        System.out.println("expressionCalculation.parseAndCalculate(expression) = " + expressionCalculation.parseAndCalculate(expression));
    }


}
