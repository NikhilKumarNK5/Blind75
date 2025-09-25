/*
Given an integer array nums and an integer k, return the k most frequent elements.
You may return the answer in any order.

Example 1:
Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]

Example 2:
Input: nums = [1], k = 1
Output: [1]

Example 3:
Input: nums = [1,2,1,2,1,2,3,1,3,2], k = 2
Output: [1,2]

Constraints:
1 <= nums.length <= 10^5
-10^4 <= nums[i] <= 10^4
k is in the range [1, the number of unique elements in the array].
It is guaranteed that the answer is unique.

Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
*/

import java.util.*;

public class TopKFrequentElements {

    // Approach 1: Brute Force - Count frequency of each element using a HashMap - Convert to a list and sort by frequency descending - Take first k elements.
    // TC => O(NlogN)
    // SC => O(N)
    public int[] topKFrequentBruteForce(int[] nums, int k) {
        //Count frequency of each element
        HashMap<Integer, Integer> freqMap = new HashMap<>();
        for(int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        //Convert map entries to a list
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(freqMap.entrySet());

        //Sort the list by frequency descending
        Collections.sort(list, (a, b) -> b.getValue() - a.getValue());

        //Take the first k elements
        int[] result = new int[k];
        for(int i = 0; i < k; i++) {
            result[i] = list.get(i).getKey();
        }

        return result;
    }

    // Approach 2: Using Min-Heap (PriorityQueue)
    // Build a frequency map - Push entries into a min-heap of size k by frequency - Pop when size > k to keep only top k - Extract the elements from the heap at the end
    // TC => O(NlogK)
    // SC => O(N)
    public int[] topKFrequentPriorityQueue(int[] nums, int k) {
        // Count frequency of each element
        HashMap<Integer, Integer> freqMap = new HashMap<>();
        for(int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // Create a min-heap (PriorityQueue) by frequency
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap =
                new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());

        // Push entries into min-heap
        for(Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            minHeap.offer(entry);
            if(minHeap.size() > k) {
                minHeap.poll(); // remove smallest frequency
            }
        }

        // Extract top k elements from the heap
        int[] result = new int[k];
        int idx = 0;
        while(!minHeap.isEmpty()) {
            result[idx++] = minHeap.poll().getKey();
        }

        return result;
    }

    // Approach 3: Bucket Sort Approach
    // Build frequency map - Create an array of lists (buckets) where index = frequency - Place numbers into their bucket - Traverse buckets from high to low frequency to pick k elements
    // TC => O(N)
    // SC => O(N)
    public int[] topKFrequent(int[] nums, int k) {
        // Build frequency map
        HashMap<Integer, Integer> freqMap = new HashMap<>();
        for(int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // Create buckets
        List<Integer>[] buckets = new List[nums.length + 1]; // The maximum frequency any number can have = nums.length
        for(int i = 0; i <= nums.length; i++) {
            buckets[i] = new ArrayList<>();
        }

        // Place numbers into their frequency bucket
        for(Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            int number = entry.getKey();
            int freq = entry.getValue();
            buckets[freq].add(number);
        }

        // Traverse buckets from high frequency to low
        List<Integer> resultList = new ArrayList<>();
        for(int i = buckets.length - 1; i >= 0 && resultList.size() < k; i--) {
            for(int num : buckets[i]) {
                resultList.add(num);
                if(resultList.size() == k)
                    break;
            }
        }

        // Convert to array
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = resultList.get(i);
        }

        return result;
    }
}
