package Stack.java.recursion_10.practice;

/**
 * 编程实现斐波那契数列求第 n项 f(n)=f(n-1)+f(n-2)
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2024/3/22 17:10
 */
public class Fibonacci {

    public int execute(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        return execute(n - 1) + execute(n - 2);
    }

    public static void main(String[] args) {
        Fibonacci fibonacci = new Fibonacci();
        int execute = fibonacci.execute(11);
        System.out.println("execute = " + execute);
    }

}
