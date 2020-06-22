package lucku.baijunhan.alg.array;

import java.util.Arrays;

/**
 * 最接近的三数之和
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 *
 *
 *
 * 示例：
 *
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 *
 *
 * 提示：
 *
 * 3 <= nums.length <= 10^3
 * -10^3 <= nums[i] <= 10^3
 * -10^4 <= target <= 10^4
 */
public class ThreeSumClosest {

    public static void main(String[] args) {
        System.out.println(new Solution().threeSumClosest(new int[]{-1, 2, 1, -4}, 1));
    }

    static class Solution {
        public int threeSumClosest(int[] nums, int target) {
            Arrays.sort(nums);
            int min = Integer.MAX_VALUE;
            int res = 0;
            for (int i = 0 ;i < nums.length - 2; i++){
                int curNum = nums[i];
                int l = i + 1;
                int r = nums.length - 1;
                while(l < r){
                    int now = curNum + nums[l] + nums[r];
                    int minus = now - target;
                    if(minus == 0)
                        return now;
                    if(Math.abs(minus) < min){
                        res = now;
                        min = Math.abs(minus);
                    }
                    if(now > target)
                        r --;
                    else
                        l ++;
                }
            }
            return res;
        }
    }
}
