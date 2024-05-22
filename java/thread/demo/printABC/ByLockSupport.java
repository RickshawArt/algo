package Stack.java.thread.demo.printABC;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * 使用 LockSupport 实现
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2024/5/11 15:31
 */
public class ByLockSupport {

    private Thread a;
    private Thread b;
    private Thread c;

    public void execute() {
        int times = 10;
        a = new Thread(() -> {
            for (int i = 0; i < times; i++) {
                LockSupport.park();
                try {
                    TimeUnit.MILLISECONDS.sleep(300);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.print("A");
                LockSupport.unpark(b);
            }
        });

        b = new Thread(() -> {
            for (int i = 0; i < times; i++) {
                LockSupport.park();
                try {
                    TimeUnit.MILLISECONDS.sleep(300);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.print("B");
                LockSupport.unpark(c);
            }
        });

        c = new Thread(() -> {
            for (int i = 0; i < times; i++) {
                LockSupport.park();
                try {
                    TimeUnit.MILLISECONDS.sleep(300);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.print("C");
                LockSupport.unpark(a);
            }
        });
        a.start();
        b.start();
        c.start();
        LockSupport.unpark(a);
    }

    public static void main(String[] args) {
        ByLockSupport byLockSupport = new ByLockSupport();
        byLockSupport.execute();
    }

}
