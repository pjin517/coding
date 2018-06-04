package com.jin.leet;

import java.util.Arrays;

/**
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
 *
 * Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
 *
 * Example 1:
 *
 * Input: [2,3,2]
 * Output: 3
 * Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2),
 *              because they are adjacent houses.
 * Example 2:
 *
 * Input: [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 *              Total amount you can rob = 1 + 3 = 4.
 *
 */
public class HouseRobberII {
    public static int rob(int[] nums) {
        int size = nums.length;
        if (size == 0)
            return 0;
        if (size == 1)
            return nums[0];
        int[] a1 = Arrays.copyOfRange(nums, 0, size-1);
        int[] a2 = Arrays.copyOfRange(nums, 1, size);
        return Math.max(util(a1), util(a2));
    }

    static int util(int[] nums) {
        int previousMax = 0;
        int currentMax = 0;

        for (int n: nums) {
            int tmp = currentMax;
            currentMax = Math.max(currentMax, previousMax+n);
            previousMax = tmp;
        }

        return currentMax;
    }

    public static void main(String[] args) {
        System.out.println(" [1,2,3,1] => " + rob(new int[] {1,2,3,1}));
    }

}
