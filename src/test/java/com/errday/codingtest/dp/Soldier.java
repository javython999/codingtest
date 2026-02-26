package com.errday.codingtest.dp;

import java.util.*;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Soldier {

    @Test
    void case1() {
        int[] soldiers = {15, 11, 4, 8, 5, 2, 4};

        int answer = 2;

        assertThat(solution(soldiers)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int[] soldiers = {50, 20, 30, 10, 20, 10};

        int answer = 2;

        assertThat(solution(soldiers)).isEqualTo(answer);
    }

    private int solution(int[] soldiers) {

        int n = soldiers.length;
        int[] reversedSoldiers = new int[n];

        for (int i = 0; i < n; i++) {
            reversedSoldiers[n - i - 1] = soldiers[i];
        }

        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int i = 1; i < n; i++) {
            for (int searchIndex = 0; searchIndex < i; searchIndex++) {
                if (reversedSoldiers[searchIndex] < reversedSoldiers[i]) {
                    dp[i] = Math.max(dp[i], dp[searchIndex] + 1);
                }
            }
        }

        int lis = Arrays.stream(dp)
                .max()
                .getAsInt();

        return n - lis;
    }
}
