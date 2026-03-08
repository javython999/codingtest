package com.errday.codingtest.unionfind.kruskal;

import org.junit.jupiter.api.Test;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class PlanetTunnel {

    @Test
    void case1() {
        int n = 5;
        int[][] input = {
                {11, -15, -15},
                {14, -5, -15},
                {-1, -1, -5},
                {10, -4, -1},
                {19, -4, 19}
        };

        int answer = 4;
        assertThat(solution(n, input)).isEqualTo(answer);
    }

    private int solution(int n, int[][] input) {

        int[] roots = new int[n];
        for (int planet = 0; planet < n; planet++) {
            roots[planet] = planet;
        }

        int[][] planets = new int[n][4];
        for (int index = 0; index < n; index++) {
            int x = input[index][0];
            int y = input[index][1];
            int z = input[index][2];
            planets[index] = new int[] {index, x, y, z};
        }


        int[][] edges = new int[3 * (n - 1)][3];
        int edgeIndex = 0;


        for (int dimension = 1; dimension < 4; dimension++) {
            int currentDimension = dimension;

            Arrays.sort(planets, Comparator.comparingInt(p -> p[currentDimension]));
            for (int i = 0; i < n - 1; i++) {
                edges[edgeIndex++] = new int[] {
                        planets[i][0],
                        planets[i+1][0],
                        Math.abs(planets[i][currentDimension] - planets[i+1][currentDimension])
                };
            }
        }

        Arrays.sort(edges, Comparator.comparingInt(e -> e[2]));
        int result = 0;

        for (int[] edge : edges) {
            int start = edge[0];
            int end = edge[1];
            int cost = edge[2];

            if (find(roots, start) != find(roots, end)) {
                union(roots, start, end);
                result += cost;
            }
        }

        return result;
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

        if (xRoot < yRoot) {
            roots[yRoot] = xRoot;
        } else {
            roots[xRoot] = yRoot;
        }
    }
}
