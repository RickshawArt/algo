package Stack.java.design_pattern.singleton;

/**
 * DCL懒汉单例模式
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2024/5/12 11:18
 */
public class LazySingleton {

    private static volatile LazySingleton INSTANCE = null;

    private LazySingleton() {

    }

    public LazySingleton getInstance() {
        if (INSTANCE == null) {
            synchronized (LazySingleton.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LazySingleton();
                }
            }
        }
        return INSTANCE;
    }

}
