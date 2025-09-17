/*
Given the root of a binary tree, determine if it is a valid binary search tree (BST).
A valid BST is defined as follows:
The left subtree of a node contains only nodes with keys strictly less than the node's key.
The right subtree of a node contains only nodes with keys strictly greater than the node's key.
Both the left and right subtrees must also be binary search trees.

Example 1:
Input: root = [2,1,3]
Output: true

Example 2:
Input: root = [5,1,4,null,null,3,6]
Output: false
Explanation: The root node's value is 5 but its right child's value is 4.

Constraints:
The number of nodes in the tree is in the range [1, 10^4].
-2^31 <= Node.val <= 2^31 - 1
*/

import java.util.ArrayList;

public class ValidateBinarySearchTree {
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

    // Approach 1: Using an array to store the inorder traversal of tree as inorder stores the BST in sorted order
    // Check if the array is sorted or not
    // TC => O(N)
    // SC => O(N)
    public boolean isValidBSTUsingArray(TreeNode root) {
        ArrayList<Integer> in = new ArrayList<>();
        inOrder(root, in);
        for(int i = 0; i < in.size() - 1; i++) {
            if(in.get(i) >= in.get(i + 1))
                return false;
        }
        return true;
    }

    private void inOrder(TreeNode root, ArrayList<Integer> in) {
        if(root == null)
            return;
        inOrder(root.left, in);
        in.add(root.val);
        inOrder(root.right, in);
    }

    // Approach 2: During inroder traversal keeping track of previous node and checking if current node is greater
    // TC => O(N)
    // SC => O(h)
    private Long prev = null;

    public boolean isValidBST(TreeNode root) {
        prev = null;
        return inOrder(root);
    }

    private boolean inOrder(TreeNode node) {
        if(node == null)
            return true;

        if(!inOrder(node.left))
            return false;

        if(prev != null && node.val <= prev)
            return false;

        prev = (long) node.val;
        return inOrder(node.right);
    }
}
