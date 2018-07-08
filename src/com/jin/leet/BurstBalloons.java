package com.jin.leet;

import java.util.HashMap;
import java.util.Objects;

/**
 * LeetCode312
 * Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums.
 * You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins.
 * Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.
 *
 * Find the maximum coins you can collect by bursting the balloons wisely.
 *
 * Note:
 *
 * You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
 * 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 * Example:
 *
 * Input: [3,1,5,8]
 * Output: 167
 * Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 *              coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 *
 * https://leetcode.com/problems/burst-balloons/discuss/76228/Share-some-analysis-and-explanations
 */
public class BurstBalloons {
    public static int maxCoins(int[] iNums) {

        int[] nums = new int[iNums.length + 2];
        int n = 1;
        for (int x : iNums)
            if (x > 0)
                nums[n++] = x;
        nums[0] = nums[n++] = 1;

        HashMap<SubSequence, Integer> cache = new HashMap<>();

        return maxCoins(nums, new SubSequence(0, n-1), cache);
    }

    private static int maxCoins(int[] nums, SubSequence sequence, HashMap<SubSequence, Integer> cache) {
        if (cache.containsKey(sequence))
            return cache.get(sequence);
        int start = sequence.start;
        int end = sequence.end;
        int max = 0;
        if (start+1 == end) {
            return 0;
        }

        for (int i=start+1; i<end; i++) {
            max = Math.max(max, maxCoins(nums, new SubSequence(start, i), cache) + nums[start]*nums[i]*nums[end] + maxCoins(nums, new SubSequence(i, end), cache));
        }

        cache.put(sequence, max);
        return max;
    }

    public static void main(String args[]) {
        int[] nums = new int[]{3, 1, 5, 8};
        System.out.println("MaxCoins of {3, 1, 5, 8}: " + maxCoins(nums));
    }
}

class SubSequence {
    int start;
    int end;

    public SubSequence(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubSequence that = (SubSequence) o;
        return start == that.start &&
                end == that.end;
    }

    @Override
    public int hashCode() {

        return Objects.hash(start, end);
    }
}
