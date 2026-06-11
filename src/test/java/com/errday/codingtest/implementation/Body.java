package com.errday.codingtest.implementation;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class Body {

    @Test
    void case1() {
        int[][] input = {
                {55, 185},
                {58, 183},
                {88, 186},
                {60, 175},
                {46, 155}
        };
        int[] answer = {2, 2, 1, 2, 5};
        Assertions.assertThat(solution(input)).containsExactly(answer);
    }

    private int[] solution(int[][] input) {

        int[] ranks = new int[input.length];

        for (int i = 0; i < input.length; i++) {
            int rank = 1;
            int[] current = input[i];
            int weight = current[0];
            int height = current[1];

            for (int j = 0; j < input.length; j++) {
                if (i == j) {
                    continue;
                }

                int otherWeight = input[j][0];
                int otherHeight = input[j][1];

                if (weight < otherWeight && height < otherHeight) {
                    rank += 1;
                }
            }

            ranks[i] = rank;
        }

        return ranks;
    }
}
