package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Matrix {
    @Test
    void case1() {
        int[][] array1 = {{1, 4,}, {3, 2}, {4, 1}};
        int[][] array2 = {{3, 3}, {3, 3}};
        int[][] answer = {{15, 15}, {15, 15}, {15, 15}};

        assertThat(solution(array1, array2)).isEqualTo(answer);
    }

    private int[][] solution(int[][] array1, int[][] array2) {

        int rowSize = array1.length;
        int colSize = array2[0].length;

        int[][] result = new int[rowSize][colSize];

        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                for (int k = 0; k < array1[row].length; k++) {
                    result[row][col] += array1[row][k] * array2[k][col];
                }
            }
        }
        return result;
    }
}
