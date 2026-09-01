package com.errday.codingtest.twopointer;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class NoDuplicaties {

    @Test
    void case1() {
        int n = 9;
        int k = 2 ;
        int[] numbers = {3, 2, 5, 5, 6, 4, 4, 5, 7};
        int answer = 7;
        Assertions.assertThat(solution(n, k, numbers)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int n = 10;
        int k = 1 ;
        int[] numbers = {1, 2, 3, 4, 5, 6, 6, 7, 8, 9};
        int answer = 6;
        Assertions.assertThat(solution(n, k, numbers)).isEqualTo(answer);
    }

    private int solution(int n, int k, int[] numbers) {

        int[] countOfNumbers = new int[100_000 + 1];

        int left = 0;
        int maxLength = 0;

        for (int right = 0; right < n; right++) {
            int currentValue = numbers[right];

            countOfNumbers[currentValue] += 1;

            while (countOfNumbers[currentValue] > k) {
                countOfNumbers[numbers[left]] -= 1;
                left += 1;
            }


            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}
