package com.errday.codingtest.dp;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class Ant {

    private int n = 4;
    private int[] data = {1, 3, 1, 5};
    private int answer = 8;

    @Test
    void solution() {

        int[] memo = new int[100];

        memo[0] = data[0];
        memo[1] = Math.max(memo[0], data[1]);

        for (int i = 2; i < n; i++) {
            memo[i] = Math.max(memo[i - 1], memo[i - 2] + data[i]);
        }

        Assertions.assertThat(memo[n - 1]).isEqualTo(answer);
    }
}
