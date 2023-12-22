package Stack.java.sorts_28.practice;

import java.util.*;

/**
 * 求一组数据中最大的k个元素
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2023/12/22 9:41
 */
public class TopK {

    /**
     * 求一组数据中最大的k个元素
     * @param list  集合
     * @param k   最大的k个元素
     * @return java.util.List<java.lang.Integer>
     * @author Rickshaw
     * @since 2023/12/22 9:44
     */
    public List<Integer> getTopK(List<Integer> list, int k) {
        if (Objects.isNull(list) || list.size() < k) {
            throw new IllegalArgumentException();
        }
        CustomHeap<Integer> minHeap = new CustomHeap<>(k, false);
        //取集合前k个元素放进最小堆里面
        for (int i = 0; i < k; i++) {
            minHeap.add(list.get(i));
        }
        //将k+1之后的元素依次和堆顶比较, 大于堆顶则替换, 再堆化
        for (int i = k; i < list.size(); i++) {
            Integer compare = list.get(i);
            if (compare.compareTo(minHeap.peek()) > 0) {
                minHeap.remove();
                minHeap.add(compare);
            }
        }
        Integer[] sort = minHeap.sort(new Integer[0]);
        minHeap.printAll();
        return Arrays.asList(sort);
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        Collections.addAll(list, 2, 4, 0, 34, 43, 10, 25, 50, 101, 74, 73);
        TopK topK = new TopK();
        List<Integer> topList = topK.getTopK(list, 2);
    }

}
