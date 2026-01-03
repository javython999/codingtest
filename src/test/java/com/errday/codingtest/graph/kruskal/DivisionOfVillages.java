package com.errday.codingtest.graph.kruskal;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class DivisionOfVillages {

    private int n = 7;
    private int m = 12;
    private int[][] datas = {
            {1, 2, 3},
            {1, 3, 2},
            {3, 2, 1},
            {2, 5, 2},
            {3, 4, 4},
            {7, 3, 6},
            {5, 1, 5},
            {1, 6, 2},
            {6, 4, 1},
            {6, 5, 3},
            {4, 5, 3},
            {6, 7, 4}
    };
    private int answer = 8;

    @Test
    void solution() {

        int[] roots = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            roots[i] = i;
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(v -> v[2]));
        for (int[] data : datas) {
            queue.offer(data);
        }

        int totalCost = 0;
        int lastCost = 0;
        while (!queue.isEmpty()) {
            int[] vertex = queue.poll();
            int x = vertex[0];
            int y = vertex[1];
            int cost = vertex[2];

            if (findRoot(roots, x) != findRoot(roots, y)) {
                unionFind(roots, x, y);
                totalCost += cost;
                lastCost = cost;
            }
        }

        int result = totalCost - lastCost;

        Assertions.assertThat(result).isEqualTo(answer);
    }

    private void unionFind(int[] roots, int x, int y) {
        int xRoot = findRoot(roots, x);
        int yRoot = findRoot(roots, y);

        if (xRoot < yRoot) {
            roots[yRoot] = xRoot;
        } else {
            roots[xRoot] = yRoot;
        }
    }

    private int findRoot(int[] roots, int find) {
        if (roots[find] != find) {
            return findRoot(roots, roots[find]);
        }
        return roots[find];
    }
}
