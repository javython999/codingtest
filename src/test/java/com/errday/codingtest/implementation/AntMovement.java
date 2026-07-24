package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AntMovement {

    @Test
    void case1() {
        int width = 6;
        int height = 4;
        int[] startPosition = {4, 1};
        int t = 8;
        int[] answer = {0, 1};
        assertThat(solution(width, height, startPosition, t)).containsExactly(answer);
    }

    private int[] solution(int width, int height, int[] startPosition, int time) {

        int x = startPosition[0] + time;
        int y = startPosition[1] + time;

        x = x % (width * 2);
        y = y % (height * 2);

        if (x > width) {
            x = x - width;
            x = width - x;
        }

        if (y > height) {
            y = y - height;
            y = height - y;
        }

        return new int[] {x, y};
    }
}
