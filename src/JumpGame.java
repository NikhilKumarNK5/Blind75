/*
You are given an integer array nums.
You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.
Return true if you can reach the last index, or false otherwise.

Example 1:
Input: nums = [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.

Example 2:
Input: nums = [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what.
Its maximum jump length is 0, which makes it impossible to reach the last index.

Constraints:
1 <= nums.length <= 10^4
0 <= nums[i] <= 10^5
*/

public class JumpGame {

    // Approach: Greedy (Reverse)
    // Start from the end of the array and move backward.
    // Keep a finalPos pointer representing the leftmost position that can reach the end.
    // If the current index can jump to or beyond finalPos, update finalPos to this index.
    // At the end, if finalPos == 0, the jump is possible.
    // TC => O(N)
    // SC => O(1)
    public boolean canJump(int[] nums) {
        int n = nums.length;
        // initially final position is the last index
        int finalPos = n - 1;

        for(int i = n - 2; i >= 0; i--) {
            // if we can reach the final position from this index we update the final position flag
            if(i + nums[i] >= finalPos)
                finalPos = i;
        }

        // if we reach the first index then the jump is possible
        return finalPos == 0;
    }
}
