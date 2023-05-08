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
        //i: 未排序序号，首次arr[0]作为已排序区
        for (int i = 1; i < length; i++) {
            //未排序区中要排序的值
            int value = arr[i];
            //j: 已排序序号
            int j = i - 1;
            for (; j >= 0; j--) {
                if (arr[j] <= value) {
                    break;
                }
                arr[j + 1] = arr[j];
            }
            //因为判断j >= 0的时候j--了，所以要补1
            arr[j + 1] = value;
        }
    }

    /**
     * 选择排序
     * @param arr   待排序数组
     * @author Rickshaw
     * @since 2023/5/9 0:03
     */
    public static void selectionSort(int[] arr) {
        int length = arr.length;
        //未排序区遍历找到的最小值
        int minIndex;
        //用于交换数据的变量
        int exchange;
        //i: 已排序区序号
        for (int i = 0; i < length - 1; i++) {
            minIndex = i;
            //j: 待排序区序号
            for (int j = i + 1; j < length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            //如果当前位置就是最小值，无需进行交换
            if (minIndex != i) {
                exchange = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = exchange;
            }
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
//        bubbleSort(arr);
//        insertionSort(arr);
        selectionSort(arr);
        printArr(arr);
    }

}
