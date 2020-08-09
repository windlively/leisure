package lucku.baijunhan.alg.dfs;

import java.util.HashMap;
import java.util.Map;

/**
 * 329. 矩阵中的最长递增路径
 * 给定一个整数矩阵，找出最长递增路径的长度。
 *
 * 对于每个单元格，你可以往上，下，左，右四个方向移动。 你不能在对角线方向上移动或移动到边界外（即不允许环绕）。
 *
 * 示例 1:
 *
 * 输入: nums =
 * [
 *   [9,9,4],
 *   [6,6,8],
 *   [2,1,1]
 * ]
 * 输出: 4
 * 解释: 最长递增路径为 [1, 2, 6, 9]。
 * 示例 2:
 *
 * 输入: nums =
 * [
 *   [3,4,5],
 *   [3,2,6],
 *   [2,2,1]
 * ]
 * 输出: 4
 * 解释: 最长递增路径是 [3, 4, 5, 6]。注意不允许在对角线方向上移动。
 *
 */
public class LongestIncreasingPathInAMatrix {

    static class Solution {

        private Map<String, Integer> map;

        public int longestIncreasingPath(int[][] matrix) {
            if(matrix.length == 0) return 0;
            int max = 0;
            map = new HashMap<>();
            for(int i = 0; i < matrix.length; i ++){
                for(int j = 0; j < matrix[0].length; j ++){
                    max = max(max, recursive(matrix, i, j, -1));
                }
            }
            return max;
        }

        public int recursive(int[][] m, int i, int j, int k){
            Integer result;
            if(i >= 0 && i < m.length && j >= 0 && j < m[0].length && m[i][j] > k){
                if((result = map.get(i + "-" + j)) != null) return result;
                result = 1 + max(
                        recursive(m, i + 1, j, m[i][j]),
                        recursive(m, i, j + 1, m[i][j]),
                        recursive(m, i - 1, j, m[i][j]),
                        recursive(m, i, j - 1, m[i][j])
                );
                map.put(i + "-" + j, result);
                return result;
            }
            return 0;
        }

        int max(int...ns){
            int max = ns[0];
            for(int n : ns) if(n > max) max = n;
            return max;
        }
    }
}
