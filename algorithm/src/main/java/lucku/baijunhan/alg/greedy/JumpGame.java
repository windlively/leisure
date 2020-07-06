package lucku.baijunhan.alg.greedy;

import java.util.Map;

/**
 * 55. 跳跃游戏
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 判断你是否能够到达最后一个位置。
 *
 * 示例 1:
 *
 * 输入: [2,3,1,1,4]
 * 输出: true
 * 解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
 * 示例 2:
 *
 * 输入: [3,2,1,0,4]
 * 输出: false
 * 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
 */
public class JumpGame {

    public static void main(String[] args) {
        System.out.println(new Solution().canJump(new int[]{2, 3, 1, 1, 4}));
    }

    static class Solution {
        public boolean canJump(int[] nums) {
            // return canJump(nums,0, new HashMap<>());
            return greedy1(nums);
        }

        // 递归
        public boolean canJump(int[] nums, int index, Map<Integer, Boolean> backup){
            if(index >= nums.length - 1)
                return true;

            int n = nums[index];
            if(n == 0) return false;
            for(int i = 1; i <= n; i++){
                Boolean bool = backup.get(index + i);
                if(bool != null && bool) return true;
                bool = canJump(nums, index + i, backup);
                if(bool) return true;
                backup.put(index + i, bool);
            }
            return false;
        }

        boolean greedy1(int[] nums){
            int maxLen = 0;
            for(int i = 0; i < nums.length-1; i++){
                maxLen = Math.max(maxLen-1, nums[i]);
                if(maxLen == 0) return false;
            }
            return true;
        }

        boolean greedy2(int[] nums){
            int maxLen = 0;
            for (int i = 0; i < nums.length ; i++) {
                if(i <= maxLen)
                    maxLen = Math.max(nums[i] + i, maxLen);
            }
            return  maxLen >= nums.length - 1;
        }

    }
}
