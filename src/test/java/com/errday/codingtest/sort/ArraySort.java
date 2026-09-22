package com.errday.codingtest.sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class ArraySort {

    @Test
    void case1() {
        int[] array = {1, -5, 2, 4, 3};
        int[] answer = {-5, 1, 2, 3, 4};
        assertThat(solution(array)).containsExactly(answer);
    }

    @Test
    void case2() {
        int[] array = {2, 1, 1, 3, 2, 5, 4};
        int[] answer = {1, 1, 2, 2, 3, 4, 5};
        assertThat(solution(array)).containsExactly(answer);
    }

    @Test
    void case3() {
        int[] array = {6, 1, 7};
        int[] answer = {1, 6, 7};
        assertThat(solution(array)).containsExactly(answer);
    }

    private int[] solution(int[] array) {
        int[] answer = array.clone();
        Arrays.sort(answer);
        return answer;
    }
}
