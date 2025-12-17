package com.errday.codingtest.dp;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class Tile {

    private int n = 3;
    private int answer = 5;


    @Test
    void solution() {

        int[] dp = new int[1000 + 1];

        dp[1] = 1;
        dp[2] = 3;

        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + 2 * dp[i - 2]) % 796796;
        }

        Assertions.assertThat(dp[n]).isEqualTo(answer);
    }
}
