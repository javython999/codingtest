package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class SwitchOnOff {

    @Test
    void case1() {
        int[] switches =  {0, 1, 0, 1, 0, 0, 0, 1};
        int[][] input = {{1, 3}, {2, 3}};
        int[] answer = {1, 0, 0, 0, 1, 1, 0, 1};
        assertThat(solution(switches, input)).containsExactly(answer);
    }

    private int[] solution(int[] switches, int[][] input) {
        int[] status = new int[switches.length + 1];
        System.arraycopy(switches, 0, status, 1, switches.length);
        for (int[] info : input) {
            int gender = info[0];
            int index = info[1];

            if (gender == 1) {
                for (int i = index; i < switches.length; i += index) {
                    status[i] = (status[i] + 1) % 2;
                }

            } else {
                int start = index;
                int end = index;

                while (0 < start && end <= switches.length) {
                    if (status[start] == status[end]) {
                        start -= 1;
                        end += 1;
                    } else {
                        break;
                    }
                }

                for (int j = start; j < end; j++) {
                    status[j] = (status[j] + 1) % 2;
                }
            }
        }

        return Arrays.copyOfRange(status, 1, status.length);
    }
}
