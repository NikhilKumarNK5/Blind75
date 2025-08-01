/*
Given two integers a and b, return the sum of the two integers without using the operators + and -.

Example 1:
Input: a = 1, b = 2
Output: 3

Example 2:
Input: a = 2, b = 3
Output: 5

Constraints:
-1000 <= a, b <= 1000
*/

public class SumOfTwoIntegers {
    // Approach: Basic approach to find the sum of two integers is using '+' operator but we are asked to not use '+' or '-' operator
    /*
        Input: a = 5, b = 3
        Binary: a = 0101, b = 0011

        Iteration 1
        int carry = (a & b) << 1; carry = (0101 & 0011) << 1 = (0001) << 1 = 0010 (2)
        a = a ^ b;                a = 0101 ^ 0011 = 0110 (6)
        b = carry;                b = 0010 (2)

        Iteration 2
        carry = (a & b) << 1;     carry = (0110 & 0010) << 1 = (0010) << 1 = 0100 (4)
        a = a ^ b;                a = 0110 ^ 0010 = 0100 (4)
        b = carry;                b = 0100 (4)

        Iteration 3
        carry = (a & b) << 1;     carry = (0100 & 0100) << 1 = (0100) << 1 = 1000 (8)
        a = a ^ b;                a = 0100 ^ 0100 = 0000 (0)
        b = carry;                b = 1000 (8)

        Iteration 4
        carry = (a & b) << 1;     carry = (0000 & 1000) << 1 = (0000) << 1 = 0000 (0)
        a = a ^ b;                a = 0000 ^ 1000 = 1000 (8)
        b = carry;                b = 0000 (0)

        b is now 0, loop ends

        Final result: a = 8
    */
    // TC => O(1)
    public int getSum(int a, int b) {
        while (b != 0) {
            // Carry contains common set bits of a and b, shifted left (since it's the carry in addition)
            int carry = (a & b) << 1;

            // XOR gives sum without carry
            a = a ^ b;

            // Update b to the carry; will be added in the next iteration
            b = carry;
        }

        // When carry becomes 0, a contains the final result
        return a;
    }
}
