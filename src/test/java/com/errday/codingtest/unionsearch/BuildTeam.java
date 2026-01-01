package com.errday.codingtest.unionsearch;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class BuildTeam {

    private int n = 7;
    private int m = 8;
    private int[][] datas = {
            {0, 1, 3},
            {1, 1, 7},
            {0, 7, 6},
            {1, 7, 1},
            {0, 3, 7},
            {0, 4, 2},
            {0, 1, 1},
            {1, 1, 1},
    };
    private String[] answer = {
            "NO",
            "NO",
            "YES"
    };

    @Test
    void solution() {
        int[] parent = new int[n + 1];

        for (int i = 0; i < n + 1; i++) {
            parent[i] = i;
        }

        List<String> temp = new ArrayList<>();
        for (int[] data : datas) {
            int command = data[0];
            int x = data[1];
            int y = data[2];

            if (command == 0) {
                unionFind(parent, x, y);
            } else if (command == 1) {
                int xParent = findParent(parent, x);
                int yParent = findParent(parent, y);

                String checkResult = (xParent == yParent) ? "YES" : "NO";
                temp.add(checkResult);
            }
        }

        String[] result = temp.toArray(String[]::new);
        Assertions.assertThat(result).isEqualTo(answer);
    }

    private void unionFind(int[] parent, int x, int y) {
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
