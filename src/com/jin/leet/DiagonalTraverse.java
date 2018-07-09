package com.jin.leet;

import java.util.Arrays;

/**
 * LeetCode498
 * Given a matrix of M x N elements (M rows, N columns), return all elements of the matrix in diagonal order as shown in the below image.
 *
 * Example:
 * Input:
 * [
 *  [ 1, 2, 3 ],
 *  [ 4, 5, 6 ],
 *  [ 7, 8, 9 ]
 * ]
 * Output:  [1,2,4,7,5,3,6,8,9]
 * Explanation:
 *
 * Note:
 * The total number of elements of the given matrix will not exceed 10,000.
 *
 * https://www.geeksforgeeks.org/print-matrix-diagonal-pattern/
 *
 *
 */

public class DiagonalTraverse {
    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix.length == 0) {
            return new int[]{};
        }
        int rows = matrix.length;
        int cols = matrix[0].length;

        int[] result = new int[rows*cols];
        int x = 0;
        int y = 0;
        boolean goUp = true;

        for (int i=0; i<rows*cols;) {
            if (goUp) {
                while(x >=0 && y <cols) {
                    result[i++] = matrix[x][y];
                    x--;
                    y++;
                }
                // Set i and j according to direction
                if (x < 0 && y<=cols-1)
                    x = 0;
                if (y == cols)
                {
                    x = x+2 ; y--;
                }
            } else {
                while(y >=0 && x <rows) {
                    result[i++] = matrix[x][y];
                    y--;
                    x++;
                }
                // Set i and j according to direction
                if (y < 0 && x<=rows-1)
                    y = 0;
                if (x == rows)
                {
                    y = y+2 ; x--;
                }
            }
            goUp = !goUp;
        }
        return result;
    }


    public static void main(String[] args) {
        int[][] m = new int[][] {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };
        DiagonalTraverse dt = new DiagonalTraverse();
        System.out.println(Arrays.toString(dt.findDiagonalOrder(m)));
    }


}

