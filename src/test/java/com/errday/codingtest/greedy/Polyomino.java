package com.errday.codingtest.greedy;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Polyomino {

    @Test
    void case1() {
        String input = "XXXXXX";
        String answer = "AAAABB";
        assertThat(solution(input)).isEqualTo(answer);
    }

    @Test
    void case2() {
        String input = "XX.XX";
        String answer = "BB.BB";
        assertThat(solution(input)).isEqualTo(answer);
    }

    @Test
    void case3() {
        String input = "XXXX....XXX.....XX";
        String answer = "-1";
        assertThat(solution(input)).isEqualTo(answer);
    }

    @Test
    void case4() {
        String input = "X";
        String answer = "-1";
        assertThat(solution(input)).isEqualTo(answer);
    }

    @Test
    void case5() {
        String input = "XX.XXXXXXXXXX..XXXXXXXX...XXXXXX";
        String answer = "BB.AAAAAAAABB..AAAAAAAA...AAAABB";
        assertThat(solution(input)).isEqualTo(answer);
    }

    private String solution(String input) {

        String answer = input.replace("XXXX", "AAAA")
                .replace("XX", "BB");

        if (answer.contains("X")) {
            return "-1";
        }

        return answer;
    }
}
