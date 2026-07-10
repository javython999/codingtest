package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class SolvedAC {

    @Test
    void case1() {
        int[] opinions = {1, 5, 5, 7, 8};
        int answer = 6;
        assertThat(solution(opinions)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int[] opinions = {1, 7, 6, 6, 5, 7, 8, 10};
        int answer = 7;
        assertThat(solution(opinions)).isEqualTo(answer);
    }

    private int solution(int[] opinions) {
        Arrays.sort(opinions);

        int length = opinions.length;

        int n = (int) Math.round(length * 0.15);

        for (int i = 0; i < n; i++) {
            opinions[i] = 0;
        }

        for (int i = length - 1; i >= length - n; i--) {
            opinions[i] = 0;
        }

        int count = 0;
        int sum = 0;
        for (int opinion : opinions) {
            if (opinion != 0) {
                count += 1;
                sum += opinion;
            }
        }

        return Math.round((float) sum / count);
    }
}
