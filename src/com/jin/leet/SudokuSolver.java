package com.jin.leet;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 37. Sudoku Solver
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 *
 * A sudoku solution must satisfy all of the following rules:
 *
 * Each of the digits 1-9 must occur exactly once in each row.
 * Each of the digits 1-9 must occur exactly once in each column.
 * Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
 * Empty cells are indicated by the character '.'.
 *
 *
 * A sudoku puzzle...
 *
 *
 * ...and its solution numbers marked in red.
 *
 * Note:
 *
 * The given board contain only digits 1-9 and the character '.'.
 * You may assume that the given Sudoku puzzle will have a single unique solution.
 * The given board size is always 9x9.
 */
public class SudokuSolver {

    public void solveSudoku(char[][] board) {
        HashSet<Character>[] rowSets = new HashSet[9];
        HashSet<Character>[] colSets = new HashSet[9];
        HashSet<Character>[] gridSets = new HashSet[9];

        for (int i=0; i<9; i++) {
            for(int j=0; j<9; j++){
                if (rowSets[i] == null)
                    rowSets[i] = new HashSet<>();
                if (colSets[j] == null)
                    colSets[j] = new HashSet<>();
                if (gridSets[i/3+3*(j/3)] == null)
                    gridSets[i/3+3*(j/3)] = new HashSet<>();
                char c = board[i][j];
                if (c=='.')
                    continue;
                rowSets[i].add(c);
                colSets[j].add(c);
                gridSets[i/3+3*(j/3)].add(c);
            }
        }
        solve(board, rowSets, colSets, gridSets);
    }

    private boolean solve(char[][] board, HashSet<Character>[] rowSets, HashSet<Character>[] colSets, HashSet<Character>[] gridSets) {
        for (int i=0; i<9; i++) {
            for (int j=0; j<9; j++) {
                if (board[i][j] != '.')
                    continue;
                for (char c='1'; c<='9'; c++) {
                    if (isValid(c, i, j, rowSets, colSets, gridSets)) {
                        board[i][j] = c;
                        rowSets[i].add(c);
                        colSets[j].add(c);
                        gridSets[i/3+3*(j/3)].add(c);
                        if (solve(board, rowSets, colSets, gridSets)) {
                            return true;
                        } else {
                            board[i][j]='.';
                            rowSets[i].remove(c);
                            colSets[j].remove(c);
                            gridSets[i/3+3*(j/3)].remove(c);
                        }
                    }
                }
                return false;
            }
        }
        return true;
    }

    private boolean isValid(char c, int i, int j, HashSet<Character>[] rowSets, HashSet<Character>[] colSets, HashSet<Character>[] gridSets) {
        if (rowSets[i].contains(c) || colSets[j].contains(c) || gridSets[i/3+3*(j/3)].contains(c))
            return false;
        return true;
    }
    
    public static void main(String[] args) {
        char[][] board = new char[][] {
                {'.','.','9','7','4','8','.','.','.'},
                {'7','.','.','.','.','.','.','.','.'}, 
                {'.','2','.','1','.','9','.','.','.'},
                {'.','.','7','.','.','.','2','4','.'}, 
                {'.','6','4','.','1','.','5','9','.'},
                {'.','9','8','.','.','.','3','.','.'},
                {'.','.','.','8','.','3','.','2','.'}, 
                {'.','.','.','.','.','.','.','.','6'}, 
                {'.','.','.','2','7','5','9','.','.'}
        };
        SudokuSolver solver = new SudokuSolver();
        solver.solveSudoku(board);
        System.out.println(Arrays.toString(board));
    }


}
