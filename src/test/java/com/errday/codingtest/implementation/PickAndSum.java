package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class PickAndSum {

    @Test
    void case1() {
        int[] numbers = {2, 1, 3, 4, 1};
        int[] answer = {2, 3, 4, 5, 6, 7};
        assertThat(solution(numbers)).containsExactly(answer);
    }

    @Test
    void case2() {
        int[] numbers = {5, 0, 2, 7};
        int[] answer = {2, 5, 7, 9, 12};
        assertThat(solution(numbers)).containsExactly(answer);
    }

    private int[] solution(int[] numbers) {
        Set<Integer> set = new HashSet<>();

        for (int xIndex = 0; xIndex < numbers.length - 1; xIndex++) {
            int x = numbers[xIndex];

            for (int yIndex = 1 + xIndex; yIndex < numbers.length; yIndex++) {
                int y = numbers[yIndex];
                set.add(x + y);
            }
        }

        return set.stream()
                .mapToInt(Integer::intValue)
                .sorted()
                .toArray();
    }
}
