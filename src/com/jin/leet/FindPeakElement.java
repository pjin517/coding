package com.jin.leet;

/**
 * A peak element is an element that is greater than its neighbors.
 *
 * Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.
 *
 * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
 *
 * You may imagine that nums[-1] = nums[n] = -∞.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,1]
 * Output: 2
 * Explanation: 3 is a peak element and your function should return the index number 2.
 * Example 2:
 *
 * Input: nums = [1,2,1,3,5,6,4]
 * Output: 1 or 5
 * Explanation: Your function can return either index number 1 where the peak element is 2,
 *              or index number 5 where the peak element is 6.
 * Note:
 *
 * Your solution should be in logarithmic complexity.
 *
 * @see com.jin.cmp.gg.MinimumPointInCurve
 */
public class FindPeakElement {
    public static int findPeakElement(int[] nums) {
        return findPeakUtil(nums, 0, nums.length-1);
    }

    private static int findPeakUtil(int[] num, int start, int end) {
        if (start == end) {
            return start;
        }

        int mid = (start+end)/2;
        if (num[mid]>num[mid+1]) {
            return findPeakUtil(num, start, mid);
        } else {
            return findPeakUtil(num, mid + 1, end);
        }
    }

    public static void main(String args[]) {
        int[] nums = new int[]{1,2,3,1};
        System.out.println("Peak of 1,2,3,1 : " + findPeakElement(nums));
        nums = new int[]{1,2,1,3,5,6,4};
        System.out.println("Peak of 1,2,1,3,5,6,4 : " + findPeakElement(nums));
    }
}
