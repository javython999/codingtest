package com.errday.codingtest.greedy;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class Bowling {


    @Test
    void case1() {
        int[] balls = {1, 3, 2, 3, 2};
        int answer = 8;
        assertThat(solution2(balls)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int[] balls = {1, 5, 4, 3, 2, 4, 5, 2};
        int answer = 25;
        assertThat(solution2(balls)).isEqualTo(answer);
    }

    private int solution(int[] balls) {

        int count = 0;

        for (int i = 0; i < balls.length - 1; i++) {
            for (int j = i + 1; j < balls.length; j++) {

                int playerA = balls[i];
                int playerB = balls[j];

                if (playerA != playerB) {
                    count += 1;
                }
            }
        }

        return count;
    }

    private int solution2(int[] balls) {
        int count = 0;
        int n = balls.length;
        int m = 10;

        int[] countByBall = new int[m + 1];
        for (int ball : balls) {
            countByBall[ball]++;
        }

        for (int i = 1; i < countByBall.length; i++) {
            n -= countByBall[i];
            count += countByBall[i] * n;
        }

        return count;
    }
}
