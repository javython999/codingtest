package com.errday.codingtest.shortpath.floydwarshall;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class Taxi {

    private final int INF = Integer.MAX_VALUE / 2;

    @Test
    void case1() {
        int n = 6;
        int s = 4;
        int a = 6;
        int b = 2;
        int[][] fares = {
                {4, 1, 10},
                {3, 5, 24},
                {5, 6, 2},
                {3, 1, 41},
                {5, 1, 24},
                {4, 6, 50},
                {2, 4, 66},
                {2, 3, 22},
                {1, 6, 25}
        };
        int answer = 82;
        assertThat(solution(n, s, a, b, fares)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int n = 7;
        int s = 3;
        int a = 4;
        int b = 1;
        int[][] fares = {
                {5, 7, 9},
                {4, 6, 4},
                {3, 6, 1},
                {3, 2, 3},
                {2, 1, 6}
        };
        int answer = 14;
        assertThat(solution(n, s, a, b, fares)).isEqualTo(answer);
    }

    @Test
    void case3() {
        int n = 6;
        int s = 4;
        int a = 5;
        int b = 6;
        int[][] fares = {
                {2,6,6},
                {6,3,7},
                {4,6,7},
                {6,5,11},
                {2,5,12},
                {5,3,20},
                {2,4,8},
                {4,3,9}
        };
        int answer = 18;
        assertThat(solution(n, s, a, b, fares)).isEqualTo(answer);
    }

    private int solution(int n, int s, int a, int b, int[][] fares) {
        int[][] graph = initGraph(n, fares);

        floydWarshall(n, graph);

        int answer = INF;

        for (int i = 1; i <= n; i++) {

            if (graph[s][i] == INF || graph[i][a] == INF || graph[i][b] == INF) {
                continue;
            }

            answer = Math.min(answer, graph[s][i] + graph[i][a] + graph[i][b]);
        }

        return answer;
    }

    private int[][] initGraph(int nodeCount, int[][] fares) {
        int[][] graph = new int[nodeCount + 1][nodeCount + 1];
        for (int[] row : graph) {
            Arrays.fill(row, INF);
        }

        for (int[] fare : fares) {
            int a = fare[0];
            int b = fare[1];
            int cost = fare[2];
            graph[a][a] = 0;
            graph[b][b] = 0;
            graph[a][b] = cost;
            graph[b][a] = cost;
        }

        return graph;
    }

    private void floydWarshall(int n, int[][] graph) {
        for (int stopOver = 1; stopOver <= n; stopOver++) {
            for (int start = 1; start <= n; start++) {
                for (int end = 1; end <= n; end++) {

                    if (graph[start][stopOver] == INF || graph[stopOver][end] == INF) {
                        continue;
                    }

                    graph[start][end] = Math.min(graph[start][end], graph[start][stopOver] + graph[stopOver][end]);
                }
            }
        }
    }

}
