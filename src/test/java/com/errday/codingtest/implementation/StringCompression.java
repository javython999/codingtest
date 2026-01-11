package com.errday.codingtest.implementation;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringCompression {

    @Test
    void case1() {
        String source = "aabbaccc";
        int answer = 7;
        assertThat(solution(source)).isEqualTo(answer);
    }

    @Test
    void case2() {
        String source = "ababcdcdababcdcd";
        int answer = 9;
        assertThat(solution(source)).isEqualTo(answer);
    }

    @Test
    void case3() {
        String source = "abcabcdede";
        int answer = 8;
        assertThat(solution(source)).isEqualTo(answer);
    }

    @Test
    void case4() {
        String source = "abcabcabcabcdededededede";
        int answer = 14;
        assertThat(solution(source)).isEqualTo(answer);
    }

    @Test
    void case5() {
        String source = "xababcdcdababcdcd";
        int answer = 17;
        assertThat(solution(source)).isEqualTo(answer);
    }


    private int solution(String source) {
        int result = source.length();

        for (int step = 1; step < source.length() / 2 + 1; step++) {

            String compressed = "";
            String pattern = source.substring(0, step);
            int count = 1;

            for (int j = step; j < source.length(); j += step) {
                String current = source.substring(j, Math.min(j + step, source.length()));

                if (pattern.equals(current)) {
                    count += 1;
                } else {

                    if (count > 1) {
                        compressed += count;
                    }
                    compressed += pattern;

                    pattern = current;
                    count = 1;
                }
            }

            if (count > 1) {
                compressed += count;
            }
            compressed += pattern;

            result = Math.min(result, compressed.length());
        }
        return result;
    }

}
