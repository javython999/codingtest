package com.errday.codingtest.graph.dfs;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;

import static org.assertj.core.api.Assertions.assertThat;

public class OrganicCabbage {

    @Test
    void case1() {
        int col = 10;
        int row = 8;
        int[][] cabbages = {
                {0, 0},
                {0, 1},
                {1, 1},
                {2, 4},
                {3, 4},
                {5, 4},
                {4, 2},
                {4, 3},
                {4, 7},
                {4, 8},
                {4, 9},
                {5, 7},
                {5, 8},
                {5, 9},
                {6, 7},
                {6, 8},
                {6, 9}
        };
        int answer = 5;
        assertThat(solution(row, col, cabbages)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int m = 10;
        int n = 10;
        int[][] cabbages = {
                {5, 5}
        };
        int answer = 1;
        assertThat(solution(m, n, cabbages)).isEqualTo(answer);
    }

    private int solution(int rowSize, int colSize, int[][] cabbages) {
        int[][] field = new int[rowSize][colSize];
        for (int[] cabbage : cabbages) {
            field[cabbage[0]][cabbage[1]] = 1;
        }

        int[][] moves = {
                {-1, 0},
                {0, 1},
                {1, 0},
                {0, -1}
        };

        int answer = 0;
        ArrayDeque<int[]> stack = new ArrayDeque<>();

        for (int[] cabbage : cabbages) {
            int row = cabbage[0];
            int col = cabbage[1];


            if (field[row][col] == 0) {
                continue;
            }

            answer += 1;

            stack.push(new int[]{row, col});
            while (!stack.isEmpty()) {
                int[] current = stack.pop();

                for (int[] move : moves) {
                    int nextRow = current[0] + move[0];
                    int nextCol = current[1] + move[1];

                    if (nextRow < 0 || nextRow >= rowSize) {
                        continue;
                    }

                    if (nextCol < 0 || nextCol >= colSize) {
                        continue;
                    }

                    if (field[nextRow][nextCol] == 0) {
                        continue;
                    }
                    field[nextRow][nextCol] = 0;
                    stack.push(new int[]{nextRow, nextCol});

                }
            }


        }
        return answer;
    }

}
