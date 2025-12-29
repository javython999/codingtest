package com.errday.codingtest.unionsearch;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class Basic {

    private int n = 6;
    private int[][] datas = {
            {1, 4},
            {2, 3},
            {2, 4},
            {5, 6},
    };
    private int[] group = {1, 1, 1, 1, 5, 5};
    private int[] answer = {1, 1, 2, 1, 5, 5};


    @Test
    void solution() {
        int[] parent = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }

        for (int[] data : datas) {
            union(parent, data[0], data[1]);
        }

        int[] myGroup = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            myGroup[i] = findParent(parent, i);
        }
        myGroup = Arrays.copyOfRange(myGroup, 1, myGroup.length);

        int[] myAnswer = Arrays.copyOfRange(parent, 1, parent.length);

        Assertions.assertThat(myGroup).isEqualTo(group);
        Assertions.assertThat(myAnswer).isEqualTo(answer);
    }

    private void union(int[] parent, int x, int y) {
        int xParent = findParent(parent, x);
        int yParent = findParent(parent, y);

        if (xParent < yParent) {
            parent[yParent] = xParent;
        } else {
            parent[xParent] = yParent;
        }
    }

    private int findParent(int[] parent, int x) {
        if (parent[x] != x) {
            return findParent(parent, parent[x]);
        }
        return parent[x];
    }
}
