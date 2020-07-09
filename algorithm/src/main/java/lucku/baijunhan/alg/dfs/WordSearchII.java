package lucku.baijunhan.alg.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * 212. 单词搜索 II
 * 给定一个二维网格 board 和一个字典中的单词列表 words，找出所有同时在二维网格和字典中出现的单词。
 *
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。
 *
 * 示例:
 *
 * 输入:
 * words = ["oath","pea","eat","rain"] and board =
 * [
 *   ['o','a','a','n'],
 *   ['e','t','a','e'],
 *   ['i','h','k','r'],
 *   ['i','f','l','v']
 * ]
 *
 * 输出: ["eat","oath"]
 * 说明:
 * 你可以假设所有输入都由小写字母 a-z 组成。
 *
 * 提示:
 *
 * 你需要优化回溯算法以通过更大数据量的测试。你能否早点停止回溯？
 * 如果当前单词不存在于所有单词的前缀中，则可以立即停止回溯。什么样的数据结构可以有效地执行这样的操作？散列表是否可行？为什么？ 前缀树如何？如果你想学习如何实现一个基本的前缀树，请先查看这个问题： 实现Trie（前缀树）。
 */
public class WordSearchII {


    static class Solution {

        public List<String> findWords(char[][] board, String[] words) {
            if(board == null || board.length == 0) return new ArrayList<>();
            Trie trie = new Trie();
            for(String w : words) trie.insert(w);
            List<String> list = new ArrayList<>();
            boolean[][] vis = new boolean[board.length][board[0].length];
            for(int i = 0; i < board.length; i++){
                for(int j = 0; j < board[0].length; j++){
                    recursive(board, i, j, "", trie, list, vis);
                }
            }
            return list;
        }

        void recursive(char[][] b, int i, int j, String s, Trie trie, List<String> list, boolean[][] vis){

            if(i >= 0 && i < b.length && j >= 0 && j < b[0].length && !vis[i][j]){
                s += b[i][j];
                vis[i][j] = true;
                boolean[] bool = trie.search(s);
                if(bool[1] && !list.contains(s)){
                    list.add(s);
                }
                if(bool[0]){
                    recursive(b, i + 1, j, s, trie, list, vis);
                    recursive(b, i - 1, j, s, trie, list, vis);
                    recursive(b, i, j + 1, s, trie, list, vis);
                    recursive(b, i, j - 1, s, trie, list, vis);
                }
                vis[i][j] = false;
            }

        }

        static class Trie {

            private final TrieNode root = new TrieNode();

            /** Inserts a word into the trie. */
            public void insert(String word) {
                TrieNode node = root;
                int i;
                for(char ch : word.toCharArray()){
                    i = ch - 'a';
                    TrieNode child;
                    if((child = node.childs[i]) == null){
                        child = new TrieNode();
                        node.childs[i] = child;
                    }
                    node = child;
                }
                node.leaf = true;
            }

            /** Returns if the word is in the trie. */
            public boolean[] search(String word) {
                TrieNode node = root;
                int i;
                for(char ch : word.toCharArray()){
                    i = ch - 'a';
                    TrieNode child;
                    if((child = node.childs[i]) == null)
                        return new boolean[]{false, false};
                    node = child;
                }
                return new boolean[]{true, node.leaf};
            }

            private static class TrieNode{

                TrieNode[] childs = new TrieNode[26];

                boolean leaf;

                TrieNode(){}
            }
        }
    }
}
