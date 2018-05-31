package com.jin.cmp.gg;

/**
 * A message containing letters from A-Z is being encoded to numbers using the following mapping way:
 *
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Beyond that, now the encoded string can also contain the character '*', which can be treated as one of the numbers from 1 to 9.
 *
 * Given the encoded message containing digits and the character '*', return the total number of ways to decode it.
 *
 * Also, since the answer may be very large, you should return the output mod 10^9 + 7.
 *
 * Example 1:
 * Input: "*"
 * Output: 9
 * Explanation: The encoded message can be decoded to the string: "A", "B", "C", "D", "E", "F", "G", "H", "I".
 * Example 2:
 * Input: "1*"
 * Output: 9 + 9 = 18
 * Note:
 * The length of the input string will fit in range [1, 105].
 * The input string will only contain the character '*' and digits '0' - '9'.
 *
 */
public class DecodeWaysII {
    static final int M = 1000000007;
    private static int check(char ch) {
        if (ch > '0' && ch <= '9')
            return 1;
        if (ch == '*')
            return 9;
        return 0;
    }

    private static int check(char c1, char c2) {
        if (c1 == '1') {
            if (c2 == '*')
                return 9;
            else if (c2>='0' && c2 <= '9') {
                return 1;
            }
        }
        if (c1 == '2') {
            if (c2 == '*')
                return 6;
            else if (c2>='0' && c2 <= '6')
                return 1;
        }
        if (c1 == '*') {
            if (c2 == '*')
                return 15;
            if (c2>='0' && c2 <= '6')
                return 2;
            if (c2>='7' && c2 <= '9')
                return 1;
        }
        return 0;
    }

    public static int numDecodings(String s) {
        int size = s.length();
        char[] chars = s.toCharArray();
        if (size<1)
            return 0;
        if (size == 1)
            return check(chars[0]);


        long[] dp = new long[size];

        dp[0] = check(chars[0]);
        dp[1] = dp[0]*check(chars[1]) + check(chars[0], chars[1]);

        // dp[i] = dp[i-1] * check(i)
        //       = dp[i-1] * check(i) + dp[i-2]*check(i-1, i)
        for (int i=2; i<size; i++) {
            if (check(chars[i])>0)
                dp[i] = (dp[i-1] * check(chars[i])) % M;
            if (check(chars[i-1], chars[i])>0)
                dp[i] = (dp[i-1] * check(chars[i]) + dp[i-2]*check(chars[i-1], chars[i])) % M;
        }
        return (int)dp[size-1];
    }

    public static void main(String[] args) {
        System.out.println("*: " + numDecodings("*"));
        System.out.println("1*: " + numDecodings("1*"));
        System.out.println("1*72*: " + numDecodings("1*72*"));
        System.out.println("*********: " + numDecodings("*********"));
    }
}
