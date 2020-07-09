package lucku.baijunhan.alg.string;

/**
 * 面试题 17.13. 恢复空格
 * 哦，不！你不小心把一个长篇文章中的空格、标点都删掉了，并且大写也弄成了小写。像句子"I reset the computer. It still didn’t boot!"已经变成了"iresetthecomputeritstilldidntboot"。在处理标点符号和大小写之前，你得先把它断成词语。当然了，你有一本厚厚的词典dictionary，不过，有些词没在词典里。假设文章用sentence表示，设计一个算法，把文章断开，要求未识别的字符最少，返回未识别的字符数。
 *
 * 注意：本题相对原题稍作改动，只需返回未识别的字符数
 *
 *
 *
 * 示例：
 *
 * 输入：
 * dictionary = ["looked","just","like","her","brother"]
 * sentence = "jesslookedjustliketimherbrother"
 * 输出： 7
 * 解释： 断句后为"jess looked just like tim her brother"，共7个未识别字符。
 * 提示：
 *
 * 0 <= len(sentence) <= 1000
 * dictionary中总字符数不超过 150000。
 * 你可以认为dictionary和sentence中只包含小写字母。
 *
 */
public class ReSpaceLcci {

    public static void main(String[] args) {
        System.out.println(new Solution().respace(new String[]{"looked", "just", "like", "her", "brother"}, "jesslookedjustliketimherbrother"));
    }

    static class Solution {
        public int respace(String[] dictionary, String sentence) {

            // char[] s = sentence.toCharArray();
            int[] dp = new int[sentence.length() + 1];
            // for(int i = 1; i <= s.length; i++){
            //     dp[i] = dp[i-1] + 1;
            //     for(String w : dictionary){
            //         int len = w.length();
            //         if(i >= len){
            //             if(w.equals(sentence.substring(i - len, i))){
            //                 dp[i] = Math.min(dp[i], dp[i - len]);
            //             }
            //         }
            //     }
            // }

            Trie trie = new Trie();
            for(String s : dictionary) trie.reverseInsert(s);
            for(int i = 1; i <= sentence.length(); i++){
                dp[i] = dp[i-1] + 1;
                TrieNode node = trie.root;
                char ch;
                for(int j = i-1;j >= 0; j --){
                    ch = sentence.charAt(j);
                    int index = ch - 'a';
                    TrieNode child;
                    if((child = node.childs[index]) == null)
                        break;
                    node = child;
                    if(node.leaf){
                        System.out.println(j);
                        dp[i] = Math.min(dp[i], dp[j]);
                    }
                }
            }
            return dp[sentence.length()];
        }

        static class Trie {

            private final TrieNode root = new TrieNode();

            public void reverseInsert(String word) {
                TrieNode node = root;
                int i;
                char ch;
                for(int j = word.length() - 1; j >= 0; j--){
                    ch = word.charAt(j);
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


        }

        private static class TrieNode{
            TrieNode[] childs = new TrieNode[26];
            boolean leaf;
        }
    }
}
