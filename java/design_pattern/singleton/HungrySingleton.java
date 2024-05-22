package Stack.java.design_pattern.singleton;

/**
 * 饿汉单例模式
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2024/5/12 11:06
 */
public class HungrySingleton {

    private final static HungrySingleton INSTANCE = new HungrySingleton();

    private HungrySingleton() {

    }

    public static HungrySingleton getInstance() {
        return INSTANCE;
    }

}
