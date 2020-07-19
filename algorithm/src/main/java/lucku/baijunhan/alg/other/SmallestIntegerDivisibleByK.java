package lucku.baijunhan.alg.other;

/**
 * 1015. 可被 K 整除的最小整数
 * 给定正整数 K，你需要找出可以被 K 整除的、仅包含数字 1 的最小正整数 N。
 *
 * 返回 N 的长度。如果不存在这样的 N，就返回 -1。
 *
 *
 *
 * 示例 1：
 *
 * 输入：1
 * 输出：1
 * 解释：最小的答案是 N = 1，其长度为 1。
 * 示例 2：
 *
 * 输入：2
 * 输出：-1
 * 解释：不存在可被 2 整除的正整数 N 。
 * 示例 3：
 *
 * 输入：3
 * 输出：3
 * 解释：最小的答案是 N = 111，其长度为 3。
 *
 *
 * 提示：
 *
 * 1 <= K <= 10^5
 */
public class SmallestIntegerDivisibleByK {

    public static void main(String[] args) {
        System.out.println(new Solution().smallestRepunitDivByK(39));
    }

    static class Solution {
        public int smallestRepunitDivByK(int K) {
            int i = 1;
            int c = 1;
            while(c <= K) {
                if(i % K == 0) return c;
                i = (i%K) * 10 + 1;
                c ++;
            }
            return -1;
        }
    }

}
