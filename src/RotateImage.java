/*
You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
You have to rotate the image in-place, which means you have to modify the input 2D matrix directly.
DO NOT allocate another 2D matrix and do the rotation.

Example 1:
Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [[7,4,1],[8,5,2],[9,6,3]]

Example 2:
Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]

Constraints:
n == matrix.length == matrix[i].length
1 <= n <= 20
-1000 <= matrix[i][j] <= 1000
*/

public class RotateImage {

    // Approach 1: We can create a new 2D matrix and place the elements as per the rotated position
    // Since the ask is to do it in in-place so we won't be using this approach
    // TC => O(N^2)
    // SC => O(N^2)

    // Approach 2: to rotate a matrix by 90 degrees - we can transpose and then row reverse the matrix
    // This approach only works for square matrices
    // TC => O(N^2)
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // transpose of the matrix
        for(int row = 0; row < n; row++) {
            for(int col = 0; col < n; col++) {
                if(row < col) {
                    int temp = matrix[row][col];
                    matrix[row][col] = matrix[col][row];
                    matrix[col][row] = temp;
                }
            }
        }

        // reversing each row of the matrix
        for(int row = 0; row < n; row++) {
            int left = 0;
            int right = n - 1;
            while(left < right) {
                int temp = matrix[row][left];
                matrix[row][left] = matrix[row][right];
                matrix[row][right] = temp;
                left++;
                right--;
            }
        }
    }
}
