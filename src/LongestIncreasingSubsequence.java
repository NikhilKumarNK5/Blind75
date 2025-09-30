/*
Given an integer array nums, return the length of the longest strictly increasing subsequence.

Example 1:
Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.

Example 2:
Input: nums = [0,1,0,3,2,3]
Output: 4

Example 3:
Input: nums = [7,7,7,7,7,7,7]
Output: 1

Constraints:
1 <= nums.length <= 2500
-10^4 <= nums[i] <= 10^4

Follow up: Can you come up with an algorithm that runs in O(n log(n)) time complexity?
*/

import java.util.ArrayList;
import java.util.Arrays;

public class LongestIncreasingSubsequence {

    // Approach 1: Using Dynamic Programming - Memoization
    // Use a dp[i] array where dp[i] = length of LIS ending at index i
    // TC => O(N^2)
    // SC => O(N)
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];

        // maxLength at each index is at-least 1 at the start
        Arrays.fill(dp, 1);

        for(int i = 1; i < nums.length; i++) {
            for(int j = 0; j < i; j++) {
                if(nums[j] < nums[i])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }

        int res = Integer.MIN_VALUE;
        for(int i = 0; i < dp.length; i++) {
            res = Math.max(dp[i], res);
        }

        return res;
    }

    // Approach 2: Using Binary Search + Greedy
    // TC => O(NLogN)
    // SC => O(N)
    /*
        Dry run for nums = [10, 9, 2, 5, 3, 7, 101, 18]

        Initially: tails = []

        num = 10:
         left=0,right=0 -> no loop
         left==tails.size() so add 10
         tails=[10]

        num = 9:
         left=0,right=1
         mid=0 tails[0]=10<9? no -> right=0
         loop ends left=0
         replace tails[0]=9
         tails=[9]

        num = 2:
         left=0,right=1
         mid=0 tails[0]=9<2? no -> right=0
         loop ends left=0
         replace tails[0]=2
         tails=[2]

        num = 5:
         left=0,right=1
         mid=0 tails[0]=2<5? yes -> left=1
         loop ends left=1==size so add 5
         tails=[2,5]

        num = 3:
         left=0,right=2
         mid=1 tails[1]=5<3? no -> right=1
         mid=0 tails[0]=2<3? yes -> left=1
         loop ends left=1
         replace tails[1]=3
         tails=[2,3]

        num = 7:
         left=0,right=2
         mid=1 tails[1]=3<7? yes -> left=2
         loop ends left=2==size so add 7
         tails=[2,3,7]

        num = 101:
         left=0,right=3
         mid=1 tails[1]=3<101? yes left=2
         mid=2 tails[2]=7<101? yes left=3
         loop ends left=3==size so add 101
         tails=[2,3,7,101]

        num = 18:
         left=0,right=4
         mid=2 tails[2]=7<18? yes left=3
         mid=3 tails[3]=101<18? no right=3
         loop ends left=3
         replace tails[3]=18
         tails=[2,3,7,18]

        Return tails.size() = 4
    */
    public int LengthOfLISBinarySearch(int[] nums) {
        ArrayList<Integer> tails = new ArrayList<>();

        for(int num : nums) {
            int left = 0;
            int right = tails.size();

            while(left < right) {
                int mid = left + (right - left) / 2;
                if(tails.get(mid) < num)
                    left = mid + 1;
                else
                    right = mid;
            }
            if(left == tails.size())
                tails.add(num);
            else
                tails.set(left, num);
        }

        return tails.size();
    }
}
