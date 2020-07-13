package lucku.baijunhan.alg.dp;

/**
 * 97. 交错字符串
 * 给定三个字符串 s1, s2, s3, 验证 s3 是否是由 s1 和 s2 交错组成的。
 *
 * 示例 1:
 *
 * 输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * 输出: true
 * 示例 2:
 *
 * 输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * 输出: false
 *
 */
public class InterleavingString {

    public static void main(String[] args) {
        System.out.println(new Solution().isInterleave("aabcc", "dbbca", "aadbbcbcac"));
    }

    static class Solution {
        public boolean isInterleave(String s1, String s2, String s3) {
            return dp(s1.toCharArray(), s2.toCharArray(), s3.toCharArray());
            // return recursive(s1.toCharArray(), 0, s2.toCharArray(), 0, s3.toCharArray(), 0);
        }

        // 暴力递归
        public boolean recursive(char[] s1, int i1, char[] s2, int i2,
                                 char[] s3, int i3){
            if(i3 == s3.length) return i1 == s1.length && i2 == s2.length;

            return i1 < s1.length && s1[i1] == s3[i3] && recursive(s1, i1 + 1, s2, i2, s3, i3 + 1) ||
                   i2 < s2.length && s2[i2] == s3[i3] && recursive(s1, i1, s2, i2 + 1, s3, i3 + 1);

        }

        /**
         * 动态规划 s1前缀与s2前缀组合是否为s3前缀
         * 二维数组版本:
         * boolean dp[][] = new boolean[s1.length() + 1][s2.length() + 1];
         *         for (int i = 0; i <= s1.length(); i++) {
         *             for (int j = 0; j <= s2.length(); j++) {
         *                 if (i == 0 && j == 0) {
         *                     dp[i][j] = true;
         *                 } else if (i == 0) {
         *                     dp[i][j] = dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
         *                 } else if (j == 0) {
         *                     dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
         *                 } else {
         *                     dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
         *                 }
         *             }
         *         }
         *         return dp[s1.length()][s2.length()];
         *
         * 作者：LeetCode
         * 链接：https://leetcode-cn.com/problems/interleaving-string/solution/jiao-cuo-zi-fu-chuan-by-leetcode/
         * 来源：力扣（LeetCode）
         * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
         */
        boolean dp(char[] s1, char[] s2, char[] s3){
            // 优化为一维数组
            int l1 = s1.length, l2 = s2.length, l3 = s3.length;
            if(l1 + l2 != l3 ) return false;
            boolean[] dp = new boolean[l1 + 1];
            for(int i = 0; i <= l1; i++)
                dp[i] = i == 0 || dp[i-1] && s1[i-1] == s3[i-1];
            for(int i = 1; i <= l2; i++){
                dp[0] = s2[i-1] == s3[i-1];
                for(int j = 1; j <= l1; j++){
                    dp[j] = dp[j-1] && s1[j-1] == s3[i + j - 1]
                            || dp[j] && s2[i - 1] == s3[i + j - 1];
                }
            }
            return dp[l1];
        }
    }
}
