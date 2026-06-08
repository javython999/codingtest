package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CroatianAlphabet {

    @Test
    void case1() {
        String word = "ljes=njak";
        int answer = 6;
        assertThat(solution(word)).isEqualTo(answer);
    }

    @Test
    void case2() {
        String word = "ddz=z=";
        int answer = 3;
        assertThat(solution(word)).isEqualTo(answer);
    }

    @Test
    void case3() {
        String word = "nljj";
        int answer = 3;
        assertThat(solution(word)).isEqualTo(answer);
    }

    @Test
    void case4() {
        String word = "c=c=";
        int answer = 2;
        assertThat(solution(word)).isEqualTo(answer);
    }

    @Test
    void case5() {
        String word = "dz=ak";
        int answer = 3;
        assertThat(solution(word)).isEqualTo(answer);
    }

    @Test
    void case6() {
        String word = "dz=s=s=c=c-";
        int answer = 5;
        assertThat(solution(word)).isEqualTo(answer);
    }
    private int solution(String word) {
        String[] croatianAlphabet = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};

        for (String croatian : croatianAlphabet) {
            if (word.contains(croatian)) {
                word = word.replaceAll(croatian, "*");
            }
        }

        return word.length();
    }
}
