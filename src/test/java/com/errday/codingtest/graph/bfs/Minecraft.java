package com.errday.codingtest.graph.bfs;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Minecraft {

    @Test
    void case1() {
        int n = 3;
        int m = 4;
        int b = 99;
        int[][] map = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 1}
        };
        int[] answer = {2, 0};
        assertThat(solution(n, m, b, map)).containsExactly(answer);
    }

    @Test
    void case2() {
        int n = 3;
        int m = 4;
        int b = 1;
        int[][] map = {
                {64, 64, 64, 64},
                {64, 64, 64, 64},
                {64, 64, 64, 63},
        };
        int[] answer = {1, 64};
        assertThat(solution(n, m, b, map)).containsExactly(answer);
    }

    private int[] solution(int n, int m, int b, int[][] map) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                int current = map[row][col];

                if (current < min) {
                    min = current;
                }

                if (current > max) {
                    max = current;
                }
            }
        }

        int minTime = Integer.MAX_VALUE;
        int height = 0;
        for (int h = min; h <= max; h++) {
            int time = 0;
            int tempBlock = b;
            for (int row = 0; row < n; row++) {
                for (int col = 0; col < m; col++) {
                    int currentHeight = map[row][col];

                    if (currentHeight > h) {
                        int diff = currentHeight - h;
                        time += diff * 2;
                        tempBlock += diff;
                        continue;
                    }

                    if (currentHeight < h) {
                        int diff = h - currentHeight;
                        time += diff;
                        tempBlock -= diff;
                    }
                }
            }

            if (minTime >= time && tempBlock >= 0) {
                minTime = time;
                height = h;
            }
        }

        return new int[] {minTime, height};
    }
}
