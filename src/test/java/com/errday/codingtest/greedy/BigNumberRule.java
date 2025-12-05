package com.errday.codingtest.greedy;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class BigNumberRule {

    private final int N = 5;
    private final int M = 8;
    private final int K = 3;
    private final int[] DATA = {2, 4, 5, 4, 6};
    private final int ANSWER = 46;

    @Test
    void solution1() {

        Arrays.sort(DATA);
        System.out.println(Arrays.toString(DATA));

        int result = 0;
        int first = DATA[N - 1];
        int second = DATA[N - 2];
        int maxNumberPlusCount = 0;

        for (int i = 0; i < M; i++) {

            if (maxNumberPlusCount < K) {
                result += first;
                maxNumberPlusCount += 1;
                continue;
            }

            result += second;
            maxNumberPlusCount = 0;
        }


        Assertions.assertThat(result).isEqualTo(ANSWER);
    }

    @Test
    void solution2() {

        Arrays.sort(DATA);

        int first = DATA[N - 1];
        int second = DATA[N - 2];

        int loopCount = (M / (K + 1)) * K;
        loopCount += M % (K + 1);
        int result = 0;

        result += first * loopCount;
        result += second * (M - loopCount);

        Assertions.assertThat(result).isEqualTo(ANSWER);
    }

}
