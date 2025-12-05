package com.errday.codingtest.greedy;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class NumberCardGame {

    private final int N = 3;
    private final int M = 3;
    private final int[][] DATA = {
            {3, 1, 2},
            {4, 1, 4},
            {2, 2, 2}
    };
    private final int ANSWER = 2;

    @Test
    void solution() {

        int[] minNumbers = new int[N];

        for (int i = 0; i < N; i++) {
            int[] row = DATA[i];

            int min = Arrays.stream(row)
                    .min()
                    .getAsInt();
            minNumbers[i] = min;
        }

        int answer = Arrays.stream(minNumbers)
                .max()
                .getAsInt();


        Assertions.assertThat(answer).isEqualTo(ANSWER);
    }
}
