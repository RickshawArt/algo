package Stack.java.thread.demo.printABC;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 用 CountDownLatch实现
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2024/5/11 13:56
 */
public class ByCountDownLatch {
    private CountDownLatch latchA = new CountDownLatch(1);
    private CountDownLatch latchB = new CountDownLatch(1);
    private CountDownLatch latchC = new CountDownLatch(1);

    public void execute() {
        int times = 10;

        Thread a = new Thread(() -> {
            for (int i = 0; i < times; i++) {
                try {
                    latchA.await();
                    TimeUnit.MILLISECONDS.sleep(300);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.print("A");
                synchronized (ByCountDownLatch.class) {
                    latchB.countDown();
                    latchA = new CountDownLatch(1);
                }
            }
        });

        Thread b = new Thread(() -> {
            for (int i = 0; i < times; i++) {
                try {
                    latchB.await();
                    TimeUnit.MILLISECONDS.sleep(300);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.print("B");
                synchronized (ByCountDownLatch.class) {
                    latchC.countDown();
                    latchB = new CountDownLatch(1);
                }
            }
        });

        Thread c = new Thread(() -> {
            for (int i = 0; i < times; i++) {
                try {
                    latchC.await();
                    TimeUnit.MILLISECONDS.sleep(300);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.print("C");
                synchronized (ByCountDownLatch.class) {
                    latchA.countDown();
                    latchC = new CountDownLatch(1);
                }
            }
        });
        a.start();
        b.start();
        c.start();
        latchA.countDown();
    }

    public static void main(String[] args) {
        ByCountDownLatch byCountDownLatch = new ByCountDownLatch();
        byCountDownLatch.execute();
    }

}
