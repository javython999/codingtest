package com.errday.codingtest.sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class Antenna {


    @Test
    void case1() {
        int[] input = {5, 1, 7, 9};
        int answer = 5;
        assertThat(answer).isEqualTo(solution(input));
    }

    @Test
    void case2() {
        int[] input = {1, 10, 32, 22, 6, 3, 8};
        int answer = 6;
        assertThat(answer).isEqualTo(solution(input));
    }

    private int solution(int[] input) {
        Arrays.sort(input);
        return input[(input.length - 1) / 2];
    }

}
