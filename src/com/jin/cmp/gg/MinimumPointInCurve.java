package com.jin.cmp.gg;

/**
 *  有一个曲线，曲线的形状是先递减再增加，找曲线的最低点，如果只考虑int怎么做，如果考虑double怎么做
 *
 *  Answer: int就是二分，double的搜索空间变成无限了，所以函数必须要给定一个delta，结果返回的是一个区间，区间长度是2*delta，
 *  里面需要包含最低点就可以了，其实也是二分法就搞定了
 *
 *  https://leetcode.com/problems/find-peak-element/solution/
 */
public class MinimumPointInCurve {
    public static int findLowestPoint(int[] curve) {
        return binarySearch(curve, 0, curve.length);
    }

    public static int binarySearch(int[] nums, int start, int end) {
        if (start >= end) {
            return nums[start];
        }

        int mid = (start + end)/2;

        if (nums[mid]<= nums[mid-1] && nums[mid]<=nums[mid+1])
            return nums[mid];
        else if(nums[mid]<= nums[mid-1] && nums[mid]>=nums[mid+1]) //the mid point is on left curve
            return binarySearch(nums, mid, end);
        else
            return binarySearch(nums, start, mid);
    }

    public static void main(String args[]) {
        System.out.println("Lowest of {10, 8, 6, 4, 2, 3, 5, 7, 9}: " + findLowestPoint(new int[] {10, 8, 6, 4, 2, 3, 5, 7, 9}));
        System.out.println("Lowest of {12, 10, 8, 6, 4, 2, 3, 5, 7, 9}: " + findLowestPoint(new int[] {12, 10, 8, 6, 4, 2, 3, 5, 7, 9}));
        System.out.println("Lowest of {10, 8, 6, 4, 2, 3, 5, 7, 9, 11}: " + findLowestPoint(new int[] {10, 8, 6, 4, 2, 3, 5, 7, 9, 11}));
        System.out.println("Lowest of {12, 10, 8, 6, 4, 2, 3}: " + findLowestPoint(new int[] {12, 10, 8, 6, 4, 2, 3}));
    }
}
