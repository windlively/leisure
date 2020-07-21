package lucku.baijunhan.alg.other;

import lucku.baijunhan.alg.tree.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 95. 不同的二叉搜索树 II
 * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的 二叉搜索树 。
 *
 *
 *
 * 示例：
 *
 * 输入：3
 * 输出：
 * [
 *   [1,null,3,2],
 *   [3,2,null,1],
 *   [3,1,null,null,2],
 *   [2,1,3],
 *   [1,null,2,null,3]
 * ]
 * 解释：
 * 以上的输出对应以下 5 种不同结构的二叉搜索树：
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 *
 *
 * 提示：
 *
 * 0 <= n <= 8
 */
public class UniqueBinarySearchTreesII {

    class Solution {
        public List<TreeNode> generateTrees(int n) {
            if(n == 0) return Collections.emptyList();
            return buildTree(1, n);
        }

        public List<TreeNode> buildTree(int l, int r){
            if(l == r) return Collections.singletonList(new TreeNode(l));
            if(l > r) return Collections.singletonList(null);
            List<TreeNode> list = new ArrayList<>();
            for(int i = l; i <= r; i++){
                List<TreeNode> leftTrees = buildTree(l, i - 1);
                List<TreeNode> rightTrees = buildTree(i + 1, r);
                for(TreeNode left : leftTrees){
                    for(TreeNode right : rightTrees){
                        TreeNode current = new TreeNode(i);
                        current.left = left;
                        current.right = right;
                        list.add(current);
                    }
                }
            }
            return list;
        }
    }
}
