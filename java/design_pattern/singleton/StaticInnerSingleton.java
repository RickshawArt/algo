package Stack.java.design_pattern.singleton;

/**
 * 静态内部类懒汉单例模式
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2024/5/12 11:32
 */
public class StaticInnerSingleton {
    private StaticInnerSingleton() {

    }

    public static class SingletonHolder {
        private static final StaticInnerSingleton INSTANCE = new StaticInnerSingleton();
    }

    public StaticInnerSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }

}
