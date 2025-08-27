/*
A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward.
Alphanumeric characters include letters and numbers.
Given a string s, return true if it is a palindrome, or false otherwise.

Example 1:
Input: s = "A man, a plan, a canal: Panama"
Output: true
Explanation: "amanaplanacanalpanama" is a palindrome.

Example 2:
Input: s = "race a car"
Output: false
Explanation: "raceacar" is not a palindrome.

Example 3:
Input: s = " "
Output: true
Explanation: s is an empty string "" after removing non-alphanumeric characters.
Since an empty string reads the same forward and backward, it is a palindrome.

Constraints:
1 <= s.length <= 2 * 10^5
s consists only of printable ASCII characters.
*/
public class ValidPalindrome {

    // Approach 1: Removing the non-alphanumeric characters from the string and appending to new StringBuilder
    // After that checking using two pointers if it is palindrome or not
    // TC => O(N)
    // SC => O(N)
    public boolean isPalindrome(String s) {
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < s.length(); i++) {
            if(Character.isLetterOrDigit(s.charAt(i))) // checking if the char is alphanumeric
                res.append(Character.toLowerCase(s.charAt(i))); // adding only if it is alphanumeric
        }

        s = res.toString();
        int start = 0;
        int end = s.length() - 1;

        while(start <= end) {
            if(s.charAt(start) != s.charAt(end))
                return false;
            start++;
            end--;
        }
        return true;
    }

    // Approach 2: Two Pointers directly on the given string
    // TC => O(N)
    // SC => O(1)
    public boolean isPalindromeTP(String s) {
        int start = 0;
        int end = s.length() - 1;

        while(start <= end) {

            // checking if it is non-alphanumeric then skip and continue to next iteration
            if(!Character.isLetterOrDigit(s.charAt(start))) {
                start++;
                continue;
            }

            // checking if it is non-alphanumeric then skip and continue to next iteration
            if(!Character.isLetterOrDigit(s.charAt(end))) {
                end--;
                continue;
            }

            // check for charAt(end) and charAt(start) to be equal only if start <= end
            if(start <= end) {
                if(Character.toLowerCase(s.charAt(start)) != Character.toLowerCase(s.charAt(end)))
                    return false;
            }

            start++;
            end--;
        }
        return true;
    }
}
