package com.errday.codingtest.dp;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MakeIt1Ver2 {

    @Test
    void case1() {
        int n = 2;
        int answer = 1;
        int[] path = {2, 1};

        int[][] solve = solution(n);

        assertThat(solve[0][0]).isEqualTo(answer);
        assertThat(solve[1]).containsExactly(path);
    }

    @Test
    void case2() {
        int n = 10;
        int answer = 3;
        int[] path = {10, 9, 3, 1};

        int[][] solve = solution(n);

        assertThat(solve[0][0]).isEqualTo(answer);
        assertThat(solve[1]).containsExactly(path);
    }

    private int[][] solution(int n) {

        int boundary = 1_000_000 + 1;

        int[] memo = new int[boundary];
        Arrays.fill(memo, Integer.MAX_VALUE);
        memo[1] = 0;
        memo[2] = 1;
        memo[3] = 1;

        int[] path = new int[boundary];
        path[1] = 1;
        path[2] = 1;
        path[3] = 1;


        for (int i = 4; i <= n; i++) {

            memo[i] = memo[i - 1] + 1;
            path[i] = i - 1;

            if (i % 2 == 0) {
                if (memo[i / 2] + 1 < memo[i]) {
                    memo[i] = memo[i / 2] + 1;
                    path[i] = i / 2;
                }
            }

            if (i % 3 == 0) {
                if (memo[i / 3] + 1 < memo[i]) {
                    memo[i] = memo[i / 3] + 1;
                    path[i] = i / 3;
                }
            }
        }

        int current = n;
        List<Integer> pathList = new ArrayList<>();
        while (true) {
            pathList.add(current);
            if (current == 1) {
                break;
            }
            current = path[current];
        }

        int[] array = pathList.stream()
                .mapToInt(Integer::intValue)
                .toArray();

        return new int[][] {{memo[n]}, array};
    }
}
