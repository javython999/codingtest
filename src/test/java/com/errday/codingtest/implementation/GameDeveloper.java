package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameDeveloper {

    @Test
    void case1() {
        int[] levels = {5, 5, 5};
        int answer = 3;
        assertThat(solution(levels)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int[] levels = {5, 3, 7, 5};
        int answer = 6;
        assertThat(solution(levels)).isEqualTo(answer);
    }

    private int solution(int[] levels) {

        int updateAmount = 0;
        for (int i = levels.length - 2; i >= 0; i--) {
            int now = levels[i];
            int next = levels[i + 1];

            if (next <= now) {
                int gap = next - now - 1;
                levels[i] += gap;
                updateAmount += Math.abs(gap);
            }

        }
        return updateAmount;
    }
}
