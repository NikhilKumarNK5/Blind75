/*
Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
You must write an algorithm that runs in O(n) time.

Example 1:
Input: nums = [100,4,200,1,3,2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.

Example 2:
Input: nums = [0,3,7,2,5,8,4,6,0,1]
Output: 9

Example 3:
Input: nums = [1,0,1,2]
Output: 3

Constraints:
0 <= nums.length <= 10^5
-10^9 <= nums[i] <= 10^9
*/

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class LongestConsecutiveSequence {

    // Approach 1: Sorting the array
    // Sort the array and traverse through it to identify the longest consecutive subsequence
    // TC => O(NLogN)
    // SC => O(1)
    public int longestConsecutiveSorting(int[] nums) {

        if(nums.length == 0)
            return 0; // edge case -> empty array

        Arrays.sort(nums);

        int maxLen = 1;
        int currLen = 1;

        for(int i = 1; i < nums.length; i++) {
            if(nums[i] == nums[i - 1] + 1)
                currLen++;
            else if(nums[i] == nums[i - 1])
                continue; // skips duplicate
            else
                currLen = 1;
            maxLen = Math.max(maxLen, currLen);
        }
        return maxLen;
    }

    // Approach 2: Using HashMap -> Add all elements to a HashMap and the check inside the HashMap
    // TC => O(N)
    // SC => O(N)
    public int longestConsecutive(int[] nums) {
        int maxLen = 0;

        HashMap<Integer, Boolean> map = new HashMap<>();
        for(int num : nums)
            map.put(num, Boolean.FALSE);

        for(int num : nums) {
            int currLen = 1;

            // check in forward direction
            int nextNum = num + 1;
            while(map.containsKey(nextNum) && !map.get(nextNum)) {
                currLen++;
                map.put(nextNum, Boolean.TRUE);
                nextNum++;
            }

            // check in reverse direction
            int prevNum = num - 1;
            while(map.containsKey(prevNum) && !map.get(prevNum)) {
                currLen++;
                map.put(prevNum, Boolean.TRUE);
                prevNum--;
            }

            maxLen = Math.max(maxLen, currLen);
        }

        return maxLen;
    }

    // Approach 3: Using HashSet
    // TC => O(N)
    // SC => O(N)
    public int longestConsecutiveSet(int[] nums) {

        if(nums.length == 0)
            return 0;

        int maxLen = 0;

        HashSet<Integer> set = new HashSet<>();
        for(int num : nums)
            set.add(num);

        for(int num : set) {
            // only start conting if it's the start of a sequence
            if(!set.contains(num - 1)) {
                int currNum = num;
                int currLen = 1;

                while(set.contains(currNum + 1)) {
                    currNum++;
                    currLen++;
                }

                maxLen = Math.max(maxLen, currLen);
            }
        }

        return maxLen;
    }
}
