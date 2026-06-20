package com.errday.codingtest.sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

public class NumberSort4 {

    @Test
    void case1() {
        int[] numbers = {1, 2, 3, 4, 5};
        int[] answer = {5, 4, 3, 2, 1};
        assertThat(solution(numbers)).containsExactly(answer);
    }

    @Test
    void case2() {
        int[] numbers = {1, 2, 3, 4, 5};
        int[] answer = {5, 4, 3, 2, 1};
        assertThat(streamSolution(numbers)).containsExactly(answer);
    }

    private int[] solution(int[] numbers) {

        Arrays.sort(numbers);

        int[] reverse = new int[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            reverse[i] = numbers[numbers.length - 1 - i];
        }

        return reverse;
    }

    private int[] streamSolution(int[] numbers) {
        return Arrays.stream(numbers)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .mapToInt(Integer::intValue)
                .toArray();
    }
}
