package lucku.baijunhan.alg.other;

import java.util.ArrayList;
import java.util.List;

/**
 * 78. 子集
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 *
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 */
public class Subsets {

    public static void main(String[] args) {
        System.out.println(new Solution().subsets(new int[]{1, 2, 3, 4, 5}));
    }


    static class Solution {
        public List<List<Integer>> subsets(int[] nums) {

            List<List<Integer>> result = new ArrayList<>();
            // 幂集性质
            result.add(new ArrayList<>());
            for(int i = 0; i < nums.length; i ++){
                List<List<Integer>> tmp = new ArrayList<>(result);
                for(List<Integer> list : result){
                    List<Integer> copy = new ArrayList<>(list);
                    copy.add(nums[i]);
                    tmp.add(copy);
                }
                result = tmp;
            }






            // recursive(result, new ArrayList<>(), nums, 0);
            return result;
        }

        // 回溯递归
        public void recursive(List<List<Integer>> result, List<Integer> list, int[] nums, int index){

            if(index == nums.length){
                result.add(new ArrayList<>(list));
                return;
            }

            list.add(nums[index]);
            recursive(result, list, nums, index + 1);
            list.remove(list.size() - 1);
            // if(index > 0 && nums[index - 1] == nums[index]) return;
            recursive(result, list, nums, index + 1);
        }


    }
}
