package lucku.baijunhan.alg.dp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 120. 三角形最小路径和
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 *
 * 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
 *
 *
 *
 * 例如，给定三角形：
 *
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 *
 * 说明：
 *
 * 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
 */
public class Triangle {

    public static void main(String[] args) {

    }

    static class Solution {

        static Map<Integer, Map<Integer, Integer>> map;


        public int minimumTotal(List<List<Integer>> triangle) {
            if(triangle.size() == 0) return 0;
            // map = new HashMap<>();
            // return minSum(triangle, 0, 0);

            // 动态规划
            int len = triangle.get(triangle.size() - 1).size();
            int[] dp = new int[len + 1];
            for(int i = len; i > 0; i--){
                List<Integer> list = triangle.get(i - 1);
                for(int j = 0; j < list.size(); j ++){
                    dp[j] = Math.min(dp[j], dp[j + 1]) + list.get(j);
                }
            }
            return dp[0];
        }

        // 记忆化递归
        public int minSum(List<List<Integer>> list, int level, int index){
            List<Integer> row = list.get(level);
            if(level == list.size() - 1)
                return row.get(index);
            Map<Integer, Integer> cache = map.computeIfAbsent(level, k -> new HashMap<>());
            Integer s;
            if((s = cache.get(index)) != null) return s;
            int val = row.get(index);
            s = Math.min(minSum(list, level + 1, index), minSum(list,level + 1, index + 1)) + val;
            cache.put(index, s);
            return s;
        }

    }

}
