package com.errday.codingtest.shortpath.dijkstra;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

import java.util.PriorityQueue;

public class HideAndSeek {

    @Test
    void case1() {
        int n = 5;
        int k = 17;
        int answer = 2;
        Assertions.assertThat(solution(n, k)).isEqualTo(answer);
    }


    private int solution(int n, int k) {
        int boundary = 100_000 + 1;
        int inf = Integer.MAX_VALUE / 2;
        int[] distances = new int[boundary];
        Arrays.fill(distances, inf);

        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(v -> v[1]));
        queue.offer(new int[] {n, 0});
        distances[n] = 0;

        while (!queue.isEmpty()) {

            int[] current = queue.poll();
            int position = current[0];
            int cost = current[1];

            if (position == k) {
                return cost;
            }

            if (distances[position] < cost) {
                continue;
            }

            int[][] nexts = new int[][] {
                    {position + 1, cost + 1},
                    {position - 1, cost + 1},
                    {position * 2, cost}
            };

            for (int[] next : nexts) {
                int nextPosition = next[0];
                int nextCost = next[1];

                if (nextPosition < 0 || nextPosition >= boundary) {
                    continue;
                }

                if (nextCost < distances[nextPosition]) {
                    distances[nextPosition] = nextCost;
                    queue.offer(new int[] {nextPosition, nextCost});
                }

            }
        }

        return distances[k];
    }
}
