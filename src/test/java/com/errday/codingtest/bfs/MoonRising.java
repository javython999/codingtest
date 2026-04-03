package com.errday.codingtest.bfs;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class MoonRising {

    @Test
    void case1() {
        int n = 1;
        int m = 7;
        char[][] map = {
                {'f', '0', '.', 'F', '.', '.', '1'}
        };
        int answer = 7;
        Assertions.assertThat(solution(n, m, map)).isEqualTo(answer);
    }

    private char EMPTY = '.';
    private char WALL = '#';
    private char START = '0';
    private char EXIT = '1';
    private int[][] MOVES = {
            {-1, 0},
            {0, 1},
            {1, 0},
            {0, -1}
    };

    private int solution(int rowSize, int colSize, char[][] map) {

        boolean[][][] visited = new boolean[rowSize][colSize][64];

        Queue<int[]> queue = new ArrayDeque<>();

        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                if (map[row][col] == START) {
                    queue.offer(new int[]{row, col, 0, 0});
                    visited[row][col][0] = true;
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];
            int keyState = current[2];
            int moveCount = current[3];


            for (int[] move : MOVES) {
                int nextRow = row + move[0];
                int nextCol = col + move[1];

                if (nextRow < 0 || nextRow >= rowSize) {
                    continue;
                }

                if (nextCol < 0 || nextCol >= colSize) {
                    continue;
                }

                char nextType = map[nextRow][nextCol];

                if (nextType == WALL) {
                    continue;
                }

                if (nextType == EXIT) {
                    return moveCount + 1;
                }

                if (nextType == EMPTY || nextType == START) {
                    if (!visited[nextRow][nextCol][keyState]) {
                        visited[nextRow][nextCol][keyState] = true;
                        queue.offer(new int[]{nextRow, nextCol, keyState, moveCount + 1});
                    }
                    continue;
                }

                // 열쇠
                if (Character.isLowerCase(nextType)) {
                    int newKeyState = keyState | (1 << getKeyIndex(nextType));
                    if (!visited[nextRow][nextCol][newKeyState]) {
                        visited[nextRow][nextCol][newKeyState] = true;
                        queue.offer(new int[]{nextRow, nextCol, newKeyState, moveCount + 1});
                    }
                    continue;
                }

                // 문
                if (Character.isUpperCase(nextType)) {
                    int doorIndex = getKeyIndex(Character.toLowerCase(nextType));

                    // 열쇠 있음
                    if ((keyState & (1 << doorIndex)) != 0) {
                        if (!visited[nextRow][nextCol][keyState]) {
                            visited[nextRow][nextCol][keyState] = true;
                            queue.offer(new int[]{nextRow, nextCol, keyState, moveCount + 1});
                        }
                    }
                }
            }
        }

        return -1;
    }

    private int getKeyIndex(char key) {
        return key - 'a';
    }
}
