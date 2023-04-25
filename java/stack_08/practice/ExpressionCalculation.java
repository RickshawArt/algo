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
        StringBuilder numBuf = new StringBuilder();
        for (int i = 0; i < charArr.length; i++) {
            String c = charArr[i];
            //把操作数入栈
            if (this.isNumber(c) && i == charArr.length - 1) {
                this.operandStack.push(c);
                numBuf.setLength(0);
                break;
            }
            if (this.isNumber(c)) {
                numBuf.append(c);
                continue;
            }
            this.operandStack.push(numBuf.toString());
            numBuf.setLength(0);
            compareOperatorCal(c);
            this.operatorStack.push(c);
        }
        //把表达式的内容录入进操作数栈跟运算符栈，根据运算符栈的数量再进行计算输出
        int size = this.operatorStack.size();
        for (int i = 0; i < size; i++) {
            int result = this.calculate();
            this.operandStack.push(String.valueOf(result));
        }
        return Integer.parseInt(this.operandStack.pop());
    }

    /**
     * 递归比较当前运算符和栈顶运算符，直到比栈顶运算符优先级高跳出递归
     * @param operator  当前运算符
     * @author Rickshaw
     * @since 2023/4/25 20:03
     */
    private void compareOperatorCal(String operator) {
        //是否需要优先计算
        boolean priorCalculate = needPriorCalculate(operator);
        if (priorCalculate) {
            int result = this.calculate();
            if (result == Integer.MIN_VALUE) {
                return;
            }
            this.operandStack.push(String.valueOf(result));
            compareOperatorCal(operator);
        }
    }

    /**
     * 计算
     * @return int
     * @author Rickshaw
     * @since 2023/4/25 15:14
     */
    private int calculate() {
        if (this.operandStack.size() <2) {
            return Integer.MIN_VALUE;
        }
        int right = Integer.parseInt(this.operandStack.pop());
        int left = Integer.parseInt(this.operandStack.pop());
        String operator = this.operatorStack.pop();
        int result = 0;
        //由于做不到运算符字符串，真正的算数运算符一一映射，所以目前先写死加减乘除规则，Just play
        if ("+".equals(operator)) {
            result = left + right;
        } else if ("-".equals(operator)) {
            result = left - right;
        } else if ("*".equals(operator)) {
            result = left * right;
        } else if ("/".equals(operator)) {
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
        expression = "34+13*9+44-12/3";
        expression = "8*9-13+10/2";
        Map<String, Integer> priority = new HashMap<>();
        priority.putIfAbsent("+", 1);
        priority.putIfAbsent("-", 1);
        priority.putIfAbsent("*", 2);
        priority.putIfAbsent("/", 2);
        ExpressionCalculation expressionCalculation = new ExpressionCalculation(priority);
        System.out.println(expression + " = " + expressionCalculation.parseAndCalculate(expression));
    }


}
