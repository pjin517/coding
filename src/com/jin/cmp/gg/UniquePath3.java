package com.jin.cmp.gg;

/**
 * 有个matrix， 从左上角走到右上角多少种方法，只能右上，右下，右三个方向走
 * http://www.1point3acres.com/bbs/thread-437128-1-1.html
 */
public class UniquePath3 {
    public static int uniquePath(int m, int n) {
        int[][] cache = new int[m][n];
        cache[0][0] = 1;
        return uniquePathUtil(m,  0, n-1, cache);
    }

    private static int uniquePathUtil(int m, int x, int y, int[][] cache) {
        if (x<0 || y<0 || x>=m)
            return 0;
        if (cache[x][y] > 0)
            return cache[x][y];

        int result = uniquePathUtil(m,x-1, y-1, cache) + uniquePathUtil(m, x, y-1, cache) + uniquePathUtil(m, x+1, y-1, cache);
        cache[x][y] = result;
        return result;
    }

    public static void main(String[] args) {
        System.out.println("m=3, n=2: " + uniquePath(2, 3));
    }
}
