package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MakeColorPaper {

    @Test
    void case1() {
        int n = 8;
        int[][] paper = {
                {1, 1, 0, 0, 0, 0, 1, 1},
                {1, 1, 0, 0, 0, 0, 1, 1},
                {0, 0, 0, 0, 1, 1, 0, 0},
                {0, 0, 0, 0, 1, 1, 0, 0},
                {1, 0, 0, 0, 1, 1, 1, 1},
                {0, 1, 0, 0, 1, 1, 1, 1},
                {0, 0, 1, 1, 1, 1, 1, 1},
                {0, 0, 1, 1, 1, 1, 1, 1}
        };
        int[] answer = {9, 7};
        assertThat(solution(n, paper)).containsExactly(answer);
    }


    private int whitePaper = 0;
    private int bluePaper = 0;
    private int[][] _paper;
    private int[] solution(int n, int[][] paper) {
        _paper = paper;
        colorCheck(0, 0, n);
        return new int[] {whitePaper, bluePaper};
    }

    private void colorCheck(int row, int col, int size) {
        int standardColor = _paper[row][col];

        if (isSameColor(row, col, size)) {
            if (standardColor == 0) {
                whitePaper++;
            } else {
                bluePaper++;
            }
            return;
        }

        int newSize = size / 2;
        colorCheck(row, col, newSize);
        colorCheck(row + newSize, col, newSize);
        colorCheck(row, col + newSize, newSize);
        colorCheck(row + newSize, col+newSize, newSize);
    }

    private boolean isSameColor(int row, int col, int size) {
        int standardColor = _paper[row][col];

        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                if (_paper[i][j] != standardColor) {
                    return false;
                }
            }
        }

        return true;
    }



}
