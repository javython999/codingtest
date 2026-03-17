package com.errday.codingtest.bfs;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Queue;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Maze2 {


    @Test
    void case1() {
        int n = 4;
        int m = 6;
        int[][] map = {
                {1, 0, 1, 1, 1, 1},
                {1, 0, 1, 0, 1, 0},
                {1, 0, 1, 0, 1, 1},
                {1, 1, 1, 0, 1, 1}
        };

        int answer = 15;

        assertThat(solution(n, m, map)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int n = 4;
        int m = 6;
        int[][] map = {
                {1, 1, 0, 1, 1, 0},
                {1, 1, 0, 1, 1, 0},
                {1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 0, 1}
        };
        int answer = 9;

        assertThat(solution(n, m, map)).isEqualTo(answer);
    }

    @Test
    void case3() {
        int n = 2;
        int m = 25;
        int[][] map = {
                {1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1},
                {1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1}
        };
        int answer = 38;

        assertThat(solution(n, m, map)).isEqualTo(answer);
    }

    @Test
    void case4() {
        int n = 7;
        int m = 7;
        int[][] map = {
                {1, 0, 1, 1, 1, 1, 1},
                {1, 1, 1, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1}
        };
        int answer = 13;

        assertThat(solution(n, m, map)).isEqualTo(answer);
    }

    private int solution(int n, int m, int[][] map) {

        int[] moveRow = {-1, 0, 1, 0};
        int[] moveCol = {0, 1, 0, -1};

        int[][] distance = new int[n][m];
        Queue<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[]{0, 0});
        distance[0][0] = 1;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            int row = current[0];
            int col = current[1];

            for (int i = 0; i < 4; i++) {

                int nextRow = row + moveRow[i];
                int nextCol = col + moveCol[i];

                if (nextRow < 0 || nextRow >= n) {
                    continue;
                }

                if (nextCol < 0 || nextCol >= m) {
                    continue;
                }

                if (map[nextRow][nextCol] == 0) {
                    continue;
                }

                if (distance[nextRow][nextCol] != 0) {
                    continue;
                }

                distance[nextRow][nextCol] = distance[row][col] + 1;
                queue.offer(new int[]{nextRow, nextCol});
            }

        }

        return distance[n - 1][m - 1];
    }

}
