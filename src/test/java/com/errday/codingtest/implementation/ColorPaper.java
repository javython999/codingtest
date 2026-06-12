package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ColorPaper {

    @Test
    void case1() {
        int[][] papers = {
                {3, 7},
                {15, 7},
                {5, 2}
        };

        int answer = 260;
        assertThat(solution(papers)).isEqualTo(answer);
    }

    private int solution(int[][] papers) {

        int[][] canvas = new int[100 + 1][100 + 1];


        for (int[] paper : papers) {

            int positionX = paper[0];
            int positionY = paper[1];

            for (int col = positionX; col < positionX + 10; col++) {
                for (int row = positionY; row < positionY + 10; row++) {
                    canvas[row][col] = 1;
                }
            }
        }

        int answer = 0;
        for (int row = 1; row <= 100; row++) {
            for (int col = 1; col <= 100; col++) {
                answer += canvas[row][col];
            }
        }

        return answer;
    }

}
