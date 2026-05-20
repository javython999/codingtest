package com.errday.codingtest.greedy;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Coin {

    @Test
    void case1() {
        int n = 10;
        int k = 4200;
        int[] coins = {1, 5, 10, 50, 100, 500, 1000, 5000, 10000, 50000};
        int answer = 6;

        assertThat(solution(n, k, coins)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int n = 10;
        int k = 4790;
        int[] coins = {1, 5, 10, 50, 100, 500, 1000, 5000, 10000, 50000};
        int answer = 12 ;

        assertThat(solution(n, k, coins)).isEqualTo(answer);
    }

    private int solution(int n, int k, int[] coins) {
        int remain = k;
        int coinCount = 0;

        for (int i = n - 1; i >= 0; i--) {
            int coin = coins[i];

            if (remain < coin) {
                continue;
            }

            coinCount += remain / coin;
            remain %= coin;

        }

        return coinCount;
    }

}
