/*
Given the roots of two binary trees p and q, write a function to check if they are the same or not.
Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.

Example 1:
Input: p = [1,2,3], q = [1,2,3]
Output: true

Example 2:
Input: p = [1,2], q = [1,null,2]
Output: false

Example 3:
Input: p = [1,2,1], q = [1,1,2]
Output: false

Constraints:
The number of nodes in both trees is in the range [0, 100].
-10^4 <= Node.val <= 10^4
*/

import java.util.LinkedList;
import java.util.Queue;

public class SameTree {
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

    // Approach 1: Using Queue - Level order traversal
    // TC => O(N)
    // SC => O(N)
    public boolean isSameTreeUsingQueue(TreeNode p, TreeNode q) {
        Queue<TreeNode> queue = new LinkedList<>();

        // add both the root nodes to queue
        queue.add(p);
        queue.add(q);

        while(!queue.isEmpty()) {
            TreeNode first = queue.poll();
            TreeNode second = queue.poll();

            // compare the first and second to check the level
            if(first == null && second == null)
                continue;
            else if(first == null || second == null || first.val != second.val)
                return false;

            // add to queue level wise
            queue.add(first.left);
            queue.add(second.left);
            queue.add(first.right);
            queue.add(second.right);
        }

        return true;
    }

    // Approach 2: Using recursion - recursively check the left and right subtrees for equality
    // TC => O(N)
    // SC => O(N)
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // if both are empty / null - they are same
        if(p == null && q == null)
            return true;
        // if one is null or the values don't match - they are not same
        if(p == null || q == null || p.val != q.val)
            return false;
        // recursively check the left and right subtrees
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
