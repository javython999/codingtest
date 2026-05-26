package com.errday.codingtest.unionfind;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RepresentSet {

    @Test
    void case1() {
        int n = 7;
        int m = 8;
        int[][] input = {
                {0, 1, 3},
                {1, 1, 7},
                {0, 7, 6},
                {1, 7, 1},
                {0, 3, 7},
                {0, 4, 2},
                {0, 1, 1},
                {1, 1, 1}
        };
        boolean[] answer = {false, false, true};
        assertThat(solution(n, m, input)).containsExactly(answer);
    }

    @Test
    void case2() {
        int n = 1_000_000;
        int m = 5;
        int[][] input = {
                {0, 1, 1_000_000},
                {1, 1, 1_000_000},
                {0, 500_000, 999_999},
                {1, 1, 500_000},
                {1, 500_000, 999_999}
        };
        boolean[] answer = {true, false, true};

        assertThat(solution(n, m, input)).containsExactly(answer);
    }

    private int UNION_FLAG = 0;
    private int FIND_FLAG = 1;

    private boolean[] solution(int n, int m, int[][] input) {
        int[] roots = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            roots[i] = i;
        }

        List<Boolean> result = new ArrayList<>();

        for (int[] data : input) {

            int command = data[0];

            if (command == UNION_FLAG) {
                union(roots, data[1], data[2]);
            } else if (command == FIND_FLAG) {
                int xRoot = find(roots, data[1]);
                int yRoot = find(roots, data[2]);
                result.add(xRoot == yRoot);
            }

        }

        boolean[] answer = new boolean[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        return answer;
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

        if (xRoot == yRoot) {
            return;
        }

        if (xRoot < yRoot) {
            roots[y] = xRoot;
        } else {
            roots[x] = yRoot;
        }
    }


}

