package Stack.java.thread.demo.printABC;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用 Lock + Condition + state 实现
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2024/5/11 15:31
 */
public class ByLockCondition {

    /**
     * 1（线程 a打印），2（线程 b打印），3（线程 c打印）
     */
    int state = 1;

    public void execute() {
        int times = 10;
        Lock lock = new ReentrantLock();
        Condition conditionA = lock.newCondition();
        Condition conditionB = lock.newCondition();
        Condition conditionC = lock.newCondition();
        Thread a = new Thread(() -> {
            for (int i = 0; i < times; i++) {
                lock.lock();
                try {
                    while (state != 1) {
                        conditionA.await();
                    }
                    TimeUnit.MILLISECONDS.sleep(300);
                    System.out.print("A");
                    state = 2;
                    conditionB.signal();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    lock.unlock();
                }
            }
        });

        Thread b = new Thread(() -> {
            for (int i = 0; i < times; i++) {
                lock.lock();
                try {
                    while (state != 2) {
                        conditionB.await();
                    }
                    TimeUnit.MILLISECONDS.sleep(300);
                    System.out.print("B");
                    state = 3;
                    conditionC.signal();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    lock.unlock();
                }
            }
        });

        Thread c = new Thread(() -> {
            for (int i = 0; i < times; i++) {
                lock.lock();
                try {
                    while (state != 3) {
                        conditionC.await();
                    }
                    TimeUnit.MILLISECONDS.sleep(300);
                    System.out.print("C");
                    state = 1;
                    conditionA.signal();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    lock.unlock();
                }
            }
        });
        a.start();
        b.start();
        c.start();
        lock.lock();
        conditionA.signal();
        lock.unlock();
    }

    public static void main(String[] args) {
        ByLockCondition byLockCondition = new ByLockCondition();
        byLockCondition.execute();
    }

}
