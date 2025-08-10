/*
Given an integer array nums, find a subarray that has the largest product, and return the product.
The test cases are generated so that the answer will fit in a 32-bit integer.

Example 1:
Input: nums = [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.

Example 2:
Input: nums = [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.

Constraints:
1 <= nums.length <= 2 * 10^4
-10 <= nums[i] <= 10

The product of any subarray of nums is guaranteed to fit in a 32-bit integer.
*/

public class MaximumSubarrayProduct {
    // Approach 1: Brute force
    // Generate the product of each subarray and calculate the maximum and return
    /*
        Input: nums = [2, 3, -2, 4]
        Initial: maxProduct = Integer.MIN_VALUE

        Outer loop i = 0:
          j = 0 → product = 2             → maxProduct = max(-∞, 2) = 2
          j = 1 → product = 2 * 3 = 6     → maxProduct = max(2, 6) = 6
          j = 2 → product = 6 * -2 = -12  → maxProduct = max(6, -12) = 6
          j = 3 → product = -12 * 4 = -48 → maxProduct = max(6, -48) = 6

        Outer loop i = 1:
          j = 1 → product = 3             → maxProduct = max(6, 3) = 6
          j = 2 → product = 3 * -2 = -6   → maxProduct = max(6, -6) = 6
          j = 3 → product = -6 * 4 = -24  → maxProduct = max(6, -24) = 6

        Outer loop i = 2:
          j = 2 → product = -2            → maxProduct = max(6, -2) = 6
          j = 3 → product = -2 * 4 = -8   → maxProduct = max(6, -8) = 6

        Outer loop i = 3:
          j = 3 → product = 4             → maxProduct = max(6, 4) = 6

        Final result:
          maxProduct = 6
    */
    // TC => O(N^2)
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int maxProduct = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++) {
            int product = 1;
            for(int j = i; j < n; j++) {
                product *= nums[j];
                maxProduct = Math.max(maxProduct, product);
            }
        }
        return maxProduct;
    }

    // Approach 2: 2 Pointers -
    // keeping track of left subarray product and right subarray product
    // this handles zeroes as well as negative values
    /*
        Input: nums = [2, 3, -2, 4]
        Initial:
            leftProduct = 1
            rightProduct = 1
            maxProduct = nums[0] = 2

        Loop iteration-wise:

        i = 0:
            leftProduct = 1 * nums[0] = 1 * 2 = 2
            rightProduct = 1 * nums[3] = 1 * 4 = 4
            maxProduct = max(2, max(2, 4)) = 4

        i = 1:
            leftProduct = 2 * nums[1] = 2 * 3 = 6
            rightProduct = 4 * nums[2] = 4 * -2 = -8
            maxProduct = max(4, max(6, -8)) = 6

        i = 2:
            leftProduct = 6 * nums[2] = 6 * -2 = -12
            rightProduct = -8 * nums[1] = -8 * 3 = -24
            maxProduct = max(6, max(-12, -24)) = 6

        i = 3:
            leftProduct = -12 * nums[3] = -12 * 4 = -48
            rightProduct = -24 * nums[0] = -24 * 2 = -48
            maxProduct = max(6, max(-48, -48)) = 6

        Final Result:
            maxProduct = 6
            (from subarray [2, 3])
     */
    // TC => O(N)
    public int maxProductTwoPointers(int[] nums) {
        int n = nums.length;
        int leftProduct = 1;
        int rightProduct = 1;
        int maxProduct = nums[0];

        for(int i = 0; i < n; i++) {
            // if product becomes 0 then new subarray product begins so setting the value to 1
            leftProduct = leftProduct == 0 ? 1 : leftProduct;
            rightProduct = rightProduct == 0 ? 1 : rightProduct;

            // calculating the product from beginning of the array
            leftProduct *= nums[i];

            // calculating the product from the end of the array
            rightProduct *= nums[n - 1 - i];

            // calculating the max product
            maxProduct = Math.max(maxProduct, Math.max(leftProduct, rightProduct));
        }

        return maxProduct;
    }

    // Approach 3: DP
    // TC => O(N)
    public int maxProductDP(int[] nums) {
        // Initialize result with the minimum possible integer value
        // It will eventually hold the maximum product of any subarray
        int res = Integer.MIN_VALUE;

        // First, set res to the maximum element in the array
        // This helps handle cases where all elements are negative or zero
        for (int ele : nums) {
            res = Math.max(res, ele);
        }

        int currMax = 1; // Tracks the current maximum product ending at the current index
        int currMin = 1; // Tracks the current minimum product ending at the current index (used for handling negatives)

        for (int ele : nums) {
            // temp stores current max before it gets overwritten
            int temp = currMax * ele;

            // Update currMax by considering:
            // 1. current element alone (starting new subarray)
            // 2. product of current element with previous max
            // 3. product of current element with previous min (in case ele is negative)
            currMax = Math.max(temp, Math.max(currMin * ele, ele));

            // Similarly, update currMin
            currMin = Math.min(temp, Math.min(currMin * ele, ele));

            // Update global max product found so far
            res = Math.max(res, currMax);
        }

        return res;
    }
}
