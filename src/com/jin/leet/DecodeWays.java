package com.jin.leet;


/**
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 *
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Given a non-empty string containing only digits, determine the total number of ways to decode it.
 *
 * Example 1:
 *
 * Input: "12"
 * Output: 2
 * Explanation: It could be decoded as "AB" (1 2) or "L" (12).
 * Example 2:
 *
 * Input: "226"
 * Output: 3
 * Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 */
public class DecodeWays {
    //Count[i] = Count[i-1]              if S[i-1] is a valid char (not '0')
    //         = Count[i-1]+ Count[i-2]  if S[i-1] and S[i-2] together is still a valid char (10 to 26).

    static int check(char ch){
        //check 0 or not digit
        return (ch<='0' || ch>'9') ? 0 : 1;
    }

    static int check(char ch1, char ch2){
        //check it's between 10 and 26
        return (ch1=='1' || (ch1=='2' && ch2<='6')) ? 1: 0;
    }

    public static int numDecodings(String s) {
        int size = s.length();
        if (size<=0) return 0;
        if (size==1) return check(s.charAt(0));

        int[] dp = new int[size];

        dp[0] = check(s.charAt(0));
        dp[1] = check(s.charAt(0)) * check(s.charAt(1)) + check(s.charAt(0), s.charAt(1));

        for (int i=2; i<size; i++) {
            if (check(s.charAt(i))>0)
                dp[i] = dp[i-1];
            if (check(s.charAt(i-1), s.charAt(i))>0)
                dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[size-1];
    }

    public static void main(String args[]) {
        System.out.println("12: " + DecodeWays.numDecodings("12"));
        System.out.println("226: " + DecodeWays.numDecodings("226"));
    }
}
