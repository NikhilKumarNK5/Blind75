/*
Given two strings s and t, return true if t is an anagram of s, and false otherwise.

Example 1:
Input: s = "anagram", t = "nagaram"
Output: true

Example 2:
Input: s = "rat", t = "car"
Output: false

Constraints:
1 <= s.length, t.length <= 5 * 10^4
s and t consist of lowercase English letters.
Follow up: What if the inputs contain Unicode characters? How would you adapt your solution to such a case?
*/

import java.util.Arrays;
import java.util.HashMap;

public class ValidAnagram {

    // Approach 1: Brute Force - we can sort both the string and compare
    // TC => O(NLogN)
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length())
            return false;

        // convert strings to character array
        char[] arrS = s.toCharArray();
        char[] arrT = t.toCharArray();

        // sort the arrays
        Arrays.sort(arrS);
        Arrays.sort(arrT);

        return Arrays.equals(arrS, arrT);
    }

    // Approach 2: Using an array of fixed size assuming all the characters are english alphabet
    // TC => O(N), SC => O(1)
    public boolean isAnagramFixArray(String s, String t) {
        if(s.length() != t.length())
            return false;

        int[] alphabet = new int[26];

        for(int i = 0; i < s.length(); i++) {
            alphabet[s.charAt(i) - 'a']++;
            alphabet[t.charAt(i) - 'a']--;
        }

        for(int i = 0; i < alphabet.length; i++) {
            if(alphabet[i] != 0)
                return false;
        }
        return true;
    }

    // Approach 3: Simple approach is to use two HashMaps and store the frequency of each character for both strings
    // Compare the frequency and if it is equal return true else return false
    // This approach can be used even when the string contains Unicode characters
    // TC => O(N)
    // SC => O(N)
    public boolean isAnagramHashMap(String s, String t) {
        HashMap<Character, Integer> frequencyS = new HashMap<>();
        HashMap<Character, Integer> frequencyT = new HashMap<>();

        if(s.length() != t.length())
            return false; // if the length of the string is not same then no need to check

        for(int i = 0; i < s.length(); i++) {
            if(frequencyS.containsKey(s.charAt(i))) {
                frequencyS.put(s.charAt(i), frequencyS.get(s.charAt(i)) + 1);
            }
            else {
                frequencyS.put(s.charAt(i), 1);
            }
        }
        for(int i = 0; i < t.length(); i++) {
            if(frequencyT.containsKey(t.charAt(i))) {
                frequencyT.put(t.charAt(i), frequencyT.get(t.charAt(i)) + 1);
            }
            else {
                frequencyT.put(t.charAt(i), 1);
            }
        }

        if(frequencyS.equals(frequencyT)) {
            return true;
        }
        return false;
    }

    // Approach 4: Instead of using two HashMaps the solution can be achieved in only one HashMap
    // TC => O(N)
    public boolean isAnagramHashMapOptimized(String s, String t) {
        HashMap<Character, Integer> frequency = new HashMap<>();

        if(s.length() != t.length())
            return false;

        for (int i = 0; i < s.length(); i++) {
            // Add from s
            frequency.put(s.charAt(i), frequency.getOrDefault(s.charAt(i), 0) + 1);

            // Subtract from t
            frequency.put(t.charAt(i), frequency.getOrDefault(t.charAt(i), 0) - 1);
        }

        // After processing both strings, all counts should be 0
        for (int count : frequency.values()) {
            if (count != 0) return false;
        }

        return true;
    }
}
