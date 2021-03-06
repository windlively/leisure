package lucku.baijunhan.alg.dp;

/**
 * 343. 整数拆分
 * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
 *
 * 示例 1:
 *
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1。
 * 示例 2:
 *
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
 * 说明: 你可以假设 n 不小于 2 且不大于 58。
 */
public class IntegerBreak {

    static class Solution {
        public int integerBreak(int n) {
            int[] dp = new int[n + 1];
            for(int i = 2; i <= n; i++){
                int max = 0;
                for(int j = 1; j <= i; j ++){
                    max = max(max, j * (i - j), j * dp[i - j]);
                }
                dp[i] = max;
            }
            return dp[n];

            // 数学公式: 3*3*3*...*(2/4)最大
            // if(n <= 3) return n - 1;
            // int a = n / 3, b = n % 3;
            // if(b == 0) return (int)Math.pow(3, a);
            // if(b == 1) return (int)Math.pow(3, a - 1) * 4;
            // return (int)Math.pow(3, a) * 2;


            // 作者：jyd
            // 链接：https://leetcode-cn.com/problems/integer-break/solution/343-zheng-shu-chai-fen-tan-xin-by-jyd/
            // 来源：力扣（LeetCode）
            // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        }

        int max(int... ns){
            int max = ns[0];
            for(int n : ns) if(n > max) max = n;
            return max;
        }
    }
}
