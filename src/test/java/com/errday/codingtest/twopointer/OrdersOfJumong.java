package com.errday.codingtest.twopointer;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class OrdersOfJumong {

    @Test
    void case1() {
        int n = 6;
        int m = 9;
        int[] input = {2, 7, 4, 1, 5, 3};
        int answer = 2;
        assertThat(solution(n, m, input)).isEqualTo(answer);
    }

    private int solution(int n, int m, int[] input) {

        Arrays.sort(input);

        int start = 0;
        int end = input.length - 1;
        int count = 0;



        while (start < end) {

            int sum = input[start] + input[end];

            if (sum == m) {
                count += 1;
                start += 1;
                end -= 1;
            } else {
                if (sum < m) {
                    start += 1;
                } else {
                    end -= 1;
                }
            }

        }

        return count;
    }
}
