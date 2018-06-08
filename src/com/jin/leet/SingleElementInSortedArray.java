package com.jin.leet;

/**
 *  LeetCode 540
 *
 *  Given a sorted array consisting of only integers where every element appears twice except for one element which appears once. Find this single element that appears only once.
 *
 * Example 1:
 * Input: [1,1,2,3,3,4,4,8,8]
 * Output: 2
 * Example 2:
 * Input: [3,3,7,7,10,11,11]
 * Output: 10
 *
 * Note: Your solution should run in O(log n) time and O(1) space.
 *
 * Note: also works when the array is not sorted but same numbers are next to each other.
 */
public class SingleElementInSortedArray {

    public static int singleNonDuplicate(int[] nums) {
        int start = 0;
        int end = nums.length-1;

        while (start<end) {
            int mid = (start+end)/2;
            if (mid%2 == 1)
                mid--;
            if (nums[mid] == nums[mid+1])
                start = mid+2;
            else
                end = mid-1;
        }
        return nums[start];
    }
    public static void main(String args[]) {
        System.out.println("[1,1,2,3,3,4,4,8,8] : " + singleNonDuplicate(new int[] {1,1,2,3,3,4,4,8,8}));
        System.out.println("[3,3,7,7,10,11,11] : " + singleNonDuplicate(new int[] {3,3,7,7,10,11,11}));
        System.out.println("[8,8,3,3,7,7,10,11,11] : " + singleNonDuplicate(new int[] {8,8, 3,3,7,7,10,11,11}));
    }
}
