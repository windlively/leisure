package lucku.baijunhan.alg.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 90. 子集 II
 * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 *
 * 输入: [1,2,2]
 * 输出:
 * [
 *   [2],
 *   [1],
 *   [1,2,2],
 *   [2,2],
 *   [1,2],
 *   []
 * ]
 *
 */
public class SubsetsII {

    public static void main(String[] args) {
        System.out.println(new Solution().subsetsWithDup(new int[]{1, 2, 2, 2, 3, 3, 4, 5, 6, 6}));
    }

    static class Solution {
        public List<List<Integer>> subsetsWithDup(int[] nums) {
            Arrays.sort(nums);
            List<List<Integer>> result = new ArrayList<>();
            // 幂集性质
            result.add(new ArrayList<>());
            int oldCount = 1;
            for(int i = 0; i < nums.length; i ++){
                int size = result.size();
                for(int j = 0; j < size; j ++){
                    // 去重, 只添加重复数字之后的子集
                    if(i > 0 && nums[i] == nums[i-1] && j < oldCount)
                        continue;
                    List<Integer> list = result.get(j);
                    List<Integer> copy = new ArrayList<>(list);
                    copy.add(nums[i]);
                    result.add(copy);
                }

                oldCount = size;

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
            // 去重
            if(list.size() > 0 && list.get(list.size() - 1) == nums[index]) return;
            recursive(result, list, nums, index + 1);
        }
    }
}
