package com.errday.codingtest.backtracking;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class Nqueen {

    @Test
    void case1() {
        int n = 8;
        int answer = 92;
        Assertions.assertThat(solution(n)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int n = 8;
        int answer = 92;
        Assertions.assertThat(solution(n)).isEqualTo(answer);
    }

    private int[] board;
    private int N;
    private int count = 0;

    private int solution(int n) {
        N = n;
        board = new int[N];

        backtracking(0);

        return count;
    }

    private void backtracking(int row) {

        if (row == N) {
            count += 1;
            return;
        }

        for (int i = 0; i < N; i++) {

            board[row] = i;

            if (check(row)) {
                row += 1;
                backtracking(row);
                row -= 1;
            }
        }
    }

    private boolean check(int row) {

        for (int i = 0; i < row; i++) {
            if (board[i] == board[row]) return false;
            if (Math.abs(board[i] - board[row]) == Math.abs(row - i)) return false;
        }

        return true;
    }
}
