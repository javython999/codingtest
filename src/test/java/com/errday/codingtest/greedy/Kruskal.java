package com.errday.codingtest.greedy;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class Kruskal {

    private int n = 7;
    private int m = 9;

    private int[][] datas = {
            {1, 2, 29},
            {1, 5, 75},
            {2, 3, 35},
            {2, 6, 34},
            {3, 4, 7},
            {4, 6, 23},
            {4, 7, 13},
            {5, 6, 53},
            {6, 7, 25},
    };

    private int answer = 159;

    @Test
    void solution() {

        int[] parent = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            parent[i] = i;
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(data -> data[2]));
        queue.addAll(Arrays.asList(datas));

        int myAnswer = 0;
        while (!queue.isEmpty()) {
            int[] data = queue.poll();
            int x = data[0];
            int y = data[1];
            int cost = data[2];

            if (findParent(parent, x) != findParent(parent, y)) {
                union(parent, x, y);
                myAnswer += cost;
            }
        }

        Assertions.assertThat(myAnswer).isEqualTo(answer);
    }

    private void union(int[] parent, int x, int y) {
        int parentX = findParent(parent, x);
        int parentY = findParent(parent, y);

        if (parentX < parentY) {
            parent[parentY] = parentX;
        } else {
            parent[parentX] = parentY;
        }
    }

    private int findParent(int[] parent, int find) {
        if (parent[find] != find) {
            parent[find] = findParent(parent, parent[find]);
        }
        return parent[find];
    }
}
