package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;

import static org.assertj.core.api.Assertions.assertThat;

public class Hamburger {

    @Test
    void case1() {
        int k = 1;
        String input = "HHPHPPHHPPHPPPHPHPHP";
        int answer = 8;
        assertThat(solution(input, k)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int k = 2;
        String input = "HHHHHPPPPPHPHPHPHHHP";
        int answer = 7;
        assertThat(solution(input, k)).isEqualTo(answer);
    }

    private int solution(String input, int k) {

        char[] chars = input.toCharArray();
        int count = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 'P') {
                for (int distance = i - k; distance <= i + k; distance++) {

                    if (isOverBoundary(distance, chars.length)) {
                        continue;
                    }

                    if (isHamburger(chars[distance])) {
                        count += 1;
                        chars[distance] = ' ';
                        break;
                    }
                }
            }
        }

        return count;
    }

    private boolean isOverBoundary(int distance, int length) {
        return distance < 0 || distance >= length;
    }

    private boolean isHamburger(char c) {
        return c == 'H';
    }
}
