package com.errday.codingtest.sort;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class ArraySortAndDistinct {
    @Test
    void case1() {
        int[] array = {4, 2, 2, 1, 3, 4};
        int[] answer = {4, 3, 2, 1};
        assertThat(solution(array)).containsExactly(answer);
    }

    @Test
    void case2() {
        int[] array = {2, 1, 1, 3, 2, 5, 4};
        int[] answer = {5, 4, 3, 2, 1};
        assertThat(solution(array)).containsExactly(answer);
    }

    @Test
    void case3() {
        int[] array = {1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 5};
        int[] answer = {5, 4, 3, 2, 1};
        assertThat(solution(array)).containsExactly(answer);
    }

    private int[] solution(int[] array) {
        Integer[] result = Arrays.stream(array)
                .distinct()
                .boxed()
                .toArray(Integer[]::new);

        Arrays.sort(result, Comparator.reverseOrder());

        return Arrays.stream(result)
                .mapToInt(Integer::valueOf)
                .toArray();
    }


}
