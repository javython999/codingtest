package com.errday.codingtest.greedy;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class CannotBeMade {

    @Test
    void case1() {
        int[] coins = {3, 2, 1, 1, 9};
        int answer = 8;

        assertThat(solution(coins)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int[] coins = {3, 5, 7};
        int answer = 1;
        assertThat(solution(coins)).isEqualTo(answer);
    }

    private int solution(int[] coins) {

        int[] sorted = Arrays.copyOf(coins, coins.length);
        Arrays.sort(sorted);

        int target = 1;

        for (int coin : sorted) {

            if (target < coin) {
                return target;
            } else {
                target += coin;
            }
        }

        return target;
    }
}
