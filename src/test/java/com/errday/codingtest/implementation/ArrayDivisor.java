package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class ArrayDivisor {

    @Test
    void case1() {
        int[] array = {5, 9, 7, 10};
        int divisor = 5;

        int[] answer = {5, 10};

        assertThat(solution(array, divisor)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int[] array = {2, 36, 1, 3};
        int divisor = 1;

        int[] answer = {1, 2, 3, 36};

        assertThat(solution(array, divisor)).isEqualTo(answer);
    }

    @Test
    void case3() {
        int[] array = {3, 2, 6};
        int divisor = 10;

        int[] answer = {-1};

        assertThat(solution(array, divisor)).isEqualTo(answer);
    }

    private int[] solution(int[] array, int divisor) {
        int[] result = Arrays.stream(array)
                .filter(n -> n % divisor == 0)
                .sorted()
                .toArray();

        if (result.length == 0) {
            return new int[] {-1};
        }

        return result;
    }
}
