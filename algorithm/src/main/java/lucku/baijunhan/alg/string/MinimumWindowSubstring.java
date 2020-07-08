package lucku.baijunhan.alg.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 76. 最小覆盖子串
 * 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字符的最小子串。
 *
 * 示例：
 *
 * 输入: S = "ADOBECODEBANC", T = "ABC"
 * 输出: "BANC"
 * 说明：
 *
 * 如果 S 中不存这样的子串，则返回空字符串 ""。
 * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
 */
public class MinimumWindowSubstring {

    public static void main(String[] args) {
        System.out.println(new Solution().minWindow("a", "a"));
    }

    static class Solution {
        public String minWindow(String s, String t) {
            List<Integer> list = new ArrayList<>();
            Map<Character, Integer> map = new HashMap<>();
            for(char c : t.toCharArray()){
                map.put(c, map.get(c) == null ? 0 :  map.get(c)-1);
            }

            for(int i = 0; i < s.length(); i++){
                if(t.indexOf(s.charAt(i)) != -1)
                    list.add(i);
            }
            int min = Integer.MAX_VALUE;
            int minL = -1, minR = -1;
            int l = 0, r = 0;
            minL = 0;
            for(int i = 0; i < list.size(); i++){
                int index = list.get(i);
                r = i;
                char ch = s.charAt(index);
                map.put(ch, map.get(ch) + 1);
                while(l <= r && containsAll(map)){
                    int lIndex = list.get(l);
                    ch = s.charAt(lIndex);
                    int k = map.get(ch);
                    map.put(ch, --k);
                    if(index - lIndex + 1 < min){
                        min = index - lIndex + 1;
                        minL = lIndex;
                        minR = index;
                    }
                    l ++;
                }
            }
            if(minR == -1) return "";
            return s.substring(minL, minR + 1);
        }

        boolean containsAll(Map<Character, Integer> map){
            return map.values().stream().allMatch(s -> s >= 1);
        }
    }

}
