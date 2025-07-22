/*
There is an integer array nums sorted in ascending order (with distinct values).
Prior to being passed to your function, nums is possibly rotated at an unknown
pivot index k (1 <= k < nums.length) such that the resulting array is
[nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed).
For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
Given the array nums after the possible rotation and an integer target,
return the index of target if it is in nums, or -1 if it is not in nums.
You must write an algorithm with O(log n) runtime complexity.

Example 1:
Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4

Example 2:
Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1

Example 3:
Input: nums = [1], target = 0
Output: -1

Constraints:
1 <= nums.length <= 5000
-10^4 <= nums[i] <= 10^4
All values of nums are unique.
nums is an ascending array that is possibly rotated.
-10^4 <= target <= 10^4
*/

public class SearchInRotatedSortedArray {
    // Approach 1: Brute Force - using Linear Search
    // TC => O(N)
    public int search(int[] nums, int target) {
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == target)
                return i;
        }
        return -1;
    }

    // Approach 2: Using Binary Search - as the expected time complexity is mentioned as O(LogN)
    /*
        Initial:
            n = 7
            start = 0, end = 6
        Iteration 1
            mid = 0 + (6 - 0) / 2 = 3
            nums[mid] = 7

            nums[start] <= nums[mid] → 4 <= 7 → true → left side is sorted

            target = 0
            target >= nums[start] && target <= nums[mid] → 0 >= 4 && 0 <= 7 → false

            → search right half → start = mid + 1 = 4

        Iteration 2
            start = 4, end = 6
            mid = 4 + (6 - 4) / 2 = 5
            nums[mid] = 1

            nums[start] <= nums[mid] → 0 <= 1 → true → left side is sorted

            target = 0
            target >= nums[start] && target <= nums[mid] → 0 >= 0 && 0 <= 1 → true

            → search left half → end = mid - 1 = 4

        Iteration 3
            start = 4, end = 4
            mid = 4
            nums[mid] = 0 → MATCH

        Return:
            index = 4
    */
    // Works in case of distinct elements in the array
    // TC => O(LogN)
    public int searchBinarySearch(int[] nums, int target) {
        int n = nums.length;
        int start = 0;
        int end = n - 1;

        while(start <= end) {
            int mid = start + (end - start) / 2;
            if(nums[mid] == target) {
                return mid; // if the target is found
            } else if(nums[start] <= nums[mid]) { // left half is sorted
                // target lies in left half
                if(target >= nums[start] && target <= nums[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else { //right half is sorted
                // target lies in right half
                if(target <= nums[end] && target >= nums[mid]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        return -1;
    }
}
