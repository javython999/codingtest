package com.errday.codingtest.dp;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class PrefixSum {

    @Test
    void case1() {
        int n = 5;
        int m = 3;
        int[] array = {5, 4, 3, 2, 1};
        int[][] input = {{1, 3}, {2, 4}, {5, 5}};

        int[] answer = {12, 9 ,1};

        Assertions.assertThat(solution(n, m, array, input))
                .containsExactly(answer)
                .hasSize(answer.length);
    }

    private int[] solution(int n, int m, int[] array, int[][] input) {

        int[] dp = new int[n + 1];
        dp[0] = 0;

        for (int i = 1; i < n + 1; i++) {
            dp[i] = dp[i - 1] + array[i - 1];
        }

        int[] result = new int[m];
        for (int i = 0; i < m; i++) {
            int[] range = input[i];
            result[i] = dp[range[1]] - dp[range[0] - 1];
        }

        return result;
    }
}
