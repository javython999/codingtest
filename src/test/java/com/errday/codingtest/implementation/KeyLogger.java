package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;

import static org.assertj.core.api.Assertions.assertThat;

public class KeyLogger {

    @Test
    void case1() {
        assertThat(solution("<<BP<A>>Cd-")).isEqualTo("BAPC");
    }

    @Test
    void case2() {
        assertThat(solution("ThIsIsS3Cr3t")).isEqualTo("ThIsIsS3Cr3t");
    }

    @Test
    void case3() {
        assertThat(solution("<<>>A<<-B->BC")).isEqualTo("ABC");
    }

    private char LEFT = '<';
    private char RIGHT = '>';
    private char BACK_SPACE = '-';

    private String solution(String input) {
        ArrayDeque<Character> leftBuffer = new ArrayDeque<>();
        ArrayDeque<Character> rightBuffer = new ArrayDeque<>();

        char[] chars = input.toCharArray();

        for (char c : chars) {
            if (c == LEFT) {
                if(!leftBuffer.isEmpty()) {
                    rightBuffer.addFirst(leftBuffer.pollLast());
                }
                continue;
            }

            if (c == RIGHT) {
                if (!rightBuffer.isEmpty()) {
                    leftBuffer.addLast(rightBuffer.pollFirst());
                }
                continue;
            }

            if (c == BACK_SPACE) {
                if (!leftBuffer.isEmpty()) {
                    leftBuffer.pollLast();
                }
                continue;
            }

            leftBuffer.addLast(c);
        }

        StringBuilder sb = new StringBuilder(leftBuffer.size() + rightBuffer.size());

        while (!leftBuffer.isEmpty()) {
            sb.append(leftBuffer.poll());
        }

        while (!rightBuffer.isEmpty()) {
            sb.append(rightBuffer.poll());
        }

        return sb.toString();
    }
}
