package lucku.baijunhan.alg.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 组合总和 II
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的每个数字在每个组合中只能使用一次。
 *
 * 说明：
 *
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1:
 *
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 *   [1, 7],
 *   [1, 2, 5],
 *   [2, 6],
 *   [1, 1, 6]
 * ]
 * 示例 2:
 *
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 *   [1,2,2],
 *   [5]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CombinationSumII {

    public static void main(String[] args) {
        System.out.println(new Solution().combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8));
    }


    static class Solution {
        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            Arrays.sort(candidates);
            List<List<Integer>> result = new ArrayList<>();
            recursive(result, new ArrayList<>(), candidates, target, 0);
            return result;
        }

        void recursive(List<List<Integer>> result, List<Integer> list, int[] candidates, int target, int index){
            if(target == 0){
                if(!result.contains(list))
                    result.add(list);
                return;
            }

            if(index >= candidates.length || candidates[index] > target) return;

            for (int i = index;i < candidates.length; i ++){
                if(candidates[i] > target)
                    break;
                List<Integer> l = new ArrayList<>(list);
                l.add(candidates[i]);
                recursive(result, l, candidates, target-candidates[i], i + 1);
            }
        }
    }

}
