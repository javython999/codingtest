package com.errday.codingtest.dp;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class Statistics {

    @Test
    void case1() {
        int[] input = {1, 3, 8, -2, 2};
        int[] answer = {2, 2, 1, 10};
        Assertions.assertThat(solution(input)).containsExactly(answer);
    }

    @Test
    void case2() {
        int[] input = {4000};
        int[] answer = {4000, 4000, 4000, 0};
        Assertions.assertThat(solution(input)).containsExactly(answer);
    }

    @Test
    void case3() {
        int[] input = {-1, -2, -3, -1, -2};
        int[] answer = {-2, -2, -1, 2};
        Assertions.assertThat(solution(input)).containsExactly(answer);
    }

    private int[] solution(int[] input) {
        int[] answer = new int[4];
        answer[0] = (int) Math.round(Arrays.stream(input).summaryStatistics().getAverage());


        Arrays.sort(input);
        int length = input.length;
        if (length % 2 == 0) {
            answer[1] = (input[length / 2] + input[(length / 2) + 1]) / 2;
        } else {
            answer[1] = input[length / 2];
        }

        int maxValue = Arrays.stream(input)
                .max()
                .getAsInt();

        int minValue = Arrays.stream(input)
                .min()
                .getAsInt();

        int[] counts = new int[4000 + 4000 + 1];
        for (int num : input) {
            counts[4000 + num] += 1;
        }

        int modeCount = 0;
        for (int count : counts) {
            if (count > modeCount) {
                modeCount = count;
            }
        }

        int modeDuplCount = 0;
        for (int count : counts) {
            if (count == modeCount) {
                modeDuplCount += 1;
            }
        }


        boolean findFirstMode = false;
        for (int i = 0; i <= counts.length; i++) {
            if (counts[i] == modeCount) {
                if (modeDuplCount == 1) {
                    answer[2] = i - 4000;
                    break;
                }

                if (!findFirstMode) {
                    findFirstMode = true;
                    answer[2] = i - 4000;
                } else {
                    answer[2] = i - 4000;
                    break;
                }

            }
        }

        answer[3] = maxValue - minValue;

        return answer;
    }
}
