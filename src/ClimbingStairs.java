/*
You are climbing a staircase. It takes n steps to reach the top.
Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

Example 1:
Input: n = 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps

Example 2:
Input: n = 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step

Constraints:
1 <= n <= 45
*/

public class ClimbingStairs {

    // Approach 1: Using recursion
    // TC => O(2^N)
    // SC => O(N)
    public int climbStairs(int n) {
        if(n == 0 || n == 1 || n == 2)
            return n;

        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    // Approach 2: Dynamic programming using an array
    // TC => O(N)
    // SC => O(N)
    public int climbStairsDp(int n) {
        if(n == 1)
            return 1;

        int[] dp = new int[n + 1];
        dp[1] = 1; // 1 stair 1 way to climb
        dp[2] = 2; // 2 stairs 2 ways to climb

        for(int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    // Approach 3: Dynamic Programming - Space optimized
    // TC => O(N)
    // SC => O(1)
    public int climbStairsDpOp(int n) {
        if(n <= 2)
            return n;

        int prev1 = 1; // ways to climb 1 step
        int prev2 = 2; // ways to climb 2 steps

        for(int i = 3; i <= n; i++) {
            int curr = prev1 + prev2;
            prev1 = prev2;
            prev2 = curr;
        }

        return prev2;
    }
}
