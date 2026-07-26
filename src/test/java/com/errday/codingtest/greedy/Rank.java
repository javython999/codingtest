package com.errday.codingtest.greedy;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class Rank {

    @Test
    void case1() {
        long[] expected = {1, 5, 3, 1, 2};
        long answer = 3;
        assertThat(solution(expected)).isEqualTo(answer);
    }

    private long solution(long[] expected) {
        Arrays.sort(expected);

        long answer = 0;
        for (int i = 0; i < expected.length; i++) {
            answer += Math.abs((i+1) - expected[i]);
        }
        return answer;
    }
}
