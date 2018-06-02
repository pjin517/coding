package com.jin.cmp.gg;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Given a non-empty array of digits representing a non-negative integer, plus one to the integer.
 *
 * The digits are stored such that the most significant digit is at the head of the list, and each element in the array contain a single digit.
 *
 * You may assume the integer does not contain any leading zero, except the number 0 itself.
 *
 * Example 1:
 *
 * Input: [1,2,3]
 * Output: [1,2,4]
 * Explanation: The array represents the integer 123.
 * Example 2:
 *
 * Input: [4,3,2,1]
 * Output: [4,3,2,2]
 * Explanation: The array represents the integer 4321.
 *
 */

public class PlusOne {
    public static int[] plusOneOrigin(int[] digits) {
        int s=1;
        int length = digits.length;
        LinkedList<Integer> result = new LinkedList<>();
        for (int i=length-1; i>=0; i--) {
            int sum = digits[i] + s;
            if (sum == 10) {
                s = 1;
                result.addFirst(0);
            } else {
                s = 0;
                result.addFirst(sum);
            }

        }
        if (s == 1)
            result.addFirst(1);

        int[] ret = new int[result.size()];
        for(int i = 0;i < ret.length;i++)
            ret[i] = result.get(i);

        return ret;
    }

    public static int[] plusOne(int[] digits) {
        int length = digits.length;
        for (int i=length-1; i>=0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }

        int[] ret = new int[length+1];
        ret[0] = 1;

        return ret;
    }

    public static void main(String args[]) {
        int[] input = {1,2,3};
        System.out.println("[1,2,3] + 1: " + Arrays.toString(plusOne(input)));
        int[] input2 = {4,3,2, 1};
        System.out.println("[4,3,2, 1] + 1: " + Arrays.toString(plusOne(input2)));
    }
}
