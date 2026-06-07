package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GroupWord {

    @Test
    void case1() {
        String[] words = {"happy", "new", "year"};
        int answer = 3;
        assertThat(solution(words)).isEqualTo(answer);
    }

    @Test
    void case2() {
        String[] words = {"aba", "abab", "abcabc", "a"};
        int answer = 1;
        assertThat(solution(words)).isEqualTo(answer);
    }

    @Test
    void case3() {
        String[] words = {"coding", "test", "compile", "class"};
        int answer = 3;
        assertThat(solution(words)).isEqualTo(answer);
    }

    private int solution(String[] words) {

        int answer = 0;

        for (String word : words) {
            char[] chars = word.toCharArray();
            boolean[] exists = new boolean[26];

            char before = '-';
            boolean isGroup = true;

            for (char c : chars) {
                if (before != c && exists[c - 'a']) {
                    isGroup = false;
                    break;
                }

                exists[c - 'a'] = true;
                before = c;
            }

            if (isGroup) {
                answer += 1;
            }
        }

        return answer;
    }
}
