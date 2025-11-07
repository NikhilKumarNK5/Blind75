/*
The median is the middle value in an ordered integer list.
If the size of the list is even, there is no middle value, and the median is the mean of the two middle values.
For example, for arr = [2,3,4], the median is 3.
For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
Implement the MedianFinder class:
MedianFinder() initializes the MedianFinder object.
void addNum(int num) adds the integer num from the data stream to the data structure.
double findMedian() returns the median of all elements so far.
Answers within 10-5 of the actual answer will be accepted.

Example 1:
Input
["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
[[], [1], [2], [], [3], []]

Output
[null, null, null, 1.5, null, 2.0]

Explanation
MedianFinder medianFinder = new MedianFinder();
medianFinder.addNum(1);    // arr = [1]
medianFinder.addNum(2);    // arr = [1, 2]
medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
medianFinder.addNum(3);    // arr[1, 2, 3]
medianFinder.findMedian(); // return 2.0

Constraints:
-10^5 <= num <= 10^5
There will be at least one element in the data structure before calling findMedian.
At most 5 * 10^4 calls will be made to addNum and findMedian.

Follow up:
If all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
If 99% of all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
*/

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class FindMedianFromDataStream {

    // Approach: Finding median using Heaps
    // TC => O(LogN)
    // SC => O(N)
    class MedianFinder {

        Queue<Integer> maxHeap; // Left will apply Max heap, because we want large element at the top
        Queue<Integer> minHeap; // Right will apply Min heap, because we want small element at the top

        public MedianFinder() {
            maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            minHeap = new PriorityQueue<>();
        }

        public void addNum(int num) {
            // In left heap we will add element only if it is empty or num is smaller than the top element of left
            if(maxHeap.isEmpty() || num < maxHeap.peek())
                maxHeap.add(num);
            else
                minHeap.add(num); // Else we will add element in the right heap

            // Either both heap should have equal number of values, or left heap should have only 1 value extra than right heap

            // If the size difference between two is greater than 1 then move left top to right
            if(Math.abs(maxHeap.size() - minHeap.size()) > 1)
                minHeap.add(maxHeap.poll());
            else if(maxHeap.size() < minHeap.size())
                maxHeap.add(minHeap.poll());
        }

        public double findMedian() {

            // even stream size
            if(minHeap.size() == maxHeap.size())
                return (minHeap.peek() + maxHeap.peek()) / 2.0;

            // odd stream size
            return maxHeap.peek();
        }
    }

    /**
     * Your MedianFinder object will be instantiated and called as such:
     * MedianFinder obj = new MedianFinder();
     * obj.addNum(num);
     * double param_2 = obj.findMedian();
     */
}
