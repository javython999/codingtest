package com.errday.codingtest.stack;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;

public class CorrectBracket {
    @Test
    void case1() {
        String input = "()()";

        boolean answer = true;

        Assertions.assertThat(solution(input)).isEqualTo(answer);
    }

    @Test
    void case2() {
        String input = "(())()";

        boolean answer = true;

        Assertions.assertThat(solution(input)).isEqualTo(answer);
    }

    @Test
    void case3() {
        String input = ")()(";

        boolean answer = false;

        Assertions.assertThat(solution(input)).isEqualTo(answer);
    }

    @Test
    void case4() {
        String input = "(()(";

        boolean answer = false;

        Assertions.assertThat(solution(input)).isEqualTo(answer);
    }

    private boolean solution(String input) {
        if (input.startsWith(")")) {
            return false;
        }

        ArrayDeque<Character> stack = new ArrayDeque<>();
        char[] chars = input.toCharArray();

        for (char c : chars) {
            if ('(' == c) {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }

                stack.pop();
            }
        }

        return stack.isEmpty();
    }
}
