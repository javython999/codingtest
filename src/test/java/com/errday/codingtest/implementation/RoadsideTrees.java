package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class RoadsideTrees {

    @Test
    void case1() {
        int[] input = {1, 3, 7, 13};
        int answer = 3;
        assertThat(solution(input)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int[] input = {2, 6, 12, 18};
        int answer = 5;
        assertThat(solution(input)).isEqualTo(answer);
    }

    private int solution(int[] input) {
        int[] space = new int[input.length - 1];
        for (int i = 0; i < space.length; i++) {
            space[i] = input[i + 1] - input[i];
        }
        Arrays.sort(space);

        int gap = gcd(space[0], space[1]);
        for (int i = 2; i < space.length; i++) {
            gap = gcd(gap, space[i]);
        }

        int plantCount = 0;
        for (int s : space) {
            plantCount += (s / gap) - 1;
        }

        return plantCount;
    }

    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
