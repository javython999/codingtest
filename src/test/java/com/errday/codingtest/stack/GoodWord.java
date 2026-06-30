package com.errday.codingtest.stack;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;

import static org.assertj.core.api.Assertions.assertThat;

public class GoodWord {

    @Test
    void case1() {
        String[] input = {
                "ABAB",
                "AABB",
                "ABBA"
        };
        int answer = 2;
        assertThat(solution(input)).isEqualTo(answer);
    }

    @Test
    void case2() {
        String[] input = {
                "123321",
                "44544",
                "779977997799"
        };
        int answer = 2;
        assertThat(solution(input)).isEqualTo(answer);
    }

    private int solution(String[] input) {
        int answer = 0;
        ArrayDeque<Character> stack = new ArrayDeque<>();

        for (String word : input) {

            char[] chars = word.toCharArray();

            for (char c : chars) {
                if (stack.isEmpty()) {
                    stack.push(c);
                    continue;
                }

                if (stack.peek() == c) {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            }

            if (stack.isEmpty()) {
                answer += 1;
            }

            stack.clear();

        }
        return answer;
    }



}
