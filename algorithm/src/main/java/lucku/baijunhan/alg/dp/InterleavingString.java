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
            return isInterleave(s1.toCharArray(), 0, s2.toCharArray(), 0, s3.toCharArray(), 0);
        }

        public boolean isInterleave(char[] s1, int i1, char[] s2, int i2, char[] s3, int i3){
            if(i3 == s3.length) return i1 == s1.length && i2 == s2.length;
            if(i1 == s1.length) return i2 < s2.length && s2[i2] == s3[i3] && isInterleave(s1, i1, s2, i2 + 1, s3, i3 + 1);
            if(i2 == s2.length) return i1 < s1.length && s1[i1] == s3[i3] && isInterleave(s1, i1 + 1, s2, i2, s3, i3 + 1);

            return s1[i1] == s3[i3] && isInterleave(s1, i1 + 1, s2, i2, s3, i3 + 1) ||
                   s2[i2] == s3[i3] && isInterleave(s1, i1, s2, i2 + 1, s3, i3 + 1);

        }
    }
}
