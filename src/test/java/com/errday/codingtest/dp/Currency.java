package com.errday.codingtest.dp;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class Currency {

    private int n = 2;
    private int m = 15;
    private int[] currencies = {2, 3};

    private int answer = 5;

    @Test
    void solution() {

        int[] dp = new int[m + 1];

        int inf = Integer.MAX_VALUE;

        Arrays.fill(dp, inf);

        dp[0] = 0;

        for (int currencyIndex = 0; currencyIndex < n; currencyIndex++) {
            int currency = currencies[currencyIndex];
            for (int target = currency; target <= m; target++) {

                if (dp[target - currency] != inf) {
                    dp[target] = Math.min(dp[target], dp[target - currency] + 1);
                }


            }

        }

        int myAnswer = -1;
        if (dp[m] != inf) {
            myAnswer = dp[m];
        }

        Assertions.assertThat(myAnswer).isEqualTo(answer);
    }
}
