/*
Given the root of a binary search tree, and an integer k,
return the kth smallest value (1-indexed) of all the values of the nodes in the tree.

Example 1:
Input: root = [3,1,4,null,2], k = 1
Output: 1

Example 2:
Input: root = [5,3,6,2,4,null,null,1], k = 3
Output: 3

Constraints:
The number of nodes in the tree is n.
1 <= k <= n <= 10^4
0 <= Node.val <= 10^4

Follow up: If the BST is modified often (i.e., we can do insert and delete operations)
and you need to find the kth smallest frequently, how would you optimize?
*/

import java.util.ArrayList;

public class KthSmallestElementInABST {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // Approach 1: Use and arraylist to store the inorder traversal of the tree
    // Since the inorder traversal stores the tree in sorted order so the kth smallest will be the k-1 th index element since it is considered 1 based indexing
    // TC => O(N)
    // SC => O(N)
    public int kthSmallestUsingArray(TreeNode root, int k) {
        ArrayList<Integer> in = new ArrayList<>();
        inOrder(root, in);

        if(k <= in.size())
            return in.get(k - 1);
        return 0;
    }

    private void inOrder(TreeNode root, ArrayList<Integer> in) {
        if(root == null)
            return;
        inOrder(root.left, in);
        in.add(root.val);
        inOrder(root.right, in);
    }

    // Approach 2: Using a global variable to count the number of nodes visited so that we keep track of k and also global to store result
    // TC => O(N)
    // SC => O(H)
    private int count = 0;
    private int result = -1;

    public int kthSmallest(TreeNode root, int k) {
        inOrderTraversal(root, k);
        return result;
    }

    private void inOrderTraversal(TreeNode root, int k) {
        if(root == null || count >= k)
            return;

        inOrderTraversal(root.left, k);
        count++;

        if(count == k) {
            result = root.val;
            return;
        }

        inOrderTraversal(root.right, k);
    }
}
