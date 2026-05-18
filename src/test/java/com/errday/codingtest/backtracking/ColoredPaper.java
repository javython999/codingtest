package com.errday.codingtest.backtracking;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ColoredPaper {

    @Test
    void case1() {
        int[][] map = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        int answer = 0;

        assertThat(solution(map)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int[][] map = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        int answer = -1;

        assertThat(solution(map)).isEqualTo(answer);
    }

    @Test
    void case3() {
        int[][] map = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 1, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        int answer = 7;

        assertThat(solution(map)).isEqualTo(answer);
    }

    private int[] papers = {0, 5, 5, 5, 5, 5};
    private int result = Integer.MAX_VALUE;

    private int solution(int[][] map) {
        backtracking(map, 0, 0);

        if (result == Integer.MAX_VALUE) {
            return -1;
        }

        return result;
    }

    private void backtracking(int[][] map, int position, int useCount) {

        if (position == 100) {
            result = Math.min(result, useCount);
            return;
        }

        int row = position / 10;
        int col = position % 10;

        if (result <= useCount) {
            return;
        }

        if (map[col][row] == 1) {

            for (int i = 5; i > 0; i--) {
                if (papers[i] > 0 && check(map, row, col, i)) {
                    papers[i] -= 1;
                    fill(map, row, col, i, 0);
                    backtracking(map, position + 1, useCount + 1);
                    fill(map, row, col, i, 1);
                    papers[i] += 1;
                }
            }

        } else {
            backtracking(map, position + 1, useCount);
        }
    }

    private boolean check(int[][] map, int x, int y, int paperSize) {
        if (x + paperSize > 10) {
            return false;
        }

        if (y + paperSize > 10) {
            return false;
        }

        for (int row = y; row < y + paperSize; row++) {
            for (int col = x; col < x + paperSize; col++) {
                if (map[row][col] != 1) {
                    return false;
                }
            }
        }

        return true;
    }

    private void fill(int[][] map, int x, int y, int paperSize, int value) {
        for (int row = y; row < y + paperSize; row++) {
            for (int col = x; col < x + paperSize; col++) {
                map[row][col] = value;
            }
        }
    }
}
