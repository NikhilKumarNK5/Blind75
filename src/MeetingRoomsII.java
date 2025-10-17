/*
Given an array of meeting time interval objects consisting of start and end times [[start_1,end_1],[start_2,end_2],...] (start_i < end_i),
find the minimum number of days required to schedule all meetings without any conflicts.

Note: (0,8),(8,10) is not considered a conflict at 8.

Example 1:
Input: intervals = [(0,40),(5,10),(15,20)]
Output: 2
Explanation:
day1: (0,40)
day2: (5,10),(15,20)

Example 2:
Input: intervals = [(4,9)]
Output: 1

Constraints:
0 <= intervals.length <= 500
0 <= intervals[i].start < intervals[i].end <= 1,000,000

*/

import java.util.List;
import java.util.PriorityQueue;

/**
 * Definition of Interval:
 * public class Interval {
 *     public int start, end;
 *     public Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 * }
 */
public class MeetingRoomsII {
    class Interval {
        public int start, end;
        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    // Approach: Sorting + Priority Queue
    // TC => O(NLogN)
    // TC => O(N)
    public int minMeetingRooms(List<Interval> intervals) {
        if(intervals == null || intervals.size() == 0)
            return 0;

        // Sort intervals by start time
        intervals.sort((a, b) -> a.start - b.start);

        // Min-heap to keep track of earliest ending meeting
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        heap.add(intervals.get(0).end);

        for(int i = 1; i < intervals.size(); i++) {
            Interval curr = intervals.get(i);

            // If current meeting starts after or at the same time as the earliest ending one
            if (curr.start >= heap.peek()) {
                heap.poll(); // reuse the room
            }

            heap.add(curr.end);
        }

        return heap.size();
    }
}
