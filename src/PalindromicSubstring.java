/*
Given a string s, return the number of palindromic substrings in it.
A string is a palindrome when it reads the same backward as forward.
A substring is a contiguous sequence of characters within the string.

Example 1:
Input: s = "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".

Example 2:
Input: s = "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".

Constraints:
1 <= s.length <= 1000
s consists of lowercase English letters.
*/

public class PalindromicSubstring {

    // Approach: Expand around center - consider odd and even length and expand on both ends considering them as center and check if they are palindrome or not
    // TC => O(N^2)
    public int countSubstrings(String s) {
        int n = s.length();
        int res = 0;

        for(int i = 0; i < n; i++) {

            // odd length
            int left = i;
            int right = i;

            while(left >= 0 && right < n) {
                if(s.charAt(left) == s.charAt(right)) {
                    res++;
                } else {
                    break;
                }

                left--;
                right++;
            }

            // even length
            left = i;
            right = i + 1;

            while(left >= 0 && right < n) {
                if(s.charAt(left) == s.charAt(right)) {
                    res++;
                } else {
                    break;
                }

                left--;
                right++;
            }
        }
        return res;
    }
}
