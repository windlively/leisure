package lucku.baijunhan.alg.array;

import java.util.*;

/**
 * 三数之和
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 *
 *
 * 示例：
 *
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 *
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 */
public class ThreeSum {

    public static void main(String[] args) {
        System.out.println(new Solution().threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
    }

    static class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            Arrays.sort(nums);
            Set<List<Integer>> res = new HashSet<>();
            for (int i = 0 ;i < nums.length - 2 && nums[i] <= 0; i++){
                int curNum = nums[i];
                int l = i + 1;
                int r = nums.length - 1;
                while(l < r){
                    int now = curNum + nums[l] + nums[r];
                    if(now == 0){
                        res.add(Arrays.asList(curNum, nums[l], nums[r]));
                    }
                    if(now > 0)
                        r --;
                    else
                        l ++;
                }
            }
            return new ArrayList<>(res);
        }
    }
}
