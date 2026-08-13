package com.errday.codingtest.backtracking;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MaxDiff {

    @Test
    void case1() {
        int n = 6;
        int[] numbers = new int[] {20, 1, 15, 8, 4, 10};
        int answer = 62;
        assertThat(solution(n, numbers)).isEqualTo(answer);
    }

    private int DEPTH;
    private int SUM = 0;
    private int[] NUMBERS;
    private boolean[] VISITED;
    private int solution(int depth, int[] numbers) {
        DEPTH = depth;
        VISITED = new boolean[depth];
        NUMBERS = numbers;
        backtracking(0, 0, 0);

        return SUM;
    }

    private void backtracking(int depth, int sum, int previous) {
        if (depth == DEPTH) {
            SUM = Math.max(sum, SUM);
            return;
        }

        for (int i = 0; i < DEPTH; i++) {
            if (VISITED[i]) {
                continue;
            }

            VISITED[i] = true;

            int current = NUMBERS[i];

            if (depth == 0) {
                backtracking(depth + 1, sum, current);
            } else {
                backtracking(depth + 1, sum + Math.abs(previous - current), current);
            }

            VISITED[i] = false;
        }
    }


}
