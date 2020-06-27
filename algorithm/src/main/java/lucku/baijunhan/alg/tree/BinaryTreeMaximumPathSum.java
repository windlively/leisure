package lucku.baijunhan.alg.tree;

/**
 * 124. 二叉树中的最大路径和
 * 给定一个非空二叉树，返回其最大路径和。
 *
 * 本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
 *
 * 示例 1:
 *
 * 输入: [1,2,3]
 *
 *        1
 *       / \
 *      2   3
 *
 * 输出: 6
 * 示例 2:
 *
 * 输入: [-10,9,20,null,null,15,7]
 *
 *    -10
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 输出: 42
 */
public class BinaryTreeMaximumPathSum {

    static class Solution {
        int max = -1000000;

        public int maxPathSum(TreeNode root) {

            max = Integer.MIN_VALUE;
            recursive(root);
            return max;
        }

        int recursive(TreeNode root) {
            // 原先的笨办法
            // if(root.left == null && root.right == null)
            //     return root.val;

            // int mL = root.left != null ? recursive(root.left) : 0;
            // int mR = root.right != null ? recursive(root.right) : 0;
            // int t;
            // int rM;
            // if(root.left == null){
            //     t = max(root.val, root.val + mR, mR);
            //     rM = max(root.val + mR,root.val);
            // }else if(root.right == null){
            //     t = max(root.val, root.val + mL, mL);
            //     rM = max(root.val + mL, root.val);
            // }else{
            //     t = max(mL + mR + root.val, root.val, root.val + mR, root.val + mL, mR, mL);
            //     rM = max(mL + root.val, mR + root.val, root.val);
            // }

            // if(max < t)
            //     max = t;

            // return rM;
            if (root == null)
                return 0;
            int left = max(recursive(root.left), 0);
            int right = max(recursive(root.right), 0);
            max = max(max, root.val + left + right);
            return max(left, right) + root.val;
        }


        int max(int... nums) {
            int res = nums[0];
            for (int i : nums) {
                if (i > res)
                    res = i;
            }
            return res;
        }
    }
}
