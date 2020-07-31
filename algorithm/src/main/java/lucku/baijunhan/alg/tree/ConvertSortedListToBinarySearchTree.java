package lucku.baijunhan.alg.tree;

import lucku.baijunhan.alg.structure.ListNode;
import lucku.baijunhan.alg.structure.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class ConvertSortedListToBinarySearchTree {

    static class Solution {
        public TreeNode sortedListToBST(ListNode head) {
            List<Integer> list = new ArrayList<>();
            ListNode node = head;
            while(node != null){
                list.add(node.val);
                node = node.next;
            }
            return recursive(0, list.size(), list);
        }

        public TreeNode recursive(int l, int r, List<Integer> list){
            if(l >= r) return null;

            int m = (l + r) >> 1;
            TreeNode node = new TreeNode(list.get(m));
            node.left = recursive(l, m, list);
            node.right = recursive(m + 1, r, list);
            return node;
        }
    }
}
