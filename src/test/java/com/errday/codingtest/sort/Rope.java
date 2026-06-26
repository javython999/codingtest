package com.errday.codingtest.sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class Rope {

    @Test
    void case1() {
        int[] ropes = {10, 15};
        int answer = 20;
        assertThat(solution(ropes)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int[] ropes = {50, 24, 23};
        int answer = 69;
        assertThat(solution(ropes)).isEqualTo(answer);
    }

    @Test
    void case3() {
        int[] ropes = {1, 2, 3, 4};
        int answer = 6;
        assertThat(solution(ropes)).isEqualTo(answer);
    }

    private int solution(int[] ropes) {
        Arrays.sort(ropes);
        int n = ropes.length;
        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, ropes[i] * (n - i));
        }
        return max;
    }
}
