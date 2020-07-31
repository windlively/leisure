package lucku.baijunhan.alg.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 77. 组合
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 *
 * 示例:
 *
 * 输入: n = 4, k = 2
 * 输出:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 *
 */
public class Combinations {
    static class Solution {
        public List<List<Integer>> combine(int n, int k) {
            List<List<Integer>> result = new ArrayList<>();
            recursive(result, new ArrayList<>(), 1, n, k);
            return result;
        }
        public void recursive(List<List<Integer>> result, List<Integer> list, int index, int max, int k){
            if(list.size() == k){
                result.add(new ArrayList<>(list));
                return;
            }

            for(int i = index; i <= max; i++){
                list.add(i);
                recursive(result, list, i + 1, max, k);
                list.remove(list.size() - 1);
            }
        }
    }
}
