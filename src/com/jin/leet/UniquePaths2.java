package com.jin.leet;

/**
 * LeetCode63
 *
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 *
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 *
 * Now consider if some obstacles are added to the grids. How many unique paths would there be?
 *
 *
 *
 * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
 *
 * Note: m and n will be at most 100.
 *
 * Example 1:
 *
 * Input:
 * [
 *   [0,0,0],
 *   [0,1,0],
 *   [0,0,0]
 * ]
 * Output: 2
 * Explanation:
 * There is one obstacle in the middle of the 3x3 grid above.
 * There are two ways to reach the bottom-right corner:
 * 1. Right -> Right -> Down -> Down
 * 2. Down -> Down -> Right -> Right
 */
public class UniquePaths2 {
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] cache = new int[m][n];
        cache[0][0] = 1;
        return uniquePathWithObstaclesUtil(m-1, n-1, cache, obstacleGrid);
    }

    private static int uniquePathWithObstaclesUtil(int x, int y, int[][] cache, int[][] obstacleGrid) {
        if (x<0 || y<0 || obstacleGrid[x][y] == 1)
            return 0;
        if (cache[x][y] != 0)
            return cache[x][y];
        int result = uniquePathWithObstaclesUtil(x-1, y, cache, obstacleGrid) + uniquePathWithObstaclesUtil(x, y-1, cache, obstacleGrid);
        cache[x][y] = result;
        return result;
    }
    public static void main(String args[]) {
        int[][] obstacle = new int[][] {
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        };
        System.out.println("m=3, n=2: " + UniquePaths2.uniquePathsWithObstacles(obstacle));
    }
}
