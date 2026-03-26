package com.errday.codingtest.bfs;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;

public class Algospot {

    @Test
    void case1() {
        int n = 3;
        int m = 3;
        int[][] map = {
                {0, 1, 1},
                {1, 1, 1},
                {1, 1, 0}
        };
        int answer = 3;
        assertThat(solution(m, n, map)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int n = 4;
        int m = 2;
        int[][] map = {
                {0, 0, 0, 1},
                {1, 0, 0, 0}
        };
        int answer = 0;
        assertThat(solution(n, m, map)).isEqualTo(answer);
    }

    @Test
    void case3() {
        int n = 6;
        int m = 6;
        int[][] map = {
                {0, 0, 1, 1, 1, 1},
                {0, 1, 0, 0, 0, 0},
                {0, 0, 1, 1, 1, 1},
                {1, 1, 0, 0, 0, 1},
                {0, 1, 1, 0, 1, 0},
                {1, 0, 0, 0, 1, 0}
        };
        int answer = 2;
        assertThat(solution(n, m, map)).isEqualTo(answer);
    }

    private int INF = Integer.MAX_VALUE / 2;
    private int ROOM = 0;
    private int WALL = 1;
    private int[][] MOVES = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private int solution(int colSize, int rowSize, int[][] map) {
        int[][] distances = new int[rowSize][colSize];
        for (int[] distance : distances) {
            Arrays.fill(distance, INF);
        }
        distances[0][0] = 0;

        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0});

        while (!queue.isEmpty()) {

            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];

            for (int[] move : MOVES) {
                int nextRow = row + move[0];
                int nextCol = col + move[1];

                if (nextRow < 0 || nextRow >= rowSize) {
                    continue;
                }

                if (nextCol < 0 || nextCol >= colSize) {
                    continue;
                }

                if (map[nextRow][nextCol] == ROOM) {
                    if (distances[nextRow][nextCol] > distances[row][col]) {
                        distances[nextRow][nextCol] = distances[row][col];
                        queue.addFirst(new int[] {nextRow, nextCol});
                    }
                }

                if (map[nextRow][nextCol] == WALL) {
                    if (distances[nextRow][nextCol] > distances[row][col] + 1) {
                        distances[nextRow][nextCol] = distances[row][col] + 1;
                        queue.addLast(new int[] {nextRow, nextCol});
                    }
                }

            }

        }

        return distances[rowSize - 1][colSize - 1];
    }
}
