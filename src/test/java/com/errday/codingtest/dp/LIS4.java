package com.errday.codingtest.dp;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LIS4 {

    @Test
    void case1() {
        int n = 6;
        int[] seq = {10, 20, 10, 30, 20, 50};

        int length = 4;
        int[] answer = {10, 20, 30, 50};

        int[] solve = solution(n, seq);

        assertThat(solve).hasSize(length);
        assertThat(solve).containsExactly(answer);
    }

    private int[] solution(int n, int[] seq) {

        int[] memo = new int[n];
        Arrays.fill(memo, 1);

        int[] prev = new int[n];
        Arrays.fill(prev, -1);

        for (int i = 1; i < n; i++) {
            for (int previous = 0; previous < i; previous++) {

                if (seq[previous] < seq[i]) {
                    if (memo[i] < memo[previous] + 1) {
                        memo[i] = memo[previous] + 1;
                        prev[i] = previous;
                    }
                }
            }
        }

        int index = 0;
        for (int i = 1; i < n; i++) {
            if (memo[i] > memo[index]) {
                index = i;
            }
        }

        List<Integer> path = new ArrayList<>();
        while (index != -1) {
            path.add(seq[index]);
            index = prev[index];
        }

        Collections.reverse(path);

        return path.stream().mapToInt(Integer::intValue).toArray();
    }
}
