package lucku.baijunhan.alg.array;

/**
 * 67. 二进制求和
 * 给你两个二进制字符串，返回它们的和（用二进制表示）。
 *
 * 输入为 非空 字符串且只包含数字 1 和 0。
 *
 *
 *
 * 示例 1:
 *
 * 输入: a = "11", b = "1"
 * 输出: "100"
 * 示例 2:
 *
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 *
 *
 * 提示：
 *
 * 每个字符串仅由字符 '0' 或 '1' 组成。
 * 1 <= a.length, b.length <= 10^4
 * 字符串如果不是 "0" ，就都不含前导零。
 */
public class AddBinary {

    public static void main(String[] args) {
        System.out.println(new Solution().addBinary("000", "0"));
    }

    static class Solution {
        public String addBinary(String a, String b) {
            StringBuilder result = new StringBuilder();
            int bIndex = b.length()-1;
            byte carry = 0;
            for(int i = a.length()-1; i >= 0; i--){
                byte aNum = getNum(a.charAt(i));
                if(bIndex >= 0){
                    byte bNum = getNum(b.charAt(bIndex));
                    byte sum = (byte)(aNum + bNum + carry);
                    if(sum > 1){
                        result.insert(0, sum & 1);
                        carry = 1;
                    }else{
                        result.insert(0,sum);
                        carry = 0;
                    }
                    bIndex --;
                }else{
                    byte sum = (byte)(aNum + carry);
                    if(sum > 1){
                        result.insert(0, sum & 1);
                        carry = 1;
                    }else{
                        result.insert(0,sum);
                        carry = 0;
                    }
                }
            }
            while(bIndex >= 0){
                byte bNum = getNum(b.charAt(bIndex));
                byte sum = (byte)(bNum + carry);
                if(sum > 1){
                    result.insert(0, sum & 1);
                    carry = 1;
                }else{
                    result.insert(0,sum);
                    carry = 0;
                }
                bIndex --;
            }
            if(carry > 0) result.insert(0, 1);
            return result.toString();
        }

        byte getNum(char ch){
            return (byte)(ch - '0');
        }

    }

}
