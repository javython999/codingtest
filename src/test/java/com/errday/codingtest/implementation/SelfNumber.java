package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SelfNumber {

    @Test
    void case1() {
        int answer = 983;
        assertThat(solution()).isEqualTo(answer);
    }

    private int solution() {

        boolean[] checks = new boolean[10000 + 1];

        for (int i = 1; i < 10000; i++) {
            int sum = i;
            char[] temp = String.valueOf(i).toCharArray();

            for (char c : temp) {
                sum += Integer.parseInt(String.valueOf(c));
            }

            if (sum < 10000 + 1) {
                checks[sum] = true;
            }
        }

        int answer = 0;

        for (int i = 1; i < 10000 + 1; i++) {

            if (checks[i]) {
                continue;
            }
            answer += 1;
        }

        return answer;
    }
}
