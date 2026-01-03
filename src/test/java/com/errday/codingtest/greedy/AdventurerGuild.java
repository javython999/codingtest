package com.errday.codingtest.greedy;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class AdventurerGuild {

    private int n = 5;
    private int[] datas = {2, 3, 1, 2, 2};
    private int answer = 2;

    @Test
    void solution() {
        int myAnswer = 0;

        Arrays.sort(datas);

        int memberCount = 0;

        for (int data : datas) {
            memberCount += 1;
            if (memberCount >= data) {
                myAnswer += 1;
                memberCount = 0;
            }
        }

        Assertions.assertThat(myAnswer).isEqualTo(answer);
    }

    private int findRoot(int[] roots, int x) {
        if (roots[x] != x) {
            return findRoot(roots, roots[x]);
        }
        return roots[x];
    }

    private void unionFind(int[] roots, int x , int y) {
        int xRoot = findRoot(roots, x);
        int yRoot = findRoot(roots, y);

        if (xRoot < yRoot) {
            roots[yRoot] = xRoot;
        } else {
            roots[xRoot] = yRoot;
        }
    }
}
