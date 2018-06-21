package com.jin.leet.unionfind;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * LeetCode305
 *
 * A 2d grid map of m rows and n columns is initially filled with water.
 * We may perform an addLand operation which turns the water at position (row, col) into a land.
 * Given a list of positions to operate, count the number of islands after each addLand operation.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 *
 * Example:
 *
 * Given m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]].
 * Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).
 *
 * 0 0 0
 * 0 0 0
 * 0 0 0
 * Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.
 *
 * 1 0 0
 * 0 0 0   Number of islands = 1
 * 0 0 0
 * Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.
 *
 * 1 1 0
 * 0 0 0   Number of islands = 1
 * 0 0 0
 * Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.
 *
 * 1 1 0
 * 0 0 1   Number of islands = 2
 * 0 0 0
 * Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.
 *
 * 1 1 0
 * 0 0 1   Number of islands = 3
 * 0 1 0
 * We return the result as an array: [1, 1, 2, 3]
 *
 * Challenge:
 *
 * Can you do it in time complexity O(k log mn), where k is the length of the positions?
 */
public class NumberOfIslandsII {
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> result = new ArrayList<>();

        HashSet<Location> islands = new HashSet<>();

        char[][] grid = new char[m][n];
        IslandUnionFind islandUnionFind = new IslandUnionFind(grid);

        for (int[] position: positions) {
            int x = position[0];
            int y = position[1];
            islandUnionFind.grid[x][y] = 1;

            boolean noAdjacent = true;
            //check left
            if (x>0 && grid[x-1][y] == '1') {
                Location id = islandUnionFind.union(new Location(x-1,y), new Location(x, y));
                islands.remove(new Location(x-1, y));
                islands.add(id);
                noAdjacent = false;
            }
            //check up
            if (y>0 && grid[x][y-1] == '1') {
                Location id = islandUnionFind.union(new Location(x,y-1), new Location(x, y));
                islands.remove(new Location(x, y-1));
                islands.add(id);
                noAdjacent = false;
            }
            //check right
            if (x+1<m && grid[x+1][y] == '1') {
                Location id = islandUnionFind.union(new Location(x, y), new Location(x+1,y));
                islands.remove(new Location(x+1, y));
                islands.add(id);
                noAdjacent = false;
            }
            //check down
            if (y+1<n && grid[x][y+1] == '1') {
                Location id = islandUnionFind.union(new Location(x, y), new Location(x,y+1));
                islands.remove(new Location(x, y+1));
                islands.add(id);
                noAdjacent = false;
            }
            if (noAdjacent) {
                islands.add(new Location(x, y));
            }

            result.add(islands.size());
        }

        return result;
    }
    public static void main(String args[]) {
        NumberOfIslandsII noi2 = new NumberOfIslandsII();
        List<Integer> result = noi2.numIslands2(3, 3, new int[][]{{0,0}, {0,1}, {1,2}, {2,1}});
        System.out.println(result);

    }
}
