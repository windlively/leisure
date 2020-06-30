package lucku.baijunhan.alg.string;

/**
 * 10. 正则表达式匹配
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 *
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 *
 * 说明:
 *
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 * 示例 1:
 *
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 *
 * 输入:
 * s = "aa"
 * p = "a*"
 * 输出: true
 * 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * 示例 3:
 *
 * 输入:
 * s = "ab"
 * p = ".*"
 * 输出: true
 * 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * 示例 4:
 *
 * 输入:
 * s = "aab"
 * p = "c*a*b"
 * 输出: true
 * 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 * 示例 5:
 *
 * 输入:
 * s = "mississippi"
 * p = "mis*is*p*."
 * 输出: false
 */
public class RegularExpressionMatching {


    public static void main(String[] args) {
        System.out.println(new Solution().isMatch("aab", "c*a*b"));
    }

    static class Solution {
        public boolean isMatch(String s, String p) {
            return recursive(s, 0, p, 0);
        }

        boolean recursive(String str, int sIndex, String p, int pIndex) {
            if (str.length() == sIndex && p.length() == pIndex)
                return true;
            if (str.length() != sIndex && p.length() == pIndex)
                return false;

            boolean currentMatch = str.length() > sIndex && p.charAt(pIndex) == str.charAt(sIndex) || p.charAt(pIndex) == '.' && str.length() != sIndex;
            if (pIndex < p.length() - 1 && p.charAt(pIndex + 1) == '*') {
                if (currentMatch)
                    return recursive(str, sIndex + 1, p, pIndex + 2) ||
                           recursive(str, sIndex + 1, p, pIndex) ||
                           recursive(str, sIndex, p, pIndex + 2);
                else
                    return recursive(str, sIndex, p, pIndex + 2);
            }

            if (currentMatch)
                return recursive(str, sIndex + 1, p, pIndex + 1);
            return false;
        }
    }

    /*
        // C语言
        bool match(char* s, char* p){
            if(*s == '\0' && *p == '\0')
                return true;
            if(*s != '\0' && *p == '\0')
                return false;
            if(*(p + 1) == '*'){
                if(*p == *s || (*p == '.' && *s != '\0'))
                    return match(s + 1, p + 2)
                        || match(s + 1, p)
                        || match(s, p + 2);
                else
                    return match(s, p+ 2);

            }
            if(*s == *p || *p == '.' && *s != '\0')
                return match(s + 1, p + 1);
            return false;
        }

        bool isMatch(char * s, char * p){
            return match(s,p);
        }

     */
}
