package com.errday.codingtest.dp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardPack {

    @Test
    void case1() {
        int count = 4;
        int[] cardPack = {1, 5, 6, 7};
        int answer = 10;
        assertThat(solution(count, cardPack)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int count = 5;
        int[] cardPack = {10, 9, 8, 7, 6};
        int answer = 50;
        assertThat(solution(count, cardPack)).isEqualTo(answer);
    }

    @Test
    void case3() {
        int count = 10;
        int[] cardPack = {1, 1, 2, 3, 5, 8, 13, 21, 34, 55};
        int answer = 55;
        assertThat(solution(count, cardPack)).isEqualTo(answer);
    }

    @Test
    void case4() {
        int count = 10;
        int[] cardPack = {5, 10, 11, 12, 13, 30, 35, 40, 45, 47};
        int answer = 50;
        assertThat(solution(count, cardPack)).isEqualTo(answer);
    }

    @Test
    void case5() {
        int count = 4;
        int[] cardPack = {5, 2, 8, 10};
        int answer = 20;
        assertThat(solution(count, cardPack)).isEqualTo(answer);
    }

    @Test
    void case6() {
        int count = 4;
        int[] cardPack = {3, 4, 15, 16};
        int answer = 18;
        assertThat(solution(count, cardPack)).isEqualTo(answer);
    }

    private int solution(int count, int[] cardPack) {

        int[] packCost = new int[count + 1];
        System.arraycopy(cardPack, 0, packCost, 1, cardPack.length);

        int[] memo = new int[count + 1];

        for (int cardCount = 1; cardCount <= count; cardCount++) {

            for (int pack = 1; pack <= cardCount; pack++) {
                memo[cardCount] = Math.max(memo[cardCount], memo[cardCount - pack] + packCost[pack]);
            }

        }

        return memo[count];
    }
}
