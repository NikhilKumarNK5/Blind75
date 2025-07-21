/*
Given an array nums containing n distinct numbers in the range [0, n],
return the only number in the range that is missing from the array.

Example 1:
Input: nums = [3,0,1]
Output: 2
Explanation:
n = 3 since there are 3 numbers, so all numbers are in the range [0,3].
2 is the missing number in the range since it does not appear in nums.

Example 2:
Input: nums = [0,1]
Output: 2
Explanation:
n = 2 since there are 2 numbers, so all numbers are in the range [0,2].
2 is the missing number in the range since it does not appear in nums.

Example 3:
Input: nums = [9,6,4,2,3,5,7,0,1]
Output: 8
Explanation:
n = 9 since there are 9 numbers, so all numbers are in the range [0,9].
8 is the missing number in the range since it does not appear in nums.

Constraints:
n == nums.length
1 <= n <= 10^4
0 <= nums[i] <= n
All the numbers of nums are unique.
Follow up: Could you implement a solution using only O(1) extra space complexity and O(n) runtime complexity?
*/

import java.util.Arrays;

public class MissingNumber {
    // Approach 1: Brute Force
    // Sort the array and compare it with index - if element not equal to index then return index
    // TC => O(NLogN)
    public int missingNumber(int[] nums) {
        Arrays.sort(nums);
        int ans = -1;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] != i)
                return i;
            else
                ans = i + 1;
        }
        return ans;
    }

    // Approach 2: Using the sum of all elements
    // We can calculate the sum of n natural number and subtract the sum of given elements from it then we will get the ans
    /*
        Input:
            nums = [3, 0, 1]
        Initial:
            n = nums.length = 3
            expectedSum = n * (n + 1) / 2 = 3 * 4 / 2 = 6
            actualSum = 0
        Iteration 0
            i = 0
            nums[0] = 3
            actualSum += 3 → actualSum = 3
        Iteration 1
            i = 1
            nums[1] = 0
            actualSum += 0 → actualSum = 3
        Iteration 2
            i = 2
            nums[2] = 1
            actualSum += 1 → actualSum = 4
        After loop:
            expectedSum = 6
            actualSum = 4
        Return:
            expectedSum - actualSum = 6 - 4 = 2
    */
    // the sum can overflow for large arrays
    // TC => O(N)
    public int missingNumberUsingSum(int[] nums) {
        int n = nums.length;
        int expectedSum = n * (n + 1) / 2; // sum of n natural numbers
        int actualSum = 0;
        for(int i = 0; i < n; i++) {
            actualSum += nums[i];
        }
        return expectedSum - actualSum;
    }

    // Approach 3: Using XOR Bitwise Operator
    // a ^ a = 0 & a ^ 0 = a
    /*
        Input:
            nums = [3, 0, 1]
        Initial:
            n = 3
            res = n = 3
        Iteration 0
            i = 0
            res = res ^ nums[0] ^ i
                = 3 ^ 3 ^ 0
                = 0 ^ 0 = 0
       Iteration 1
            i = 1
            res = res ^ nums[1] ^ i
                = 0 ^ 0 ^ 1
                = 0 ^ 1 = 1
       Iteration 2
            i = 2
            res = res ^ nums[2] ^ i
                = 1 ^ 1 ^ 2
                = 0 ^ 2 = 2
        After loop:
            res = 2
        Return:
            2
    */
    // TC => O(N)
    public int missingNumberUsingXOR(int[] nums) {
        int n = nums.length;
        int res = n;

        for(int i = 0; i < n; i++) {
            res = res ^ nums[i] ^ i;
        }
        return res;
    }
}
