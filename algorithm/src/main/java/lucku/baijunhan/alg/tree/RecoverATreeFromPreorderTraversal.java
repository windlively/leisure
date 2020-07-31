package lucku.baijunhan.alg.tree;

import lucku.baijunhan.alg.structure.TreeNode;

/**
 * 1028. 从先序遍历还原二叉树
 * 我们从二叉树的根节点 root 开始进行深度优先搜索。
 *
 * 在遍历中的每个节点处，我们输出 D 条短划线（其中 D 是该节点的深度），然后输出该节点的值。（如果节点的深度为 D，则其直接子节点的深度为 D + 1。根节点的深度为 0）。
 *
 * 如果节点只有一个子节点，那么保证该子节点为左子节点。
 *
 * 给出遍历输出 S，还原树并返回其根节点 root。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入："1-2--3--4-5--6--7"
 * 输出：[1,2,5,3,4,6,7]
 * 示例 2：
 *
 *
 *
 * 输入："1-2--3---4-5--6---7"
 * 输出：[1,2,5,3,null,6,null,4,null,7]
 * 示例 3：
 *
 *
 *
 * 输入："1-401--349---90--88"
 * 输出：[1,401,null,349,88,90]
 *
 *
 * 提示：
 *
 * 原始树中的节点数介于 1 和 1000 之间。
 * 每个节点的值介于 1 和 10 ^ 9 之间。
 */
public class RecoverATreeFromPreorderTraversal {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    class Solution {
        int curIndex;
        public TreeNode recoverFromPreorder(String S) {
            return recursive(S, 0);
        }

        public TreeNode recursive(String s, int level){
            if(curIndex >= s.length()){
                return null;
            }

            int currentLevel = getLevel(s);
            if(currentLevel != level){
                curIndex -= currentLevel;
                return null;
            }

            int number = getNumber(s);
            TreeNode node = new TreeNode(number);
            node.left = recursive(s, level + 1);
            node.right = recursive(s, level + 1);

            return node;

        }

        int getLevel(String s){
            int level = 0;
            while(s.charAt(curIndex) == '-'){
                level ++;
                curIndex ++;
            }
            return level;
        }

        int getNumber(String s){
            int result = 0;
            for(;curIndex < s.length() && s.charAt(curIndex) != '-'; curIndex ++)
                result = (s.charAt(curIndex) - '0')  + result * 10 ;
            return result;
        }
    }
}
