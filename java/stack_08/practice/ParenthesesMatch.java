package Stack.java.stack_08.practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 利用栈实现括号匹配
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2023/4/26 9:11
 */
public class ParenthesesMatch {

    /**
     * 括号（key为左括号，value为右括号）
     */
    public static final Map<String, String> PARENTHESES_MAP = new HashMap<>();

    public static final String REGEX = "[()\\[\\]{}]";

    static {
        PARENTHESES_MAP.putIfAbsent("(", ")");
        PARENTHESES_MAP.putIfAbsent("[", "]");
        PARENTHESES_MAP.putIfAbsent("{", "}");
    }

    /**
     * 存放表达式左括号的栈
     */
    private final MyStackBasedOnLinkedList<String> leftParenthesesStack;

    public ParenthesesMatch() {
        this.leftParenthesesStack = new MyStackBasedOnLinkedList<>();
    }

    /**
     * 判断表达式括号匹配是否合法
     * @param expression  要校验的表达式
     * @return boolean
     * @author Rickshaw
     * @since 2023/4/26 9:34
     */
    public boolean isLegal(String expression) {
        List<String> parenthesesList = this.getParenthesesList(expression);
        for (String parentheses : parenthesesList) {
            if (PARENTHESES_MAP.containsKey(parentheses)) {
                //匹配到左括号，则放入栈
                this.leftParenthesesStack.push(parentheses);
            } else if (!leftParenthesesStack.isEmpty()) {
                //匹配到右括号，则取出栈顶元素进行比较
                String roof = this.leftParenthesesStack.pop();
                if (!parentheses.equals(PARENTHESES_MAP.get(roof))) {
                    return false;
                }
            }
        }
        return this.leftParenthesesStack.isEmpty();
    }

    /**
     * 获取表达式中只包括括号的集合
     * @param expression   要过滤的表达式
     * @return java.util.List<java.lang.String>
     * @author Rickshaw
     * @since 2023/4/26 10:44
     */
    private List<String> getParenthesesList(String expression) {
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(expression);
        List<String> parenthesesList = new ArrayList<>();
        while (matcher.find()) {
            parenthesesList.add(matcher.group());
        }
        return parenthesesList;
    }

    public static void main(String[] args) {
        ParenthesesMatch parenthesesMatch = new ParenthesesMatch();
//        System.out.println("parenthesesMatch.isLegal(\"(gs)5{sdag}][\") = " + parenthesesMatch.isLegal("(gs)5{sdag}]["));
//        System.out.println("parenthesesMatch.isLegal(\"{[] ()[{}]}\") = " + parenthesesMatch.isLegal("{[] ()[{}]}"));
//        System.out.println("parenthesesMatch.isLegal(\"[{()}([])]\") = " + parenthesesMatch.isLegal("[{()}([])]"));
//        System.out.println("parenthesesMatch.isLegal(\"{[}()]\") = " + parenthesesMatch.isLegal("{[}()]"));
        System.out.println("parenthesesMatch.isLegal(\"[({)]\") = " + parenthesesMatch.isLegal("[({)]"));
    }
}
