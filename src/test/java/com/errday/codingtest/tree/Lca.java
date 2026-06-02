package com.errday.codingtest.tree;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;

public class Lca {

    @Test
    void case1() {
        int n = 16;
        int[][] input = {
                {1, 14},
                {8, 5},
                {10, 16},
                {5, 9},
                {4, 6},
                {8, 4},
                {4, 10},
                {1, 13},
                {6, 15},
                {10, 11},
                {6, 7},
                {10, 2},
                {16, 3},
                {8, 1},
                {16, 12}
        };
        int a = 16;
        int b = 7;
        int answer = 4;
        assertThat(solution(n, input, a, b)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int n = 5;
        int[][] input = {
                {2, 3},
                {3, 4},
                {3, 1},
                {1, 5}
        };
        int a = 3;
        int b = 5;
        int answer = 3;
        assertThat(solution(n, input, a, b)).isEqualTo(answer);
    }

    private int solution(int n, int[][] input, int a, int b) {

        int[] tree = new int[n + 1];

        for (int[] edge : input) {
            int parent = edge[0];
            int child = edge[1];
            tree[child] = parent;
        }

        int[] levels = new int[n + 1];

        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 1; i < n + 1; i++) {
            if (tree[i] == 0) {
                queue.offer(new int[] {i, 1});
            }
        }

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int node = current[0];
            int level = current[1];
            levels[node] = level;

            for (int next = 1; next < n + 1; next++) {
                if (tree[next] == node) {
                    queue.offer(new int[] {next, level + 1});
                }
            }
        }

        while (levels[a] > levels[b]) {
            a = tree[a];
        }

        while (levels[a] < levels[b]) {
            b = tree[b];
        }

        while (a != b) {
            a = tree[a];
            b = tree[b];
        }

        return a;
    }
}
