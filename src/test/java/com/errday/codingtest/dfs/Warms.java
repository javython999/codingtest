package com.errday.codingtest.dfs;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class Warms {

    private int[][] graph = {
            {1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 1, 1, 0, 0, 0, 1, 1, 1},
            {0, 0, 0, 0, 1, 0, 0, 1, 1, 1},
    };

    private int answer = 5;

    @Test
    void solution() {
        int myAnswer = 0;
        int rowSize = graph.length;
        int colSize = graph[0].length;

        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {

                if (dfs(row, col)) {
                    myAnswer += 1;
                }
            }
        }

        Assertions.assertThat(myAnswer).isEqualTo(answer);
    }

    private boolean dfs(int row, int col)  {

        if (row < 0 || row >= graph.length || col < 0 || col >= graph[0].length) {
            return false;
        }

        int currentPosition = graph[row][col];

        if (currentPosition == 0) {
            return false;
        }

        graph[row][col] = 0;

        dfs(row - 1, col);
        dfs(row, col + 1);
        dfs(row + 1, col);
        dfs(row, col -1);

        return true;
    }
}
