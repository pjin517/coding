package com.jin.leet;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Let's play the minesweeper game (Wikipedia, online game)!
 *
 * You are given a 2D char matrix representing the game board. 'M' represents an unrevealed mine, 'E' represents an unrevealed empty square,
 * 'B' represents a revealed blank square that has no adjacent (above, below, left, right, and all 4 diagonals) mines,
 * digit ('1' to '8') represents how many mines are adjacent to this revealed square, and finally 'X' represents a revealed mine.
 *
 * Now given the next click position (row and column indices) among all the unrevealed squares ('M' or 'E'),
 * return the board after revealing this position according to the following rules:
 *
 * If a mine ('M') is revealed, then the game is over - change it to 'X'.
 * If an empty square ('E') with no adjacent mines is revealed, then change it to revealed blank ('B') and all of its adjacent unrevealed squares should be revealed recursively.
 * If an empty square ('E') with at least one adjacent mine is revealed, then change it to a digit ('1' to '8') representing the number of adjacent mines.
 * Return the board when no more squares will be revealed.
 * Example 1:
 * Input:
 *
 * [['E', 'E', 'E', 'E', 'E'],
 *  ['E', 'E', 'M', 'E', 'E'],
 *  ['E', 'E', 'E', 'E', 'E'],
 *  ['E', 'E', 'E', 'E', 'E']]
 *
 * Click : [3,0]
 *
 * Output:
 *
 * [['B', '1', 'E', '1', 'B'],
 *  ['B', '1', 'M', '1', 'B'],
 *  ['B', '1', '1', '1', 'B'],
 *  ['B', 'B', 'B', 'B', 'B']]
 *
 * Explanation:
 *
 * Example 2:
 * Input:
 *
 * [['B', '1', 'E', '1', 'B'],
 *  ['B', '1', 'M', '1', 'B'],
 *  ['B', '1', '1', '1', 'B'],
 *  ['B', 'B', 'B', 'B', 'B']]
 *
 * Click : [1,2]
 *
 * Output:
 *
 * [['B', '1', 'E', '1', 'B'],
 *  ['B', '1', 'X', '1', 'B'],
 *  ['B', '1', '1', '1', 'B'],
 *  ['B', 'B', 'B', 'B', 'B']]
 *
 * Explanation:
 *
 * Note:
 * The range of the input matrix's height and width is [1,50].
 * The click position will only be an unrevealed square ('M' or 'E'), which also means the input board contains at least one clickable square.
 * The input board won't be a stage when game is over (some mines have been revealed).
 * For simplicity, not mentioned rules should be ignored in this problem. For example, you don't need to reveal all the unrevealed mines when the game is over, consider any cases that you will win the game or flag any squares.
 *
 */
public class Minesweeper {
    private final int[][] adj = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1}, {0, 1},
            {1, -1}, {1, 0}, {1, 1}
    };

    public char[][] updateBoard(char[][] board, int[] click) {
        int x = click[0];
        int y = click[1];

        if (board[x][y] == 'M') {
            board[x][y] = 'X';
            return board;
        }

        if (board[x][y] == 'E') {
            // if adj has mines, show number
            int mines = checkAdj(board, x, y);
            if (mines > 0) {
                board[x][y] = (char) ('0' + mines);
                return board;
            } else {
                // adj has no mine, change it to 'B' and reveal all adj
                Queue<Position> queue = new LinkedList<>();
                queue.offer(new Position(x, y));
                revealBoard(board, queue);
            }
        }

        // no action if others are clicked
        return board;

    }

    private int checkAdj(char[][] board, int x, int y) {
        int mines=0;
        for (int[] p: adj) {
            int m = x + p[0];
            int n = y + p[1];
            if (m<0 || n<0 || m>=board.length || n>=board[0].length)
                continue;
            if (board[m][n] == 'M')
                mines++;
        }
        return mines;
    }
    private void revealBoard(char[][] board, Queue<Position> queue) {
        while (!queue.isEmpty()){
            Position pos = queue.poll();
            int mines = checkAdj(board, pos.x, pos.y);
            if (board[pos.x][pos.y] == 'E') {
                // if all adj positions are E, set it to 'B', add all
                if ( mines == 0) {
                    board[pos.x][pos.y] = 'B';
                    for (int[] p : adj) {
                        int m = pos.x + p[0];
                        int n = pos.y + p[1];
                        if (m < 0 || n < 0 || m >= board.length || n >= board[0].length)
                            continue;
                        queue.offer(new Position(m, n));
                    }
                } else {
                    board[pos.x][pos.y] = (char) ('0' + mines);
                }
            }
            if (board[pos.x][pos.y] == 'M') {
                mines++;
                board[pos.x][pos.y] = (char) ('0' + mines);
            }
        }
    }

    class Position {
        int x;
        int y;

        public Position() {
            x = -1;
            y = -1;
        }

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
