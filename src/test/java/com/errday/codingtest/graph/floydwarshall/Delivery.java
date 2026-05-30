package com.errday.codingtest.graph.floydwarshall;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class Delivery {

    @Test
    void case1() {
        int n = 5;
        int k = 3;
        int[][] input = {
                {1, 2, 1},
                {2, 3, 3},
                {5, 2, 2},
                {1, 4, 2},
                {5, 3, 1},
                {5, 3, 2}
        };
        int answer = 4;

        assertThat(solution(n, k, input)).isEqualTo(answer);
    }
    @Test
    void case2() {
        int n = 6;
        int k = 4;
        int[][] input = {
                {1, 2, 1},
                {1, 3, 2},
                {2, 3, 2},
                {3, 4, 3},
                {3, 5, 2},
                {3, 5 ,3}
        };
        int answer = 4;

        assertThat(solution(n, k, input)).isEqualTo(answer);
    }

    private int INF = Integer.MAX_VALUE / 2;
    private int solution(int n, int k, int[][] input) {

        int[][] graph = new int[n + 1][n + 1];
        for (int[] path : graph) {
            Arrays.fill(path, INF);
        }

        for (int i = 1; i < n + 1; i++) {
            graph[i][i] = 0;
        }

        for (int[] path : input) {
            int start = path[0];
            int end = path[1];
            int distance = path[2];
            graph[start][end] = Math.min(graph[start][end], distance);
            graph[end][start] = Math.min(graph[end][start], distance);
        }

        int[][] distances = new int[n + 1][n + 1];
        for (int[] distance : distances) {
            Arrays.fill(distance, INF);
        }
        for (int i = 1; i < n + 1; i++) {
            distances[i][i] = 0;
        }

        for (int stepOver = 1; stepOver < n + 1; stepOver++) {
            for (int start = 1; start < n + 1; start++) {
                for (int end = 1; end < n + 1; end++) {
                    if (graph[start][stepOver] + graph[stepOver][end] < distances[start][end]) {
                        distances[start][end] = graph[start][stepOver] + graph[stepOver][end];
                    }
                }
            }
        }

        return (int) Arrays.stream(distances[1])
                .filter(d -> d <= k)
                .count();
    }
}
