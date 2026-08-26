package com.errday.codingtest.dp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Wine {

    @Test
    void case1() {
        int n = 6;
        int[] glasses = {6, 10, 13, 9, 8, 1};
        int answer = 33;
        assertThat(solution(n, glasses)).isEqualTo(answer);
    }

    private int solution(int n, int[] glasses) {
        int[] adjusted = new int[n + 1];
        System.arraycopy(glasses, 0, adjusted, 1, n);

        if (n == 1) {
            return adjusted[1];
        }

        if (n == 2) {
            return adjusted[1] + adjusted[2];
        }

        int[] memo = new int[n + 1];
        memo[1] = adjusted[1];
        memo[2] = adjusted[1] + adjusted[2];

        for (int i = 3; i < n + 1; i++) {
            int value1 = memo[i - 1];
            int value2 = adjusted[i] + memo[i - 2];
            int value3 = adjusted[i] + adjusted[i - 1] + memo[i -3];
            memo[i] = Math.max(value1, Math.max(value2, value3));
        }

        return memo[n];
    }
}
