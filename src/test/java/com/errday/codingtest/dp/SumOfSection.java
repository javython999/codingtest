package com.errday.codingtest.dp;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class SumOfSection {

    @Test
    void case1() {
        int[] numbers = {10, 20, 30, 40, 50};
        int[][] sections = {
                {1, 3},
                {2, 4},
                {3, 5},
                {1, 5},
                {4, 4}
        };
        int[] answers = {60, 90, 120, 150, 40};
        assertThat(solution(numbers, sections)).containsExactly(answers);
    }

    @Test
    void case2() {
        int[] numbers = {1, 0, -1};
        int[][] sections = {
                {1, 1},
                {2, 2},
                {3, 3},
                {1, 2},
                {2, 3},
                {1, 3},
        };
        int[] answers = {1, 0, -1, 1, -1, 0};
        assertThat(solution(numbers, sections)).containsExactly(answers);
    }

    private int[] solution(int[] numbers, int[][] sections) {

        int[] sums = new int[numbers.length + 1];
        sums[1] = numbers[0];

        for (int i = 2; i < numbers.length + 1; i++) {
            sums[i] = numbers[i - 1] + sums[i - 1];
        }

        int[] answers = new int[sections.length + 1];
        answers[0] = 0;
        for (int i = 1; i < sections.length + 1; i++) {
            int[] section = sections[i - 1];
            int start = section[0];
            int end = section[1];
            answers[i] = sums[end] - sums[start-1];
        }
        System.out.println(Arrays.toString(numbers));
        System.out.println(Arrays.toString(sums));
        System.out.println(Arrays.toString(answers));
        return Arrays.copyOfRange(answers, 1, answers.length);
    }
}
