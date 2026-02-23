package com.errday.codingtest.dp;

import java.util.*;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GoldMining {

    private int[][] memo;

    @Test
    void case1() {
        int[][] map = {
                {1, 3, 3, 2},
                {2, 1, 4, 1},
                {0, 6, 4, 7}
        };

        int answer = 19;

        assertThat(solution(map)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int[][] map = {
                {1, 3, 1, 5},
                {2, 1, 4, 1},
                {5, 0, 2, 3},
                {0, 6, 1, 2}
        };

        int answer = 16;

        assertThat(solution(map)).isEqualTo(answer);
    }


    private int solution(int[][] map) {
        memo = initMemo(map);
        dp(map);
        return getMaxGold();
    }

    private int[][] initMemo(int[][] map) {
        int rowSize = map.length;
        int colSize = map[0].length;

        int[][] memo = new int[rowSize][colSize];

        for (int row = 0; row < rowSize; row++) {
            memo[row][0] = map[row][0];
        }

        return  memo;
    }

    private void dp(int[][] map) {
        int rowSize = map.length;
        int colSize = map[0].length;

        int[] moveRow = {-1, 0, 1};
        int[] moveCol = {-1, -1, -1};


        for (int col = 1; col < colSize; col++) {

            for (int row = 0; row < rowSize; row++) {

                int currentCellGold = map[row][col];

                for (int moveType = 0; moveType < moveRow.length; moveType++) {

                    int prevRow = row + moveRow[moveType];
                    int prevCol = col + moveCol[moveType];

                    if (prevRow < 0 || prevRow >= rowSize) {
                        continue;
                    }

                    int prevGold = memo[prevRow][prevCol];
                    memo[row][col] = Math.max(memo[row][col], currentCellGold + prevGold);

                }

            }

        }

    }

    private int getMaxGold() {
        int maxGold = 0;

        for (int[] row : memo) {
            int finalGold = row[row.length - 1];
            maxGold = Math.max(maxGold, finalGold);
        }

        return  maxGold;
    }

}
