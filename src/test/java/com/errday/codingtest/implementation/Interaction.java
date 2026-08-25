package com.errday.codingtest.implementation;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class Interaction {

    @Test
    void case1() {
        String input = "seungjaehwang";
        String[][] query = {
                {"a", "0", "5"},
                {"a", "0", "6"},
                {"a", "6", "10"},
                {"a", "7", "10"}
        };
        int[] answer = {0, 1, 2, 1};
        Assertions.assertThat(solution(input, query)).containsExactly(answer);
    }

    private int[] solution(String input, String[][] query) {
        int length = input.length();
        int[][] counts = new int[length][26];
        char[] chars = input.toCharArray();

        for (int charIndex = 0; charIndex < length; charIndex++) {

            if (charIndex > 0) {
                counts[charIndex] = Arrays.copyOf(counts[charIndex - 1], 26);
            }

            int alphabetIndex = chars[charIndex] - 'a';
            counts[charIndex][alphabetIndex]++;
        }


        int[] answer = new int[query.length];

        for (int i = 0; i < query.length; i++) {
            String[] q = query[i];
            int alphabetIndex = q[0].charAt(0) - 'a';
            int left = Integer.parseInt(q[1]);
            int right = Integer.parseInt(q[2]);

            if (left == 0) {
                answer[i] = counts[right][alphabetIndex];
            } else {
                answer[i] = counts[right][alphabetIndex] - counts[left - 1][alphabetIndex];
            }
        }

        return answer;
    }
}
