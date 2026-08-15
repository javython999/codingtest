package com.errday.codingtest.greedy;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SingleLine {

    @Test
    void case1() {
        int n = 4;
        int[] seq = {2, 1, 1, 0};
        int[] answer = {4, 2, 1, 3};
        assertThat(solution(n, seq)).containsExactly(answer);
    }

    @Test
    void case2() {
        int n = 5;
        int[] seq = {0, 0, 0, 0, 0};
        int[] answer = {1, 2, 3, 4, 5};
        assertThat(solution(n, seq)).containsExactly(answer);
    }

    @Test
    void case3() {
        int n = 6;
        int[] seq = {5, 4, 3, 2, 1, 0};
        int[] answer = {6, 5, 4, 3, 2, 1};
        assertThat(solution(n, seq)).containsExactly(answer);
    }

    private int[] solution(int n, int[] sequence) {
        int[] line = new int[n];

        for (int sequenceIndex = 0; sequenceIndex < n; sequenceIndex++) {
            int previousCount = sequence[sequenceIndex];
            int tallerCount = 0;

            for (int lineIndex = 0; lineIndex < n; lineIndex++) {

                if (line[lineIndex] == 0) {
                    tallerCount += 1;
                }

                if (previousCount < tallerCount) {
                    line[lineIndex] = sequenceIndex + 1;
                    break;
                }
            }
        }

        return line;
    }
}
