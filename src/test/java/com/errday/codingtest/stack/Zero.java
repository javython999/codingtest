package com.errday.codingtest.stack;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;

import static org.assertj.core.api.Assertions.assertThat;

public class Zero {

    @Test
    void case1() {
        int[] input = {3, 0, 4, 0};
        int answer = 0;
        assertThat(solution(input)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int[] input = {1, 3, 5, 4, 0, 0, 7, 0, 0, 6};
        int answer = 7;
        assertThat(solution(input)).isEqualTo(answer);
    }

    @Test
    void case3() {
        int[] input = {10, 5, 0, 7, 8, 100, 95, 0, 0, 15, 32, 0};
        int answer = 40;
        assertThat(solution(input)).isEqualTo(answer);
    }

    private int solution(int[] input) {

        ArrayDeque<Integer> stack = new ArrayDeque<>();

        for (int number : input) {

            if (number == 0) {
                stack.pop();
                continue;
            }

            stack.push(number);
        }

        return stack.stream()
                .mapToInt(Integer::valueOf)
                .sum();
    }
}
