package lucku.baijunhan.alg.tree;


/**
 * 剑指 Offer 26. 树的子结构
 * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
 *
 * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
 *
 * 例如:
 * 给定的树 A:
 *
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 * 给定的树 B：
 *
 *    4
 *   /
 *  1
 * 返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。
 *
 * 示例 1：
 *
 * 输入：A = [1,2,3], B = [3,1]
 * 输出：false
 * 示例 2：
 *
 * 输入：A = [3,4,5,1,2], B = [4,1]
 * 输出：true
 * 限制：
 * 0 <= 节点个数 <= 10000
 */
public class ShuDeZiJieGouLcof {

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    static class Solution {

        public boolean isSubStructure(TreeNode A, TreeNode B) {
            if(A == null || B == null) return false;
            if(match(A, B)) return true;
            return isSubStructure(A.left, B) || isSubStructure(A.right, B);
        }

        boolean match(TreeNode a, TreeNode b){
            if(b == null) return true;
            if(a == null) return false;
            if(a.val != b.val) return false;
            return match(a.left, b.left) && match(a.right, b.right);
        }

    }
}
