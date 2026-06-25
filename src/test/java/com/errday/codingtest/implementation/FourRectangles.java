package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FourRectangles {

    @Test
    void case1() {
        int[][] coordinates = {
                {1, 2, 4, 4},
                {2, 3, 5, 7},
                {3, 1, 6, 5},
                {7, 3, 8, 6}
        };

        int answer = 26;
        assertThat(solution(coordinates)).isEqualTo(answer);
    }

    private int solution(int[][] coordinates) {
        int[][] map = new int[100][100];

        for (int[] coordinate : coordinates) {
            int x1 = coordinate[0];
            int y1 = coordinate[1];
            int x2 = coordinate[2];
            int y2 = coordinate[3];

            for (int y = y1; y < y2; y++) {
                for (int x = x1; x < x2; x++) {
                    map[y][x] = 1;
                }
            }
        }

        int answer = 0;
        for (int[] row : map) {
            for (int count : row) {
                answer += count;
            }
        }

        return answer;
    }
}
