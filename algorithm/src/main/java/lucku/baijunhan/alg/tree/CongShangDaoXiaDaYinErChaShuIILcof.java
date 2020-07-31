package lucku.baijunhan.alg.tree;

import lucku.baijunhan.alg.structure.TreeNode;

import java.util.*;

/**
 * 剑指 Offer 32 - II. 从上到下打印二叉树 II
 * 从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
 *
 *
 *
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其层次遍历结果：
 *
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 *
 *
 * 提示：
 *
 * 节点总数 <= 1000
 *
 */
public class CongShangDaoXiaDaYinErChaShuIILcof {


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

        public List<List<Integer>> levelOrder(TreeNode root) {
            if(root == null) return Collections.emptyList();
            List<List<Integer>> result = new ArrayList<>();
            Deque<TreeNode> queu = new LinkedList<>();
            queu.offer(root);
            while(!queu.isEmpty()){
                int len = queu.size();
                List<Integer> list = new ArrayList<>(len);
                while(len -- > 0){
                    TreeNode node = queu.poll();
                    list.add(node.val);
                    if(node.left != null){
                        queu.offer(node.left);
                    }
                    if(node.right != null){
                        queu.offer(node.right);
                    }
                }
                result.add(list);
            }
            return result;

        }

    }
}
