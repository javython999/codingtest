package com.errday.codingtest.dp;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class LIS1 {

    @Test
    void case1() {
        int n = 6;
        int[] seq = {10, 20, 10, 30, 20, 50};
        int answer = 4;
        assertThat(solution(n, seq)).isEqualTo(answer);
    }

    private int solution(int n, int[] seq) {

        int[] memo = new int[n];
        Arrays.fill(memo, 1);

        for (int current = 1; current < n; current++) {
            for (int previous = 0; previous < current; previous++) {
                if (seq[previous] < seq[current]) {
                    memo[current] = Math.max(memo[current], memo[previous] + 1);
                }

            }

        }

        return Arrays.stream(memo).max().getAsInt();
    }

}
