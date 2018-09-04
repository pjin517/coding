package com.jin.cmp.fb;

/**
 * 238. Product of Array Except Self
 * Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
 *
 * Example:
 *
 * Input:  [1,2,3,4]
 * Output: [24,12,8,6]
 * Note: Please solve it without division and in O(n).
 *
 * Follow up:
 * Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)
 *
 */
public class ProductOfArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int[] output = new int[nums.length];
        output[0] = 1;
        for (int i=1; i<nums.length; i++) {
            output[i] = output[i-1] * nums[i-1];
        }
        int rightProd = 1;
        for (int i=nums.length-1; i>=0; i--) {
            output[i] = output[i] * rightProd;
            rightProd *= nums[i];
        }
        return output;
    }

    public static void main(String[] args) {
        ProductOfArrayExceptSelf p = new ProductOfArrayExceptSelf();
        int[] res = p.productExceptSelf(new int[] {1, 2, 3, 4});
        for (int i: res)
            System.out.print(i + " ");
    }
}
