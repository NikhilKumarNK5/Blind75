/*
Given an integer array nums, return true if any value appears at least twice in the array,
and return false if every element is distinct.

Example 1:
Input: nums = [1,2,3,1]
Output: true
Explanation:
The element 1 occurs at the indices 0 and 3.

Example 2:
Input: nums = [1,2,3,4]
Output: false
Explanation:
All elements are distinct.
Example 3:
Input: nums = [1,1,1,3,3,4,3,2,4,2]
Output: true

Constraints:
1 <= nums.length <= 10^5
-10^9 <= nums[i] <= 10^9
*/

import java.util.Arrays;
import java.util.HashSet;

public class ContainsDuplicate {
    // Approach 1: Brute Force
    // we can compare every single value with all the values present inside the array
    // TC => O(N^2) -> TLE
    public boolean containsDuplicate(int[] nums) {
        for(int i = 0; i < nums.length; i++) {
            for(int j = i + 1; j < nums.length; j++) {
                if(nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    // Approach Two: Using Sorting
    // Sort the array
    // Compare the adjust elements and if they are equal return true else return false
    // TC => O(NlogN)
    public boolean containsDuplicateSorting(int[] nums) {
        Arrays.sort(nums);
        for(int i = 0; i < nums.length - 1; i++) {
            if(nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
    }

    // Approach Three: Using HashSet
    // Put the element in the HashSet if it is not present in the HashSet and if already present return true
    // TC => O(N)
    // SC => O(N)
    public boolean containsDuplicateHashMap(int[] nums) {
        HashSet<Integer> hs = new HashSet<>();
        for(int i = 0; i < nums.length; i++) {
            if(hs.contains(nums[i])) {
                return true;
            } else {
                hs.add(nums[i]);
            }
        }
        return false;
    }
}
