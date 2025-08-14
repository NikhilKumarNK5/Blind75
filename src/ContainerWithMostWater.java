/*
You are given an integer array height of length n.
There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
Find two lines that together with the x-axis form a container,
such that the container contains the most water.
Return the maximum amount of water a container can store.
Notice that you may not slant the container.

Example 1:
Input: height = [1,8,6,2,5,4,8,3,7]
Output: 49
Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7].
In this case, the max area of water (blue section) the container can contain is 49.

Example 2:
Input: height = [1,1]
Output: 1

Constraints:
n == height.length
2 <= n <= 10^5
0 <= height[i] <= 10^4
*/

public class ContainerWithMostWater {

    // Approach 1: Brute Force - Checks every possible pairs of lines and checks area of each
    // TC => O(N^2)
    public int maxArea(int[] height) {
        int maxArea = 0;
        for(int i = 0; i < height.length; i++) {
            for(int j = i + 1; j < height.length; j++) {
                int area = Math.min(height[i], height[j]) * (j - i);
                maxArea = Math.max(maxArea, area);
            }
        }
        return maxArea;
    }

    // Approach 2: Using two pointer and finding the max area
    // TC => O(N)
    public int maxAreaTwoPointer(int[] height) {
        int maximumArea = 0;
        int start = 0;
        int end = height.length - 1;
        while(start < end) {
            int h = Math.min(height[start], height[end]); // height
            int b = end - start; // width
            int currArea = h * b;
            maximumArea = Math.max(maximumArea, currArea);
            if(height[start] <= height[end]) {
                start++;
            } else {
                end--;
            }
        }
        return maximumArea;
    }
}
