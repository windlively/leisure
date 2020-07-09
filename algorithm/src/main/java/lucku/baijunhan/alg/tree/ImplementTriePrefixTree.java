package lucku.baijunhan.alg.tree;

import org.junit.Assert;

import java.util.Objects;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * 208. 实现 Trie (前缀树)
 * 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 *
 * 示例:
 *
 * Trie trie = new Trie();
 *
 * trie.insert("apple");
 * trie.search("apple");   // 返回 true
 * trie.search("app");     // 返回 false
 * trie.startsWith("app"); // 返回 true
 * trie.insert("app");
 * trie.search("app");     // 返回 true
 * 说明:
 *
 * 你可以假设所有的输入都是由小写字母 a-z 构成的。
 * 保证所有输入均为非空字符串。
 *
 */
public class ImplementTriePrefixTree {

    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.insert("apple");
        assertTrue(trie.search("apple"));   // 返回 true
        assertFalse( trie.search("app"));     // 返回 false
        assertTrue(trie.startsWith("app")); // 返回 true
        trie.insert("app");
        assertTrue(trie.search("app"));     // 返回 true

    }

    /**
     * Your Trie object will be instantiated and called as such:
     * Trie obj = new Trie();
     * obj.insert(word);
     * boolean param_2 = obj.search(word);
     * boolean param_3 = obj.startsWith(prefix);
     */
    static class Trie {

        private final TrieNode root = new TrieNode();

        /** Initialize your data structure here. */
        public Trie() {

        }

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
        public boolean search(String word) {
            TrieNode node = root;
            int i;
            for(char ch : word.toCharArray()){
                i = ch - 'a';
                TrieNode child;
                if((child = node.childs[i]) == null)
                    return false;
                node = child;
            }
            return node.leaf;
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            TrieNode node = root;
            int i;
            for(char ch : prefix.toCharArray()){
                i = ch - 'a';
                TrieNode child;
                if((child = node.childs[i]) == null)
                    return false;
                node = child;
            }
            return true;
        }

        private static class TrieNode{

            TrieNode[] childs = new TrieNode[26];

            boolean leaf;

            TrieNode(){}
        }
    }


}
