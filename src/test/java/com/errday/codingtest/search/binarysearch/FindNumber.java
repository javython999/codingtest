package com.errday.codingtest.search.binarysearch;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class FindNumber {

    @Test
    void case1() {
        int n = 5;
        int[] input1 = {4, 1, 5, 2, 3};
        int m = 5;
        int[] input2 = {1, 3, 7, 9, 5};
        int[] answer = {1, 1, 0, 0, 1};
        Assertions.assertThat(solution(n, input1, m, input2)).containsExactly(answer);
    }

    @Test
    void case2() {
        int n = 10;
        int[] input1 = {9, 5, 6, 4, 2, 30, 15, 45, 11, 22};
        int m = 5;
        int[] input2 = {1, 3, 5, 7, 9};
        int[] answer = {0, 0, 1, 0, 1};
        Assertions.assertThat(solution(n, input1, m, input2)).containsExactly(answer);
    }


    private int[] solution(int n, int[] input1, int m, int[] input2) {
        Arrays.sort(input1);

        int[] exists = new int[m];

        for (int i = 0; i < m; i++) {

            int searchTarget = input2[i];

            int left = 0;
            int right = n;

            while (left < right) {

                int mid = (left + right) / 2;

                int diff = input1[mid];

                if (diff < searchTarget) {
                    left = mid + 1;
                } else if (diff > searchTarget) {
                    right = mid;
                } else {
                    exists[i] = 1;
                    break;
                }
            }
        }
        // 이거 한글폰트 적용 안되는대 떄
        return exists;
    }
}
