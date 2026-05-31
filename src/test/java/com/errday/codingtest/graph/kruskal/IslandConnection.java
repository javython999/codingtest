package com.errday.codingtest.graph.kruskal;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

import static org.assertj.core.api.Assertions.assertThat;

public class IslandConnection {

    @Test
    void case1() {
        int n = 4;
        int[][] costs = {
                {0, 1, 1},
                {0, 2, 2},
                {1, 2, 5},
                {1, 3, 1},
                {2, 3, 8}
        };
        int answer = 4;
        assertThat(solution(n, costs)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int n = 4;
        int[][] costs = {
                {0, 1, 10},
                {1, 2, 20},
                {2, 3, 30},

        };
        int answer = 60;
        assertThat(solution(n, costs)).isEqualTo(answer);
    }

    @Test
    void case3() {
        int n = 4;
        int[][] costs = {
                {0, 1, 5},
                {1, 2, 5},
                {2, 3, 5},
                {0, 3, 5},
                {0, 2, 10}
        };
        int answer = 15;
        assertThat(solution(n, costs)).isEqualTo(answer);
    }

    @Test
    void case4() {
        int n = 5;
        int[][] costs = {
                {0, 1, 1},
                {1, 2, 2},
                {0, 2, 3},
                {2, 3, 10},
                {3, 4, 20}
        };
        int answer = 33;
        assertThat(solution(n, costs)).isEqualTo(answer);
    }

    private int solution(int n, int[][] costs) {

        int[] roots = new int[n];
        for (int i = 0; i < n; i++) {
            roots[i] = i;
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(v -> v[2]));
        for (int[] cost : costs) {
            queue.offer(cost);
        }

        int connectCost = 0;
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            int cost = current[2];

            if (find(roots, x) != find(roots, y)) {
                union(roots, x, y);
                connectCost += cost;
            }
        }
        return connectCost;
    }

    private int find(int[] roots, int target) {
        if (roots[target] != target) {
            roots[target] = find(roots, roots[target]);
        }
        return roots[target];
    }

    private void union(int[] roots, int x, int y) {
        int xRoot = find(roots, x);
        int yRoot = find(roots, y);

        if (xRoot < yRoot) {
            roots[yRoot] = xRoot;
        } else {
            roots[xRoot] = yRoot;
        }
    }
}
