/*
Reverse bits of a given 32 bits signed integer.

Example 1:
Input: n = 43261596
Output: 964176192
Explanation:
Integer	Binary
43261596	00000010100101000001111010011100
964176192	00111001011110000010100101000000

Example 2:
Input: n = 2147483644
Output: 1073741822
Explanation:
Integer	Binary
2147483644	01111111111111111111111111111100
1073741822	00111111111111111111111111111110

Constraints:
0 <= n <= 2^31 - 2
n is even.
Follow up: If this function is called many times, how would you optimize it?
*/

public class ReverseBits {

    // Approach 1: Converting to Binary string and then reversing
    // TC => O(1) - binary string is always 32 bit integer
    // SC => O(1)
    public int reverseBits(int n) {
        // convert to binary String
        String binary = String.format("%32s", Integer.toBinaryString(n)).replace(' ', '0');

        // reverse the binary String
        String reversed = new StringBuilder(binary).reverse().toString();

        // convert back to Integer and return (2 here signifies the base of the reverse string (binary))
        return Integer.parseInt(reversed, 2); // Integer.parseUnsignedInt(reversed, 2);
    }

    // Approach 2: Bit Manipulation - Left shift, OR, AND, Right shift
    // TC => O(1)
    // SC => O(1)
    public int reverseBitsBitManipulation(int n) {
        int res = 0;
        for(int i = 0; i < 32; i++){
            res = (res << 1) | (n & 1);
            n = n >> 1;
        }
        return res;
    }

    // Approach 3:
}
