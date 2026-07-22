package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class KoreaServer {

    @Test
    void case1() {
        String pattern = "a*d";
        String[] inputs = {"abcd", "anestonestod", "facebook"};
        String[] answer = {"DA", "DA", "NE"};
        assertThat(solution(pattern, inputs)).containsExactly(answer);
    }

    @Test
    void case2() {
        String pattern = "h*n";
        String[] inputs = {"huhovdjestvarnomozedocisvastan", "honijezakon", "atila", "je", "bio", "hun"};
        String[] answer = {"DA", "DA", "NE", "NE", "NE", "DA"};
        assertThat(solution(pattern, inputs)).containsExactly(answer);
    }

    private String[] solution(String pattern, String[] inputs) {
        String[] split = pattern.split("[*]");
        String[] answers = new String[inputs.length];

        for (int i = 0; i < inputs.length; i++) {
            String input = inputs[i];

            boolean startWiths = input.startsWith(split[0]);
            boolean endWiths = input.endsWith(split[1]);

            if (startWiths && endWiths) {
                answers[i] = "DA";
            } else {
                answers[i] = "NE";
            }

        }
        return answers;
    }

}
