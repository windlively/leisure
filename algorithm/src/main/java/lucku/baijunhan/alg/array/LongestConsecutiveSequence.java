package lucku.baijunhan.alg.array;

import java.util.HashSet;
import java.util.Set;

/**
 * 128. 最长连续序列
 * 给定一个未排序的整数数组，找出最长连续序列的长度。
 *
 * 要求算法的时间复杂度为 O(n)。
 *
 * 示例:
 *
 * 输入: [100, 4, 200, 1, 3, 2]
 * 输出: 4
 * 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
 */
public class LongestConsecutiveSequence {

    public static void main(String[] args) {
        System.out.println(new Solution().longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));
    }

    static class Solution {
        public int longestConsecutive(int[] nums) {
            if(nums.length == 0) return 0;
            Set<Integer> set = new HashSet<>(nums.length);
            for(int n : nums) set.add(n);
            int maxLen = 1;
            for(int n : nums){
                if(set.remove(n)){
                    int len = 1;
                    int i = n;
                    // 向前找
                    while(set.remove(--i)) len ++;
                    i = n;
                    // 向后找
                    while(set.remove(++i)) len ++;
                    maxLen = Math.max(len, maxLen);
                }
            }
            return maxLen;
        }
    }
}
