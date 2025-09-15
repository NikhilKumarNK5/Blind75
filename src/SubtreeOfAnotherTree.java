/*
Given the roots of two binary trees root and subRoot, return true if there is a subtree of root with the same structure and node values of subRoot and false otherwise.
A subtree of a binary tree tree is a tree that consists of a node in tree and all of this node's descendants.
The tree tree could also be considered as a subtree of itself.

Example 1:
Input: root = [3,4,5,1,2], subRoot = [4,1,2]
Output: true

Example 2:
Input: root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2]
Output: false

Constraints:
The number of nodes in the root tree is in the range [1, 2000].
The number of nodes in the subRoot tree is in the range [1, 1000].
-10^4 <= root.val <= 10^4
-10^4 <= subRoot.val <= 10^4
*/

public class SubtreeOfAnotherTree {
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

    // Approach 1: Recursively check if the subroot is same as the left of root or right of root
    // TC => O(M * N)
    // SC => O(M + N)
    public boolean isSubtreeRecursive(TreeNode root, TreeNode subRoot) {
        // if the root is null then there can't be subroot
        if(root == null)
            return false;
        // if they are same return true
        if(isSameTree(root, subRoot))
            return true;
        // recursively compare the left and right of the root with the subRoot
        return isSubtreeRecursive(root.left, subRoot) || isSubtreeRecursive(root.right, subRoot);
    }

    private boolean isSameTree(TreeNode p, TreeNode q) {
        // if both are null they are same
        if(p == null && q == null)
            return true;
        // if either is null or values don't match they are not same
        if(p == null || q == null || p.val != q.val)
            return false;
        // recursively check the left and right of both the trees
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    // Approach 2: Using serialization in preOrder traversal and then comparing if the subroot is present in the root
    // TC => O(M + N)
    // SC => O(M + N)
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        String rootString = serialize(root);
        String subRootString = serialize(subRoot);

        return rootString.contains(subRootString);
    }

    // serialize the tree using postorder traversal
    private String serialize(TreeNode node) {
        if(node == null)
            return "#";

        StringBuilder sb = new StringBuilder(" ");
        sb.append(node.val);
        serialize(node.left);
        serialize(node.right);

        return sb.toString();
    }
}
