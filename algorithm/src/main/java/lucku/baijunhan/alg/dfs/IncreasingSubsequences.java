package lucku.baijunhan.alg.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * 491. 递增子序列
 * 给定一个整型数组, 你的任务是找到所有该数组的递增子序列，递增子序列的长度至少是2。
 *
 * 示例:
 *
 * 输入: [4, 6, 7, 7]
 * 输出: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
 * 说明:
 *
 * 给定数组的长度不会超过15。
 * 数组中的整数范围是 [-100,100]。
 * 给定数组中可能包含重复数字，相等的数字应该被视为递增的一种情况。
 *
 */
public class IncreasingSubsequences {

    public static void main(String[] args) {
        System.out.println(new Solution().findSubsequences(new int[]{4, 6, 7, 7}));
    }

    static class Solution {

        public List<List<Integer>> findSubsequences(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            dfs(result, new ArrayList<>(), nums, 0);
            return result;
        }

        public void dfs(List<List<Integer>> result, List<Integer> list, int[] nums, int index){
            if(index == nums.length) {
                if(list.size() > 1) result.add(new ArrayList<>(list));
                return;
            }
            if(list.isEmpty() || list.get(list.size() - 1) <= nums[index]){
                list.add(nums[index]);
                dfs(result, list, nums, index + 1);
                list.remove(list.size() - 1);
            }
            if(list.isEmpty() || list.get(list.size() - 1) != nums[index])
                dfs(result, list, nums, index + 1);
        }
    }

}
