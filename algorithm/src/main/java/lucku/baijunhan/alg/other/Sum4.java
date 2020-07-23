package lucku.baijunhan.alg.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 18. 四数之和
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 *
 * 注意：
 *
 * 答案中不可以包含重复的四元组。
 *
 * 示例：
 *
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 *
 * 满足要求的四元组集合为：
 * [
 *   [-1,  0, 0, 1],
 *   [-2, -1, 1, 2],
 *   [-2,  0, 0, 2]
 * ]
 *
 */
public class Sum4 {

    class Solution {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            if(nums.length < 4) return Collections.emptyList();
            Arrays.sort(nums);
            List<List<Integer>> result = new ArrayList<>();
            for(int a = 0; a < nums.length - 3; a ++){
                if(a > 0 && nums[a] == nums[a-1]) continue;
                for(int b = a + 1; b < nums.length - 2; b ++){
                    if(b > a + 1 && nums[b] == nums[b-1]) continue;
                    int l = b + 1, r = nums.length - 1;
                    while(l < r){
                        int n = nums[a] + nums[b] + nums[l] + nums[r];
                        if(n == target){
                            while(l < r && nums[r] == nums[r - 1]) r --;
                            while(l < r && nums[l+1] == nums[l]) l ++;
                            result.add(Arrays.asList(nums[a], nums[b], nums[l], nums[r]));
                            r--;
                            l ++;
                        }else if(n > target){
                            r --;
                        }else{
                            l ++;
                        }
                    }
                }
            }
            return result;
        }
    }
}
