package com.errday.codingtest.dp;

import java.util.*;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Triangle {


    @Test
    void case1() {
        int[][] triangle = {
                {7},
                {3, 8},
                {8, 1, 0},
                {2, 7, 4, 4},
                {4, 5, 2, 6, 5}
        };

        int answer = 30;

        assertThat(solution(triangle)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int[][] triangle = {
                {1},
                {2, 3},
                {4, 5, 6},
                {7, 8, 9, 0},
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 0, 1},
                {2, 3, 4, 5, 6, 7, 8}
        };

        int answer = 38;

        assertThat(solution(triangle)).isEqualTo(answer);
    }

    private int solution(int[][] triangle) {
        int rowSize = triangle.length;

        for (int rowIndex = rowSize-1; rowIndex > 0; rowIndex--) {

            for (int colIndex = 0; colIndex < triangle[rowIndex].length - 1; colIndex++) {

                int prevValue = triangle[rowIndex-1][colIndex];
                int currentValue = triangle[rowIndex][colIndex];
                int siblingValue = triangle[rowIndex][colIndex+1];

                triangle[rowIndex-1][colIndex] = Math.max(prevValue + currentValue, prevValue + siblingValue);

            }

        }

        return triangle[0][0];
    }
}
