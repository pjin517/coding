package com.jin.leet;

/**
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
 *
 * Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
 *
 * Example 1:
 *
 * Input: [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 *              Total amount you can rob = 1 + 3 = 4.
 * Example 2:
 *
 * Input: [2,7,9,3,1]
 * Output: 12
 * Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 *              Total amount you can rob = 2 + 9 + 1 = 12.
 *
 */
public class HouseRobber {

    /**
     * DP(n) = max(DP(n-1), DP(n-2) + A(n))
     *
     * @param nums
     * @return
     */
    public static int rob_(int[] nums) {
        int size = nums.length;
        if (size == 0)
            return 0;
        if (size == 1)
            return nums[0];
        int[] dp = new int[size];
        dp[0] = nums[0];
        dp[1] = max(nums[0], nums[1]);
        for (int i=2; i<size; i++) {
            dp[i] = max(dp[i-1], dp[i-2]+nums[i]);
        }

        return dp[size-1];
    }

    public static int rob(int[] nums) {
        int previousMax=0;
        int currentMax = 0;
        for (int m: nums) {
            int tmp = currentMax;
            currentMax = max(currentMax, previousMax + m);
            previousMax = tmp;
        }
        return currentMax;
    }

    private static int max(int a, int b) {
        return a>b?a:b;
    }

    public static void main(String[] args) {
        System.out.println("[2,7,9,3,1,10] => " + rob(new int[] {2,7,9,3,1, 10}));
    }
}
