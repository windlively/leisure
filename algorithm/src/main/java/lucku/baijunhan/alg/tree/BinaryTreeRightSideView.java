package lucku.baijunhan.alg.tree;

import lucku.baijunhan.alg.structure.TreeNode;

import java.util.*;

/**
 * 199. 二叉树的右视图
 * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 *
 * 示例:
 *
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1, 3, 4]
 * 解释:
 *
 *    1            <---
 *  /   \
 * 2     3         <---
 *  \     \
 *   5     4       <---
 */
public class BinaryTreeRightSideView {

    static class Solution {
        public List<Integer> rightSideView(TreeNode root) {
            if(root == null) return Collections.emptyList();
            Queue<TreeNode> queu = new LinkedList<>();
            queu.offer(root);
            List<Integer> list = new ArrayList<>();
            while(!queu.isEmpty()){
                int len = queu.size();
                while(len -- > 0){
                    TreeNode node = queu.poll();
                    assert node != null;
                    if(node.left != null) queu.offer(node.left);
                    if(node.right != null) queu.offer(node.right);
                    if(len == 0) list.add(node.val);
                }
            }
            return list;
        }
    }

}
