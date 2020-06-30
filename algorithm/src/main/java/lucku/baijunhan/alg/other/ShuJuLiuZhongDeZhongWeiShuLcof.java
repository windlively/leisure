package lucku.baijunhan.alg.other;

import java.util.PriorityQueue;

/**
 * 剑指 Offer 41. 数据流中的中位数
 * 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
 *
 * 例如，
 *
 * [2,3,4] 的中位数是 3
 *
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 *
 * 设计一个支持以下两种操作的数据结构：
 *
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 * 示例 1：
 *
 * 输入：
 * ["MedianFinder","addNum","addNum","findMedian","addNum","findMedian"]
 * [[],[1],[2],[],[3],[]]
 * 输出：[null,null,null,1.50000,null,2.00000]
 * 示例 2：
 *
 * 输入：
 * ["MedianFinder","addNum","findMedian","addNum","findMedian"]
 * [[],[2],[],[3],[]]
 * 输出：[null,null,2.00000,null,2.50000]
 *
 *
 * 限制：
 *
 * 最多会对 addNum、findMedia进行 50000 次调用。
 */
public class ShuJuLiuZhongDeZhongWeiShuLcof {

    class MedianFinder {

        // 大顶堆
        PriorityQueue<Integer> greater = new PriorityQueue<>();
        // 小顶堆
        PriorityQueue<Integer> less = new PriorityQueue<>((i, j) -> j - i);

        /**
         * initialize your data structure here.
         */
        public MedianFinder() {

        }

        public void addNum(int num) {
            greater.offer(num);
            less.offer(greater.poll());

            if ((length() & 1) == 0) {
                greater.offer(less.poll());
            }

        }

        @SuppressWarnings("ConstantConditions")
        public double findMedian() {
            if (length() == 0) return 0;
            if ((length() & 1) == 0) {
                return (less.peek() + greater.peek()) / 2.0;
            }
            return less.peek();
        }

        private int length() {
            return less.size() + greater.size();
        }

    }

}
