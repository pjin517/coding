package com.jin.cmp.fb;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 15. 3Sum
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
 *
 * Note:
 *
 * The solution set must not contain duplicate triplets.
 *
 * Example:
 *
 * Given array nums = [-1, 0, 1, 2, -1, -4],
 *
 * A solution set is:
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 */
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new LinkedList<>();
        for (int i=0; i<nums.length-2; i++) {
            int target = -nums[i];
            for (int j = i+1, k = nums.length-1; j<k; ) {
                if (nums[j] + nums[k] == target) {
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j ++;
                    k --;
                } else if (nums[j] + nums[k] > target)
                    k--;
                else
                    j++;
            }
        }
        return res;
    }
}
