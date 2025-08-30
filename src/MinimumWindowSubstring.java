/*
Given two strings s and t of lengths m and n respectively,
return the minimum window substring of s such that every character in t (including duplicates) is included in the window.
If there is no such substring, return the empty string "".
The testcases will be generated such that the answer is unique.

Example 1:
Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.

Example 2:
Input: s = "a", t = "a"
Output: "a"
Explanation: The entire string s is the minimum window.

Example 3:
Input: s = "a", t = "aa"
Output: ""
Explanation: Both 'a's from t must be included in the window.
Since the largest window of s only has one 'a', return empty string.

Constraints:
m == s.length
n == t.length
1 <= m, n <= 10^5
s and t consist of uppercase and lowercase English letters.
Follow up: Could you find an algorithm that runs in O(m + n) time?
*/

public class MinimumWindowSubstring {

    // Approach 1: Find out all the substrings of s and compare their count with that of t
    // TC => O(N^3)
    public String minWindow(String s, String t) {

        if(s.length() < t.length())
            return ""; // if the length of t is greater than return empty string

        int minStart = 0; // to track the start point of the substring
        int minLen = Integer.MAX_VALUE; // to track the minLen of the substring

        // store the character count at their decimal index (A = 65 so count stored at index 65)
        int[] tCount = new int[128];
        for(int i = 0; i < t.length(); i++) {
            tCount[t.charAt(i)]++;
        }

        // checking each substring and comparing the count with the tCount
        for(int start = 0; start < s.length(); start++) {
            for(int end = start + 1; end <= s.length(); end++) {
                String sub = s.substring(start, end); // substring
                int[] windowCount = new int[128]; // to store the count of each character in that substring

                for(int i = 0; i < sub.length(); i++) {
                    windowCount[sub.charAt(i)]++;
                }

                // checks if the count of each character in our substring is greater or equal to as that of tCount
                boolean valid = true;
                for(int c = 0; c < 128; c++) {
                    if(windowCount[c] < tCount[c]) {
                        valid = false;
                        break;
                    }
                }

                // if it is valid that is count is greater or equal to tount and the length of substring is smaller than minLen then update the minLen and minStart
                if(valid && (end - start < minLen)) {
                    minLen = end - start;
                    minStart = start;
                }
            }
        }

        // if no such substring found
        if(minLen == Integer.MAX_VALUE)
            return "";

        return s.substring(minStart, minLen + minStart);
    }

    // Approach 2: Sliding Window
    // TC => O(N)
    public String minWindowSliding(String s, String t) {
        if(s.length() < t.length())
            return "";

        int minStart = 0;
        int minLen = Integer.MAX_VALUE;

        int[] tCount = new int[128];
        for(char c : t.toCharArray()) {
            tCount[c]++;
        }

        int required = 0; // Number of unique characters in t with at least one count
        for(int count : tCount) {
            if (count > 0)
                required++;
        }

        int formed = 0; // to track inside window if substring can be formed or not
        int left = 0;
        int right = 0;

        int[] windowCount = new int[128];

        while(right < s.length()) {
            char c = s.charAt(right);
            windowCount[c]++;
            // if windowCount matches tCount for this char, increment formed
            if(tCount[c] > 0 && windowCount[c] == tCount[c])
                formed++;

            // try to shrink the window from left
            while(formed == required) {
                if(right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minStart = left;
                }
                char leftChar = s.charAt(left);
                windowCount[leftChar]--;
                if(tCount[leftChar] > 0 && windowCount[leftChar] < tCount[leftChar])
                    formed--;
                left++;
            }
            right++;
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minLen + minStart);
    }
}
