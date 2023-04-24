package Stack.java.stack_08.practice;

import java.util.EmptyStackException;
import java.util.regex.Pattern;

/**
 * 模拟浏览器前进后退
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2023/4/24 21:19
 */
public class MySampleBrowser {

    /**
     * 后退栈
     */
    private final MyStackBasedOnLinkedList<String> backStack;
    /**
     * 前进栈
     */
    private final MyStackBasedOnLinkedList<String> forwardStack;

    public MySampleBrowser() {
        this.backStack = new MyStackBasedOnLinkedList<>();
        this.forwardStack = new MyStackBasedOnLinkedList<>();
    }

    /**
     * 打开一个页面
     * @param url 页面的url
     * @author Rickshaw
     * @since 2023/4/24 21:47
     */
    public void open(String url) {
        this.backStack.push(url);
        //清空前进栈
        this.forwardStack.clear();
    }

    /**
     * 前进一个页面
     * @return java.lang.String
     * @author Rickshaw
     * @since 2023/4/24 21:48
     */
    public String forward() {
        String forwardPage = "";
        try {
            forwardPage = this.forwardStack.pop();
        } catch (EmptyStackException e) {
            return "Previous page does not exist";
        }
        return this.backStack.push(forwardPage);
    }

    /**
     * 后退一个页面
     * @return java.lang.String
     * @author Rickshaw
     * @since 2023/4/24 22:02
     */
    public String back() {
        String backPage = "";
        try {
            backPage = this.backStack.pop();
        } catch (EmptyStackException e) {
            return "Back page does not exist";
        }
        this.forwardStack.push(backPage);
        return this.backStack.peek();
    }

    /**
     * 获取当前展示页面
     * @return java.lang.String
     * @author Rickshaw
     * @since 2023/4/24 22:04
     */
    public String getCurPage() {
        String curPage = "";
        try {
            curPage = this.backStack.peek();
        } catch (EmptyStackException e) {
            return "The current page is not open yet";
        }
        return curPage;
    }

    public static void main(String[] args) {
        MySampleBrowser browser = new MySampleBrowser();
        browser.open("https://juejin.cn/");
        browser.open("https://chat.openai.com/");
        browser.open("https://translate.google.cn/");
        browser.open("https://time.geekbang.com/");
        System.out.println("browser.getCurPage() = " + browser.getCurPage());
        System.out.println("browser.back() = " + browser.back());
        System.out.println("browser.back() = " + browser.back());
        System.out.println("browser.forward() = " + browser.forward());
        browser.open("https://cupfox.app/");
        System.out.println("browser.forward() = " + browser.forward());
        System.out.println("browser.getCurPage() = " + browser.getCurPage());
        System.out.println("browser.back() = " + browser.back());
        System.out.println("browser.getCurPage() = " + browser.getCurPage());

        String c = "-1";
        boolean matches = Pattern.matches("[0-9]", c);

        System.out.println("matches = " + matches);
    }

}
