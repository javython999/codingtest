package com.errday.codingtest.dfs;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MakeIce {

    private int[][] graph = {
            {0,0,0,0,0,1,1,1,1,0,0,0,0,0},
            {1,1,1,1,1,1,0,1,1,1,1,1,1,0},
            {1,1,0,1,1,1,0,1,1,0,1,1,1,0},
            {1,1,0,1,1,1,0,1,1,0,0,0,0,0},
            {1,1,0,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,0,1,1,1,1,1,1,1,1,1,0,0},
            {1,1,0,0,0,0,0,0,0,1,1,1,1,1},
            {0,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {0,0,0,0,0,0,0,0,0,1,1,1,1,1},
            {0,1,1,1,1,1,1,1,1,1,1,0,0,0},
            {0,0,0,1,1,1,1,1,1,1,1,0,0,0},
            {0,0,0,0,0,0,0,1,1,1,1,0,0,0},
            {1,1,1,1,1,1,1,1,1,1,0,0,1,1},
            {1,1,1,0,0,0,1,1,1,1,1,1,1,1},
            {1,1,1,0,0,0,1,1,1,1,1,1,1,1},
    };

    private int n = graph.length;
    private int m = graph[0].length;

    private int answer = 8;


    @Test
    void solution() {
        int iceCount = 0;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {

                if (dfs(row, col)) {
                    iceCount += 1;
                }

            }
        }

        Assertions.assertThat(iceCount).isEqualTo(answer);
    }

    private boolean dfs(int x, int y) {

        if (x <= -1 || x >= n || y <= -1 || y >= m) {
            return false;
        }

        int currentNode = graph[x][y];

        if (currentNode == 0) {
            graph[x][y] = 1;

            dfs(x, y+1);
            dfs(x+1, y);
            dfs(x, y-1);
            dfs(x-1, y);

            return true;
        }

        return false;
    }
}
