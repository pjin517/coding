package com.jin.cmp.fb;

/**
 * 283. Move Zeroes
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 *
 * Example:
 *
 * Input: [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 * Note:
 *
 * You must do this in-place without making a copy of the array.
 * Minimize the total number of operations.
 *
 */
public class MoveZeros {
    public void moveZeroes(int[] nums) {
        int lastPos = 0;
        for (int cur = 0; cur < nums.length; cur++) {
            if (nums[cur] != 0) {
                int tmp = nums[lastPos];
                nums[lastPos] = nums[cur];
                nums[cur] = tmp;
                lastPos ++;
            }
        }
    }
    public static void main(String args[]) {

    }
}
