package com.errday.codingtest.bruteforce;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class Strings {

    @Test
    void case1() {
        String a = "adaabc";
        String b = "aababbc";
        int answer = 2;
        assertThat(solution(a, b)).isEqualTo(answer);
    }

    @Test
    void case2() {
        String a = "hello";
        String b = "xello";
        int answer = 1;
        assertThat(solution(a, b)).isEqualTo(answer);
    }

    @Test
    void case3() {
        String a = "koder";
        String b = "topcoder";
        int answer = 1;
        assertThat(solution(a, b)).isEqualTo(answer);
    }

    @Test
    void case4() {
        String a = "abc";
        String b = "topabccoder";
        int answer = 0;
        assertThat(solution(a, b)).isEqualTo(answer);
    }

    private int solution(String a, String b) {
        char[] aChars = a.toCharArray();
        char[] bChars = b.toCharArray();

        int diffCount = Integer.MAX_VALUE;
        for (int i = 0; i <= bChars.length - aChars.length; i++) {
            int tempDiffCount = 0;
            for (int j = i; j < aChars.length+i; j++) {
                if (bChars[j] != aChars[j-i]) {
                    tempDiffCount += 1;
                }
            }
            diffCount = Math.min(diffCount, tempDiffCount);
        }

        return diffCount;
    }
}
