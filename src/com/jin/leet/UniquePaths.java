package com.jin.leet;

/**
 * LeetCode62
 *
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 *
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 *
 * How many possible unique paths are there?
 *
 *
 * Above is a 7 x 3 grid. How many possible unique paths are there?
 *
 * Note: m and n will be at most 100.
 *
 * Example 1:
 *
 * Input: m = 3, n = 2
 * Output: 3
 * Explanation:
 * From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
 * 1. Right -> Right -> Down
 * 2. Right -> Down -> Right
 * 3. Down -> Right -> Right
 * Example 2:
 *
 * Input: m = 7, n = 3
 * Output: 28
 *
 */
public class UniquePaths {
    public static int uniquePaths(int m, int n) {
        int[][] cache = new int[m][n];
        cache[0][0] = 1;
        return uniquePathUtil(m-1, n-1, cache);
    }

    private static int uniquePathUtil(int x, int y, int[][] cache) {
        if (x<0 || y<0)
            return 0;
        if (cache[x][y] != 0)
            return cache[x][y];
        int result = uniquePathUtil(x-1, y, cache) + uniquePathUtil(x, y-1, cache);
        cache[x][y] = result;
        return result;
    }
    public static void main(String args[]) {
        System.out.println("m=3, n=2: " + uniquePaths(3, 2));
        System.out.println("m=7, n=3: " + uniquePaths(7, 3));
    }
}
