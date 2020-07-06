package lucku.baijunhan.alg.greedy;

/**
 * 45. 跳跃游戏 II
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 *
 * 示例:
 *
 * 输入: [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * 说明:
 *
 * 假设你总是可以到达数组的最后一个位置。
 */
public class JumpGameII {

    public static void main(String[] args) {
        System.out.println(new Solution().jump(new int[]{2, 3, 1, 1, 4}));
    }

     static class Solution {
        public int jump(int[] nums) {
            // dp[i][0]代表当前位置最小步数, dp[i][1]代表当前位置能到达的最远距离
            // int[][] dp = new int[nums.length][2];
            // dp[0][1] = nums[0];
            // int s = 0;
            // for(int i = 1; i < nums.length; i++){

            //     if(i > dp[s][1]) s ++;

            //     if(i <= dp[s][1]){
            //         dp[i][0] = s + 1;
            //         dp[s + 1][1] = Math.max(dp[s + 1][1], nums[i] + i);
            //     }
            // }
            // return dp[nums.length - 1][0];
            return jump1(nums);
        }

        // 贪心算法
        int jump1(int[] nums){
            int end = 0;
            int maxLen = 0;
            int s = 0;
            for(int i = 0; i < nums.length - 1; i ++){
                maxLen = Math.max(maxLen, nums[i] + i);
                if(end == i) {
                    end = maxLen;
                    s ++;
                }
            }
            return s;
        }
    }
}
