package com.jin.leet;

/**
 * 79. Word Search
 * Given a 2D board and a word, find if the word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.
 *
 * Example:
 *
 * board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 *
 * Given word = "ABCCED", return true.
 * Given word = "SEE", return true.
 * Given word = "ABCB", return false.
 *
 */
public class WordSearch {
    public boolean exist(char[][] board, String word) {
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[0].length; j++) {
                if (search(board, i, j, word.toCharArray(), 0))
                    return true;
            }
        }
        return false;
    }

    private boolean search(char[][] board, int i, int j, char[] chars, int index) {
        if (index == chars.length)
            return true;
        if (i<0||i>=board.length||j<0||j>=board[0].length)
            return false;

        char c = chars[index];
        if (board[i][j] != c) {
            return false;
        }
        board[i][j] = '#';
        boolean result = (search(board, i-1, j, chars, index+1) || search(board, i, j-1, chars, index+1)
                        ||search(board, i+1, j, chars, index+1) || search(board, i, j+1, chars, index+1));
        if (!result)
            board[i][j] = c;
        return result;
    }

    public static void main(String args[]) {
        WordSearch ws = new WordSearch();
        char[][] board = new char[][] {
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        };
        System.out.println(ws.exist(board, "ABCCED"));
        System.out.println(ws.exist(board, "ABCB"));
    }
}
