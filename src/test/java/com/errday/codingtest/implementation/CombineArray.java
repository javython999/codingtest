package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class CombineArray {

    @Test
    void case1() {
        int[] array1 = {3, 5};
        int[] array2 = {2, 9};
        int[] answer = {2, 3, 5, 9};
        assertThat(solution(array1, array2)).containsExactly(answer);
    }

    @Test
    void case2() {
        int[] array1 = {4, 7};
        int[] array2 = {1};
        int[] answer = {1, 4, 7};
        assertThat(solution(array1, array2)).containsExactly(answer);
    }


    @Test
    void case3() {
        int[] array1 = {2, 3, 5, 9};
        int[] array2 = {1, 4, 7};
        int[] answer = {1, 2, 3, 4, 5, 7, 9};
        assertThat(solution2(array1, array2)).containsExactly(answer);
    }

    private int[] solution(int[] array1, int[] array2) {
        int array1length = array1.length;
        int array2length = array2.length;
        int n = array1length + array2length;
        int[] result = new int[n];

        int index = 0;
        int array1Index = 0;
        int array2Index = 0;

        while (array1Index < array1length && array2Index < array2length) {

            int x = array1[array1Index];
            int y = array2[array2Index];

            if (x <= y) {
                result[index++] = x;
                array1Index++;
            } else {
                result[index++] = y;
                array2Index++;
            }
        }

        for (int i = array1Index; i < array1length; i++) {
            result[index++] = array1[i];
        }

        for (int i = array2Index; i < array2length; i++) {
            result[index++] = array2[i];
        }

        return result;
    }

    private int[] solution2(int[] array1, int[] array2) {
        int n = array1.length + array2.length;
        int[] result = new int[n];
        System.arraycopy(array1, 0, result, 0, array1.length);

        for (int i = array1.length; i < n; i++) {
            result[i] = array2[i - array1.length];
        }

        Arrays.sort(result);
        return result;
    }

}
