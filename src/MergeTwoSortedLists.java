/*
You are given the heads of two sorted linked lists list1 and list2.
Merge the two lists into one sorted list.
The list should be made by splicing together the nodes of the first two lists.
Return the head of the merged linked list.

Example 1:
Input: list1 = [1,2,4], list2 = [1,3,4]
Output: [1,1,2,3,4,4]

Example 2:
Input: list1 = [], list2 = []
Output: []

Example 3:
Input: list1 = [], list2 = [0]
Output: [0]

Constraints:
The number of nodes in both lists is in the range [0, 50].
-100 <= Node.val <= 100
Both list1 and list2 are sorted in non-decreasing order.
*/

import java.util.Stack;

public class MergeTwoSortedLists {
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
    static class ListNode {
        private int val;
        private ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        public void setNext(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        public ListNode getNext() {
            return this.next;
        }
    }


    // Approach 1: Using Stack to store values from both list and then merge in a new List
    // TC => O(N + M)
    // SC => O(N + M)
    public ListNode mergeTwoListsUsingStack(ListNode list1, ListNode list2) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();

        ListNode c1 = list1;
        ListNode c2 = list2;

        while(c1 != null) {
            stack1.push(c1.val);
            c1 = c1.next;
        }

        while(c2 != null) {
            stack2.push(c2.val);
            c2 = c2.next;
        }

        ListNode res = null;
        while(!stack1.isEmpty() || !stack2.isEmpty()) {
            int val;
            if(stack1.isEmpty())
                val = stack2.pop();
            else if(stack2.isEmpty())
                val = stack1.pop();
            else if(stack1.peek() > stack2.peek())
                val = stack1.pop();
            else
                val = stack2.pop();

            ListNode node = new ListNode(val);
            node.next = res;
            res = node;
        }
        return res;
    }

    // Approach 2: Two Pointers
    // TC => O(N + M)
    // SC => O(1)
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode l1 = list1;
        ListNode l2 = list2;

        ListNode resNode = new ListNode(Integer.MIN_VALUE);
        ListNode headNode = resNode;

        while(l1 != null && l2 != null) {
            if(l1.val <= l2.val) {
                resNode.next = l1;
                l1 = l1.next;
            } else {
                resNode.next = l2;
                l2 = l2.next;
            }
            resNode = resNode.next;
        }

        // adding the remaining list
        if(l1 == null) {
            resNode.next = l2;
        }
        if(l2 == null) {
            resNode.next = l1;
        }
        return headNode.next;
    }
}
