package com.errday.codingtest.slidingwindow;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DnaPassword {

    @Test
    void case1() {
        String dnaString = "CCTGGATTG";
        int length = 8;
        int[] condition = {2, 0, 1, 1};
        int answer = 0;
        assertThat(solution(dnaString, length, condition)).isEqualTo(answer);
    }

    @Test
    void case2() {
        String dnaString = "GATA";
        int length = 2;
        int[] condition = {1, 0, 0, 1};
        int answer = 2;
        assertThat(solution(dnaString, length, condition)).isEqualTo(answer);
    }

    private int solution(String dnaString, int length, int[] condition) {
        char[] origin = dnaString.toCharArray();

        int[] charCount = new int[4];

        for (int i = 0; i < length; i++) {
            char c = origin[i];

            if (c == 'A') {
                charCount[0] += 1;
                continue;
            }

            if (c == 'C') {
                charCount[1] += 1;
                continue;
            }

            if (c == 'G') {
                charCount[2] += 1;
                continue;
            }

            if (c == 'T') {
                charCount[3] += 1;
                continue;
            }
        }

        int result = 0;
        if (checkCondition(condition, charCount)) {
            result += 1;
        }


        int start = 0;
        int end = length;

        while (end < origin.length) {
            char outChar = origin[start];

            if (outChar == 'A') {
                charCount[0] -= 1;
            }

            if (outChar == 'C') {
                charCount[1] -= 1;
            }

            if (outChar == 'G') {
                charCount[2] -= 1;
            }

            if (outChar == 'T') {
                charCount[3] -= 1;
            }

            char inChar = origin[end];

            if (inChar == 'A') {
                charCount[0] += 1;
            }

            if (inChar == 'C') {
                charCount[1] += 1;
            }

            if (inChar == 'G') {
                charCount[2] += 1;
            }

            if (inChar == 'T') {
                charCount[3] += 1;
            }

            if (checkCondition(condition, charCount)) {
                result += 1;
            }

            start += 1;
            end += 1;
        }


        return result;
    }

    private boolean checkCondition(int[] condition, int[] charCount) {
        for (int i = 0; i < condition.length; i++) {
            if (charCount[i] < condition[i]) {
                return false;
            }
        }
        return true;
    }
}
