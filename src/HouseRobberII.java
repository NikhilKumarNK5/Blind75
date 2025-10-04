/*
You are a professional robber planning to rob houses along a street.
Each house has a certain amount of money stashed. All houses at this place are arranged in a circle.
That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have a security system connected, and it will automatically contact the police if two adjacent houses were broken into on the same night.
Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.

Example 1:
Input: nums = [2,3,2]
Output: 3
Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2),
because they are adjacent houses.

Example 2:
Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.

Example 3:
Input: nums = [1,2,3]
Output: 3

Constraints:
1 <= nums.length <= 100
0 <= nums[i] <= 1000
*/
public class HouseRobberII {

    // Approach 1: DP - Using extra array to skip first and last
    // TC => O(N)
    // SC => O(N)
    public int rob(int[] nums) {
        int n = nums.length;

        if(n == 1)
            return nums[0];

        // we need to either skip first house or last house as it is circular
        int[] skipFirstHouse = new int[n - 1];
        int[] skipLastHouse = new int[n - 1];

        for(int i = 0; i < n - 1; i++) {
            skipFirstHouse[i] = nums[i + 1];
            skipLastHouse[i] = nums[i];
        }

        // getting the loot from both possibilites
        int lootSkippingFirst = loot(skipFirstHouse);
        int lootSkippingLast = loot(skipLastHouse);

        return Math.max(lootSkippingFirst, lootSkippingLast);
    }

    private int loot(int[] nums) {

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

    // Approach 2: DP - Without the use of extra array to skip first and last
    // TC => O(N)
    // SC => O(1)
    public int robOptimized(int[] nums) {
        int n = nums.length;

        if(n == 1)
            return nums[0];
        if(n == 2)
            return Math.max(nums[0], nums[1]);

        // Rob houses from 0 to n-2 (skipping last house)
        int skipLast = lootOptimized(nums, 0, n - 2);

        // Rob houses from 1 to n-1 (skipping first house)
        int skipFirst = lootOptimized(nums, 1, n - 1);

        // max of the two
        return Math.max(skipLast, skipFirst);
    }

    private int lootOptimized(int[] nums, int start, int end) {
        int prev1 = 0; // max profit till previous house
        int prev2 = 0; // max profit till the house before previous

        for(int i = start; i <= end; i++) {
            int curr = Math.max(prev1, nums[i] + prev2);
            prev2 = prev1;
            prev1 = curr;
        }

        return prev1;
    }
}
