package com.errday.codingtest.parametericsearch;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class Budget {

    @Test
    void case1() {
        int[] budgets = new int[] {120, 110, 140, 150};
        int total = 485;
        int answer = 127;
        assertThat(solution(budgets, total)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int[] budgets = new int[] {70, 80, 30, 40, 100};
        int total = 450;
        int answer = 100;
        assertThat(solution(budgets, total)).isEqualTo(answer);
    }

    @Test
    void case3() {
        int[] budgets = new int[] {250, 250, 250, 200};
        int total = 800;
        int answer = 200;
        assertThat(solution(budgets, total)).isEqualTo(answer);
    }

    @Test
    void case4() {
        int[] budgets = new int[] {10, 20, 100, 60};
        int total = 210;
        int answer = 100;
        assertThat(solution(budgets, total)).isEqualTo(answer);
    }

    private int solution(int[] budgets, int total) {
        Arrays.sort(budgets);

        int low = 0;
        int high = budgets[budgets.length - 1];

        while (low < high) {
            int mid = low + (high - low + 1) / 2;

            if (isPossible(budgets, total, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    private boolean isPossible(int[] budgets, int total, int candidate) {
        long sum = 0;

        for (int b : budgets) {
            sum += Math.min(b, candidate);
        }

        return sum <= total;
    }
}
