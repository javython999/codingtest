package com.errday.codingtest.graph.bfs;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;

public class Cousin {

    @Test
    void case1() {
        int n = 9;
        int x = 7;
        int y = 3;
        int[][] tree = {
                {1, 2},
                {1, 3},
                {2, 7},
                {2, 8},
                {2, 9},
                {4, 5},
                {4, 6}
        };
        int answer = 3;
        assertThat(solution(n, x, y, tree)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int n = 9;
        int x = 8;
        int y = 6;
        int[][] tree = {
                {1, 2},
                {1, 3},
                {2, 7},
                {2, 8},
                {2, 9},
                {4, 5},
                {4, 6}
        };
        int answer = -1;
        assertThat(solution(n, x, y, tree)).isEqualTo(answer);
    }

    private int solution(int n, int x, int y, int[][] tree) {
        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] branch : tree) {
            graph[branch[0]].add(branch[1]);
            graph[branch[1]].add(branch[0]);
        }

        boolean[] visited = new boolean[n + 1];

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {x, 0});
        visited[x] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int number = current[0];
            int cost = current[1];

            for (int next : graph[number]) {

                if (visited[next]) {
                    continue;
                }
                visited[next] = true;

                if (next == y) {
                    return cost + 1;
                }

                queue.offer(new int[] {next, cost + 1});
            }
        }

        return -1;
    }
}
