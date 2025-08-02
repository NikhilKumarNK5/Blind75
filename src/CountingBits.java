/*
Given an integer n, return an array ans of length n + 1 such that for each i (0 <= i <= n),
ans[i] is the number of 1's in the binary representation of i.

Example 1:
Input: n = 2
Output: [0,1,1]
Explanation:
0 --> 0
1 --> 1
2 --> 10
Example 2:

Input: n = 5
Output: [0,1,1,2,1,2]
Explanation:
0 --> 0
1 --> 1
2 --> 10
3 --> 11
4 --> 100
5 --> 101

Constraints:
0 <= n <= 10^5

Follow up:
It is very easy to come up with a solution with a runtime of O(n log n).
Can you do it in linear time O(n) and possibly in a single pass?
Can you do it without using any built-in function (i.e., like __builtin_popcount in C++)?
*/

public class CountingBits {

    // Approach 1: Count the number of set bits of each number from 0 to n and put the set bits into an array
    // TC => O(NLogN)
    public int[] countBits(int n) {
        int[] res = new int[n + 1];
        for(int i = 0; i <= n; i++) {
            res[i] = numSetBits(i);
        }
        return res;
    }

    public static int numSetBits(int A) {
        int count = 0;
        while(A != 0) {
            if((A & 1) == 1) {
                count++;
            }
            A = A >> 1;
        }
        return count;
    }

    // Approach 2: Using Dynamic Programming
    // All numbers between powers of 2 can be built from numbers before that power.
    // For example, 6 = 4 (which is power of 2) + 2 → so set bits in 6 = 1 (from 4) + set bits in 2.
    /*
        Dry Run for countBitsDP(n = 12):

        | i  | offset | i == offset * 2? | res[i] = 1 + res[i - offset] | res[i] | Binary (i) |
        |----|--------|------------------|------------------------------|--------|------------|
        | 1  | 1      | No               | 1 + res[0] = 1 + 0           | 1      | 0001       |
        | 2  | 1 → 2  | Yes              | 1 + res[0] = 1 + 0           | 1      | 0010       |
        | 3  | 2      | No               | 1 + res[1] = 1 + 1           | 2      | 0011       |
        | 4  | 2 → 4  | Yes              | 1 + res[0] = 1 + 0           | 1      | 0100       |
        | 5  | 4      | No               | 1 + res[1] = 1 + 1           | 2      | 0101       |
        | 6  | 4      | No               | 1 + res[2] = 1 + 1           | 2      | 0110       |
        | 7  | 4      | No               | 1 + res[3] = 1 + 2           | 3      | 0111       |
        | 8  | 4 → 8  | Yes              | 1 + res[0] = 1 + 0           | 1      | 1000       |
        | 9  | 8      | No               | 1 + res[1] = 1 + 1           | 2      | 1001       |
        | 10 | 8      | No               | 1 + res[2] = 1 + 1           | 2      | 1010       |
        | 11 | 8      | No               | 1 + res[3] = 1 + 2           | 3      | 1011       |
        | 12 | 8      | No               | 1 + res[4] = 1 + 1           | 2      | 1100       |

        Final result: res = [0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 2, 3, 2]
     */
    // TC: O(N)
    public int[] countBitsDP(int n) {
        int[] res = new int[n + 1]; // Result array to hold number of set bits for each i
        int offset = 1; // Tracks the most recent power of 2 ≤ i

        // i = 0 is base case, res[0] = 0 by default

        for (int i = 1; i <= n; i++) {
            // If i reaches the next power of 2 (offset * 2), update offset
            if (i == (offset * 2))
                offset *= 2;

            // res[i] = 1 (for the set bit at offset) + res[i - offset]
            res[i] = 1 + res[i - offset];
        }

        return res;
    }
}
