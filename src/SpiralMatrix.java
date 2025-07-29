/*
Given an m x n matrix, return all elements of the matrix in spiral order.

Example 1:
Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,2,3,6,9,8,7,4,5]

Example 2:
Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]

Constraints:
m == matrix.length
n == matrix[i].length
1 <= m, n <= 10
-100 <= matrix[i][j] <= 100
*/

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {
    // Approach 1: Boundary Approach
    /*
        Dry Run Example:
        Input matrix:
        [
          [1,  2,  3],
          [4,  5,  6],
          [7,  8,  9]
        ]
        Initial variables:
        n = 3, m = 3
        top = 0, bottom = 2, left = 0, right = 2
        res = []

        Step 1: Traverse top row (left → right)
          matrix[0][0] = 1 → res = [1]
          matrix[0][1] = 2 → res = [1, 2]
          matrix[0][2] = 3 → res = [1, 2, 3]
        top = 1

        Step 2: Traverse right column (top → bottom)
          matrix[1][2] = 6 → res = [1, 2, 3, 6]
          matrix[2][2] = 9 → res = [1, 2, 3, 6, 9]
        right = 1

        Step 3: Traverse bottom row (right → left)
          matrix[2][1] = 8 → res = [1, 2, 3, 6, 9, 8]
          matrix[2][0] = 7 → res = [1, 2, 3, 6, 9, 8, 7]
        bottom = 1

        Step 4: Traverse left column (bottom → top)
          matrix[1][0] = 4 → res = [1, 2, 3, 6, 9, 8, 7, 4]
        left = 1

        Step 5: Traverse top row again (left → right)
          matrix[1][1] = 5 → res = [1, 2, 3, 6, 9, 8, 7, 4, 5]
        top = 2

        Now, top > bottom and left > right → Exit loop

        Final result:
        res = [1, 2, 3, 6, 9, 8, 7, 4, 5]
    */
    // TC => O(N * M)
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int m = matrix.length;    // Number of rows
        int n = matrix[0].length; // Number of columns
        // Define the boundaries of the matrix
        int top = 0;                 // Topmost unvisited row
        int bottom = m - 1;          // Bottommost unvisited row
        int left = 0;                // Leftmost unvisited column
        int right = n - 1;           // Rightmost unvisited column

        // Continue looping as long as the boundaries do not overlap
        while(top <= bottom && left <= right) {
            // Traverse from left to right across the top row
            for(int i = left; i <= right; i++) {
                res.add(matrix[top][i]);
            }
            top++; // Move top boundary down

            // Traverse from top to bottom along the right column
            for(int i = top; i <= bottom; i++) {
                res.add(matrix[i][right]);
            }
            right--; // Move right boundary left

            // Traverse from right to left across the bottom row (if any rows remain)
            if(top <= bottom) {
                for(int i = right; i >= left; i--) {
                    res.add(matrix[bottom][i]);
                }
                bottom--; // Move bottom boundary up
            }

            // Traverse from bottom to top along the left column (if any columns remain)
            if(left <= right) {
                for(int i = bottom; i >= top; i--) {
                    res.add(matrix[i][left]);
                }
                left++; // Move left boundary right
            }
        }
        return res;
    }

    // Approach 2: Layer wise traversal
    // Instead of maintaining 4 different variables for top, bottom, left and right, just using one variable to maintain layers of the matrix
    // Traversal works similar to above approach
    /*
        Dry Run Example:
        Input matrix:
        [
          [ 1,  2,  3,  4],
          [ 5,  6,  7,  8],
          [ 9, 10, 11, 12]
        ]
        m = 3 (rows), n = 4 (columns)
        Number of layers = (min(m, n) + 1) / 2 = 2

        Layer 0:
        // ➡ Top row (left → right)
        matrix[0][0] = 1
        matrix[0][1] = 2
        matrix[0][2] = 3
        matrix[0][3] = 4
        res = [1, 2, 3, 4]
        // ⬇ Right column (top → bottom)
        matrix[1][3] = 8
        matrix[2][3] = 12
        res = [1, 2, 3, 4, 8, 12]
        // ⬅ Bottom row (right → left) - since bottom row index (2) > top row index (0)
        matrix[2][2] = 11
        matrix[2][1] = 10
        matrix[2][0] = 9
        res = [1, 2, 3, 4, 8, 12, 11, 10, 9]
        // ⬆ Left column (bottom → top) - since right col index (3) > left col index (0)
        matrix[1][0] = 5
        res = [1, 2, 3, 4, 8, 12, 11, 10, 9, 5]

        Layer 1:
        // ➡ Top row (left → right)
        matrix[1][1] = 6
        matrix[1][2] = 7
        res = [1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7]
        // ⬇ Right column: start = 2, end = 1 → skipped
        // ⬅ Bottom row: row 1 == top → skipped
        // ⬆ Left column: start = 1, end = 1 → skipped

        Final result:
        [1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7]
    */
    // TC => O(N * M)
    public List<Integer> spiralOrderLayer(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int m = matrix.length;    // Number of rows
        int n = matrix[0].length; // Number of columns
        int layer = 0;            // Current layer index

        while (layer < (Math.min(m, n) + 1) / 2) {
            // Traverse the top row of the current layer
            for (int col = layer; col < n - layer; col++) {
                res.add(matrix[layer][col]);
            }

            // Traverse the right column of the current layer
            for (int row = layer + 1; row < m - layer; row++) {
                res.add(matrix[row][n - layer - 1]);
            }

            // Traverse the bottom row of the current layer (if it exists)
            if (m - layer - 1 > layer) {
                for (int col = n - layer - 2; col >= layer; col--) {
                    res.add(matrix[m - layer - 1][col]);
                }
            }

            // Traverse the left column of the current layer (if it exists)
            if (n - layer - 1 > layer) {
                for (int row = m - layer - 2; row > layer; row--) {
                    res.add(matrix[row][layer]);
                }
            }

            // Move to the next layer
            layer++;
        }

        return res;
    }

}
