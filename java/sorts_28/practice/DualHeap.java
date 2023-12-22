package Stack.java.sorts_28.practice;

import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;

/**
 * 双堆求中值
 *
 * @author Rickshaw
 * @version 1.0
 * @since 2023/12/21 14:11
 */
public class DualHeap {

    /**
     * 最大堆, 存放 < 中值的数
     */
    private final PriorityQueue<Integer> max;

    /**
     * 最小堆, 存放 >= 中值的数
     */
    private final PriorityQueue<Integer> min;

    public DualHeap() {
        this.min = new PriorityQueue<>();
        this.max = new PriorityQueue<>(Comparator.reverseOrder());
    }

    /**
     * 新增, 动态保证两个堆顶就是中值的位置
     * @param e 元素
     * @author Rickshaw
     * @since 2023/12/21 15:12
     */
    public void add(Integer e) {
        if (this.max.size() == this.min.size()) {
            //把最小堆的堆顶放入最大堆, 并堆化
            this.min.offer(e);
            this.max.offer(this.min.poll());
        } else {
            //把最大堆的堆顶放入最小堆, 并堆化
            this.max.offer(e);
            this.min.offer(this.max.poll());
        }
    }

    /**
     * 获取中位数
     * @return double
     * @author Rickshaw
     * @since 2023/12/21 15:44
     */
    public double getMedian() {
        if (this.max.size() == this.min.size()) {
            return Objects.requireNonNull(this.min.peek()) / 2.0 + this.max.peek() / 2.0;

        }
        return Objects.requireNonNull(this.max.peek());
    }

    public static void main(String[] args) {
        DualHeap dualHeap = new DualHeap();
        dualHeap.add(5);
        dualHeap.add(1);
        dualHeap.add(60);
        dualHeap.add(100);
        dualHeap.add(10);
        dualHeap.add(43);
        dualHeap.add(25);
        dualHeap.add(30);
        dualHeap.add(70);
        dualHeap.add(55);
        System.out.println("dualHeap.getMedian() = " + dualHeap.getMedian());
    }
}
