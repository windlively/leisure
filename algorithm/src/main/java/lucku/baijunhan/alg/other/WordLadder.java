package lucku.baijunhan.alg.other;

import java.util.*;

/**
 * 127. 单词接龙
 * 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
 *
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典中的单词。
 * 说明:
 *
 * 如果不存在这样的转换序列，返回 0。
 * 所有单词具有相同的长度。
 * 所有单词只由小写字母组成。
 * 字典中不存在重复的单词。
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 * 示例 1:
 *
 * 输入:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 *
 * 输出: 5
 *
 * 解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 *      返回它的长度 5。
 * 示例 2:
 *
 * 输入:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 *
 * 输出: 0
 *
 * 解释: endWord "cog" 不在字典中，所以无法进行转换。
 */
public class WordLadder {


    static class Solution {

        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            Map<String, List<String>> graph = new HashMap<>();
            Queue<String> queue = new LinkedList<>();
            queue.offer(beginWord);
            List<String> vis = new ArrayList<>();
            int result = 0;
            while (!queue.isEmpty()){
                int len = queue.size();
                result ++;
                while (len-- > 0) {
                    String w = queue.poll();
                    if (vis.contains(w)) continue;
                    if(endWord.equals(w)) return result;
                    for (String s : wordList) {
                        if (hasPath(w, s))
                            queue.offer(s);
                    }
                    vis.add(w);
                }
            }
            return 0;
        }

        public boolean hasPath(String w1, String w2){
            char[] cs1 = w1.toCharArray();
            char[] cs2 = w2.toCharArray();
            int diffCount = 0;
            for(int i = 0; i < cs1.length; i ++){
                if(cs1[i] != cs2[i]) diffCount ++;
            }
            return diffCount == 1;
        }
    }


}
