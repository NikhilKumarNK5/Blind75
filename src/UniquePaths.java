/*
There is a robot on an m x n grid.
The robot is initially located at the top-left corner (i.e., grid[0][0]).
The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]).
The robot can only move either down or right at any point in time.
Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.
The test cases are generated so that the answer will be less than or equal to 2 * 10^9.

Example 1:
Input: m = 3, n = 7
Output: 28

Example 2:
Input: m = 3, n = 2
Output: 3
Explanation: From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Down -> Down
2. Down -> Down -> Right
3. Down -> Right -> Down

Constraints:
1 <= m, n <= 100
*/

import java.util.Arrays;

public class UniquePaths {

    // Approach 1: DP - Use a 2D DP table where dp[i][j] = number of ways to reach cell (i, j).
    // Transition: dp[i][j] = dp[i-1][j] + dp[i][j-1]
    // Initialize first row and first column with 1 (only one path).
    // TC => O(M * N)
    // SC => O(M * N)
    public int uniquePaths(int m, int n) {
        int[][] grid = new int[m][n];

        for(int r = 0; r < m; r++) {
            for(int c = 0; c < n; c++) {
                // if we start at the first row or first column there is only one way to reach that cell
                if(r == 0 || c == 0)
                    grid[r][c] = 1;
                else
                    grid[r][c] = grid[r][c - 1] + grid[r - 1][c];
            }
        }

        return grid[m - 1][n - 1];
    }

    // Approach 2: DP
    // Since each cell depends only on the top and left cell, we can use a 1D array.
    // Iterate row by row, updating paths dynamically.
    // TC => O(M * N)
    // SC => O(N)
    public int uniquePathsSpaceOptimized(int m, int n) {
        int[] dp = new int[n];
        Arrays.fill(dp, 1); // first row has only 1 way

        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                dp[j] += dp[j - 1];
            }
        }
        return dp[n - 1];
    }
}
