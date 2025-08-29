/*
You are given a string s and an integer k.
You can choose any character of the string and change it to any other uppercase English character.
You can perform this operation at most k times.
Return the length of the longest substring containing the same letter you can get after performing the above operations.

Example 1:
Input: s = "ABAB", k = 2
Output: 4
Explanation: Replace the two 'A's with two 'B's or vice versa.

Example 2:
Input: s = "AABABBA", k = 1
Output: 4
Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.
There may exists other ways to achieve this answer too.

Constraints:
1 <= s.length <= 10^5
s consists of only uppercase English letters.
0 <= k <= s.length
*/

public class LongestRepeatingCharacterReplacement {

    // Approach 1: Brute Force - for each substring count how many characters we need to change to make all it's letters same, if no less than equal to k, update the answer.
    // TC => O(N^2) - TLE
    public int characterReplacement(String s, int k) {
        int n = s.length();
        int maxLen = 0;
        for(int i = 0; i < n; i++) {
            int[] count = new int[26];
            int maxCount = 0;
            for(int j = i; j < n; j++) {
                count[s.charAt(j) - 'A']++;
                maxCount = Math.max(maxCount, count[s.charAt(j) - 'A']);
                int changes = (j - i + 1) - maxCount; // count of characters that need to be changed (length - max-> character that appears most number of times)
                if(changes <= k) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }
        return maxLen;
    }

    // Approach 2: Sliding Window - maintain maxFrequency character in window
    // TC => O(N)
    public int characterReplacementSlidingWindow(String s, int k) {
        int n = s.length();
        int left = 0;
        int right = 0;
        int maxCount = 0;
        int maxLen = 0;
        int[] freq = new int[26];
        while(right < n) {
            freq[s.charAt(right) - 'A']++;
            maxCount = Math.max(maxCount, freq[s.charAt(right) - 'A']);

            if((right - left + 1) - maxCount > k) {
                freq[s.charAt(left) - 'A']--;
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
            right++;
        }
        return maxLen;
    }
}
