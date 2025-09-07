/*
You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi]
represent the start and the end of the ith interval and intervals is sorted in ascending order by starti.
You are also given an interval newInterval = [start, end] that represents the start and end of another interval.
Insert newInterval into intervals such that intervals is still sorted in ascending order by starti
and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).

Return intervals after the insertion.
Note that you don't need to modify intervals in-place. You can make a new array and return it.

Example 1:
Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]

Example 2:
Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].

Constraints:
0 <= intervals.length <= 10^4
intervals[i].length == 2
0 <= starti <= endi <= 10^5
intervals is sorted by starti in ascending order.
newInterval.length == 2
0 <= start <= end <= 10^5
*/

import java.util.ArrayList;

public class InsertInterval {

    // Approach 1: Insert the interval at the end, sort and then perform the merge intervals logic
    // TC => O(NLogN)
    // SC => O(N)


    // Approach 2: Insert the non overlapping intervals before the new interval then find the overlapping intervals and add and finally insert the remaining non overlapping intervals
    // TC => O(N)
    // SC => O(N)
    public int[][] insert(int[][] intervals, int[] newInterval) {
        ArrayList<int[]> res = new ArrayList<>();
        int i = 0;
        int currS = newInterval[0];
        int currE = newInterval[1];

        // Add the non overlapping intervals before the new Interval
        while(i < intervals.length && intervals[i][1] < currS) {
            res.add(intervals[i++]);
        }

        // Merge the overlapping intervals
        while(i < intervals.length && intervals[i][0] <= currE) {
            currS = Math.min(intervals[i][0], currS);
            currE = Math.max(intervals[i][1], currE);
            i++;
        }
        // add the overlapped interval
        res.add(new int[] {currS, currE});

        // Add remaining intervals
        while(i < intervals.length) {
            res.add(intervals[i++]);
        }

        return res.toArray(new int[res.size()][]);
    }
}
