/*
Given an array of integers nums and an integer target,
return indices of the two numbers such that they add up to target.
You may assume that each input would have exactly one solution, and you may not use the same element twice.
You can return the answer in any order.

Example 1:
Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].

Example 2:
Input: nums = [3,2,4], target = 6
Output: [1,2]

Example 3:
Input: nums = [3,3], target = 6
Output: [0,1]

Constraints:
2 <= nums.length <= 104
-109 <= nums[i] <= 109
-109 <= target <= 109
Only one valid answer exists.
*/

import java.util.Arrays;
import java.util.HashMap;

public class TwoSum {

    // Approach 1: Brute Force Approach
    // for each element in nums check all the other elements and verify if the sum is equal to the target of not
    // if the sum equals target that store the element indices in the result array and return
    // TC => O(N^2)
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        for(int i = 0; i < nums.length; i++) {
            for(int j = i + 1; j < nums.length; j++) {
                if(nums[i] + nums[j] == target) {
                    res[0] = i;
                    res[1] = j;
                }
            }
        }
        return res;
    }

    // Approach 2: Using Sorting
    // We can sort the array and store in new variable (O(nlogn))
    // We can do the binary search and find out if an element, and it's target - element value exists or not (O(logn))
    // We can get the elements and in the original array we can search for the indices of the those elements and return the indices as required
    // TC => O(NlogN)
    // SC => O(N)
    public int[] twoSumSortAndSearch(int[] nums, int target) {
        // copy and sort the array to preserve the original array
        int[] sorted = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sorted);

        // using binary search to find out the respective numbers
        int num1 = -1, num2 = -1;
        for (int i = 0; i < sorted.length; i++) {
            int difference = target - sorted[i];
            int index = Arrays.binarySearch(sorted, i + 1, sorted.length, difference);
            if (index > i) {
                num1 = sorted[i];
                num2 = sorted[index];
                break;
            }
        }

        // finding original indices in the unsorted array
        int index1 = -1, index2 = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == num1 && index1 == -1) {
                index1 = i;
            } else if (nums[i] == num2 && index2 == -1) {
                index2 = i;
            }
        }

        return new int[] {index1, index2};
    }

    // Approach 3: Using HashMap
    // Use the element as key and index as value
    // Will check if the value and the difference exists in the HashMap
    // if exists we can get their indices otherwise we can move forward and add the element and index in HashMap
    // TC => O(N)
    // SC => O(N)
    public int[] twoSumHashMap(int[] nums, int target) {
        HashMap<Integer, Integer> hs = new HashMap<>();

        for(int i = 0; i < nums.length; i++) {
            int difference = target - nums[i];
            if(hs.containsKey(difference)) {
                return new int[] {hs.get(difference), i};
            } else {
                hs.put(nums[i], i);
            }
        }
        return null;
    }

}
