package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Gomoku {

    @Test
    void case1() {
        int[][] board = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 2, 0, 0, 2, 2, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 2, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 2, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        int[] answer = {1, 3, 2};
        assertThat(solution(board)).containsExactly(answer);
    }

    private int[][] BOARD;
    private int EMPTY = 0;
    private int[][] MOVES = {
            {-1, 1},
            {0, 1},
            {1, 1},
            {1, 0}
    };

    private int[] solution(int[][] board) {
        BOARD = board;

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {

                if (BOARD[row][col] == EMPTY) {
                    continue;
                }

                for (int[] move : MOVES) {
                    if (isGomoku(row, col, move)) {
                        return new int[]{
                                BOARD[row][col],
                                row + 1,
                                col + 1
                        };
                    }
                }
            }
        }

        return new int[]{0};
    }

    private boolean isGomoku(int row, int col, int[] move) {
        int color = BOARD[row][col];
        int prevRow = row - move[0];
        int prevCol = col - move[1];

        if (isInBoundary(prevRow, prevCol) && BOARD[prevRow][prevCol] == color) {
            return false;
        }

        int count = 1;
        int nextRow = row + move[0];
        int nextCol = col + move[1];

        while (isInBoundary(nextRow, nextCol) && BOARD[nextRow][nextCol] == color) {
            count++;
            nextRow += move[0];
            nextCol += move[1];
        }

        return count == 5;
    }

    private boolean isInBoundary(int row, int col) {
        if (row < 0 || row >= BOARD.length) {
            return false;
        }

        if (col < 0 || col >= BOARD[row].length) {
            return false;
        }

        return true;
    }
}
