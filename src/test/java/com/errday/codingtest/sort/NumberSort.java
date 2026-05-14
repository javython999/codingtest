package com.errday.codingtest.sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class NumberSort {

    @Test
    void case1() {
        int[] array = {5, 2, 3, 4, 1};
        int[] answer = {1, 2, 3, 4, 5};
        assertThat(javaSort(array)).containsExactly(answer);
    }

    @Test
    void case2() {
        int[] array = {5, 2, 3, 4, 1};
        int[] answer = {1, 2, 3, 4, 5};
        assertThat(bubbleSort(array)).containsExactly(answer);
    }

    @Test
    void case3() {
        int[] array = {5, 2, 3, 4, 1};
        int[] answer = {1, 2, 3, 4, 5};
        assertThat(selectionSort(array)).containsExactly(answer);
    }

    private int[] javaSort(int[] array) {
        Arrays.sort(array);
        return array;
    }

    private int[] bubbleSort(int[] array) {
        for (int offset = 0; offset < array.length - 1; offset++) {

            for (int pointer = 0; pointer < array.length - 1 - offset; pointer++) {

                int current = array[pointer];
                int next = array[pointer + 1];

                if (current > next) {
                    array[pointer] = next;
                    array[pointer+1] = current;
                }

            }

        }
        return array;
    }


    private int[] selectionSort(int[] array) {

        for (int i = 0; i < array.length - 1; i++) {

            int minIndex = i;

            for (int next = i + 1; next < array.length; next++) {
                if (array[next] < array[minIndex]) {
                    minIndex = next;
                }
            }

            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }

        return array;
    }

}

