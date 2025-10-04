/*
You are a professional robber planning to rob houses along a street.
Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.

Example 1:
Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.

Example 2:
Input: nums = [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
Total amount you can rob = 2 + 9 + 1 = 12.

Constraints:
1 <= nums.length <= 100
0 <= nums[i] <= 400
*/

public class HouseRobber {

    // Approach 1: DP - Using extra array to store the max loot at each index
    // TC => O(N)
    // SC => O(N)
    public int robExtraSpace(int[] nums) {

        int n = nums.length;

        // if only one element return that
        if(n == 1)
            return nums[0];

        // this will store max loot at each index
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for(int i = 2 ;i < n; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }

        return dp[n - 1];
    }

    // Approach 2: DP - just maintaining three variable to keep track of prev1 and prev2 and current
    // TC => O(N)
    // SC => O(1)
    public int rob(int[] nums) {

        int n = nums.length;

        // if only one element return that
        if(n == 1)
            return nums[0];

        int prev1 = nums[0];
        int prev2 = Math.max(nums[0], nums[1]);
        int current = prev2;

        for(int i = 2 ;i < n; i++) {
            current = Math.max(prev1 + nums[i], prev2);
            prev1 = prev2;
            prev2 = current;
        }

        return current;
    }
}
