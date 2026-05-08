package com.errday.codingtest.twopointer;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class ConsecutiveNumber {

    @Test
    void case1() {
        int n = 15;
        int answer = 4;
        assertThat(solution(n)).isEqualTo(answer);
    }

    private int solution(int n) {

        int count = 1;
        int sum = 1;
        int start = 1;
        int end = 1;

        while (end != n) {

            if (sum == n) {
                count += 1;
                end += 1;
                sum += end;
            } else if (sum > n) {
                sum -= start;
                start += 1;
            } else {
                end += 1;
                sum += end;
            }

        }


        return count;
    }
}
