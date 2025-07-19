/*
Suppose an array of length n sorted in ascending order is rotated between 1 and n times.
For example, the array nums = [0,1,2,4,5,6,7] might become:
[4,5,6,7,0,1,2] if it was rotated 4 times.
[0,1,2,4,5,6,7] if it was rotated 7 times.
Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]]
1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
Given the sorted rotated array nums of unique elements, return the minimum element of this array.
You must write an algorithm that runs in O(log n) time.

Example 1:
Input: nums = [3,4,5,1,2]
Output: 1
Explanation: The original array was [1,2,3,4,5] rotated 3 times.

Example 2:
Input: nums = [4,5,6,7,0,1,2]
Output: 0
Explanation: The original array was [0,1,2,4,5,6,7] and it was rotated 4 times.

Example 3:
Input: nums = [11,13,15,17]
Output: 11
Explanation: The original array was [11,13,15,17] and it was rotated 4 times.

Constraints:
n == nums.length
1 <= n <= 5000
-5000 <= nums[i] <= 5000
All the integers of nums are unique.
nums is sorted and rotated between 1 and n times.
*/

public class FindMinimumInRotatedSortedArray {

    // Approach 1: Brute Force
    // Do Liner search and find the minimum element in the array
    // TC => O(N) -> not was is asked as they want the TC to be O(LogN)
    public int findMin(int[] nums) {
        int n = nums.length;
        int min = nums[0];

        for(int i = 1; i < n; i++) {
            if(nums[i] < min) {
                min = nums[i];
            }
        }
        return min;
    }

    // Approach 2: Binary Search
    // Whenever in the question we see 'sorted', we can immediately think of Binary Search
    /*
        Input: nums = [4, 5, 6, 7, 0, 1, 2]

        Initial:
            n = 7
            start = 0
            end = 6

        Iteration 1:
            mid = 3 → nums[3] = 7
            next = (3 + 1) % 7 = 4 → nums[4] = 0
            prev = (3 - 1 + 7) % 7 = 2 → nums[2] = 6
            Check: 7 <= 6 && 7 <= 0 → ❌
            nums[mid] < nums[end]? → 7 < 2? ❌
            else → start = mid + 1 = 4

        Iteration 2:
            mid = 5 → nums[5] = 1
            next = (5 + 1) % 7 = 6 → nums[6] = 2
            prev = (5 - 1 + 7) % 7 = 4 → nums[4] = 0
            Check: 1 <= 0 && 1 <= 2 → ❌
            nums[mid] < nums[end]? → 1 < 2 → ✅
            So → end = mid - 1 = 4

        Iteration 3:
            mid = 4 → nums[4] = 0
            next = (4 + 1) % 7 = 5 → nums[5] = 1
            prev = (4 - 1 + 7) % 7 = 3 → nums[3] = 7
            Check: 0 <= 7 && 0 <= 1 → ✅ → RETURN 0

        Result: 0 (Minimum element)
    */

    // TC => O(LogN)
    public int findMinBinarySearch(int[] nums) {
        int n = nums.length;
        int start = 0;
        int end = n - 1;

        while(start <= end) {
            int mid = start + (end - start) / 2;
            int next = (mid + 1) % n; // modulo n to avoid IndexOutOfBound
            int prev = (mid - 1 + n) % n; // we are doing mid - 1 + n to prevent the prev to become negative

            if(nums[mid] <= nums[next] && nums[mid] <= nums[prev]) {
                return nums[mid]; // if the current element is less than both then it is the minimum
            } else if(nums[mid] <= nums[end]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }

    // Approach 3 - Binary Search - apply when the array contains duplicates as well
    /*
        Input: nums = [2, 2, 2, 0, 1, 2]

        Initial:
            start = 0, end = 5

        Iteration 1
            mid = (0 + 5) / 2 = 2 → nums[2] = 2, nums[end] = 2
            nums[mid] == nums[end] → can't determine side
            → end-- → end = 4

        Iteration 2
            mid = (0 + 4) / 2 = 2 → nums[2] = 2, nums[end] = 1
            nums[mid] > nums[end] → min must be to the right
            → start = mid + 1 = 3

        Iteration 3
            mid = (3 + 4) / 2 = 3 → nums[3] = 0, nums[end] = 1
            nums[mid] < nums[end] → min is in left half (including mid)
            → end = mid = 3

        Now:
            start = 3, end = 3 → loop exits

        Return: nums[start] = nums[3] = 0
    */

    // TC => O(LogN)
    public int findMinWithDuplicates(int[] nums) {
        int n = nums.length;
        int start = 0;
        int end = n - 1;

        while (start < end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] < nums[end]) {
                end = mid; // minimum is in the left half (including mid)
            } else if (nums[mid] > nums[end]) {
                start = mid + 1; // minimum is in the right half (excluding mid)
            } else {
                end--; // nums[mid] == nums[end], cannot decide — shrink the search space
            }
        }

        return nums[start];
    }
}
