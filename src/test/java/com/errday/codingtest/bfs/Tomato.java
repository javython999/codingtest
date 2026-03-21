package com.errday.codingtest.bfs;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;

public class Tomato {

    @Test
    void case1() {
        int m = 6;
        int n = 4;
        int[][] box = {
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1}
        };
        int answer = 8;
        assertThat(solution(m, n, box)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int m = 6;
        int n = 4;
        int[][] box = {
                {0, -1, 0, 0, 0, 0},
                {-1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1}
        };
        int answer = -1;
        assertThat(solution(m, n, box)).isEqualTo(answer);
    }

    @Test
    void case3() {
        int m = 5;
        int n = 5;
        int[][] box = {
                {-1, 1, 0, 0, 0},
                {0, -1, -1, -1, 0},
                {0, -1, -1, -1, 0},
                {0, -1, -1, -1, 0},
                {0, 0, 0, 0, 0}
        };
        int answer = 14;
        assertThat(solution(m, n, box)).isEqualTo(answer);
    }

    @Test
    void case4() {
        int m = 2;
        int n = 2;
        int[][] box = {
                {1, -1},
                {-1 ,1}
        };
        int answer = 0;
        assertThat(solution(m, n, box)).isEqualTo(answer);
    }

    private int TOMATO = 1;
    private int UNRIPE = 0;
    private int EMPTY = - 1;
    private int[][] moves = {
            {-1, 0},
            {0, 1},
            {1, 0},
            {0, -1}
    };

    private int solution(int m, int n, int[][] box) {

        Queue<int[]> queue = new ArrayDeque<>();
        int days = 0;
        int unripeCount = 0;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {

                if (box[row][col] == TOMATO) {
                    queue.offer(new int[] {row, col});
                } else if (box[row][col] == UNRIPE) {
                    unripeCount += 1;
                }

            }
        }

        if (unripeCount == 0) {
            return 0;
        }

        if (queue.isEmpty()) {
            return EMPTY;
        }

        while (!queue.isEmpty()) {
            int todaySize = queue.size();

            for (int i = 0; i < todaySize; i++) {
                int[] current = queue.poll();
                int row = current[0];
                int col = current[1];

                for (int[] move : moves) {
                    int nextRow = row + move[0];
                    int nextCol = col + move[1];

                    if (nextRow < 0 || nextRow >= n) {
                        continue;
                    }

                    if (nextCol < 0 || nextCol >= m) {
                        continue;
                    }

                    if (box[nextRow][nextCol] != UNRIPE) {
                        continue;
                    }

                    box[nextRow][nextCol] = TOMATO;
                    queue.offer(new int[]{nextRow, nextCol});
                    unripeCount -= 1;

                    if (unripeCount == 0) {
                        return days + 1;
                    }
                }
            }
            days += 1;
        }

        return EMPTY;
    }

}
