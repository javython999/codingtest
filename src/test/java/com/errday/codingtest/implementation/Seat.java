package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Seat {

    @Test
    void case1() {
        int c = 7;
        int r = 6;
        int k = 11;
        int[] answer = {6, 6};
        assertThat(solution(c, r, k)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int c = 7;
        int r = 6;
        int k = 32;
        int[] answer = {6, 3};
        assertThat(solution(c, r, k)).isEqualTo(answer);
    }

    private int[] solution(int col, int row, int k) {

        int x = 1;
        int y = 1;

        while ( (col + row) * 2 - 4 < k)  {
            x += 1;
            y += 1;

            k -= (col + row) * 2 - 4;
            col -= 2;
            row -= 2;
        }

        if (k <= row) {
            y += k - 1;
        } else if (k <= row + col - 1) {
            y += row - 1;
            k -= row;
            x += k;
        } else if (k<= row * 2 + col -2) {
            x += col -1;
            k -= row+col-1;
            y += row - 1 - k;
        } else {
            k -= row*2 + col - 2;
            x += col - 1 - k;
        }

        return new int[] {x, y};
    }
}
