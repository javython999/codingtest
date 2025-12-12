package com.errday.codingtest.dfs;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class FindTreeParent {

    private int n = 7;
    private int[][] edges = {
            {1, 6},
            {6, 3},
            {3, 5},
            {4, 1},
            {2, 4},
            {4, 7},
    };
    private String answer = "4 6 1 3 1 4";

    private int[] parent;
    private boolean[] visited;

    @Test
    void solution() {

        List<List<Integer>> tree = new ArrayList<>();
        for (int i = 0; i < n+1; i++) {
            tree.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            tree.get(edge[0]).add(edge[1]);
            tree.get(edge[1]).add(edge[0]);
        }

        parent = new int[n + 1];
        visited = new boolean[n + 1];

        dfs(tree, 1);

        StringJoiner joiner = new StringJoiner(" ");
        for (int i = 2; i <= n; i++) {
            joiner.add(String.valueOf(parent[i]));
        }


        Assertions.assertThat(joiner.toString()).isEqualTo(answer);
    }

    private void dfs(List<List<Integer>> tree, int nodeNumber) {

        visited[nodeNumber] = true;

        for (int node : tree.get(nodeNumber)) {

            if (!visited[node]) {
                parent[node] = nodeNumber;
                dfs(tree, node);
            }
        }
    }
}

