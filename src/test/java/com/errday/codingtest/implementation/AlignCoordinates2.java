package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class AlignCoordinates2 {

    @Test
    void case1() {
        int[][] coordinates = {
                {0, 4},
                {1, 2},
                {1, -1},
                {2, 2},
                {3, 3}
        };

        int[][] answer = {
                {1, -1},
                {1, 2},
                {2, 2},
                {3, 3},
                {0, 4}
        };

        assertThat(solution(coordinates)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int[][] coordinates = {
                {0, -4},
                {1, -2},
                {1, -1},
                {2, -2},
                {3, -3}
        };

        int[][] answer = {
                {0, -4},
                {3, -3},
                {1, -2},
                {2, -2},
                {1, -1},
        };

        assertThat(solution(coordinates)).isEqualTo(answer);
    }


    private int[][] solution(int[][] coordinates) {

        Arrays.sort(coordinates, (a, b) -> {
           int x1 = a[0];
           int y1 = a[1];
           int x2 = b[0];
           int y2 = b[1];

           if (y1 == y2) {
               return x1 - x2;
           }

           return y1 - y2;
        });

        return coordinates;
    }
}
