package com.jin.leet;

/**
 * LeetCode 31. Next Permutation
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 *
 * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
 *
 * The replacement must be in-place and use only constant extra memory.
 *
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 *
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 */
public class NextPermutation {
    public void nextPermutation(int[] nums) {
        if (nums.length<=1)
            return;
        int k;
        for (k=nums.length-2; k>=0; k--) {
            if (nums[k]<nums[k+1])
                break;
        }
        if (k == -1) {          // whole array is sorted descending
            reverse(nums, 0, nums.length - 1);
            return;
        }
        int j;
        for (j=nums.length-1; j>k; j--) {
            if (nums[j]>nums[k])
                break;
        }
        swap(nums, j, k);
        reverse(nums, k+1, nums.length-1);

    }

    public void swap(int[] nums, int i, int j) {
        if (i > j) {
            swap(nums, j, i);
            return;
        }

        if (i<0 || j>nums.length-1)
            return;

        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public void reverse(int[] nums, int start, int end) {
        if (start>= end)
            return;
        if (start<0 || end > nums.length -1 )
            return;

        while (start<end) {
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
            start++;
            end--;
        }

    }


    public static void main(String[] args) {
        NextPermutation np = new NextPermutation();
        int[] nums = new int[] {3, 2, 1};
        np.nextPermutation(nums);
        for (int v: nums)
            System.out.print(v+" ");
    }
}
