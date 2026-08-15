package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Field {

    @Test
    void case1() {
        int n = 7;
        int[][] input = {
                {4, 50},
                {2, 160},
                {3, 30},
                {1, 60},
                {3, 20},
                {1, 100}
        };
        int answer = 47600;
        assertThat(solution(n, input)).isEqualTo(answer);
    }

    private int EAST = 1;
    private int WEST = 2;
    private int SOUTH = 3;
    private int NORTH = 4;
    private int solution(int n, int[][] input) {
        int maxWidth = 0;
        int maxHeight = 0;

        int maxWidthIndex = 0;
        int maxHeightIndex = 0;

        for (int index = 0; index < input.length; index++) {
            int direction = input[index][0];
            int length = input[index][1];

            if (direction == EAST || direction == WEST) {
                if (maxWidth < length) {
                    maxWidth = length;
                    maxWidthIndex = index;
                }
                continue;
            }

            if (direction == SOUTH || direction == NORTH) {
                if (maxHeight < length) {
                    maxHeight = length;
                    maxHeightIndex = index;
                }
                continue;
            }
        }

        int minX = input[maxWidthIndex + 3][1];
        int minY = input[maxHeightIndex + 3][1];

        int squareSize = maxWidth * maxHeight;
        int smallSquareSize = minX * minY;
        int fieldSize = squareSize - smallSquareSize;

        return fieldSize * n;
    }
}
