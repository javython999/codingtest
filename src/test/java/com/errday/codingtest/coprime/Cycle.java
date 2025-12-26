package com.errday.codingtest.coprime;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class Cycle {

    private int n = 3;
    private int m = 3;
    private int[][] datas = {
            {1, 2},
            {1, 3},
            {2, 3},
    };
    private boolean answer = true;

    @Test
    void solution() {

        int[] parent = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            parent[i] = i;
        }

        boolean isCycle = false;
        for (int[] data : datas) {
            int x = data[0];
            int y = data[1];

            if (parent[x] != parent[y]) {
                union(parent, x, y);
            } else {
                isCycle = true;
                break;
            }
        }

        Assertions.assertThat(isCycle).isEqualTo(answer);
    }

    private int findParent(int[] parent, int x) {
        if (parent[x] != x) {
            return findParent(parent, parent[x]);
        }

        return parent[x];
    }

    private void union(int[] parent, int x , int y) {
        int parentX = findParent(parent, x);
        int parentY = findParent(parent, y);

        if (parentX < parentY) {
            parent[y] = parentX;
        } else {
            parent[x] = parentY;
        }
    }
}
