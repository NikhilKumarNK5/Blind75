/*
Given a string s containing just the characters '(', ')', '{', '}', '[' and ']',
determine if the input string is valid.
An input string is valid if:
Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Every close bracket has a corresponding open bracket of the same type.

Example 1:
Input: s = "()"
Output: true

Example 2:
Input: s = "()[]{}"
Output: true

Example 3:
Input: s = "(]"
Output: false

Example 4:
Input: s = "([])"
Output: true

Example 5:
Input: s = "([)]"
Output: false

Constraints:
1 <= s.length <= 10^4
s consists of parentheses only '()[]{}'.
*/

import java.util.Stack;

public class ValidParentheses {

    // Approach 1: Brute Force - look for the parentheses combo and remove from string
    // TC => O(N^2)
    // SC => O(1)
    public boolean isValid(String s) {
        int len;
        do {
            len = s.length();
            s = s.replace("()", "")
                    .replace("[]", "")
                    .replace("{}", "");
        } while (len != s.length());
        return s.isEmpty();
    }

    // Approach 2: Using Stack
    // TC => O(N)
    // SC => O(N)
    public boolean isValidUsingStack(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[')
                stack.push(c);
            else {
                if (stack.isEmpty())
                    return false;
                char top = stack.pop();
                if ((c == ')' && top != '(') || (c == ']' && top != '[') || (c == '}' && top != '{'))
                    return false;
            }
        }
        return stack.isEmpty();
    }

    // Approach 3: Using stack in a different way as compared to above
    // TC => O(N)
    // SC => O(N)
    public boolean isValidUsingStackSecond(String s) {
        Stack<Character> stack = new Stack<>();
        for(char c : s.toCharArray()) {
            if(c == '(' || c == '{' || c == '[')
                stack.push(c);
            else {
                if(c == ')') {
                    if(!stack.isEmpty() && stack.peek() == '(')
                        stack.pop();
                    else
                        return false;
                } else if(c == '}') {
                    if(!stack.isEmpty() && stack.peek() == '{')
                        stack.pop();
                    else
                        return false;
                } else if(c == ']') {
                    if(!stack.isEmpty() && stack.peek() == '[')
                        stack.pop();
                    else
                        return false;
                }
            }
        }

        return stack.isEmpty();
    }
}
