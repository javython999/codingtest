package com.errday.codingtest.tree;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;

public class Virus {

    @Test
    void case1() {
        int n = 7;
        int[][] node = {
                {1, 2},
                {2, 3},
                {1, 5},
                {5, 2},
                {5, 6},
                {4, 7}
        };
        int answer = 4;
        assertThat(solution(n, node)).isEqualTo(answer);
    }

    private int solution(int n, int[][] node) {

        boolean[] visited = new boolean[n + 1];
        int infectedCount = 0;

        List<Integer>[] graph = new ArrayList[n + 1];

        for (int i = 0; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] info : node) {
            graph[info[0]].add(info[1]);
            graph[info[1]].add(info[0]);
        }

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(1);
        visited[1] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            infectedCount += 1;

            List<Integer> next = graph[current];
            for (int nextNode : next) {

                if (visited[nextNode]) {
                    continue;
                }
                visited[nextNode] = true;
                queue.offer(nextNode);
            }

        }

        return infectedCount - 1;
    }
}
