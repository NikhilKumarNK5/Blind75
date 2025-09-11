/*
Given the root of a binary tree, invert the tree, and return its root.

Example 1:
Input: root = [4,2,7,1,3,6,9]
Output: [4,7,2,9,6,3,1]

Example 2:
Input: root = [2,1,3]
Output: [2,3,1]

Example 3:
Input: root = []
Output: []

Constraints:
The number of nodes in the tree is in the range [0, 100].
-100 <= Node.val <= 100
*/

import java.util.LinkedList;
import java.util.Queue;

public class InvertBinaryTree {
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

    // Approach 1: Using Queue: - For each node dequeued, swap its left and right children, enqueue non-null children
    // TC => O(N)
    // SC => O(N)
    public TreeNode invertTreeUsingQueue(TreeNode root) {
        if(root == null)
            return null;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()) {
            TreeNode current = queue.poll();

            // swap left and right children
            TreeNode temp = current.left;
            current.left = current.right;
            current.right = temp;

            // add the children to the queue if not null
            if(current.left != null)
                queue.add(current.left);
            if(current.right != null)
                queue.add(current.right);
        }
        return root;
    }

    // Approach 2: Recursive - swap the left and right nodes recursively
    // TC => O(N)
    // SC => O(N)
    public TreeNode invertTree(TreeNode root) {
        if(root == null)
            return null;

        // swap left and right
        TreeNode temp = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(temp);

        return root;
    }
}
