package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class AlignCoordinates {

    @Test
    void case1() {
        int[][] coordinates = {
                {3, 4},
                {1, 1},
                {1, -1},
                {2, 2},
                {3, 3}
        };

        int[][] answer = {
                {1, -1},
                {1, 1},
                {2, 2},
                {3, 3},
                {3, 4}
        };

        assertThat(solution(coordinates)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int[][] coordinates = {
                {3, 0},
                {2, 2},
                {2, 5},
                {1, 2},
                {3, 3},
                {3, 4},
                {1, 4},
                {1, 5},
                {2, 0},
                {2, 1},
                {2, 3},
                {1, 1},
                {3, 1},
                {1, 3},
                {2, 4},
                {3, 2},
        };

        int[][] answer = {
                {1, 1},
                {1, 2},
                {1, 3},
                {1, 4},
                {1, 5},
                {2, 0},
                {2, 1},
                {2, 2},
                {2, 3},
                {2, 4},
                {2, 5},
                {3, 0},
                {3, 1},
                {3, 2},
                {3, 3},
                {3, 4}
        };

        assertThat(solution(coordinates)).isEqualTo(answer);
    }


    private int[][] solution(int[][] coordinates) {
        Arrays.sort(coordinates, (a, b) -> {
            if (a[0] == b[0]) {
               return a[1] - b[1];
            }

           return a[0] - b[0];
        });

        return coordinates;
    }
}
