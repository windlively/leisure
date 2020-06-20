package lucku.baijunhan.alg.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 * <p>
 * 示例 1：
 * <p>
 * 输入：head = [1,3,2]
 * 输出：[2,3,1]
 *  
 * <p>
 * 限制：
 * <p>
 * 0 <= 链表长度 <= 10000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PrintLinkedListFromEndToEnd {

    public int[] reversePrint(ListNode head) {
        if (head == null)
            return new int[0];
        List<Integer> list = new ArrayList<>();
        reversePrint(head, list);
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    private void reversePrint(ListNode node, List<Integer> list){
        if(node != null) {
            reversePrint(node.next, list);
            list.add(node.val);
        }
    }


    // Definition for singly-linked list.
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}
