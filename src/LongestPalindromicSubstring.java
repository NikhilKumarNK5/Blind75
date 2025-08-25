/*
Given a string s, return the longest palindromic substring in s.

Example 1:
Input: s = "babad"
Output: "bab"
Explanation: "aba" is also a valid answer.

Example 2:
Input: s = "cbbd"
Output: "bb"

Constraints:
1 <= s.length <= 1000
s consist of only digits and English letters.
*/

public class LongestPalindromicSubstring {

    // Approach: Track the oddLength and evenLength substring to find the palindrome and calculate the maxLength
    // TC => O(N^2)
    // SC => O(1)
    public String longestPalindrome(String s) {
        int n = s.length();
        int maxLength = 0;
        int start = 0; // the starting index for the palindromic substring

        for(int i = 0; i < n; i++) {
            // for odd length
            int left = i;
            int right = i;

            while(left >= 0 && right < n) {
                if(s.charAt(left) != s.charAt(right))
                    break;
                // expand the window
                left--;
                right++;
            }
            if(maxLength < right - left - 1) {
                maxLength = right - left - 1;
                start = i;
            }

            // for even length
            left = i;
            right = i + 1;

            while(left >= 0 && right < n) {
                if(s.charAt(left) != s.charAt(right))
                    break;
                // expand the window
                left--;
                right++;
            }
            if(maxLength < right - left + 1) {
                maxLength = right - left - 1;
                start = i;
            }
        }
        return s.substring(start, maxLength + start);
    }
}
