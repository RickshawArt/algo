package Stack.java.stack_08.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

/**
 * 实现一个浏览器的前进、后退功能
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2024/3/20 15:05
 */
public class BrowserForwardBackward {

    /**
     * 存放新建页面的栈（用于后退功能）
     */
    private final Deque<String> mainDeque = new ArrayDeque<>();

    /**
     * 存放弹出页面的栈（用于前进功能）
     */
    private final Deque<String> popDeque = new ArrayDeque<>();

    /**
     * 前进页面
     * @return java.lang.String
     * @author Rickshaw
     * @since 2024/3/20 16:36
     */
    public String forward() {
        String forwardDeque = this.popDeque.peek();
        if (Objects.isNull(forwardDeque)) {
            return null;
        }
        this.popDeque.pop();
        this.mainDeque.push(forwardDeque);
        return forwardDeque;
    }

    /**
     * 后退页面
     * @return java.lang.String
     * @author Rickshaw
     * @since 2024/3/20 16:34
     */
    public String backward() {
        String backwardPage = this.mainDeque.peek();
        if (Objects.isNull(backwardPage)) {
            return null;
        }
        this.mainDeque.pop();
        this.popDeque.push(backwardPage);
        return backwardPage;
    }

    /**
     * 打开一个新指定页面
     * @param page      要打开的页面
     * @return boolean
     * @author Rickshaw
     * @since 2024/3/20 16:16
     */
    public boolean open(String page) {
        if (Objects.isNull(page) || page.isEmpty()) {
            return false;
        }
        this.mainDeque.push(page);
        return true;
    }

    /**
     * 当前访问的页面
     * @return java.lang.String
     * @author Rickshaw
     * @since 2024/3/20 16:45
     */
    public String currentPage() {
        return this.mainDeque.peek();
    }

    public static void main(String[] args) {
        BrowserForwardBackward browser = new BrowserForwardBackward();
        browser.open("掘金");
        browser.open("Apple Store");
        browser.open("ChatGPT");
        browser.open("GitHub");
        System.out.println("browser.backward() = " + browser.backward());
        System.out.println("browser.backward() = " + browser.backward());
        System.out.println("browser.currentPage() = " + browser.currentPage());
        System.out.println("browser.forward() = " + browser.forward());
        System.out.println("browser.currentPage() = " + browser.currentPage());
        System.out.println("browser.forward() = " + browser.forward());
        System.out.println("browser.forward() = " + browser.forward());
        browser.open(null);
        System.out.println("browser.currentPage() = " + browser.currentPage());
    }

}
