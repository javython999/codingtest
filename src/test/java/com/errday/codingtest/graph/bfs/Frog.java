package com.errday.codingtest.graph.bfs;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;

public class Frog {

    @Test
    void case1() {
        int[] bridge = {1, 2, 2, 1, 2};
        int start = 1;
        int end = 5;
        int answer = 1;
        assertThat(solution(bridge, start, end)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int[] bridge = {1, 2, 2, 1, 2};
        int start = 3;
        int end = 5;
        int answer = 1;
        assertThat(solution(bridge, start, end)).isEqualTo(answer);
    }

    private int solution(int[] bridge, int start, int end) {

        boolean[] visited = new boolean[bridge.length];
        int[] costs = new int[bridge.length];
        Arrays.fill(costs, -1);

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start-1);
        visited[start-1] = true;
        costs[start-1] = 0;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            int moveAmount = bridge[current];

            for (int i = current + moveAmount; i < bridge.length; i += moveAmount) {
                if (visited[i]) {
                    continue;
                }

                visited[i] = true;
                costs[i] = costs[current] + 1;
                queue.offer(i);
            }

            for (int i = current - moveAmount; i >= 0; i -= moveAmount) {
                if (visited[i]) {
                    continue;
                }

                visited[i] = true;
                costs[i] = costs[current] + 1;
                queue.offer(i);
            }
        }

        return costs[end-1];
    }
}
