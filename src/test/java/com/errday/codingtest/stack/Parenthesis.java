package com.errday.codingtest.stack;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;

import static org.assertj.core.api.Assertions.assertThat;

public class Parenthesis {

    @Test
    void case1() {
        String n = "(())())";
        String answer = "NO";
        assertThat(solution(n)).isEqualTo(answer);
        assertThat(solution2(n)).isEqualTo(answer);
    }

    @Test
    void case2() {
        String n = "(((()())()";
        String answer = "NO";
        assertThat(solution(n)).isEqualTo(answer);
        assertThat(solution2(n)).isEqualTo(answer);
    }

    @Test
    void case3() {
        String n = "(()())((()))";
        String answer = "YES";
        assertThat(solution(n)).isEqualTo(answer);
        assertThat(solution2(n)).isEqualTo(answer);
    }

    @Test
    void case4() {
        String n = "((()()(()))(((())))()";
        String answer = "NO";
        assertThat(solution(n)).isEqualTo(answer);
        assertThat(solution2(n)).isEqualTo(answer);
    }

    @Test
    void case5() {
        String n = "()()()()(()()())()";
        String answer = "YES";
        assertThat(solution(n)).isEqualTo(answer);
        assertThat(solution2(n)).isEqualTo(answer);
    }

    @Test
    void case6() {
        String n = "(()((())()(";
        String answer = "NO";
        assertThat(solution(n)).isEqualTo(answer);
        assertThat(solution2(n)).isEqualTo(answer);
    }

    private String solution(String n) {
        String[] split = n.split("");

        ArrayDeque<String> stack = new ArrayDeque<>();
        for (int i = 0; i < split.length; i++) {
            if ("(".equals(split[i])) {
                stack.push(split[i]);
                continue;
            }

            if (")".equals(split[i])) {
                if (stack.isEmpty()) {
                    return "NO";
                }
                stack.pop();
            }
        }

        return stack.isEmpty() ? "YES" : "NO";
    }

    private String solution2(String n) {
        int count = 0;

        for (char c : n.toCharArray()) {
            if ('(' == c) {
                count += 1;
            } else {
                count -= 1;
            }

            if (count < 0) {
                return "NO";
            }
        }

        return count == 0 ? "YES" : "NO";
    }

}

