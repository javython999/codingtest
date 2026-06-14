package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ArraySum2D {

    @Test
    void case1() {
        int[][] array = {
                {1, 2, 4},
                {8, 16, 32}
        };
        int[][] coordinates = {
                {1, 1, 2, 3},
                {1, 2, 1, 2},
                {1, 3, 2, 3}
        };
        int[] answer = {63, 2, 36};
        assertThat(solution(array, coordinates)).isEqualTo(answer);
    }

    private int[] solution(int[][] array, int[][] coordinates) {

        int n = array.length;
        int m = array[0].length;

        int[][] dp = new int[n + 1][m + 1];
        for (int row = 1; row < n + 1; row++) {
            for (int col = 1; col < m + 1; col++) {
                dp[row][col] = dp[row - 1][col] + dp[row][col - 1] + array[row - 1][col - 1] - dp[row - 1][col - 1];
            }
        }

        int k = coordinates.length;
        int[] answer = new int[k];
        for (int i = 0; i < k; i++) {
            int[] coordinate = coordinates[i];
            int row1 = coordinate[0];
            int col1 = coordinate[1];
            int row2 = coordinate[2];
            int col2 = coordinate[3];

            answer[i] = dp[row2][col2]
                    - dp[row2][col1 - 1]
                    - dp[row1 - 1][col2]
                    + dp[row1 - 1][col1 - 1];
        }
        return answer;
    }
}
