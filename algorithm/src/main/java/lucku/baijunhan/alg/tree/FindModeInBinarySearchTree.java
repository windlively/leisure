package lucku.baijunhan.alg.tree;

import lucku.baijunhan.alg.structure.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 501. 二叉搜索树中的众数
 * 给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。
 * <p>
 * 假定 BST 有如下定义：
 * <p>
 * 结点左子树中所含结点的值小于等于当前结点的值
 * 结点右子树中所含结点的值大于等于当前结点的值
 * 左子树和右子树都是二叉搜索树
 * 例如：
 * 给定 BST [1,null,2,2],
 * <p>
 * 1
 * \
 * 2
 * /
 * 2
 * 返回[2].
 * <p>
 * 提示：如果众数超过1个，不需考虑输出顺序
 * <p>
 * 进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内）
 */
public class FindModeInBinarySearchTree {

    static class Solution {

        int maxCount;

        int currentNum;

        int currentCount;

        public int[] findMode(TreeNode root) {
            if (root == null) return new int[0];
            maxCount = 0;
            currentNum = -9999999;
            currentCount = 0;
            findMaxCount(root);
            System.out.println(maxCount);
            currentNum = -9999999;
            currentCount = 0;
            List<Integer> r = new ArrayList<>();
            recursive(root, r);
            int[] result = new int[r.size()];
            for (int i = 0; i < result.length; i++) {
                result[i] = r.get(i);
            }
            return result;
        }

        public void recursive(TreeNode node, List<Integer> result) {
            if (node == null) return;
            recursive(node.left, result);
            if (currentNum == node.val)
                currentCount++;
            else {
                currentNum = node.val;
                currentCount = 1;
            }
            if (currentCount == maxCount) {
                result.add(currentNum);
            }
            recursive(node.right, result);
        }

        public void findMaxCount(TreeNode node) {
            if (node == null) return;
            findMaxCount(node.left);
            if (currentNum == node.val) {
                currentCount++;
            } else {
                currentNum = node.val;
                currentCount = 1;
            }
            if (currentCount > maxCount)
                maxCount = currentCount;
            findMaxCount(node.right);
        }
    }

}
