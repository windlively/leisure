package lucku.baijunhan.alg.dp;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 32. 最长有效括号
 * 难度
 * 困难
 *
 * 715
 *
 * 收藏
 *
 * 分享
 * 切换为英文
 * 关注
 * 反馈
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 *
 * 示例 1:
 *
 * 输入: "(()"
 * 输出: 2
 * 解释: 最长有效括号子串为 "()"
 * 示例 2:
 *
 * 输入: ")()())"
 * 输出: 4
 * 解释: 最长有效括号子串为 "()()"
 */
public class LongestValidParentheses {

    public static void main(String[] args) {
        System.out.println(new Solution().longestValidParentheses(")(()()()())((())()())))(()(())()()()()()())"));
    }

    static class Solution {
        public int longestValidParentheses(String s) {
            char[] cs = s.toCharArray();
            // 记录')'结尾的时候最大有效括号长度
            int[] dp = new int[cs.length + 1];
            int max = 0;
            // int[] 0为括号字符, 1为所处的索引位置
            Deque<int[]> stack = new LinkedList<>();
            for(int i = 0; i < cs.length;){
                if(stack.isEmpty()){
                    stack.push(new int[]{(int)cs[i], i++});
                    continue;
                }
                // 如果当前位置为')'且能匹配到'('
                while (i < cs.length && cs[i] == ')' && !stack.isEmpty() && stack.peek()[0] == '('){
                    // dp数组i+1位置的')'有效括号长度 = 栈中'('前一个位置的值 + 2  + 'i'处的值
                    dp[i+1] = dp[stack.pop()[1]] + 2 + dp[i];
                    max = Math.max(dp[i + 1], max);
                    i ++;
                }
                if(i < cs.length) {
                    stack.push(new int[]{(int) cs[i], i});
                    dp[i + 1] = 0;
                }
                i ++;
            }
            return max;
        }
    }
}
