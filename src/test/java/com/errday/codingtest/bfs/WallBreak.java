package com.errday.codingtest.bfs;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Queue;

public class WallBreak {

    @Test
    void case1() {
        int n = 6;
        int m = 4;
        int[][] map = {
                {0,1,0,0},
                {1,1,1,0},
                {1,0,0,0},
                {0,0,0,0},
                {0,1,1,1},
                {0,0,0,0}
        };
        int answer = 15;
        Assertions.assertThat(solution(n, m, map)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int n = 4;
        int m = 4;
        int[][] map = {
                {0,1,1,1},
                {1,1,1,1},
                {1,1,1,1},
                {1,1,1,0}
        };
        int answer = -1;
        Assertions.assertThat(solution(n, m, map)).isEqualTo(answer);
    }

    private int[][] MOVES = {
            {-1, 0},
            {0, 1},
            {1, 0},
            {0, -1}
    };
    private int solution(int n, int m, int[][] map) {

        int[][][] distance = new int[n][m][2];
        distance[0][0][0] = 1;

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {0, 0, 0});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];
            int broken = current[2];

            if (row == n-1 && col == m-1) {
                return distance[n-1][m-1][broken];
            }

            for (int[] move : MOVES) {
                int nextRow = row + move[0];
                int nextCol = col + move[1];

                if (nextRow < 0 || nextRow >= n) {
                    continue;
                }

                if (nextCol < 0 || nextCol >= m) {
                    continue;
                }

                if (map[nextRow][nextCol] == 0 && distance[nextRow][nextCol][broken] == 0) {
                    queue.offer(new int[] {nextRow, nextCol, broken});
                    distance[nextRow][nextCol][broken] = distance[row][col][broken] + 1;

                }

                if (map[nextRow][nextCol] == 1 && broken == 0 && distance[nextRow][nextCol][1] == 0) {
                    queue.offer(new int[] {nextRow, nextCol, 1});
                    distance[nextRow][nextCol][1] = distance[row][col][broken] + 1;
                }
            }
        }

        return -1;
    }
}
