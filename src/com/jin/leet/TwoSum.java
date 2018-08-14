package com.jin.leet;

import java.util.HashMap;

/**
 * Given an array of integers, find two numbers such that they add up to a specific target number.
 * The function twoSum should return indices of the two numbers such that they add up to the target,
 * where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.
 * For example:
 * Input: numbers={2, 7, 11, 15}, target=9
 * Output: index1=1, index2=2
 */
public class TwoSum {
    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] result = new int[2];
        for (int i=0; i<nums.length; i++) {
            if (map.containsKey(target-nums[i])) {
                result[1] = i;
                result[0] = map.get(target - nums[i]);
                return result;
            } else {
                map.put(nums[i], i);
            }
        }
        return result;
    }

    public static void main(String args[]) {
        int[] numbers = new int[] {2, 7, 11, 15};
        int[] result = twoSum(numbers, 9);
        for (int v: result) {
            System.out.println(v);
        }
    }
}
