/*
Given an integer array nums, find the subarray with the largest sum, and return its sum.

Example 1:
Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: The subarray [4,-1,2,1] has the largest sum 6.

Example 2:
Input: nums = [1]
Output: 1
Explanation: The subarray [1] has the largest sum 1.

Example 3:
Input: nums = [5,4,-1,7,8]
Output: 23
Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.

Constraints:
1 <= nums.length <= 10^5
-10^4 <= nums[i] <= 10^4
Follow up: If you have figured out the O(n) solution,
try coding another solution using the divide and conquer approach, which is more subtle.
*/

public class MaximumSubarray {
    // Approach 1: Brute Force
    // Find sum of each subarray and find the maximum sum and return
    // TC => O(N^2) -> TLE
    public int maxSubArray(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int n = nums.length;

        for(int i = 0; i < n; i++) {
            int sum = 0;
            for(int j = i; j < n; j++) {
                sum += nums[j];
                maxSum = Math.max(maxSum, sum);
            }
        }
        return maxSum;
    }

    // Approach 2: Kadane's algorithm
    // Add to the sum only if the sum is >= 0 otherwise start new subarray from element and new sum
    // TC => O(N)
    public int maxSubArrayKadaneAlgorithm(int[] nums) {
        int n = nums.length;
        int currSum = 0;
        int maxSum = nums[0]; // setting the maxSum as the first element to take care of edge case where array is of length 1

        for(int i = 0; i < n; i++) {
            if(currSum >= 0) {
                currSum += nums[i]; // add to currentSum only if the currentSum is greater than or equal to 0 because if current sum is negative than adding to it won't be helpful
            } else {
                currSum = nums[i]; // if it is negative than start the currentSum from the element and our subarray starts from here
            }
            maxSum = Math.max(maxSum, currSum); // finding the maxSum
        }

        /*
        for(int i = 0; i < n; i++) {
            // can also be done using this
            currSum += nums[i]; // add the element to find the currSum
            maxSum = Math.max(maxSum, currSum); // finding the maxSum

            // if currSum is less than 0, we can reset it to zero and begin our sum again
            if(currSum < 0)
                currSum = 0;
        }
        */

        return maxSum;
    }
}
