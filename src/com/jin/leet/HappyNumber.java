package com.jin.leet;

import java.util.HashSet;

/**
 * 202. Happy Number
 * Write an algorithm to determine if a number is "happy".
 *
 * A happy number is a number defined by the following process: Starting with any positive integer,
 * replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay),
 * or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.
 *
 * Example:
 *
 * Input: 19
 * Output: true
 * Explanation:
 * 1^2 + 9^2 = 82
 * 8^2 + 2^2 = 68
 * 6^2 + 8^2 = 100
 * 1^2 + 0^2 + 0^2 = 1
 */
public class HappyNumber {
    public boolean isHappy(int n) {
        if (n<0) return false;
        HashSet<Integer> set = new HashSet<>();
        while (!set.contains(n)) {
            set.add(n);
            n = compute(n);
            if (n==1) return true;
        }
        return false;
    }

    private int compute(int n) {
        char[] chars = String.valueOf(n).toCharArray();
        int result = 0;
        for (char c: chars) {
            result += Math.pow(c-'0', 2);
        }
        return result;
    }

    public static void main(String[] args) {
        HappyNumber hn = new HappyNumber();
        System.out.println("19: " + hn.isHappy(19));
    }
}
