/*
Given an integer array nums, return an array answer such that answer[i]
is equal to the product of all the elements of nums except nums[i].
The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
You must write an algorithm that runs in O(n) time and without using the division operation.

Example 1:
Input: nums = [1,2,3,4]
Output: [24,12,8,6]

Example 2:
Input: nums = [-1,1,0,-3,3]
Output: [0,0,9,0,0]

Constraints:
2 <= nums.length <= 105
-30 <= nums[i] <= 30
The input is generated such that answer[i] is guaranteed to fit in a 32-bit integer.
Follow up: Can you solve the problem in O(1) extra space complexity?
(The output array does not count as extra space for space complexity analysis.)
*/

import java.util.Arrays;

public class ProductOfAnArrayExceptSelf {

    // Approach 1: Brute Force
    // TC => O(N^2) - TLE
    // SC => O(N)
    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        for(int i = 0; i < nums.length; i++) {
            int product = 1;
            for(int j = 0; j < nums.length; j++) {
                if(i != j) {
                    product *= nums[j];
                }
            }
            res[i] = product;
        }
        return res;
    }

    // Approach 2: we will take prefix and suffix arrays
    // [1, 2, 3, 4]
    // store the values in prefix [1, 1, 2, 6]
    // store the values in suffix [24, 12, 4, 1]
    // final result -> [24, 12, 8, 6]
    // TC => O(N)
    // SC => O(N)
    public int[] productExceptSelfOptimizedWithPrefixAndSuffix(int[] nums) {
        int n = nums.length;
        int[] prefix = new int[n];
        int[] suffix = new int[n];
        int[] res = new int[n];

        prefix[0] = 1;
        for(int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] * nums[i - 1];
        }

        suffix[n - 1] = 1;
        for(int j = n - 2; j >= 0; j--) {
            suffix[j] = suffix[j + 1] * nums[j + 1];
        }

        for(int i = 0; i < n; i++) {
            res[i] = prefix[i] * suffix[i];
        }

        return res;
    }

    // Optimized - without extra space
    /*
    Dry Run for nums = [1, 2, 3, 4]
    Initial:
    res    = [1, 1, 1, 1]
    prefix = 1
    suffix = 1

    First loop (left to right) – calculating prefix products:
    i = 0: res[0] = prefix = 1      → prefix = 1 * nums[0] = 1 * 1 = 1
    i = 1: res[1] = prefix = 1      → prefix = 1 * nums[1] = 1 * 2 = 2
    i = 2: res[2] = prefix = 2      → prefix = 2 * nums[2] = 2 * 3 = 6
    i = 3: res[3] = prefix = 6      → prefix = 6 * nums[3] = 6 * 4 = 24

    After first loop:
    res = [1, 1, 2, 6]

    Second loop (right to left) – calculating suffix products:
    i = 3: res[3] = 6 * suffix = 6 * 1 = 6        → suffix = 1 * nums[3] = 1 * 4 = 4
    i = 2: res[2] = 2 * suffix = 2 * 4 = 8        → suffix = 4 * nums[2] = 4 * 3 = 12
    i = 1: res[1] = 1 * suffix = 1 * 12 = 12      → suffix = 12 * nums[1] = 12 * 2 = 24
    i = 0: res[0] = 1 * suffix = 1 * 24 = 24      → suffix = 24 * nums[0] = 24 * 1 = 24

    Final result:
    res = [24, 12, 8, 6]
     */
    // TC => O(N)
    // SC => O(1)
    public int[] productExceptSelfOptimized(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, 1); // filling all the values as 1 so it is easier to calculate product otherwise default are 0 and anything multiplied by 0 will return 0
        int prefix = 1;
        int suffix = 1;
        for(int i = 0; i < n; i++) {
            res[i] = prefix;
            prefix = nums[i] * prefix;
        }

        for(int i = n - 1; i >= 0; i--) {
            res[i] = res[i] * suffix;
            suffix = nums[i] * suffix;
        }

        return res;
    }
}
