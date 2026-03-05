package com.errday.codingtest.unionfind;

import org.junit.jupiter.api.Test;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class TourPlan {

    @Test
    void case1() {
        int n = 5;
        int m = 4;
        int[][] input = {
                {0, 1, 0, 1, 1},
                {1, 0, 1, 1, 0},
                {0, 1, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {1, 0, 0, 0, 0},
        };
        int[] plan = {2, 3, 4, 3};

        String answer = "YES";

        assertThat(solution(n, m, input, plan)).isEqualTo(answer);
    }

    private String solution(int n, int m, int[][] input, int[] plan) {
        int[] parents = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            parents[i] = i;
        }

        for (int row = 0; row < n; row++) {

            int[] currentRow = input[row];

            for (int col = 0; col < n; col++) {

                if (currentRow[col] == 1) {
                    unionParent(parents, row + 1, col + 1);
                }
            }
        }

        boolean isGroup = true;

        for (int i = 0; i < m - 1; i++) {
            if (findParent(parents, plan[i]) != findParent(parents, plan[i + 1])) {
                isGroup = false;
            }
        }


        return isGroup ? "YES" : "NO";
    }

    private void unionParent(int[] parent, int x, int y) {
        int xParent = findParent(parent, x);
        int yParent = findParent(parent, y);

        if (xParent < yParent) {
            parent[yParent] = xParent;
        } else {
            parent[xParent] = yParent;
        }
    }

    private int findParent(int[] parent, int x) {
        if (parent[x] != x) {
            return findParent(parent, parent[x]);
        }

        return parent[x];
    }
}
