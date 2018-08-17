package com.jin.leet;

import java.util.Arrays;

/**
 * 300. Longest Increasing Subsequence
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 *
 * Example:
 *
 * Input: [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 * Note:
 *
 * There may be more than one LIS combination, it is only necessary for you to return the length.
 * Your algorithm should run in O(n2) complexity.
 * Follow up: Could you improve it to O(n log n) time complexity?
 */
public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        int[] tails = new int[nums.length];
        int size = 0;
        for (int n: nums) {
            int i=0, j=size;
            while(i!=j) {
                int mid = (i+j)/2;
                if (n>tails[mid])
                    i=mid+1;
                else
                    j = mid;
            }
            tails[i] = n;
            if (i==size)
                size++;
        }
        return size;
    }

    public static void main(String[] args) {
        int[] a = new int[] {1, 3, 5, 7, 9};
        System.out.println("search 1 in {1, 3, 5, 7, 9}: " + Arrays.binarySearch(a, 1));
        System.out.println("search 5 in {1, 3, 5, 7, 9}: " + Arrays.binarySearch(a, 5));
        System.out.println("search 9 in {1, 3, 5, 7, 9}: " + Arrays.binarySearch(a, 9));
        System.out.println("search 8 in {1, 3, 5, 7, 9}: " + Arrays.binarySearch(a, 8));
        System.out.println("search 0 in {1, 3, 5, 7, 9}: " + Arrays.binarySearch(a, 0));
        System.out.println("search 10 in {1, 3, 5, 7, 9}: " + Arrays.binarySearch(a, 10));
    }
}
