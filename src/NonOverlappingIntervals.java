/*
Given an array of intervals intervals where intervals[i] = [starti, endi],
return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
Note that intervals which only touch at a point are non-overlapping. For example, [1, 2] and [2, 3] are non-overlapping.

Example 1:
Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
Output: 1
Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.

Example 2:
Input: intervals = [[1,2],[1,2],[1,2]]
Output: 2
Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlapping.

Example 3:
Input: intervals = [[1,2],[2,3]]
Output: 0
Explanation: You don't need to remove any of the intervals since they're already non-overlapping.

Constraints:
1 <= intervals.length <= 10^5
intervals[i].length == 2
-5 * 10^4 <= starti < endi <= 5 * 10^4
*/

import java.util.Arrays;

public class NonOverlappingIntervals {

    // Approach: Sort based upon end time and compare and keep the interval with less end time
    // TC => O(NLogN)
    // SC => O(1)
    public int eraseOverlapIntervals(int[][] intervals) {
        int n = intervals.length;

        if (n == 0)
            return 0;

        // Sort the intervals based on the end time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));

        int count = 1;

        // end time of the first/current interval
        int prev = 0;

        for (int i = 1; i < n; i++) {

            // If the start time of the next interval is greater than or equal to
            // the end time of the current interval, then we can keep it
            if (intervals[i][0] >= intervals[prev][1]) {
                prev = i;
                count++;
            }
        }

        // Return the number of intervals to remove
        return n - count;
    }
}
