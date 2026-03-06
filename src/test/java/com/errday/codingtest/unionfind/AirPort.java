package com.errday.codingtest.unionfind;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AirPort {

    private int[] roots;

    @Test
    void case1() {
        int portCount = 4;
        int airPlainCount = 3;
        int[] airPlainDockingInfo = {4, 1, 1};

        int answer = 2;

        assertThat(solution(portCount, airPlainCount, airPlainDockingInfo)).isEqualTo(answer);
    }

    private int solution(int portCount, int airPlainCount, int[] airPlainDockingInfo) {
        roots = new int[portCount + 1];
        for (int i = 1; i < portCount + 1; i++) {
            roots[i] = i;
        }

        int result = 0;
        for (int i = 0; i < airPlainDockingInfo.length; i++) {
            int maxPortNumber = airPlainDockingInfo[i];

            int port = find(maxPortNumber);
            if (port == 0) {
                break;
            }

            union(port, port - 1);
            result += 1;
        }

        return result;
    }

    private int find(int x) {
        if (roots[x] != x) {
            roots[x] = find(roots[x]);
        }

        return roots[x];
    }

    private void union(int rootX, int rootY) {
        if (rootX < rootY) {
            roots[rootY] = rootX;
        } else {
            roots[rootX] = rootY;
        }
    }
}
