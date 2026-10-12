package com.errday.codingtest.stack;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;

import static org.assertj.core.api.Assertions.assertThat;

public class Crane {
    @Test
    void case1() {
        int[][] board = {
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 3},
                {0, 2, 5, 0, 1},
                {4, 2, 4, 4, 2},
                {3, 5, 1, 3, 1}
        };
        int[] moves = {1, 5, 3, 5, 1, 2, 1, 4};
        int answer = 4;

        assertThat(solution(board, moves)).isEqualTo(answer);
    }

    private int solution(int[][] board, int[] moves) {
        int rowSize = board.length;
        int colSize = board[0].length;

        ArrayDeque<Integer>[] machine = new ArrayDeque[board.length];
        for (int i = 0; i < rowSize; i++) {
            machine[i] = new ArrayDeque<>();
        }

        for (int[] rowData : board) {
            for (int col = 0; col < colSize; col++) {
                if (rowData[col] != 0) {
                    machine[col].addLast(rowData[col]);
                }
            }
        }

        ArrayDeque<Integer> basket = new ArrayDeque<>();
        int answer = 0;
        for (int move : moves) {
            int index = move - 1;

            if (machine[index].isEmpty()) {
                continue;
            }

            int doll = machine[index].pop();

            if (basket.isEmpty() || basket.peek() != doll) {
                basket.push(doll);
                continue;
            }

            answer += 2;
            basket.pop();
        }

        return answer;
    }
}
