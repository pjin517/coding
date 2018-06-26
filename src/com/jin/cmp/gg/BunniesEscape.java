package com.jin.cmp.gg;

/**
 * Prepare the Bunnies' Escape
 * ===========================
 *
 * You're awfully close to destroying the LAMBCHOP doomsday device and freeing Commander Lambda's bunny prisoners,
 * but once they're free of the prison blocks, the bunnies are going to need to escapeLambda's space station via the escape pods as quickly as possible.
 * Unfortunately, the halls of the space station are a maze of corridors and dead ends that will be a deathtrap for the escaping bunnies.
 * Fortunately, Commander Lambda has put you in charge of a remodeling project that will give you the opportunity to make things a little easier for the bunnies.
 * Unfortunately(again), you can't just remove all obstacles between the bunnies and the escape pods - at most you can remove one wall per escape pod path,
 * both to maintain structural integrity of the station and to avoid arousing Commander Lambda'ssuspicions.
 *
 * You have maps of parts of the space station, each starting at a prison exit and ending at the door to an escape pod.
 * The map is represented as a matrix of 0sand 1s, where 0s are passable space and 1s are impassable walls.
 * The door out of the prison is at the top left (0,0) and the door into an escape pod is atthe bottom right (w-1,h-1).
 *
 * Write a function answer(map) that generates the length of the shortest path from the prison door to the escape pod,
 * where you are allowed to remove onewall as part of your remodeling plans. The path length is the total number of nodes you pass through,
 * counting both the entrance and exit nodes. The starting and ending positions are always passable (0).
 * The map will always be solvable,though you may or may not need to remove a wall.
 * The height and width of the map can be from 2 to 20. Moves can only be made in cardinal directions; nodiagonal moves are allowed.
 *
 * Test cases
 * ==========
 *
 * Inputs:
 *     (int) maze = [[0, 1, 1, 0], [0, 0, 0,1], [1, 1, 0, 0], [1, 1, 1, 0]]
 * Output:
 *     (int) 7
 *
 *  0 0 0 0 0 0 0
 *  1 1 1 1 1 1 0
 *  0 0 0 0 0 0 0
 *  0 1 1 1 1 1 1
 *  0 0 0 0 0 0 0
 *
 * Inputs:
 *     (int) maze =
 *     [[0, 0, 0, 0, 0, 0],
 *      [1, 1, 1, 1, 1, 0],
 *      [0, 0, 0, 0, 0, 0],
 *      [0, 1, 1, 1, 1, 1],
 *      [0, 1, 1, 1, 1, 1],
 *      [0, 0, 0, 0, 0, 0]]
 * Output:
 *     (int) 11
 *
 *   http://www.1point3acres.com/bbs/thread-203208-1-1.html
 *
 * 1. 0和1的grid，1是墙，0是路，从左上角走到右下角，最少多少步。
 * 2. followup:.
 *    现在说能把grid中的一个1变成0，问新的最小步数是多少步。
 */
public class BunniesEscape {
    public int shortestEscape(int[][] grid) {
        return bfsWithRemoval(grid, 0, 0, new boolean[grid.length][grid[0].length], 0, true);
    }

//    private int bfs(int[][] grid, int x, int y, boolean[][] visited, int steps) {
//        int hight = grid.length;
//        int width = grid[0].length;
//        steps ++;
//        if (x == hight-1 && y == width -1)
//            return steps;
//
//        if (grid[x][y] == 0) {
//            visited[x][y] = true;
//        } else {
//            return Integer.MAX_VALUE;
//        }
//
//        int min = Integer.MAX_VALUE;
//        int a=min, b=min, c=min, d=min;
//        // down
//        if (x + 1 < hight && !visited[x+1][y])
//            a = bfs(grid, x + 1, y, visited, steps);
//        // right
//        if (y + 1 < width && !visited[x][y+1])
//            b = bfs(grid, x,y + 1, visited, steps);
//        // left
//        if (y-1 >= 0 && !visited[x][y-1])
//           c = bfs(grid, x, y-1, visited, steps);
//        // up
//        if (x -1 >= 0 && !visited[x-1][y])
//            d = bfs(grid, x-1, y, visited, steps);
//
//        min = Math.min(a, (Math.min(b, Math.min(c, d))));
//
//        visited[x][y] = false;
//        return min;
//    }

    private int bfsWithRemoval(int[][] grid, int x, int y, boolean[][] visited, int steps, boolean allowRemoval) {
        int hight = grid.length;
        int width = grid[0].length;
        steps ++;
        if (x == hight-1 && y == width -1)
            return steps;

        if (grid[x][y] == 0) {
            visited[x][y] = true;
        } else {
            if (!allowRemoval)
                return Integer.MAX_VALUE;
            else {
                visited[x][y] = true;
                allowRemoval = false;
            }
        }

        int min = Integer.MAX_VALUE;
        int a=min, b=min, c=min, d=min;
        // down
        if (x + 1 < hight && !visited[x+1][y])
            a = bfsWithRemoval(grid, x + 1, y, visited, steps, allowRemoval);
        // right
        if (y + 1 < width && !visited[x][y+1])
            b = bfsWithRemoval(grid, x,y + 1, visited, steps, allowRemoval);
        // left
        if (y-1 >= 0 && !visited[x][y-1])
           c = bfsWithRemoval(grid, x, y-1, visited, steps, allowRemoval);
        // up
        if (x -1 >= 0 && !visited[x-1][y])
            d = bfsWithRemoval(grid, x-1, y, visited, steps, allowRemoval);

        min = Math.min(a, (Math.min(b, Math.min(c, d))));

//        visited[x][y] = false;
        return min;
    }

    public static void main(String args[]) {
        BunniesEscape be = new BunniesEscape();
        int[][] maze = new int[][]{
                {0, 1, 1, 0},
                {0, 0, 0, 1},
                {1, 1, 0, 0},
                {1, 1, 1, 0}
        };
        int[][] maze2 = new int[][]{
                {0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1},
                {0, 1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0, 0}
        };
        int s = be.shortestEscape(maze);
        int s2 = be.shortestEscape(maze2);
        System.out.println("Shortest path: " + s);
        System.out.println("Shortest path: " + s2);
    }
}
