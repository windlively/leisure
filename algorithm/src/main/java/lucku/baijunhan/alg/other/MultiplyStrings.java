package lucku.baijunhan.alg.other;

/**
 * 43. 字符串相乘
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 *
 * 示例 1:
 *
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 *
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * 说明：
 *
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 */
public class MultiplyStrings {

    public static void main(String[] args) {
        System.out.println(new Solution().multiply("324", "11231"));
    }

    static class Solution {

        public String multiply(String num1, String num2) {
            if(num1.equals("0") || num2.equals("2"))
                return "0";
            StringBuilder result = new StringBuilder();
            char[] cs1 = num1.toCharArray();
            char[] cs2 = num2.toCharArray();
            int offset = 0;
            for (int i = cs1.length - 1; i >= 0; i--) {
                StringBuilder sb = new StringBuilder();
                int carry = 0;
                int m = cs1[i] - '0';
                for (int j = cs2.length - 1; j >= 0; j--) {
                    int n = cs2[j] - '0';
                    int k = m * n + carry;
                    if (k > 9) {
                        carry = k / 10;
                        k = k % 10;
                    } else {
                        carry = 0;
                    }
                    sb.insert(0, k);
                }
                if (carry > 0)
                    sb.insert(0, carry);
                result = sum(result, sb, offset);
                offset++;
            }
            return result.toString();
        }

        public StringBuilder sum(StringBuilder s1, StringBuilder s2, int offset) {
            if (s1 == null || s1.length() == 0) {
                return s2;
            }
            StringBuilder result = new StringBuilder();
            result.append(s1.substring(s1.length() - offset, s1.length()));
            int carry = 0;
            int i = s1.length() - offset - 1, j = s2.length() - 1;
            for (; i >= 0 && j >= 0; i--, j--) {
                int k = (s1.charAt(i) - '0') + (s2.charAt(j) - '0') + carry;
                if (k > 9) {
                    carry = 1;
                    k = k % 10;
                } else {
                    carry = 0;
                }

                result.insert(0, k);

            }

            carry = procRemain(s1, result, carry, i);

            carry = procRemain(s2, result, carry, j);

            if (carry > 0)
                result.insert(0, carry);
            return result;
        }

        private int procRemain(StringBuilder s1, StringBuilder result, int carry, int i) {
            while (i >= 0) {
                int k = (s1.charAt(i) - '0') + carry;
                if (k > 9) {
                    carry = 1;
                    k = k % 10;
                } else {
                    carry = 0;
                }
                result.insert(0, k);
                i--;
            }
            return carry;
        }

    }
}
