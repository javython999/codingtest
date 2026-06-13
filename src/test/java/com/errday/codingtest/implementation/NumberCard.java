package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class NumberCard {

    @Test
    void case1() {
        int[] cards = {6, 3, 2, 10, -10};
        int[] find = {10, 9, -5, 2, 3, 4, 5, -10};
        int[] answer = {1, 0, 0, 1, 1, 0, 0, 1};
        assertThat(solution(cards, find)).containsExactly(answer);
    }

    @Test
    void performanceTest() {

        int size = 500_000;

        int[] cards = new int[size];
        int[] find = new int[size];

        for (int i = 0; i < size; i++) {
            cards[i] = i;
            find[i] = i;
        }

        long start = System.currentTimeMillis();
        solution(cards, find);
        System.out.println("elapsed = " + (System.currentTimeMillis() - start) + " ms");
    }

    private int[] solution(int[] cards, int[] find) {
        Arrays.sort(cards);
        int[] result = new int[find.length];

       for (int i = 0; i < find.length; i++) {
           int findValue = find[i];
           if (Arrays.binarySearch(cards, findValue) > -1) {
               result[i] += 1;
           }
       }

        return result;
    }
}
