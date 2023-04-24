package Stack.java.stack_08.practice;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 利用栈实现表达式求值
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2023/4/24 23:53
 */
public class ExpressionEvaluation {

    /**
     * 运算符栈
     */
    private MyStackBasedOnLinkedList<String> operatorStack;

    /**
     * 操作数栈
     */
    private MyStackBasedOnLinkedList<String> operandStack;

    /**
     * 校验数字的正则
     */
    public static final Pattern PATTERN = Pattern.compile("\\d");

    /**
     * 操作符优先级, 数值越大，优先级越高
     */
    private static final Map<String, Integer> operatorMap = new HashMap<>();

    static {
        operatorMap.putIfAbsent("+", 1);
        operatorMap.putIfAbsent("-", 1);
        operatorMap.putIfAbsent("*", 2);
        operatorMap.putIfAbsent("/", 2);
    }


}
