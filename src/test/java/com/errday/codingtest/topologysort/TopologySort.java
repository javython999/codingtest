package com.errday.codingtest.topologysort;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class TopologySort {

    private int n = 7;
    private int m = 8;
    private int[][] datas = {
            {1, 2},
            {1, 5},
            {2, 3},
            {2, 6},
            {3, 4},
            {4, 7},
            {5, 6},
            {6, 4}
    };
    private int[] answer = {1, 2, 5, 3, 6, 4, 7};

    @Test
    void solution() {

        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 1; i < n + 1; i++) {
            graph.putIfAbsent(i, new ArrayList<>());
        }

        for (int[] data : datas) {
            int from = data[0];
            int to = data[1];
            graph.get(to).add(from);
        }

        ArrayDeque<Integer> queue = new ArrayDeque<>();

        for (Map.Entry<Integer, List<Integer>> entry : graph.entrySet()) {
            int to = entry.getKey();
            List<Integer> froms = entry.getValue();

            if (froms.isEmpty()) {
                queue.offer(to);
            }
        }


        List<Integer> temp = new ArrayList<>();
        while (!queue.isEmpty()) {
            Integer vertex = queue.poll();
            temp.add(vertex);

            for (Map.Entry<Integer, List<Integer>> entry : graph.entrySet()) {
                Integer to = entry.getKey();
                List<Integer> froms = entry.getValue();

                if (froms.contains(vertex)) {
                    froms.remove(vertex);

                    if (froms.isEmpty()) {
                        queue.offer(to);
                    }
                }
            }
        }

        int[] result = temp.stream()
                        .mapToInt(Integer::valueOf)
                        .toArray();

        Assertions.assertThat(result).isEqualTo(answer);
    }
}
