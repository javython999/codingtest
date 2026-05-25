package com.errday.codingtest.graph.dfs;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class BipartiteGraph {

    @Test
    void case1() {
        int n = 3;
        int[][] input = {
                {1, 3},
                {2, 3}
        };
        boolean answer = true;
        assertThat(solution(n, input)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int n = 4;
        int[][] input = {
                {1, 2},
                {2, 3},
                {3, 4},
                {4, 2}
        };
        boolean answer = false;
        assertThat(solution(n, input)).isEqualTo(answer);
    }

    private boolean[] visited;
    private int[] group;
    private ArrayList<Integer>[] graph;
    private boolean isBipartite;

    private boolean solution(int n, int[][] input) {
        visited = new boolean[n + 1];
        isBipartite = true;
        group = new int[n + 1];
        graph = new ArrayList[n + 1];
        for (int i = 1; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : input) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        for (int i = 1; i < n + 1; i++) {
            if (isBipartite) {
                dfs(i);
            } else {
                break;
            }
        }
        System.out.println(Arrays.toString(group));
        return isBipartite;
    }

    private void dfs(int node) {
        visited[node] = true;

        for (int next : graph[node]) {
            if (!visited[next]) {
                group[next] = group[node] ^ 1;
                dfs(next);
            } else {
                if (group[next] == group[node]) {
                    isBipartite = false;
                }
            }
        }
    }


}
