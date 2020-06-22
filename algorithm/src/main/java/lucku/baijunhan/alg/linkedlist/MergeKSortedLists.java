package lucku.baijunhan.alg.linkedlist;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * 23. 合并K个排序链表
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 */
public class MergeKSortedLists {


    /**
     * Definition for singly-linked list.
     */
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    static class Solution {
        public ListNode mergeKLists(ListNode[] lists) {
            ListNode[] tmp = new ListNode[lists.length];
            for (int i = 0; i < lists.length; i++) {
                tmp[i] = lists[i];
            }
            ListNode head = new ListNode(0);
            ListNode cur = head;
            int minIndex = 0;
            while (hasRemain(tmp)) {

                for (int i = 0; i < lists.length; i++) {
                    if (tmp[i] != null) {
                        if (tmp[minIndex] == null)
                            minIndex = i;
                        else if (tmp[minIndex].val > tmp[i].val)
                            minIndex = i;
                    }
                }
                cur.next = tmp[minIndex];
                tmp[minIndex] = tmp[minIndex].next;
                cur = cur.next;
                cur.next = null;
            }
            return head.next;

        }

        boolean hasRemain(ListNode[] lists) {
            return Stream.of(lists).anyMatch(Objects::nonNull);
        }
    }
}
