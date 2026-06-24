package com.errday.codingtest.stack;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;

import static org.assertj.core.api.Assertions.assertThat;

public class Balance {

    @Test
    void case1() {
        String input = "so when I die (the [first] I will seen in (heaven) is a score list).";
        String answer = "yes";
        assertThat(solution(input)).isEqualTo(answer);
    }

    @Test
    void case2() {
        String input = "[ first in ] ( first out ).";
        String answer = "yes";
        assertThat(solution(input)).isEqualTo(answer);
    }

    @Test
    void case3() {
        String input = "Half Moon tonight (At least it is better than no Moon at all].";
        String answer = "no";
        assertThat(solution(input)).isEqualTo(answer);
    }

    @Test
    void case4() {
        String input = "A rope may form ) ( a trail in a maze.";
        String answer = "no";
        assertThat(solution(input)).isEqualTo(answer);
    }

    private String solution(String input) {
        ArrayDeque<Character> stack1 = new ArrayDeque<>();
        ArrayDeque<Character> stack2 = new ArrayDeque<>();

        for (char c : input.toCharArray()) {
            if (c == '(') {
                stack1.push(c);
                continue;
            }

            if (c == ')') {
                if (stack1.isEmpty()) {
                    return "no";
                }
                stack1.pop();
                continue;
            }

            if (c == '[') {
                stack2.push(c);
                continue;
            }

            if (c == ']') {
                if (stack2.isEmpty()) {
                    return "no";
                }
                stack2.pop();
            }
        }

        return "yes";
    }
}
