package com.jin.leet.unionfind;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * LeetCode200
 *
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 *
 * Example 1:
 *
 * Input:
 * 11110
 * 11010
 * 11000
 * 00000
 *
 * Output: 1
 * Example 2:
 *
 * Input:
 * 11000
 * 11000
 * 00100
 * 00011
 *
 * Output: 3
 */

public class NumberOfIslands {

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        IslandUnionFind unionFind = new IslandUnionFind(grid);
        for (int i = 0; i<unionFind.hight; i++) {
            for(int j=0; j<unionFind.length; j++) {
                if (grid[i][j] == '0')
                    continue;
                if (i-1>=0 && grid[i-1][j]=='1') {
                    unionFind.union(new Location(i-1, j), new Location(i, j));
                }
                if (j-1>=0 && grid[i][j-1]=='1') {
                    unionFind.union(new Location(i, j-1), new Location(i, j));
                }
            }
        }

        Set<Location> uniqIslands = new HashSet<>();
        for (int i = 0; i<unionFind.hight; i++) {
            for (int j = 0; j < unionFind.length; j++) {
                if (grid[i][j] == '1')
                    uniqIslands.add(unionFind.find(i, j));
            }
        }
        return uniqIslands.size();
    }

    public static void main(String args[]) {
        HashSet<Location> locations = new HashSet<>();
        locations.add(new Location(1,1));
        locations.add(new Location(1,1));

        System.out.println(" >>>>> " + locations.size());
        char [][] grid = new char[][] {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        NumberOfIslands numberOfIslands = new NumberOfIslands();
        System.out.println("number of islands: " + numberOfIslands.numIslands(grid));

        char [][] grid2 = new char[][] {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        System.out.println("number of islands: " + numberOfIslands.numIslands(grid2));
    }
}

class Location {
    int x;
    int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Location location = (Location) o;
        return x == location.x &&
                y == location.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

class IslandUnionFind {
    char[][] grid;
    int length;
    int hight;
    Location islands[][];
    int[][] ranks;

    public IslandUnionFind(char[][] grid) {
        this.grid = grid;
        if (grid == null) {
            this.hight = 0;
            this.length = 0;
        } else {
            this.hight = grid.length;
            this.length = grid[0].length;
        }

        islands = new Location[hight][length];
        ranks = new int[hight][length];

        for(int i=0; i<hight; i++) {
            for (int j=0; j<length; j++) {
                islands[i][j] = new Location(i, j);
                ranks[i][j] = 1;
            }
        }
    }

    public Location find(int x, int y) {
        while (! islands[x][y].equals(new Location(x, y))) {
            Location p = islands[x][y];
            islands[x][y] = islands[p.x][p.y];
            x = p.x;
            y = p.y;
        }

        return islands[x][y];
    }

    public void union(Location a, Location b) {
        Location islandA = find(a.x, a.y);
        Location islandB = find(b.x, b.y);
        if (islandA.equals(islandB))
            return;
        if (ranks[islandA.x][islandA.y] >= ranks[islandB.x][islandB.y]) {
            islands[islandB.x][islandB.y] = islandA;
            ranks[islandA.x][islandA.y] += ranks[islandB.x][islandB.y];
        } else {
            islands[islandA.x][islandA.y] = islandB;
            ranks[islandB.x][islandB.y] += ranks[islandA.x][islandA.y];
        }
    }
}
