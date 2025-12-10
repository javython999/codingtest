package com.errday.codingtest.dp;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class MakeNumber1 {

    private int n = 10;
    private int answer = 4;

    @Test
    void solution() {

        int[] memo  = new int[n + 1];

        for (int i = 1; i < memo.length; i++) {
            List<Integer> temp = new ArrayList<>();

            if (i % 3 == 0) {
                temp.add(memo[i / 3] + 1);
            }

            if (i % 2 == 0) {
                temp.add(memo[i / 2] + 1);
            }

            temp.add(memo[i - 1] + 1);

            memo[i] = temp.stream()
                            .min(Integer::compare)
                            .get();

        }

        Assertions.assertThat(memo[n]).isEqualTo(answer);

    }
}
