package com.errday.codingtest.implementation;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class Knight {

    private String start = "a1";
    private int answer = 2;

    @Test
    void solution() {

        int[][] steps = {
                {1, 2}, {-1, 2},
                {2, 1}, {2, -1},
                {1, -2}, {-1, -2},
                {-2, 1}, {-2, -1}
        };

        String columnName = "_abcdefg";

        String[] split = start.split("");
        split[0] = String.valueOf(columnName.indexOf(split[0]));

        int[] position = Arrays.stream(split)
                .mapToInt(Integer::parseInt)
                .toArray();


        int result = 0;
        for (int[] step : steps) {

            int x = position[0] + step[0];
            int y = position[1] + step[1];

            if (x >= 1 && x <= 8 && y >= 1 && y <= 8) {
                result += 1;
            }

        }

        Assertions.assertThat(result).isEqualTo(answer);
    }
}
