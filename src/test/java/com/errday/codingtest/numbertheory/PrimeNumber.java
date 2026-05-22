package com.errday.codingtest.numbertheory;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class PrimeNumber {

    @Test
    void case1() {
        int n = 3;
        int m = 16;

        int[] answer = {3, 5, 7, 11, 13};

        assertThat(solution(n, m)).containsExactly(answer);
    }

    @Test
    void case2() {
        int n = 50;
        int m = 100;

        int[] answer = {53, 59, 61, 67, 71, 73, 79, 83, 89, 97};

        assertThat(solution(n, m)).containsExactly(answer);
    }

    private int[] solution(int n, int m) {
        int[] numbers = new int[m + 1];
        for (int i = 1; i < m + 1; i++) {
            numbers[i] = i;
        }

        numbers[1] = 0;

        for (int current = 2; current < Math.sqrt(m) + 1; current++) {

            if (numbers[current] == 0) {
                continue;
            }

            for (int next = current + current; next < m + 1; next += current) {
                numbers[next] = 0;
            }

        }

        return Arrays.stream(numbers)
                .filter(i -> i >= n)
                .toArray();
    }
}
