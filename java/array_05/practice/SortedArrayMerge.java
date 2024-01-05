package Stack.java.array_05.practice;

import java.util.Arrays;

/**
 * 实现两个有序数组合并为一个有序数组
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2024/1/4 16:47
 */
public class SortedArrayMerge {

    /**
     * 从前到后合并到新建一个新数组
     * @param o1    有序数组
     * @param o2    有序数组
     * @return int[]
     * @author Rickshaw
     * @since 2024/1/5 10:17
     */
    public static int[] mergeFront2Back(int[] o1, int[] o2) {
        SortedArrayMerge.checkParams(o1, o2);
        int[] merge = new int[o1.length + o2.length];
        //i为o1的遍历索引, j为o2的遍历索引, k为merge的遍历索引
        int i = 0, j = 0, k = 0;
        //在两个数组还有值的时候, 比较加入合并后的数组
        while (i < o1.length && j < o2.length) {
            merge[k++] = o1[i] < o2[j] ? o1[i++] : o2[j++];
        }
        //到此可以肯定某一个有序数组为空了, 把另一个有序数组加入合并后的数组
        while (i < o1.length) {
            merge[k++] = o1[i++];
        }
        while (j < o2.length) {
            merge[k++] = o2[j++];
        }
        return merge;
    }

    /**
     * 在某个数组直接扩充容量, 直接把另一个数组搬进来, 从后往前
     * @param o1    有序数组
     * @param o2    有序数组
     * @return int[]
     * @author Rickshaw
     * @since 2024/1/5 10:25
     */
    public static int[] mergeBack2Front(int[] o1, int[] o2) {
        SortedArrayMerge.checkParams(o1, o2);
        //i指向o1的最后一个元素, j指向o2的最后一个元素
        int i = o1.length - 1, j = o2.length - 1;
        //假设原本o1长度就涵盖o1和o2的总长度
        o1 = Arrays.copyOf(o1, o1.length + o2.length);
        //k指向扩充容量后的o1数组的最后一个元素
        int k = o1.length - 1;
        while (i >= 0 && j >= 0) {
            o1[k--] = o2[j] > o1[i] ? o2[j--] : o1[i--];
        }
        //如果j < 0证明合并已经完成, 如果不是证明o2剩下的值比o1的都要小, 只需按顺序放入剩余的空位即可
        while (j >= 0) {
            o1[k--] = o2[j--];
        }
        return o1;
    }

    private static void checkParams(int[] o1, int[] o2) {
        if (o1.length == 0 || o2.length == 0) {
            throw new IllegalArgumentException();
        }
    }

    public static void main(String[] args) {
        int[] o1 = {1, 2, 4, 7, 8};
        int[] o2 = {3, 5, 9};
        int[] front2Back = SortedArrayMerge.mergeFront2Back(o1, o2);
        System.out.println("front2Back = " + Arrays.toString(front2Back));
        int[] back2Front = SortedArrayMerge.mergeBack2Front(o1, o2);
        System.out.println("back2Front = " + Arrays.toString(back2Front));
    }

}
