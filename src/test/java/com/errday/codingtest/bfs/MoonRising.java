package com.errday.codingtest.bfs;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
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

    @Test
    void case2() {
        int n = 5;
        int m = 5;
        char[][] map = {
                {'.', '.', '.', '.', '1'},
                {'#', '1', '#', '#', '#'},
                {'.', '1', '.', '#', '0'},
                {'.', '.', '.', '.', 'A'},
                {'.', '1', '.', '#', '.'}
        };
        int answer = -1;
        Assertions.assertThat(solution(n, m, map)).isEqualTo(answer);
    }

    @Test
    void case3() {
        int n = 7;
        int m = 8;
        char[][] map = {
                {'a', '#', 'c', '#', 'e', 'F', '.', '1'},
                {'.', '#', '.', '#', '.', '#', '.', '.'},
                {'.', '#', 'B', '#', 'D', '#', '#', '#'},
                {'0', '.', '.', '.', '.', 'F', '.', '1'},
                {'C', '#', 'E', '#', 'A', '#', '#', '#'},
                {'.', '#', '.', '#', '.', '#', '.', '.'},
                {'d', '#', 'f', '#', 'b', 'F', '.', '1'}
        };
        int answer = 55;
        Assertions.assertThat(solution(n, m, map)).isEqualTo(answer);
    }

    @Test
    void case4() {
        int n = 3;
        int m = 4;
        char[][] map = {
                {'1', '.', '.', '0'},
                {'#', '#', '#', '.'},
                {'1', '.', '.', '.'}
        };
        int answer = 3;
        Assertions.assertThat(solution(n, m, map)).isEqualTo(answer);
    }

    @Test
    void case5() {
        int n = 3;
        int m = 5;
        char[][] map = {
                {'.', '.', '0', '.', '.'},
                {'.', '#', '#', '#', '.'},
                {'.', '.', '1', '.', 'A'}
        };
        int answer = 6;
        Assertions.assertThat(solution(n, m, map)).isEqualTo(answer);
    }

    @Test
    void case6() {
        int n = 4;
        int m = 5;
        char[][] map = {
                {'0', '.', '.', '.', '.'},
                {'.', '#', 'B', '#', 'A'},
                {'.', '#', '.', '#', '.'},
                {'b', '#', 'a', '#', '1'}
        };
        int answer = 19;
        Assertions.assertThat(solution(n, m, map)).isEqualTo(answer);
    }

    @Test
    void case7() {
        int n = 1;
        int m = 11;
        char[][] map = {
                {'c', '.', '0', '.', 'C', '.', 'C', '.', 'C', '.', '1'}
        };
        int answer = 12;
        Assertions.assertThat(solution(n, m, map)).isEqualTo(answer);
    }

    @Test
    void case8() {
        int n = 3;
        int m = 6;
        char[][] map = {
                {'#', '#', '#', '.', '.', '.'},
                {'#', '0', 'A', '.', '1', 'a'},
                {'#', '#', '#', '.', '.', '.'}
        };
        int answer = -1;
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

                int newKeyState = keyState;

                // 열쇠
                if ('a' <= nextType && nextType <= 'z') {
                    newKeyState = keyState | (1 << nextType - 'a');
                }

                // 문
                if ('A' <= nextType && nextType <= 'Z') {
                    int keyIndex = nextType - 'A';

                    if ((newKeyState & (1 << keyIndex)) == 0) {
                        continue;
                    }
                }

                if (visited[nextRow][nextCol][newKeyState]) {
                    continue;
                }

                visited[nextRow][nextCol][newKeyState] = true;
                queue.offer(new int[]{nextRow, nextCol, newKeyState, moveCount + 1});
            }
        }

        return -1;
    }

}
