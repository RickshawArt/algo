package Stack.java.thread.demo.printABC;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用原子整型类实现
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2024/5/11 17:08
 */
public class ByAtomicInteger {

    public void execute() {
        int times = 10;
        AtomicInteger atomicInteger = new AtomicInteger();
        Thread a = new Thread(() -> {
            //相当于自旋，等待下一次顺序打印
            for (int i = 0; i < times;) {
                if (atomicInteger.get() % 3 == 0) {
                    System.out.print("A");
                    atomicInteger.getAndIncrement();
                    //如果 i++放在for循环那里，如果一直没有等到适合的atomicInteger，那么循环会一下子走完
                    i++;
                }
            }
        });

        Thread b = new Thread(() -> {
            for (int i = 0; i < times;) {
                if (atomicInteger.get() % 3 == 1) {
                    System.out.print("B");
                    atomicInteger.getAndIncrement();
                    i++;
                }
            }
        });

        Thread c = new Thread(() -> {
            for (int i = 0; i < times;) {
                if (atomicInteger.get() % 3 == 2) {
                    System.out.print("C");
                    atomicInteger.getAndIncrement();
                    i++;
                }
            }

        });
        a.start();
        b.start();
        c.start();
    }

    public static void main(String[] args) {
        ByAtomicInteger byAtomicInteger = new ByAtomicInteger();
        byAtomicInteger.execute();
    }

}
