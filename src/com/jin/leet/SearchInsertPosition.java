package com.jin.leet;

/**
 * 35. Search Insert Position

 * Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
 *
 * You may assume no duplicates in the array.
 *
 * Example 1:
 *
 * Input: [1,3,5,6], 5
 * Output: 2
 * Example 2:
 *
 * Input: [1,3,5,6], 2
 * Output: 1
 * Example 3:
 *
 * Input: [1,3,5,6], 7
 * Output: 4
 * Example 4:
 *
 * Input: [1,3,5,6], 0
 * Output: 0
 */
public class SearchInsertPosition {
    public int searchInsert(int[] nums, int target) {
        if (nums.length == 0 || target < nums[0])
            return 0;
        if (target > nums[nums.length-1])
            return nums.length;

        return binarySearch(nums, target, 0, nums.length-1);

    }

    private int binarySearch(int[] nums, int target, int start, int end) {
        if (start > end) // Important: tricky to get the exit criteria right
            return start;
        int mid = (start + end)/2;
        if (target == nums[mid])
            return mid;
        if (target < nums[mid])
            return binarySearch(nums, target, start, mid - 1);
        else
            return binarySearch(nums, target, mid+1, end);

    }
}
