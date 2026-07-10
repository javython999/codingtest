package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SumOfNumbers {

    @Test
    void case1() {
        int n = 2;
        int[] input = {1, 1, 1, 1};
        int answer = 3;
        assertThat(solution(n, input)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int n = 5;
        int[] input = {1, 2, 3, 4, 2, 5, 3, 1, 1, 2};
        int answer = 3;
        assertThat(solution(n, input)).isEqualTo(answer);
    }

    @Test
    void case3() {
        int n = 5;
        int[] input = {5};
        int answer = 1;
        assertThat(solution(n, input)).isEqualTo(answer);
    }

    private int solution(int target, int[] input) {
        int count = 0;
        int start = 0;
        int end = 0;
        int sum = 0;

        while (true) {
            if (sum >= target) {
                if (sum == target) {
                    count += 1;
                }
                sum -= input[start];
                start += 1;

            } else {
                if (end == input.length) {
                    break;
                }
                sum += input[end];
                end += 1;
            }
        }

        return count;
    }
}
