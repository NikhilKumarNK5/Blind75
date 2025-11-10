/*
You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
Merge all the linked-lists into one sorted linked-list and return it.

Example 1:
Input: lists = [[1,4,5],[1,3,4],[2,6]]
Output: [1,1,2,3,4,4,5,6]
Explanation: The linked-lists are:
[
  1->4->5,
  1->3->4,
  2->6
]
merging them into one sorted linked list:
1->1->2->3->4->4->5->6

Example 2:
Input: lists = []
Output: []

Example 3:
Input: lists = [[]]
Output: []

Constraints:
k == lists.length
0 <= k <= 10^4
0 <= lists[i].length <= 500
-10^4 <= lists[i][j] <= 10^4
lists[i] is sorted in ascending order.
The sum of lists[i].length will not exceed 104.
*/

import java.util.PriorityQueue;

public class MergeKSortedList {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    // Approach: Using min heap to store the list nodes and merging
    // TC => O(NLogK)
    // SC => O(K)
    public ListNode mergeKLists(ListNode[] lists) {
        // min Heap with comparator to define how the two elements in the heap should be compared
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(
                (a, b) -> a.val - b.val
        );

        // push the head of each list into the heap
        for(ListNode node : lists) {
            if(node != null)
                minHeap.add(node);
        }

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while(!minHeap.isEmpty()) {
            // get the node with the smallest value
            ListNode smallest = minHeap.poll();
            tail.next = smallest;
            tail = tail.next;

            // push the next element of the list if exists
            if(smallest.next != null)
                minHeap.add(smallest.next);
        }

        return dummy.next;
    }
}
