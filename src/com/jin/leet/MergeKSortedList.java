package com.jin.leet;

/**
 * LeetCode23
 *
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 *
 * Example:
 *
 * Input:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * Output: 1->1->2->3->4->4->5->6
 */
public class MergeKSortedList {
    public ListNode mergeKLists(ListNode[] lists) {
        int minIndex = getMin(lists);
        if (minIndex == -1)
            return null;
        ListNode head = lists[minIndex];
        ListNode ptr = null;

        while (minIndex > -1) {
            if (ptr != null) {
                ptr.next = lists[minIndex];
            }
            ptr = lists[minIndex];
            lists[minIndex] = ptr.next;
            minIndex = getMin(lists);
        }

        return head;
    }

    private int getMin(ListNode[] lists) {
        int minVal = Integer.MAX_VALUE;
        int result = -1;
        for (int i=0; i<lists.length; i++) {
            if (lists[i] == null)
                continue;
            if (lists[i].val < minVal) {
                minVal = lists[i].val;
                result = i;
            }
        }
        return result;
    }

    public int[] mergeKArrays(int[][] arrays) {
        //TODO:
        return null;
    }

}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
