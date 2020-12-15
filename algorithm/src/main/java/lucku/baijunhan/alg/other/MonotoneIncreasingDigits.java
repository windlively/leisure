package lucku.baijunhan.alg.other;

/**
 * 738. 单调递增的数字
 * 给定一个非负整数 N，找出小于或等于 N 的最大的整数，同时这个整数需要满足其各个位数上的数字是单调递增。
 *
 * （当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。）
 *
 * 示例 1:
 *
 * 输入: N = 10
 * 输出: 9
 * 示例 2:
 *
 * 输入: N = 1234
 * 输出: 1234
 * 示例 3:
 *
 * 输入: N = 332
 * 输出: 299
 * 说明: N 是在 [0, 10^9] 范围内的一个整数。
 */
public class MonotoneIncreasingDigits {

    public static void main(String[] args) {
        System.out.println(new Solution().monotoneIncreasingDigits(2323));
    }

    static class Solution {
        public int monotoneIncreasingDigits(int N) {
            char[] s = String.valueOf(N).toCharArray();
            String res = "" + s[s.length - 1];
            for(int i = s.length - 2; i >= 0; ){
                if(s[i] <= s[i+1]){
                    res = s[i] + res;
                    i --;
                }else{
                    res = "9".repeat(res.length());
                    s[i] = (char)(((int)s[i]) - 1);
                    s[i + 1] = '9';
                    if(s[i] < '0')
                        break;
                }

            }
            return Integer.parseInt(res);
        }
    }
}
