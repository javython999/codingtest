package com.errday.codingtest.sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

public class NewRecruit {

    @Test
    void case1() {
        int[][] ranks = {
                {3, 2},
                {1, 4},
                {4, 1},
                {2, 3},
                {5, 5}
        };
        int answer = 4;
        assertThat(solution(ranks)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int[][] ranks = {
                {3, 6},
                {7, 3},
                {4, 2},
                {5, 7},
                {2, 5},
                {6, 1}
        };
        int answer = 3;
        assertThat(solution(ranks)).isEqualTo(answer);
    }

    @Test
    void case3() {
        int[][] ranks = {
                {3, 6}
        };
        int answer = 1;
        assertThat(solution(ranks)).isEqualTo(answer);
    }

    private int solution(int[][] ranks) {
        if (ranks.length == 1) {
            return 1;
        }

        Arrays.sort(ranks, Comparator.comparingInt(a -> a[0]));

        int answer = 1;
        int secondRank = ranks[0][1];

        for (int i = 1; i < ranks.length; i++) {
            int currentSecondRank = ranks[i][1];
            if (currentSecondRank <= secondRank) {
                secondRank = currentSecondRank;
                answer += 1;
            }
        }

        return answer;
    }
}
