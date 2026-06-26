package com.errday.codingtest.sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class Treasure {

    @Test
    void case1() {
        int[] a = {1, 1, 1, 6, 0};
        int[] b = {2, 7, 8, 3, 1};
        int answer = 18;
        assertThat(solution(a, b)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int[] a = {1, 1, 3};
        int[] b = {10, 30, 20};
        int answer = 80;
        assertThat(solution(a, b)).isEqualTo(answer);
    }

    @Test
    void case3() {
        int[] a = {5, 15, 100, 31, 39, 0, 0, 3, 26};
        int[] b = {11, 12, 13, 2, 3, 4, 5, 9, 1};
        int answer = 528;
        assertThat(solution(a, b)).isEqualTo(answer);
    }

    private int solution(int[] arrayA, int[] arrayB) {
        int n = arrayA.length;

        Arrays.sort(arrayA);
        Arrays.sort(arrayB);

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += (arrayA[i] * arrayB[n - 1 - i]);
        }

        return sum;
    }
}
