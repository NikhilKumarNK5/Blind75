/*
Given an array of strings strs, group the anagrams together.
You can return the answer in any order.

Example 1:
Input: strs = ["eat","tea","tan","ate","nat","bat"]
Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
Explanation:
There is no string in strs that can be rearranged to form "bat".
The strings "nat" and "tan" are anagrams as they can be rearranged to form each other.
The strings "ate", "eat", and "tea" are anagrams as they can be rearranged to form each other.

Example 2:
Input: strs = [""]
Output: [[""]]

Example 3:
Input: strs = ["a"]
Output: [["a"]]

Constraints:
1 <= strs.length <= 10^4
0 <= strs[i].length <= 100
strs[i] consists of lowercase English letters.
*/

import java.util.*;

public class GroupAnagrams {

    // Approach 1: Brute Force, compare each string with another to see if anagrams and store to result
    // TC => O(N^2 * k)
    // SC => O(1)
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> list = new ArrayList<>();
        HashSet<Integer> grouped = new HashSet<>();

        for(int i = 0; i < strs.length; i++) {
            if(grouped.contains(i))
                continue; // already grouped

            List<String> group = new ArrayList<>();
            group.add(strs[i]);
            grouped.add(i);

            for(int j = i + 1; j < strs.length; j++) {
                if(grouped.contains(j))
                    continue;
                if(isAnagram(strs[i], strs[j])) {
                    group.add(strs[j]);
                    grouped.add(j);
                }
            }
            list.add(group);
        }
        return list;
    }

    private boolean isAnagram(String s1, String s2) {
        if(s1.length() != s2.length())
            return false;
        int[] count = new int[26];

        for(char c: s1.toCharArray()) {
            count[c - 'a']++;
        }

        for(char c: s2.toCharArray()) {
            count[c - 'a']--;
        }

        for(int n : count) {
            if(n != 0)
                return false;
        }
        return true;
    }

    // Approach 2: Using Sorting and HashMap
    // TC => O(N * kLogk)
    // SC => O(N)
    public List<List<String>> groupAnagramsSorting(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();

        for(String word : strs) {
            String sortedWord = sortString(word);

            if(!map.containsKey(sortedWord)) {
                map.put(sortedWord, new ArrayList<>());
            }
            map.get(sortedWord).add(word);
        }
        return new ArrayList<>(map.values());
    }

    private String sortString(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        String sorted = new String(chars);
        return sorted;
    }

    // Approach 3: Character Count as Key
    // TC => O(N * K)
    // SC => O(N)
    public List<List<String>> groupAnagramsOptimizedChCount(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();

        for(String word : strs) {
            int[] charCount = new int[26];
            for(int i = 0; i < word.length(); i++) {
                charCount[word.charAt(i) - 'a']++;
            }

            String key = Arrays.toString(charCount);

            if(!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(word);
        }
        return new ArrayList<>(map.values());
    }
}
