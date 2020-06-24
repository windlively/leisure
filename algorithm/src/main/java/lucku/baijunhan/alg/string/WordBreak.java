package lucku.baijunhan.alg.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 139. 单词拆分
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 *
 * 说明：
 *
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * 示例 1：
 *
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 * 示例 2：
 *
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 *      注意你可以重复使用字典中的单词。
 * 示例 3：
 *
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 *
 */
public class WordBreak {

    public static void main(String[] args) {
        System.out.println(new Solution().wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab", Arrays.asList("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa")));
    }

    static class Solution {
        public boolean wordBreak(String s, List<String> wordDict) {
            List<Integer> dp = new ArrayList<>();
            dp.add(0);
            for(int i = 0; i <= s.length(); i++){
                for(int j = 0; j < dp.size(); j++){
                    if(wordDict.contains(s.substring(dp.get(j),i))){
                        dp.add(i);
                        break;
                    }
                }
            }
            return dp.get(dp.size()-1) == s.length();
        }


        // 递归方案，解答超时
        boolean match(int start, List<String> words, String s){
            for(String w : words){
                boolean match = true;
                if(w.length() + start <= s.length()){
                    for(int i = 0; i < w.length(); i++){
                        if(w.charAt(i) != s.charAt(start + i)){
                            match = false;
                            break;
                        }
                    }
                    if(match){
                        if(start + w.length() == s.length())
                            return true;
                        if(match(start + w.length(), words, s))
                            return true;
                    }
                }
            }
            return false;
        }
    }
}
