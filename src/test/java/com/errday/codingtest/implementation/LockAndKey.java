package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class LockAndKey {

    @Test
    void case1() {
        int[][] key = {
                {0, 0, 0},
                {1, 0, 0},
                {0, 1, 1}
        };

        int [][] lock = {
                {1, 1, 1},
                {1, 1, 0},
                {1, 0, 1}
        };

        boolean answer = true;

        assertThat(solution(key, lock)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int[][] key = {
                {0, 0, 0},
                {0, 1, 0},
                {0, 1, 0}
        };

        int [][] lock = {
                {1, 1, 1},
                {1, 0, 0},
                {1, 1, 1}
        };

        boolean answer = true;

        assertThat(solution(key, lock)).isEqualTo(answer);
    }

    private boolean solution(int[][] key, int[][] lock) {

        int[][] newLock = expandLock(lock);

        int rowSize = newLock.length;
        int colSize = newLock[0].length;

        int keyRowSize = key.length;
        int keyColSize = key[0].length;

        for (int rotate = 0; rotate < 4; rotate++) {

            if (rotate > 0) {
                key = turn90Degree(key);
            }

            for (int startRow = 0; startRow <= (rowSize - keyRowSize); startRow++) {

                for (int startCol = 0; startCol <= (colSize - keyColSize); startCol++) {

                    applyKey(key, newLock, startRow, startCol);

                    if (isOpen(newLock)) {
                        return true;
                    }

                    rollback(key, newLock, startRow, startCol);
                }
            }
        }


        return false;
    }

    private int[][] turn90Degree(int[][] key) {

        int rowSize = key.length;
        int colSize = key[0].length;

        int[][] result = new int[rowSize][colSize];

        for (int row = 0; row < rowSize; row++) {

            for (int col = 0; col < colSize; col++) {
                result[col][rowSize - 1 - row] = key[row][col];
            }
        }

        return result;
    }

    private int[][] expandLock(int[][] lock) {
        int rowSize = lock.length;
        int colSize = lock[0].length;

        int newRowSize = rowSize * 3;
        int newColSize = colSize * 3;

        int[][] newLock = new int[newRowSize][newColSize];

        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                newLock[row + rowSize][col + colSize] = lock[row][col];
            }
        }
        return newLock;
    }

    private void applyKey(int[][] key, int[][] newLock, int startRow, int startCol)  {
        int rowSize = key.length;
        int colSize = key[0].length;
        for (int keyRow = 0; keyRow < rowSize; keyRow++) {

            for (int keyCol = 0; keyCol < colSize; keyCol++) {
                newLock[startRow + keyRow][startCol + keyCol] += key[keyRow][keyCol];
            }
        }
    }

    private void rollback(int[][] key, int[][] newLock, int startRow, int startCol) {
        int rowSize = key.length;
        int colSize = key[0].length;
        for (int keyRow = 0; keyRow < rowSize; keyRow++) {

            for (int keyCol = 0; keyCol < colSize; keyCol++) {
                newLock[startRow + keyRow][startCol + keyCol] -= key[keyRow][keyCol];
            }
        }
    }

    private boolean isOpen(int[][] lock) {
        int originRowSize = lock.length / 3;
        int originColSize = lock[0].length / 3;

        for (int row = originRowSize; row < originRowSize * 2; row++) {
            for (int col = originColSize; col < originColSize * 2; col++) {
                if (lock[row][col] != 1) {
                    return false;
                }
            }
        }
        return true;
    }
}
