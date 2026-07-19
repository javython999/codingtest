package com.errday.codingtest.twopointer;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class SumOfNumber {

    @Test
    void case1() {
        int[] numbers = {5, 12, 7, 10, 9, 1, 2, 3, 11};
        int n = 13;
        int answer = 3;
        Assertions.assertThat(solution(numbers, n)).isEqualTo(answer);
    }

    private int solution(int[] numbers, int n) {
        Arrays.sort(numbers);

        int count = 0;
        int start = 0;
        int end = numbers.length - 1;

        while (start < end) {

            int sum = numbers[start] + numbers[end];

            if (sum == n) {
                start += 1;
                end -= 1;
                count += 1;
                continue;
            }

            if (sum < n) {
                start += 1;
            } else {
                end -= 1;
            }


        }

        return count;
    }
}
