package com.errday.codingtest.dp;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class LBS {

    @Test
    void case1() {
        int n = 10;
        int[] seq = {1, 5, 2, 1, 4, 3, 4, 5, 2, 1};
        int answer = 7;
        assertThat(solution(n, seq)).isEqualTo(answer);
    }

    private int solution(int n, int[] seq) {

        int[] lis = lis(n, seq);
        int[] lds = lds(n, seq);

        int[] result = new int[n];

        int max = 0;
        for (int i = 0; i < n; i++) {
            result[i] = lis[i] + lds[i] - 1;
            max = Math.max(max, result[i]);
        }
        return max;
    }

    private int[] lis(int n, int[] seq) {
        int[] memo = new int[n];
        Arrays.fill(memo, 1);

        for (int current = 1; current < n; current++) {

            for (int previous = 0; previous < current; previous++) {

                if (seq[previous] < seq[current]) {
                    memo[current] = Math.max(memo[current], memo[previous] + 1);
                }

            }

        }

        return memo;
    }

    private int[] lds(int n, int[] seq) {
        int[] memo = new int[n];
        Arrays.fill(memo, 1);

        for (int current = n - 2; current >= 0; current--) {

            for (int previous = current + 1; previous < n; previous++) {

                if (seq[previous] < seq[current]) {
                    memo[current] = Math.max(memo[current], memo[previous] + 1);
                }

            }

        }

        return memo;
    }
}
