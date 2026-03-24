package com.errday.codingtest.bfs;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Queue;

public class WallBreak2 {

    @Test
    void case1() {
        int n = 6;
        int m = 4;
        int k = 1;
        int[][] map = {
                {0, 1, 0, 0},
                {1, 1, 1, 0},
                {1, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 1, 1, 1},
                {0, 0, 0, 0}
        };
        int answer = 15;
        Assertions.assertThat(solution(n, m, k, map)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int n = 6;
        int m = 4;
        int k = 2;
        int[][] map = {
                {0, 1, 0, 0},
                {1, 1, 1, 0},
                {1, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 1, 1, 1},
                {0, 0, 0, 0}
        };
        int answer = 9;
        Assertions.assertThat(solution(n, m, k, map)).isEqualTo(answer);
    }

    @Test
    void case3() {
        int n = 4;
        int m = 4;
        int k = 3;
        int[][] map = {
                {0, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 0}
        };
        int answer = -1;
        Assertions.assertThat(solution(n, m, k, map)).isEqualTo(answer);
    }

    private int solution(int n, int m, int k, int[][] map) {

        int[][][] distances = new int[n][m][k + 1];
        distances[0][0][0] = 1;

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0, 0});

        int[][] moves = {
                {-1, 0},
                {0, 1},
                {1, 0},
                {0, -1}
        };

        while (!queue.isEmpty()) {

            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];
            int broken = current[2];

            if (row == n - 1 && col == m - 1) {
                return distances[row][col][broken];
            }

            for (int[] move : moves) {
                int nextRow = row + move[0];
                int nextCol = col + move[1];

                if (nextRow < 0 || nextRow >= n) {
                    continue;
                }

                if (nextCol < 0 || nextCol >= m) {
                    continue;
                }

                if (map[nextRow][nextCol] == 0 && distances[nextRow][nextCol][broken] == 0) {
                    distances[nextRow][nextCol][broken] = distances[row][col][broken] + 1;
                    queue.offer(new int[]{nextRow, nextCol, broken});
                }

                if (map[nextRow][nextCol] == 1 && broken < k && distances[nextRow][nextCol][broken + 1] == 0) {
                    distances[nextRow][nextCol][broken + 1] = distances[row][col][broken] + 1;
                    queue.offer(new int[]{nextRow, nextCol, broken + 1});
                }

            }

        }

        return -1;
    }
}
