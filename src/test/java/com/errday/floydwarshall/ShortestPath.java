package com.errday.floydwarshall;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class ShortestPath {

    private int n = 4;
    private int m = 7;

    private int[][] graph = {
            {1, 2, 4},
            {1, 4, 6},
            {2, 1, 3},
            {2, 3, 7},
            {3, 1, 5},
            {3, 4, 4},
            {4, 3, 2},
    };

    private int[][] answer = {
            {0, 4, 8, 6},
            {3, 0, 7, 9},
            {5, 9, 0, 4},
            {7, 11, 2, 0},
    };

    @Test
    void solution() {

        int[][] costTable = new int[4+1][4+1];
        for (int[] row : costTable) {
            Arrays.fill(row, Integer.MAX_VALUE/2);
        }

        for (int node = 1; node <= n; node++) {
            costTable[node][node] = 0;
        }

        for (int[] info : graph) {
            int start = info[0];
            int end = info[1];
            int cost = info[2];
            costTable[start][end] = cost;
        }

        for (int stopOver = 1; stopOver <= n; stopOver++) {
            for (int start = 1; start <= n; start++) {
                for (int end = 1; end <= n; end++) {
                    costTable[start][end] = Math.min(costTable[start][end], costTable[start][stopOver] + costTable[stopOver][end]);
                }
            }
        }

        int[][] temp = Arrays.copyOfRange(costTable, 1, costTable.length);
        int[][] myAnswer = new int[n][n];
        for (int i = 0; i < n; i++) {
            myAnswer[i] = Arrays.copyOfRange(temp[i], 1, temp[i].length);
        }

        Assertions.assertThat(myAnswer).isEqualTo(answer);
    }

}
