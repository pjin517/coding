package com.jin.cmp.gg;

import java.util.Arrays;

/**
 * 一堆自行车和一些人在一个2D的matrix里，要每个人match到一辆自行车，人和自行车的距离越短越好
 *
 * https://www.topcoder.com/community/data-science/data-science-tutorials/assignment-problem-and-hungarian-algorithm/
 *
 * https://www.geeksforgeeks.org/hungarian-algorithm-assignment-problem-set-1-introduction/
 *

 * Or use a greedy algorithm, each time pick the minimum match available.
 *
 */
public class AssignmentProblem {
    public static int[] findMatch(int[][] distances) {
        int m = distances.length;   // number of man
        int n = distances[0].length;  // number of bikes
        int[] result = new int[m];  // hold man->bike matches
        boolean[] manMatched = new boolean[m];
        boolean[] bikeMatched = new boolean[n];

        for (int i=0; i<m; i++)
            findNextMatch(distances, manMatched, bikeMatched, result);

        return result;
    }

    private static void findNextMatch(int[][] distances, boolean[] manMatched, boolean[] bikeMatched, int[] matches) {
        int m = distances.length;   // number of man
        int n = distances[0].length;  // number of bikes
        int min = Integer.MAX_VALUE;
        int minX = -1;
        int minY = -1;
        for (int i=0; i<m; i++) {
            if (manMatched[i])
                continue;
            for (int j=0; j<n; j++) {
                if (bikeMatched[j])
                    continue;
                if (distances[i][j] < min) {
                    minX = i;
                    minY = j;
                    min = distances[i][j];
                }
            }
        }
        manMatched[minX] = true;
        bikeMatched[minY] = true;
        matches[minX] = minY;
    }

    public static void main(String args[]) {
        int[] matches = findMatch(new int[][]{
                { 9, 2, 7, 8 },
                { 6, 4, 3, 7 },
                { 5, 8, 1, 8 },
                { 7, 6, 9, 4 }
        });

        System.out.println(Arrays.toString(matches));
    }
}
