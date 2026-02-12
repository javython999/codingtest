package com.errday.codingtest.shortpath.dijkstra;


import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class DistanceAway {

    @Test
    void case1() {
        int n = 4;
        int k = 2;
        int x = 1;
        int[][] path = {
                {1, 2},
                {1, 3},
                {2, 3},
                {2, 4}
        };

        String answer = "4";

        assertThat(solution(n, k, x, path)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int n = 4;
        int k = 2;
        int x = 1;
        int[][] path = {
                {1, 2},
                {1, 3},
                {1, 4}
        };

        String answer = "-1";

        assertThat(solution(n, k, x, path)).isEqualTo(answer);
    }

    @Test
    void case3() {
        int n = 4;
        int k = 1;
        int x = 1;
        int[][] path = {
                {1, 2},
                {1, 3},
                {2, 3},
                {2, 4}
        };

        String answer = "2, 3";

        assertThat(solution(n, k, x, path)).isEqualTo(answer);
    }


    private String solution(int cityCount, int target, int start,  int[][] path) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < cityCount + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] info : path) {
            graph.get(info[0]).add(info[1]);
        }

        int[] distance = new int[cityCount + 1];
        Arrays.fill(distance, -1);
        distance[start] = 0;

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);

        while (!queue.isEmpty()) {
            int city = queue.poll();

            if (distance[city] == target) {
                continue;
            }

            for (int next : graph.get(city)) {
                if (distance[next] == -1) {
                    distance[next] = distance[city] + 1;
                    queue.offer(next);
                }
            }
        }

        StringJoiner joiner = new StringJoiner(", ");
        for (int i = 1; i < cityCount + 1; i++) {
            if (distance[i] == target) {
                joiner.add(String.valueOf(i));
            }
        }

        return joiner.length() == 0
                ? "-1"
                : joiner.toString();

    }


}
