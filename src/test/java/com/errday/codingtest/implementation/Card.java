package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class Card {

    @Test
    void case1() {
        long[] cards = {1, 2, 1, 2, 1};
        long answer = 1;
        assertThat(solution(cards)).isEqualTo(answer);
    }

    @Test
    void case2() {
        long[] cards = {1, 2, 1, 2, 1, 2};
        long answer = 1;
        assertThat(solution(cards)).isEqualTo(answer);
    }

    @Test
    void case3() {
        long[] cards = {4, 4, 4, 4, 1, 2, 2, 3, 3, 3};
        long answer = 4;
        assertThat(solution(cards)).isEqualTo(answer);
    }

    private long solution(long[] cards) {
        Arrays.sort(cards);

        int maxCount = 1;
        int tempCount = 1;
        long maxValue = cards[0];

        for (int i = 1; i < cards.length; i++) {
            if (cards[i] == cards[i - 1]) {
                tempCount += 1;
            } else {
                tempCount = 1;
            }

            if (maxCount < tempCount) {
                maxCount = tempCount;
                maxValue = cards[i];
            }
        }

        return maxValue;
    }
}
