package com.jin.leet;

import java.util.HashMap;

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
    public int subarraySumBrutal(int[] nums, int k) {
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

    public int subarraySumN2(int[] nums, int k) {
        int n = 0;
        int[] sumArray = new int[nums.length+1];
        sumArray[0] = 0;
        for (int i=1; i< sumArray.length; i++) {
            sumArray[i] = sumArray[i-1] + nums[i-1];
        }
        for (int i=0; i<sumArray.length-1; i++) {
            for (int j=i+1; j<sumArray.length; j++) {
                if (sumArray[j]-sumArray[i] == k)
                    n++;
            }
        }
        return n;
    }

    public int subarraySum(int[] nums, int k) {
        int n=0;
        int sum=0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i=0; i< nums.length; i++) {
            sum+=nums[i];
            if (map.containsKey(sum-k)) {
                n += map.get(sum-k);
            }
            map.put(sum, map.getOrDefault(sum, 0)+1);
        }
        return n;
    }

}
