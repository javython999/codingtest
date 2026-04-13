package com.errday.codingtest.dp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LIS2 {

    @Test
    void case1() {
        int n = 6;
        int[] seq = {10, 20, 10, 30, 20, 50};
        int answer = 4;
        assertThat(solution(n, seq)).isEqualTo(answer);
    }

    private int solution(int n, int[] seq) {

        int[] list = new int[n];
        list[0] = seq[0];
        int maxLength = 1;

        for (int i = 1; i < n; i++) {

            int current = seq[i];

            if (list[maxLength - 1] < current) {
                list[maxLength] = current;
                maxLength += 1;
                continue;
            }

            int low = 0;
            int high = maxLength - 1;

            while (low < high) {
                int mid = (low + high) / 2;

                if (list[mid] < current) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }

            list[low] = current;
        }

        return maxLength;
    }
}
