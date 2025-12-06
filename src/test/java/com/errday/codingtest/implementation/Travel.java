package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Travel {

    private int n = 5;
    private String[] plan = {"R", "R", "R", "U", "D", "D"};
    private String[] answer = {"3", "4"};

    @Test
    void solution() {

        int x = 1;
        int y = 1;
        int[] directionX = {0, 1, 0, -1};
        int[] directionY = {-1, 0, 1, 0};
        List<String> moveType = List.of("U", "R", "D", "L");

        for (String move : plan) {

            int moveTypeIndex = moveType.indexOf(move);

            int nextMoveX = directionX[moveTypeIndex];
            if (x + nextMoveX > 1) {
                x += directionX[moveTypeIndex];
            }

            int nextMoveY = directionY[moveTypeIndex];
            if (y + nextMoveY > 1) {
                y += directionY[moveTypeIndex];
            }
        }

        String[] result = {String.valueOf(y), String.valueOf(x)};
        assertThat(result).isEqualTo(answer);
    }
}
