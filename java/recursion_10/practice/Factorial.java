package Stack.java.recursion_10.practice;

/**
 * 编程实现求阶乘 n!
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2024/3/25 10:42
 */
public class Factorial {

    public int execute(int n) {
        if (n == 1) {
            return n;
        }
        return n * execute(n - 1);
    }

    public static void main(String[] args) {
        Factorial factorial = new Factorial();
        int execute = factorial.execute(9);
        System.out.println("execute = " + execute);
    }

}
