package Stack.java.sorts_11.practice;

/**
 * 冒泡排序，插入排序，选择排序，希尔排序
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
        for (int i = 0; i < length - 1; i++) {
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
     * 向下冒泡排序
     * @param arr   要排序的数组
     * @author Rickshaw
     * @since 2023/5/9 10:47
     */
    public static void bubbleDownSort(int[] arr) {
        int length = arr.length;
        if (length <= 1) {
            return;
        }
        //用于交换变量
        int exchange = 0;
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                if (arr[i] > arr[j]) {
                    exchange = arr[i];
                    arr[i] = arr[j];
                    arr[j] = exchange;
                }
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
        if (length <= 1) {
            return;
        }
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
     * 希尔排序
     * @param arr   待排序数组
     * @author Rickshaw
     * @since 2023/5/9 8:53
     */
    public static void shellSort(int[] arr) {
        int length = arr.length;
        if (length <= 1) {
            return;
        }
        //初始步长为数组长度的一半，而且每次为原来的一半；直到step == 1，直接对整一个数组进行插入排序
        for (int step = length / 2; step > 0; step /= 2) {
            //从第step元素，对其所在组的元素进行插入排序
            for (int i = step; i < length; i++) {
                int j = i;
                //待排序元素
                int value = arr[j];
                //如果比组内前一个元素小，则进行交换
                while (j - step >= 0 && value < arr[j - step]) {
                    //组内插入排序
                    arr[j] = arr[j - step];
                    j -= step;
                }
                arr[j] = value;
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
        bubbleDownSort(arr);
//        insertionSort(arr);
//        selectionSort(arr);
//        shellSort(arr);
        printArr(arr);
    }

}
