package lucku.baijunhan.alg.other;

/**
 * 7. 整数反转
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 *
 * 示例 1:
 *
 * 输入: 123
 * 输出: 321
 *  示例 2:
 *
 * 输入: -123
 * 输出: -321
 * 示例 3:
 *
 * 输入: 120
 * 输出: 21
 * 注意:
 *
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−2^31,  2^31 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 *
 * 通过次数400,557
 */
public class ReverseInteger {

    public static void main(String[] args) {
        System.out.println(new Solution().reverse(Integer.MAX_VALUE));
    }

    static class Solution {
        public int reverse(int x) {
            int i = 0;
            int n = x;
            while(n != 0){
                // 数值溢出
                if(x > 0 && Integer.MAX_VALUE / 10 < i ||
                   x < 0 && Integer.MIN_VALUE / 10 > i) return 0;
                i = i * 10 + n % 10;
                n = n / 10;
            }
            return i;
        }
    }
}
