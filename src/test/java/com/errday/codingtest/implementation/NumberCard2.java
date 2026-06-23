package com.errday.codingtest.implementation;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class NumberCard2 {

    @Test
    void case1() {
        int[] cards = {6, 3, 2, 10, 10, 10, -10, -10, 7, 3};
        int[] find = {10, 9, -5, 2, 3, 4, 5, -10};
        int[] answer = {3, 0, 0, 1, 2, 0, 0, 2};

        Assertions.assertThat(solution(cards, find)).containsExactly(answer);
    }

    private int[] solution(int[] cards, int[] find) {
        int[] answer = new int[find.length];

        Map<Integer, Integer> counts = new HashMap<>();

        for (Integer card : cards) {
            counts.merge(card, 1, Integer::sum);
        }

        for (int findIndex = 0; findIndex < find.length; findIndex++) {
            Integer findNumber = find[findIndex];
            answer[findIndex] = counts.getOrDefault(findNumber, 0);
        }

        return answer;
    }

    private int[] solution2(int[] cards, int[] find) {
        int[] answer = new int[find.length];

        int[] counts = new int[20000000 + 1];

        for (int card : cards) {
            counts[card + 10000000] += 1;
        }

        for (int index = 0; index < find.length; index++) {
            int findNumber = find[index];
            answer[index] = counts[findNumber + 10000000];
        }

        return answer;
    }

}
