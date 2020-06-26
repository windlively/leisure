package lucku.baijunhan.alg.string;

/**
 * 44. 通配符匹配
 * 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
 *
 * '?' 可以匹配任何单个字符。
 * '*' 可以匹配任意字符串（包括空字符串）。
 * 两个字符串完全匹配才算匹配成功。
 *
 * 说明:
 *
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
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
 * p = "*"
 * 输出: true
 * 解释: '*' 可以匹配任意字符串。
 * 示例 3:
 *
 * 输入:
 * s = "cb"
 * p = "?a"
 * 输出: false
 * 解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
 * 示例 4:
 *
 * 输入:
 * s = "adceb"
 * p = "*a*b"
 * 输出: true
 * 解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
 * 示例 5:
 *
 * 输入:
 * s = "acdcb"
 * p = "a*c?b"
 * 输出: false
 */
public class WildcardMatching {


    public static void main(String[] args) {
        System.out.println(new Solution().isMatch("abbbaaababbaaabaaabbbabbbbaaabbaaababaabbbbbbaababbabababbababaaabbbbbabababaababaaaaaaabbbaabaabbbaabbabaababbabaababbbabbaaabbbaaaababbaaabbaabaabbbbbaaababaabaabaaabbabaabbbabbbaabbababaabbbbbbbbaaa", "*ba***bba*b**abbaa***a*****b*a*bb*b***a*bbb***a***bba*****a****a*a*b**aaaba*aab*a*aa***a*a*b**b**a*b*"));
    }

    static class Solution {
        public boolean isMatch(String s, String p) {
            return optimizeDP(s.toCharArray(), p.toCharArray());
        }

        // 递归, 超时
        boolean match(char[] s, int sI, char[] p, int pI){
            if(sI == s.length && (pI == p.length || match(p, pI)))
                return true;
            if(sI == s.length && pI != p.length)
                return false;

            if(pI < p.length && p[pI] == '*'){
                while(pI < p.length-1 && p[pI+1]=='*') pI ++;
                for(int i = sI; i < s.length; i++){
                    if(pI < p.length-1 && (s[i] == p[pI+1] || p[pI+1]=='?') && match(s, i, p, pI + 1)){
                        return true;
                    }
                }
                return (pI == p.length  - 1);
            }


            if(sI < s.length &&pI < p.length && (s[sI] == p[pI] || p[pI] == '?'))
                return match(s, sI + 1, p, pI + 1);
            return false;
        }

        boolean match(char[] p, int pI){
            for(;pI < p.length; pI ++){
                if(p[pI] != '*') return false;
            }
            return true;
        }

        // 回溯
        boolean match(String s, String p){
            int star = -1;
            int lastMatch = 0;
            int sI = 0, pI = 0;
            for(; sI < s.length() ;){
                if( pI < p.length() && (s.charAt(sI) == p.charAt(pI) || p.charAt(pI) == '?')){
                    sI ++;
                    pI ++;
                    continue;
                }
                if(pI < p.length() && p.charAt(pI) == '*'){
                    lastMatch = sI;
                    star = pI ++;
                    continue;
                }
                if(star != -1){
                    sI = ++lastMatch;
                    pI = star + 1;
                    continue;
                }
                return false;
            }
            while(pI < p.length() && p.charAt(pI) == '*') pI ++;
            return  pI == p.length();
        }

        // 动态规划
        boolean dp(char[] s, char[] p){
            boolean[][] dp = new boolean[s.length + 1][p.length+1];
            dp[0][0] = true;

            for(int i = 1; i <= p.length && p[i-1] == '*'; i++){
                dp[0][i] = true;
            }

            for(int i = 1; i <= s.length; i++){
                for(int j = 1; j <= p.length; j++){
                    dp[i][j] = dp[i-1][j-1] && (s[i-1]==p[j-1] || p[j-1]=='?')
                               || (dp[i][j-1] || dp[i-1][j]) && p[j-1] == '*' ;
                }
            }
            return dp[s.length][p.length];
        }

        // 优化后的递归  使用一维数组
        boolean optimizeDP(char[] s, char[] p){
            boolean[] dp = new boolean[p.length+1];
            dp[0] = true;
            for(int i=1;i<dp.length && p[i-1]=='*';i++) dp[i]=true;
            boolean leftMatch = false;
            boolean curMatch = false;

            for(int i = 1; i <= s.length; i++){
                leftMatch = false;
                for(int j = 1; j <= p.length; j++){
                    curMatch = p[j-1] == '*' && (leftMatch || dp[j]) || dp[j-1] && (p[j-1]==s[i-1] || p[j-1] == '?');
                    dp[j-1] = leftMatch;
                    leftMatch = curMatch;
                }
                dp[p.length] = curMatch;
            }
            return dp[p.length];
        }
    }
}
