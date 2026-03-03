package com.errday.codingtest.shortpath.dijkstra;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class MarsExploration {

    @Test
    void case1() {
        int n = 3;
        int[][] map = {
                {5, 5, 4},
                {3, 9, 1},
                {3, 2, 7}
        };

        int answer = 20;
        assertThat(solution(n, map)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int n = 5;
        int[][] map = {
                {3, 7, 2, 0, 1},
                {2, 8, 0, 9, 1},
                {1, 2, 1, 8, 1},
                {9, 8, 9, 2, 0},
                {3, 6, 5, 1, 5}
        };

        int answer = 19;
        assertThat(solution(n, map)).isEqualTo(answer);
    }

    @Test
    void case3() {
        int n = 7;
        int[][] map = {
                {9, 0, 5, 1, 1, 5, 3},
                {4, 1, 2, 1, 6, 5, 3},
                {0, 7, 6, 1, 6, 8, 5},
                {1, 1, 7, 8, 3, 2, 3},
                {9, 4, 0, 7, 6, 4, 1},
                {5, 8, 3, 2, 4, 8, 3},
                {7, 4, 8, 4, 8, 3, 4}
        };

        int answer = 36;
        assertThat(solution(n, map)).isEqualTo(answer);
    }

    private int solution(int n, int[][] map) {

        int inf = Integer.MAX_VALUE / 2;

        int[][] costs = new int[n][n];
        for (int[] row : costs) {
            Arrays.fill(row, inf);
        }

        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(x -> x[2]));

        queue.offer(new int[] {0, 0, map[0][0]});
        costs[0][0] = map[0][0];

        int[] moveRow = {-1, 0, 1, 0};
        int[] moveCol = {0, 1, 0, -1};

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentRow = current[0];
            int currentCol = current[1];
            int currentCost = current[2];

            if (costs[currentRow][currentCol] < currentCost) {
                continue;
            }

            if (currentRow == n - 1 && currentCol == n - 1) {
                return costs[n - 1][n - 1];
            }

            for (int moveType = 0; moveType < 4; moveType++) {
                int nextRow = currentRow + moveRow[moveType];
                int nextCol = currentCol + moveCol[moveType];

                if (nextRow < 0 || nextRow >= n) {
                    continue;
                }

                if (nextCol < 0 || nextCol >= n) {
                    continue;
                }

                int newCost = currentCost + map[nextRow][nextCol];

                if (newCost < costs[nextRow][nextCol]) {
                    costs[nextRow][nextCol] = newCost;
                    queue.offer(new int[] {nextRow, nextCol, newCost});
                }
            }
        }

        return costs[n - 1][n - 1];
    }
}
