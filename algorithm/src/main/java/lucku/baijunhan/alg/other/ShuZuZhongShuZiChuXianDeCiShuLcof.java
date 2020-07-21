package lucku.baijunhan.alg.other;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer 56 - I. 数组中数字出现的次数
 * 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [4,1,4,6]
 * 输出：[1,6] 或 [6,1]
 * 示例 2：
 *
 * 输入：nums = [1,2,10,4,1,4,3,3]
 * 输出：[2,10] 或 [10,2]
 *
 *
 * 限制：
 *
 * 2 <= nums.length <= 10000
 *
 */
public class ShuZuZhongShuZiChuXianDeCiShuLcof {

    public static void main(String[] args) {

    }

    static class Solution {
        public int[] singleNumbers(int[] nums) {
            int t = xor(nums);
            List<Integer> l1 = new ArrayList<>();
            List<Integer> l2 = new ArrayList<>();
            int i = 1;
            while((i << 1) <= t) i <<= 1;

            for(int n : nums){
                if((i & n) == 0) l1.add(n);
                else l2.add(n);
            }

            return new int[]{xor(l1), xor(l2)};
        }

        public int xor(int...ns){
            int t = ns[0];
            for(int i = 1; i < ns.length; i++)
                t ^= ns[i];
            return t;
        }

        public int xor(List<Integer> ns){
            if(ns.size() == 0) return 0;
            int t = ns.get(0);
            for(int i = 1; i < ns.size(); i++)
                t ^= ns.get(i);
            return t;
        }
    }
}
