package lucku.baijunhan.alg.tree;

import lucku.baijunhan.alg.structure.TreeNode;

/**
 * 106. 从中序与后序遍历序列构造二叉树
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 中序遍历 inorder = [9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 通过次数57,741提交次数81,919
 */
public class ConstructBinaryTreeFromInorderAndPostorderTraversal {

    static class Solution {
        public TreeNode buildTree(int[] inorder, int[] postorder) {
            int len = inorder.length - 1;
            return recursive(inorder, postorder, 0, len,0,len);
        }

        public TreeNode recursive(int[] inorder, int[] postorder,
                                  int pStart, int pEnd, int iStart, int iEnd){
            if(pStart == pEnd) return new TreeNode(postorder[pStart]);
            if(pStart > pEnd) return null;

            int val = postorder[pEnd];
            TreeNode node = new TreeNode(val);
            int midIndex = indexOf(inorder, val);
            int leftLen = midIndex - iStart;

            node.left = recursive(inorder, postorder, pStart, pStart + leftLen - 1,
                    iStart, midIndex - 1);
            node.right = recursive(inorder, postorder, pStart + leftLen, pEnd - 1,
                    midIndex + 1, iEnd);
            return node;
        }

        public static int indexOf(int[] arr, int element){
            for(int i = 0; i < arr.length; i ++){
                if(arr[i] == element) return i;
            }
            return -1;
        }
    }
}
