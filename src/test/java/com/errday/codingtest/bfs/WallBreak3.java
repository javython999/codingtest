package com.errday.codingtest.bfs;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;

public class WallBreak3 {

    @Test
    void case1() {
        int n = 1;
        int m = 4;
        int k = 1;
        int[][] map = {
                {0, 0, 1, 0},
        };
        int answer = 5;
        assertThat(solution(n, m, k, map)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int n = 1;
        int m = 4;
        int k = 1;
        int[][] map = {
                {0, 1, 0, 0},
        };
        int answer = 4;
        assertThat(solution(n, m, k, map)).isEqualTo(answer);
    }

    @Test
    void case3() {
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
        assertThat(solution(n, m, k, map)).isEqualTo(answer);
    }

    @Test
    void case4() {
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
        assertThat(solution(n, m, k, map)).isEqualTo(answer);
    }

    private int[][] MOVES = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int DAY = 0;
    private int NIGHT = 1;
    private int EMPTY = 0;
    private int WALL = 1;

    private int toggleTime(int time) {
        return time == DAY ? NIGHT : DAY;
    }

    private int solution(int n, int m, int k, int[][] map) {
        int[][][][] distances = new int[n][m][k + 1][2];
        distances[0][0][0][DAY] = 1;

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0, 0, DAY});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];
            int broken = current[2];
            int time = current[3];
            int nextTime = toggleTime(time);

            if (row == n - 1 && col == m - 1) {

                return distances[row][col][broken][time];
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

                if (map[nextRow][nextCol] == EMPTY
                        && distances[nextRow][nextCol][broken][nextTime] == 0) {
                    distances[nextRow][nextCol][broken][nextTime] = distances[row][col][broken][time] + 1;
                    queue.offer(new int[]{nextRow, nextCol, broken, nextTime});
                }

                if (map[nextRow][nextCol] == WALL
                        && broken < k
                        && time == DAY
                        && distances[nextRow][nextCol][broken + 1][nextTime] == 0) {
                    distances[nextRow][nextCol][broken + 1][nextTime] = distances[row][col][broken][time] + 1;
                    queue.offer(new int[]{nextRow, nextCol, broken + 1, nextTime});
                }


            }

            if (distances[row][col][broken][nextTime] == 0) {
                distances[row][col][broken][nextTime] = distances[row][col][broken][time] + 1;
                queue.offer(new int[]{row, col, broken, nextTime});
            }
        }

        return -1;
    }
}
