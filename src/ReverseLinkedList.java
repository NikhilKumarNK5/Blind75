/*
Given the head of a singly linked list, reverse the list, and return the reversed list.

Example 1:
Input: head = [1,2,3,4,5]
Output: [5,4,3,2,1]

Example 2:
Input: head = [1,2]
Output: [2,1]
Example 3:

Input: head = []
Output: []

Constraints:
The number of nodes in the list is the range [0, 5000].
-5000 <= Node.val <= 5000

Follow up: A linked list can be reversed either iteratively or recursively. Could you implement both?
*/

import java.util.Stack;

public class ReverseLinkedList {
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

    // Approach 1: Using extra space, stack -> traverse the list and store in stack and pop and put in list
    // TC => O(N)
    // SC => O(N)
    public ListNode reverseListUsingStack(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        ListNode curr = head;

        while(curr != null) {
            stack.push(curr.val);
            curr = curr.next;
        }

        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while(!stack.isEmpty()) {
            int val = stack.pop();
            current.next = new ListNode(val);
            current = current.next;
        }
        return dummy.next;
    }

    // Approach 2: Iterative two pointer approach
    // TC => O(N)
    // SC => O(1)
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while(curr != null) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }

        return prev;
    }

    // Approach 3: Recursive
    // TC => O(N)
    // SC => O(N)
    public ListNode reverseListRecursive(ListNode head) {
        if(head == null || head.next == null)
            return head;
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}
