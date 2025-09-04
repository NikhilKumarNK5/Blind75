/*
Given the head of a linked list, remove the nth node from the end of the list and return its head.

Example 1:
Input: head = [1,2,3,4,5], n = 2
Output: [1,2,3,5]

Example 2:
Input: head = [1], n = 1
Output: []

Example 3:
Input: head = [1,2], n = 1
Output: [1]

Constraints:
The number of nodes in the list is sz.
1 <= sz <= 30
0 <= Node.val <= 100
1 <= n <= sz

Follow up: Could you do this in one pass?
*/
public class RemoveNthNodeFromTheEndOfList {
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

    // Approach 1: Traverse the list and calculate the length and then remove node after length - n
    // TC => O(N) (2 passes)
    public ListNode removeNthFromEndTwoPasses(ListNode head, int n) {
        ListNode curr = head;
        int length = 0;

        while(curr != null) {
            length++;
            curr = curr.next;
        }

        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        dummy.next = head;
        curr = dummy;

        for(int i = 0; i < length - n; i++) {
            curr = curr.next;
        }

        curr.next = curr.next.next;

        return dummy.next;
    }

    // Approach 2: Using two pointers
    // TC => O(N) (single pass)
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        dummy.next = head;
        ListNode ptr1 = dummy;
        ListNode ptr2 = dummy;

        // move pointer two 2 steps ahead
        for(int i = 0; i < n; i++) {
            ptr2 = ptr2.next;
        }

        while(ptr2.next != null) {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }

        // remove the node after ptr1
        ptr1.next = ptr1.next.next;

        return dummy.next;
    }
}
