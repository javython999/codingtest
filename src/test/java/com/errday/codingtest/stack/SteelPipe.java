package com.errday.codingtest.stack;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;

import static org.assertj.core.api.Assertions.assertThat;

public class SteelPipe {

    @Test
    void case1() {
        String input = "()(((()())(())()))(())";
        int answer = 17;
        assertThat(solution(input)).isEqualTo(answer);
    }

    private int solution(String input) {
        char[] chars = input.toCharArray();

        ArrayDeque<Character> stack = new ArrayDeque<>();

        int answer = 0;
        for (char c : chars) {
            if (c == '(') {
                stack.push(c);
                continue;
            }

            if (c == ')') {
                if (!stack.isEmpty()) {
                    stack.pop();
                    answer += stack.size();
                }
            }
        }

        return answer;
    }
}
