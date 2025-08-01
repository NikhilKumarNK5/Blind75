/*
Given a positive integer n, write a function that returns the number of set bits in its
binary representation (also known as the Hamming weight).

Example 1:
Input: n = 11
Output: 3
Explanation:
The input binary string 1011 has a total of three set bits.

Example 2:
Input: n = 128
Output: 1
Explanation:
The input binary string 10000000 has a total of one set bit.

Example 3:
Input: n = 2147483645
Output: 30
Explanation:

The input binary string 1111111111111111111111111111101 has a total of thirty set bits.

Constraints:
1 <= n <= 2^31 - 1

Follow up: If this function is called many times, how would you optimize it?
*/

public class NumberOf1Bits {
    // Approach 1: By converting the number into Binary String
    // TC => O(LogN)
    public int hammingWeight(int n) {
        String binary = Integer.toBinaryString(n);
        int countOfBits = 0;
        for (char c : binary.toCharArray()) {
            if (c == '1') countOfBits++;
        }
        return countOfBits;
    }

    // Approach 2: Division by 2
    // TC => O(LogN)
    public int hammingWeightDivison(int n) {
        int countOfBits = 0;
        while (n > 0) {
            int remainder = n % 2;
            if (remainder == 1) countOfBits++;
            n = n / 2;
        }
        return countOfBits;
    }

    // Approach 3: Counting the set bits while going through all 32 bits of the int
    // TC => O(N) -> N = 32
    public int hammingWeightBitManipulation(int n) {
        int countOfBits = 0;
        for(int i = 0; i < 32; i++) {
            if((n & (1 << i)) != 0)
                countOfBits++;
        }
        return countOfBits;
    }

    // Approach 4:
    // TC => O(LogN)
    public int hammingWeightOptimized(int n) {
        int countBits = 0;
        while(n != 0) {
            if((n & 1) == 1)
                countBits++;
            n = n >> 1;
        }
        return countBits;
    }
}
