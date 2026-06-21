package com.errday.codingtest.bruteforce;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HanNumber {

    @Test
    void case1() {
        int n = 110;
        int answer = 99;
        assertThat(solution(n)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int n = 1;
        int answer = 1;
        assertThat(solution(n)).isEqualTo(answer);
    }

    @Test
    void case3() {
        int n = 210;
        int answer = 105;
        assertThat(solution(n)).isEqualTo(answer);
    }

    @Test
    void case4() {
        int n = 1000;
        int answer = 144;
        assertThat(solution(n)).isEqualTo(answer);
    }

    private int solution(int n) {

        if (1 <= n && n <= 99) {
            return n;
        }

        int count = 99;

        for (int i = 100; i <= n; i++) {

            char[] chars = String.valueOf(i).toCharArray();
            int diff = chars[0] - chars[1];
            boolean isHanNumber = true;
            for (int j = 1; j < chars.length - 1; j++) {
                if (chars[j] - chars[j + 1] != diff) {
                    isHanNumber = false;
                    break;
                }
            }

            if (isHanNumber) {
                count += 1;
            }

        }

        return count;
    }
}
