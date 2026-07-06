package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class Bingo {

    @Test
    void case1() {
        int[][] board = {
                {11, 12, 2, 24, 10},
                {16, 1, 13, 3, 25},
                {6, 20, 5, 21, 17},
                {19, 4, 8, 14, 9},
                {22, 15, 7, 23, 18}
        };
        int[][] numbers = {
                {5, 10, 7, 16, 2},
                {4, 22, 8, 17, 13},
                {3, 18, 1, 6, 25},
                {12, 19, 23, 14, 21},
                {11, 24, 9, 20, 15}
        };

        int answer = 15;
        assertThat(solution(board, numbers)).isEqualTo(answer);
    }

    private int solution(int[][] board, int[][] numbers) {

        int n = board.length;
        int m = board[0].length;

        Map<Integer, int[]> map = new HashMap<>();
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                map.put(board[row][col],  new int[]{row, col});
            }
        }

        int r = numbers.length;
        int c = numbers[0].length;

        int count = 0;
        for (int row = 0; row < r; row++) {
            for (int col = 0; col < c; col++) {
                count += 1;
                int[] coordinate = map.get(numbers[row][col]);
                board[coordinate[0]][coordinate[1]] = 0;

                if (3 <= rowCheck(board) + colCheck(board) + diagonalCheck(board)) {
                    return count;
                }
            }

        }
        return 0;
    }

    private int rowCheck(int[][] board) {
        int completeCount = 0;

        for (int row = 0; row < board.length; row++) {
            boolean isComplete = true;
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] != 0) {
                    isComplete = false;
                }
            }
            if (isComplete) {
                completeCount += 1;
            }
        }

        return completeCount;
    }

    private int colCheck(int[][] board) {
        int completeCount = 0;

        for (int col = 0; col < board[0].length; col++) {
            boolean isComplete = true;
            for (int row = 0; row < board.length; row++) {
                if (board[row][col] != 0) {
                    isComplete = false;
                }
            }
            if (isComplete) {
                completeCount += 1;
            }
        }

        return completeCount;
    }

    private int diagonalCheck(int[][] board) {
        int completeCount = 0;

        int row = 0;
        int col = 0;

        boolean isComplete = true;
        while (row < board.length && col < board[0].length) {

            if (board[row][col] != 0) {
                isComplete = false;
                break;
            }
            row += 1;
            col += 1;
        }

        if (isComplete) {
            completeCount += 1;
        }

        row = 0;
        col = board[0].length - 1;
        isComplete = true;

        while (0 <= row && 0 <= col) {

            if (board[row][col] != 0) {
                isComplete = false;
                break;
            }
            row += 1;
            col -= 1;
        }

        if (isComplete) {
            completeCount += 1;
        }

        return completeCount;
    }
}
