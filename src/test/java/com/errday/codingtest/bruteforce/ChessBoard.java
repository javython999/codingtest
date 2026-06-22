package com.errday.codingtest.bruteforce;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ChessBoard {

    @Test
    void case1() {
        String[][] board = {
                {"W", "B", "W", "B", "W", "B", "W", "B"},
                {"B", "W", "B", "W", "B", "W", "B", "W"},
                {"W", "B", "W", "B", "W", "B", "W", "B"},
                {"B", "W", "B", "B", "B", "W", "B", "W"},
                {"W", "B", "W", "B", "W", "B", "W", "B"},
                {"B", "W", "B", "W", "B", "W", "B", "W"},
                {"W", "B", "W", "B", "W", "B", "W", "B"},
                {"B", "W", "B", "W", "B", "W", "B", "W"}
        };
        int answer = 1;
        assertThat(solution(board)).isEqualTo(answer);
    }

    @Test
    void case2() {
        String[][] board = {
                {"B", "B", "B", "B", "B", "B", "B", "B", "W", "B", "W", "B", "W"},
                {"B", "B", "B", "B", "B", "B", "B", "B", "B", "W", "B", "W", "B"},
                {"B", "B", "B", "B", "B", "B", "B", "B", "W", "B", "W", "B", "W"},
                {"B", "B", "B", "B", "B", "B", "B", "B", "B", "W", "B", "W", "B"},
                {"B", "B", "B", "B", "B", "B", "B", "B", "W", "B", "W", "B", "W"},
                {"B", "B", "B", "B", "B", "B", "B", "B", "B", "W", "B", "W", "B"},
                {"B", "B", "B", "B", "B", "B", "B", "B", "W", "B", "W", "B", "W"},
                {"B", "B", "B", "B", "B", "B", "B", "B", "B", "W", "B", "W", "B"},
                {"W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "B", "W", "B"},
                {"W", "W", "W", "W", "W", "W", "W", "W", "W", "W", "B", "W", "B"}
        };
        int answer = 12;
        assertThat(solution(board)).isEqualTo(answer);
    }

    private int solution(String[][] board) {

        int maxRow = board.length;
        int maxCol = board[0].length;
        int answer = Integer.MAX_VALUE;

        for (int row = 0; row <= maxRow - 8; row++) {
            for (int col = 0; col <= maxCol - 8; col++) {

                int wCount = 0;

                for (int i = row; i < row + 8; i++) {
                    for (int j = col; j < col + 8; j++) {
                        int position = i + j;
                        if (position % 2 == 1 && "W".equals(board[i][j])) {
                            wCount += 1;
                            continue;
                        }

                        if (position % 2 == 0 && "B".equals(board[i][j])) {
                            wCount += 1;
                        }

                    }
                }

                answer = Math.min(answer, Math.min(wCount, 64 - wCount));
            }
        }

        return answer;
    }
}

