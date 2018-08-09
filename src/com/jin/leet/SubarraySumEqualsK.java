package com.jin.leet;

/**
 * LeetCode 560. Subarray Sum Equals K
 *
 * Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.
 *
 * Example 1:
 * Input:nums = [1,1,1], k = 2
 * Output: 2
 * Note:
 * The length of the array is in range [1, 20,000].
 * The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
 *
 */
public class SubarraySumEqualsK {
    public int subarraySum(int[] nums, int k) {
        int n = 0;
        for (int i=0; i< nums.length-1; i++) {
            int sum=0;
            for (int j=i; j<nums.length-1; j++) {
                sum += nums[j];
                if (sum == k)
                    n++;
            }
        }
        return n;
    }
}
