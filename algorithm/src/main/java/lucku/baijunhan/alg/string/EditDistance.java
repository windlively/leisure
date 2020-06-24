package lucku.baijunhan.alg.string;

/**
 *
 * 72. 编辑距离
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 *
 * 你可以对一个单词进行如下三种操作：
 *
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 *
 *
 * 示例 1：
 *
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * 示例 2：
 *
 * 输入：word1 = "intention", word2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 *
 */
public class EditDistance {

    public static void main(String[] args) {
        System.out.println(new Solution().minDistance("horse", "ros"));
    }

    static class Solution {
        public int minDistance(String word1, String word2) {
            char[] w1 = word1.toCharArray();
            char[] w2 = word2.toCharArray();

            // dp[i][j]代表了当前状态的最小操作数
            int[][] dp = new int[w1.length + 1][w2.length + 1];

            // 0行代表了w1为空字符的时候的(最小)操作数
            for(int i = 0; i <= w1.length; i++ ){
                dp[i][0] = i;
            }
            // 0列代表了w2为空的时候的(最小)操作数
            for(int i = 0; i <= w2.length; i++){
                dp[0][i] = i;
            }

            /*
                 0    1   2   3
                      r   o   s
                1 h   1   2   3     h -> ros
                2 o   2   1   2     ho -> ros
                3 r   2   2   2     hor -> ros
                4 s   3   3   2     hors -> ros
                5 e   4   4   3     horse -> ros
            */

            for(int i = 0; i < w1.length; i++){
                for (int j = 0; j < w2.length; j ++){
                    if(w1[i] == w2[j]){
                        dp[i + 1][j + 1] = dp[i][j];
                    } else {
                        dp[i + 1][j + 1] = 1 + min(dp[i][j + 1], dp[i + 1][j], dp[i][j]);
                    }
                }
            }
            // 最后一个位置代表了最终结果的最小操作数
            return dp[w1.length][w2.length];
        }

        int min(int... ns){
            int min = ns[0];
            for(int n : ns) if(n < min) min = n;
            return min;
        }
    }
}
