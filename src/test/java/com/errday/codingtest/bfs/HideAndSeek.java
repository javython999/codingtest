package com.errday.codingtest.bfs;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class HideAndSeek {

    @Test
    void case1() {
        int n = 5;
        int k = 17;
        int answer = 4;
        assertThat(solution(n, k)).isEqualTo(answer);
    }

    private int solution(int n, int k) {
        int BOUNDARY = 100_000 + 1;
        int[] distances = new int[BOUNDARY];
        Arrays.fill(distances, -1);
        distances[n] = 0;

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(n);

        while (!queue.isEmpty()) {
            Integer current = queue.poll();

            if (current == k) {
                return distances[current];
            }


            int[] nexts = new int[] {current - 1, current + 1, current * 2};
            for (int next : nexts) {
                if (next < 0 || next >= BOUNDARY) {
                    continue;
                }

                if (distances[next] != -1) {
                    continue;
                }

                distances[next] = distances[current] + 1;
                queue.offer(next);

            }

        }

        return -1;
    }
}
