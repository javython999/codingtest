package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Brackets {

    private char OPEN = '(';
    private char CLOSE = ')';

    @Test
    void case1() {
        String input = "(()())()";
        String answer = "(()())()";


        assertThat(solution(input)).isEqualTo(answer);
    }

    @Test
    void case2() {
        String input = ")(";
        String answer = "()";

        assertThat(solution(input)).isEqualTo(answer);
    }

    @Test
    void case3() {
        String input = "()))((()";
        String answer = "()(())()";

        assertThat(solution(input)).isEqualTo(answer);
    }

    @Test
    void case4() {
        String input = "";
        String answer = "";
        assertThat(solution(input)).isEqualTo(answer);
    }

    private String solution(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        int splitIndex = findBalancedIndex(input);
        String u = input.substring(0, splitIndex + 1);
        String v = input.substring(splitIndex + 1);

        if (isValid(u)) {
            return u + solution(v);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(OPEN)
                .append(solution(v))
                .append(CLOSE)
                .append(reverse(u.substring(1, u.length() - 1)));

        return sb.toString();
    }

    private int findBalancedIndex(String input) {
        int balance = 0;

        char[] charArray = input.toCharArray();
        for (int i = 0; i < charArray.length; i++) {

            balance += input.charAt(i) == OPEN ? 1 : -1;

            if (balance == 0) {
                return i;
            }
        }

        throw new IllegalArgumentException("Not balanced");
    }

    private boolean isValid(String input) {
        int count = 0;

        for (char c : input.toCharArray()) {

            if (c == OPEN) {
                count += 1;
            } else {
                if (count == 0) {
                    return false;
                }
                count -= 1;
            }
        }

        return count == 0;
    }

    private String reverse(String input) {
        StringBuilder sb = new StringBuilder();

        for (char c : input.toCharArray()) {
            sb.append(c == OPEN ? CLOSE : OPEN);
        }

        return sb.toString();
    }
}
