/*
Given the root of a binary tree, return its maximum depth.
A binary tree's maximum depth is the number of nodes along the longest path from the root node
down to the farthest leaf node.

Example 1:
Input: root = [3,9,20,null,null,15,7]
Output: 3

Example 2:
Input: root = [1,null,2]
Output: 2

Constraints:
The number of nodes in the tree is in the range [0, 10^4].
-100 <= Node.val <= 100
*/

import java.util.LinkedList;
import java.util.Queue;

public class MaximumDepthOfBinaryTree {
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

     // Approach 1: Using queue
    // TC => O(N)
    // SC => O(N)
     public int maxDepthQueue(TreeNode root) {
        // if the root is null then the max depth is 0
        if (root == null)
            return 0;

        Queue<TreeNode> nodes = new LinkedList<>();

        nodes.add(root);
        int levels = 0;

        while (!nodes.isEmpty()) {
            levels++;
            int size = nodes.size();
            for (int i = 0; i < size; i++) {
                TreeNode poppedNode = nodes.poll();
                if (poppedNode.left != null) nodes.add(poppedNode.left);
                if (poppedNode.right != null) nodes.add(poppedNode.right);
            }
        }
        return levels;
    }

     // Approach 2: Recursively find the depth of left and right subtrees and store the max and return
     // TC => O(N)
     // SC => O(h)
    public int maxDepth(TreeNode root) {
         // if the root node is null then the maxDepth is 0
         if(root == null)
           return 0;

         int leftDepth = maxDepth(root.left);
         int rightDepth = maxDepth(root.right);

         int maxDepth = Math.max(leftDepth, rightDepth);

         return maxDepth;
    }

}
