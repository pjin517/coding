package com.jin.leet.unionfind;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode547
 *
 * There are N students in a class. Some of them are friends, while some are not. Their friendship is transitive in nature. For example, if A is a direct friend of B, and B is a direct friend of C, then A is an indirect friend of C. And we defined a friend circle is a group of students who are direct or indirect friends.
 *
 * Given a N*N matrix M representing the friend relationship between students in the class. If M[i][j] = 1, then the ith and jth students are direct friends with each other, otherwise not. And you have to output the total number of friend circles among all the students.
 *
 * Example 1:
 * Input:
 * [[1,1,0],
 *  [1,1,0],
 *  [0,0,1]]
 * Output: 2
 * Explanation:The 0th and 1st students are direct friends, so they are in a friend circle.
 * The 2nd student himself is in a friend circle. So return 2.
 * Example 2:
 * Input:
 * [[1,1,0],
 *  [1,1,1],
 *  [0,1,1]]
 * Output: 1
 * Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends,
 * so the 0th and 2nd students are indirect friends. All of them are in the same friend circle, so return 1.
 * Note:
 * N is in range [1,200].
 * M[i][i] = 1 for all students.
 * If M[i][j] = 1, then M[j][i] = 1.
 *
 */

class FriendUnionFind {
    int size;
    int[][] matrix;
    int[] circleID;
    int[] ranks;

    public FriendUnionFind(int[][] matrix) {
        this.matrix = matrix;
        this.size = matrix.length;
        this.circleID = new int[size];
        for (int i=0; i<size; i++) {
            circleID[i] = i;
        }
        this.ranks = new int[size];
        Arrays.fill(ranks, 1);
    }

    public int find(int x) {
        while (circleID[x] != x) {
            circleID[x] = circleID[circleID[x]];
            x = circleID[x];
        }
        return x;
    }

    public int union(int x, int y) {
        if (x > y)
            return union(y, x);

        int idX = find(x);
        int idY = find(y);

        if (idX == idY)
            return idX;

        if (ranks[idX] >= ranks[idY]) {
            circleID[idY] = idX;
            ranks[idX] += ranks[idY];
            return idX;
        } else {
            circleID[idX] = idY;
            ranks[idY] += ranks[idX];
            return idY;
        }
    }
}

public class FriendCircle {
    public int findCircleNum(int[][] M) {
        int size = M.length;
        if (size <= 1)
            return size;

        FriendUnionFind friendUnionFind = new FriendUnionFind(M);
        for (int i=0; i<size; i++) {
            for(int j=0; j<size; j++) {
                if (i!=j && M[i][j]==1) {
                    friendUnionFind.union(i, j);
                }
            }
        }

        Set<Integer> ids = new HashSet<>();
        for (int i=0; i<size; i++) {
            ids.add(friendUnionFind.find(i));
        }
        return ids.size();
    }

    public static void main(String args[]) {
        int[][] M = new int[][] {
                {1,1,0},
                {1,1,0},
                {0,0,1}
        };
        int[][] M2 = new int[][] {
                {1,1,0},
                {1,1,0},
                {0,1,1}
        };
        // [[1,0,0,1],[0,1,1,0],[0,1,1,1],[1,0,1,1]]

        int[][] M3 = new int[][] {
                {1,0,0,1},
                {0,1,1,0},
                {0,1,1,1},
                {1,0,1,1}
        };
        FriendCircle fc = new FriendCircle();
        System.out.println("Friend circle num: " + fc.findCircleNum(M));
        System.out.println("Friend circle num: " + fc.findCircleNum(M2));
        System.out.println("Friend circle num: " + fc.findCircleNum(M3));
    }
}
