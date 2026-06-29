package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GCD {

    @Test
    void case1() {
        int n = 4;
        int[] numbers = {10, 20, 30, 40};
        int answer = 70;
        assertThat(solution(n, numbers)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int n = 3;
        int[] numbers = {3, 7, 5, 12};
        int answer = 3;
        assertThat(solution(n, numbers)).isEqualTo(answer);
    }

    @Test
    void case3() {
        int n = 3;
        int[] numbers = {125, 15, 25};
        int answer = 35;
        assertThat(solution(n, numbers)).isEqualTo(answer);
    }

    private int solution(int n, int[] numbers) {
        int sum = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                sum += gcd(Math.max(numbers[i], numbers[j]), Math.min(numbers[i], numbers[j]));
            }
        }

        return sum;
    }

    private int gcd(int max, int min) {
        if (min == 0) {
            return max;
        }

        return gcd(min, max % min);
    }



}
