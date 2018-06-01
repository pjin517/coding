package com.jin.cmp.gg;

/**
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 *
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 *
 * Example 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * The median is 2.0
 * Example 2:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * The median is (2 + 3)/2 = 2.5
 */
public class MedianOfTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        return 0d;
    }

    /**
     *
     * @param a1 first array
     * @param l1 length of
     * @param a2
     * @param l2
     * @param k
     * @return
     */
    public int findKth(int[] a1, int start1, int l1, int[] a2, int start2, int l2, int k){
        // let's make sure a1 is the shorter array
        if (l1 > l2) {
            return findKth(a2, start2, l2, a1, start1, l1, k);
        }
        return 0;
    }
}
