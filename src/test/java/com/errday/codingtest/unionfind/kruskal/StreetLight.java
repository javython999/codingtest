package com.errday.codingtest.unionfind.kruskal;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

import static org.assertj.core.api.Assertions.assertThat;

public class StreetLight {

    @Test
    void case1() {
        int n = 7;
        int[][] input = {
                {0, 1, 7},
                {0, 3, 5},
                {1, 2, 8},
                {1, 3, 9},
                {1, 4, 7},
                {2, 4, 5},
                {3, 4, 15},
                {3, 5, 6},
                {4, 5, 8},
                {4, 6, 9},
                {5, 6, 11}
        };

        int answer = 51;

        assertThat(solution(n, input)).isEqualTo(answer);
    }

    private int solution(int n, int[][] input) {

        int totalCost = 0;
        int newCost = 0;

        int[] roots = new int[n];
        for (int street = 0; street < n; street++) {
            roots[street] = street;
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(s -> s[2]));
        queue.addAll(Arrays.asList(input));

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int start = current[0];
            int end = current[1];
            int cost = current[2];

            totalCost += cost;

            if (find(roots, start) != find(roots, end)) {
                union(roots, start, end);
                newCost += cost;
            }
        }

        return totalCost - newCost;
    }


    private int find(int[] roots, int x) {
        if (roots[x] != x) {
            roots[x] = find(roots, roots[x]);
        }

        return roots[x];
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
