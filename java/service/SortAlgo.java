package Stack.java.service;

/**
 * 排序算法统一接口
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2023/5/15 14:44
 */
public interface SortAlgo {

    /**
     * 升序排序
     * @param arr   待排序数组
     * @author Rickshaw
     * @since 2023/5/15 14:46
     */
    void sort(long[] arr);

    /**
     * 升序排序
     * @param arr   带排序数组
     * @param length    需要排序的数据长度
     * @author Rickshaw
     * @since 2023/5/17 16:40
     */
    void sort(long[] arr, int length);

    /**
     * 打印数组
     * @param arr   待打印的数组
     * @author Rickshaw
     * @since 2023/5/15 14:53
     */
    default void printArr(long[] arr) {
        System.out.print("printArr: ");
        for (long j : arr) {
            System.out.print(j + " ");
        }
        System.out.println();
    }

}
