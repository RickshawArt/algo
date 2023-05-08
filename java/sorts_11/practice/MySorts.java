package Stack.java.sorts_11.practice;

/**
 * 冒泡排序，插入排序，选择排序
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2023/5/8 8:40
 */
public class MySorts {
    /**
     * 冒泡排序
     * @param arr   要排序的数组
     * @author Rickshaw
     * @since 2023/5/8 9:13
     */
    public static void bubbleSort(int[] arr) {
        int length = arr.length;
        if (length <= 1) {
            return;
        }
        //用于交换变量
        int exchange = 0;
        for (int i = 0; i < length; i++) {
            //如果下一次没有进行比较交换逻辑，则证明已排序好
            boolean isSorted = true;
            for (int j = 0; j < length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    exchange = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = exchange;
                    isSorted = false;
                }
            }
            if (isSorted) {
                return;
            }
        }
    }

    /**
     * 插入排序
     * @param arr   待排序数组
     * @author Rickshaw
     * @since 2023/5/8 16:38
     */
    public static void insertionSort(int[] arr) {
        int length = arr.length;
        if (length <= 1) {
            return;
        }
        for (int i = 1; i < length; i++) {

        }
    }

    /**
     * 打印数组
     * @param arr   要打印的数组
     * @author Rickshaw
     * @since 2023/5/8 9:26
     */
    public static void printArr(int[] arr) {
        System.out.print("printArr: ");
        for (int j : arr) {
            System.out.print(j + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {1, 5, 4, 7, 2, 6};
        bubbleSort(arr);
        printArr(arr);
    }

}
