package lucku.baijunhan.alg.other;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 347. 前 K 个高频元素
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 *
 * 输入: nums = [1], k = 1
 * 输出: [1]
 */
public class TopKFrequentElements {

    public static void main(String[] args) {

    }

    static class Solution {
        public int[] topKFrequent(int[] nums, int k) {
            Map<Integer, Integer> map = new HashMap<>();
            for(int n : nums)
                map.put(n, map.getOrDefault(n, 0) + 1);
            PriorityQueue<int[]> queue = new PriorityQueue<>((n1, n2) -> n2[1] - n1[1]);
            map.forEach((n, c) -> queue.offer(new int[]{n, c}));
            int[] result = new int[k];
            for(int i = 0; i < k ; i++){
                result[i] = queue.poll()[0];
            }
            return result;
        }
    }

}
