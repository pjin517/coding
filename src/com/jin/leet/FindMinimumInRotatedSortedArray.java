package com.jin.leet;

/**
 * LeetCode 153
 *
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 *
 * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
 *
 * Find the minimum element.
 *
 * You may assume no duplicate exists in the array.
 *
 * Example 1:
 *
 * Input: [3,4,5,1,2]
 * Output: 1
 * Example 2:
 *
 * Input: [4,5,6,7,0,1,2]
 * Output: 0
 */

/**
 * Classic binary search problem.
 *
 * Looking at subarray with index [start,end]. We can find out that if the first member is less than the last member, there's no rotation in the array.
 * So we could directly return the first element in this subarray.
 *
 * If the first element is larger than the last one, then we compute the element in the middle, and compare it with the first element.
 * If value of the element in the middle is larger than the first element, we know the rotation is at the second half of this array.
 * Else, it is in the first half in the array.
 *
 * Welcome to put your comments and suggestions.
 */
public class FindMinimumInRotatedSortedArray {
    public static int findMin(int[] nums) {
        return binarySearch(nums, 0, nums.length-1);
    }

    private static int binarySearch(int[] nums, int start, int end) {
        if (nums[start] <= nums[end])
            return nums[start];

        int mid = (start+end)/2;

        if (nums[mid] < nums[start]) // if the mid < start, the rotation is at the first half
            return binarySearch(nums, start, mid);
        else
            return binarySearch(nums, mid+1, end);
    }

    public static void main(String[] args) {
        System.out.println("Minimum of [3,4,5,1,2]: " + findMin(new int[]{3,4,5,1,2}));
    }
}
