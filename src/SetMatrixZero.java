/*
Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.
You must do it in place.

Example 1:
Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
Output: [[1,0,1],[0,0,0],[1,0,1]]

Example 2:
Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]

Constraints:
m == matrix.length
n == matrix[0].length
1 <= m, n <= 200
-2^31 <= matrix[i][j] <= 2^31 - 1

Follow up:
A straightforward solution using O(mn) space is probably a bad idea.
A simple improvement uses O(m + n) space, but still not the best solution.
Could you devise a constant space solution?
*/

import java.util.HashSet;

public class SetMatrixZero {

    // Approach 1: Using HashSet to mark the rows and columns
    // TC => O(M * N)
    // SC => O(M + N)
    public void setZeroes(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        HashSet<Integer> rowSet = new HashSet<>();
        HashSet<Integer> colSet = new HashSet<>();

        for(int row = 0; row < n; row++) {
            for(int col = 0; col < m; col++) {
                if(matrix[row][col] == 0) {
                    rowSet.add(row);
                    colSet.add(col);
                }
            }
        }

        for(int row = 0; row < n; row++) {
            for(int col = 0; col < m; col++) {
                if(rowSet.contains(row) || colSet.contains(col))
                    matrix[row][col] = 0;
            }
        }
    }

    // Approach 2: Using the first row and first col as markers
    // TC => O(M * N)
    // SC => O(1)
    public void setZeroesOptimized(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        boolean rowZero = false;
        boolean colZero = false;


        for(int row = 0; row < n; row++) {
            if(matrix[row][0] == 0)
                colZero = true;
        }

        for(int col = 0; col < m; col++) {
            if(matrix[0][col] == 0)
                rowZero = true;
        }

        for(int row = 1; row < n; row++) {
            for(int col = 1; col < m; col++) {
                if(matrix[row][col] == 0) {
                    matrix[row][0] = 0;
                    matrix[0][col] = 0;
                }
            }
        }

        for(int row = 1; row < n; row++) {
            for(int col = 1; col < m; col++) {
                if(matrix[row][0] == 0 || matrix[0][col] == 0)
                    matrix[row][col] = 0;
            }
        }

        if(colZero) {
            for(int row = 0; row < n; row++) {
                matrix[row][0] = 0;
            }
        }

        if(rowZero) {
            for(int col = 0; col < m; col++) {
                matrix[0][col] = 0;
            }
        }
    }
}
