package com.errday.codingtest.dp;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MakeIt1 {


    @Test
    void solution() {
        int n = 10;
        int answer = 4;
        int[] memo  = new int[n + 1];

        for (int i = 1; i < memo.length; i++) {
            List<Integer> temp = new ArrayList<>();

            if (i % 3 == 0) {
                temp.add(memo[i / 3] + 1);
            }

            if (i % 2 == 0) {
                temp.add(memo[i / 2] + 1);
            }

            temp.add(memo[i - 1] + 1);

            memo[i] = temp.stream()
                            .min(Integer::compare)
                            .get();

        }

        assertThat(memo[n]).isEqualTo(answer);
    }

    @Test
    void solution2() {
        int n = 26;
        int answer = 3;

        // n이 5로 나누어 떨어지면 5로 나눈다.
        // n이 3으로 나누어 떨어지면 3으로 나눈다.
        // n이 2로 나누어 떨어지면 2로 나눈다.
        // n에서 1을 뺀다.

        int[] memo = new int[n + 1];
        memo[1] = 1;
        memo[2] = 1;
        memo[3] = 1;
        memo[5] = 1;

        for (int i = 1; i < memo.length; i++) {
            if (memo[i] != 0) {
                continue;
            }

            List<Integer> temp = new ArrayList<>();

            if (i % 5 == 0) {
                temp.add(memo[i / 5] + 1);
            }

            if (i % 3 == 0) {
                temp.add(memo[i / 3] + 1);
            }

            if (i % 2 == 0) {
                temp.add(memo[i / 2] + 1);
            }

            temp.add(memo[i - 1] + 1);

            memo[i] = temp.stream()
                    .min(Integer::compare)
                    .get();
        }

        assertThat(memo[n]).isEqualTo(answer);
    }
}
