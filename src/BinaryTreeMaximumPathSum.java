/*
A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them.
A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.
The path sum of a path is the sum of the node's values in the path.
Given the root of a binary tree, return the maximum path sum of any non-empty path.

Example 1:
Input: root = [1,2,3]
Output: 6
Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.

Example 2:
Input: root = [-10,9,20,null,null,15,7]
Output: 42
Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.

Constraints:
The number of nodes in the tree is in the range [1, 3 * 10^4].
-1000 <= Node.val <= 1000
*/
public class BinaryTreeMaximumPathSum {
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

    // Approach: Using a recursive dfs
    // For each node, compute the max path sum including that node and one of its subtrees.
    // Maintain a global variable to track the overall max path sum that can pass through the node (left + node + right).
    // TC => O(N)
    // SC => O(H) -> H = height of the tree

    // global variable to track the maximum path sum
    int res;

    public int maxPathSum(TreeNode root) {
        res = Integer.MIN_VALUE;
        dfs(root);
        return res;
    }

    private int dfs(TreeNode root) {
        // return 0 if the leaf is null
        if(root == null)
            return 0;

        int leftMax = Math.max(0, dfs(root.left));
        int rightMax = Math.max(0, dfs(root.right));

        // stores the best path anywhere in the whole tree
        res = Math.max(0, root.val + leftMax + rightMax);

        // returns the best downward path sum starting at node
        return root.val + Math.max(leftMax, rightMax);
    }
}

// DRY RUN
/*
    Call sequence and computations (postorder)
    dfs(9)
    dfs(9.left) = dfs(null) → returns 0
    dfs(9.right) = dfs(null) → returns 0
    leftMax = Math.max(0, 0) = 0
    rightMax = Math.max(0, 0) = 0
    Candidate path through node = 9 + 0 + 0 = 9
    res = max(MIN_VALUE, 9) = 9
    dfs(9) returns 9 + max(0,0) = 9
    State after node 9: res = 9, return value = 9.
    dfs(15)
    children null → both dfs calls return 0
    leftMax = 0, rightMax = 0
    Candidate = 15 → res = max(9,15) = 15
    dfs(15) returns 15
    State after node 15: res = 15, return value = 15.
    dfs(7)
    children null → leftMax = rightMax = 0
    Candidate = 7 → res = max(15,7) = 15 (unchanged)
    dfs(7) returns 7
    State after node 7: res = 15, return value = 7.
    dfs(20)
    left child returned 15, right child returned 7
    leftMax = Math.max(0, 15) = 15
    rightMax = Math.max(0, 7) = 7
    Candidate path passing through 20 = 20 + 15 + 7 = 42
    res = max(15, 42) = 42 ← global maximum updated
    dfs(20) returns 20 + max(15,7) = 20 + 15 = 35
    State after node 20: res = 42, return value = 35.
    dfs(-10) (the root)
    left child returned 9, right child returned 35
    leftMax = Math.max(0, 9) = 9
    rightMax = Math.max(0, 35) = 35
    Candidate path passing through root = -10 + 9 + 35 = 34
    res = max(42, 34) = 42 (no change)
    dfs(-10) returns -10 + max(9,35) = -10 + 35 = 25 (return value unused by caller)
    Final state: res = 42.
    maxPathSum(root) returns res = 42 — as expected.
*/
