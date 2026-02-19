package com.errday.codingtest.sort;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Stages {

    @Test
    void case1() {
        int n = 5;
        int[] stages = {
                2, 1, 2, 6, 2, 4, 3, 3
        };

        int[] answer = {3, 4, 2, 1, 5};

        assertThat(solution2(n, stages)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int n = 4;
        int[] stages = {
                4, 4, 4, 4, 4
        };

        int[] answer = {4, 1, 2, 3};

        assertThat(solution2(n, stages)).isEqualTo(answer);
    }

    private int[] solution(int n, int[] stages) {
        int playerCount = stages.length;
        int[] currentStages = new int[n + 2];
        for (int stage : stages) {
            currentStages[stage] += 1;
        }

        List<Node> nodes = new ArrayList<>();

        for (int stage = 1; stage <= n; stage++) {

            int count = currentStages[stage];

            double fail = 0;
            if (playerCount > 0) {
                fail = (double) count / playerCount;
            }

            playerCount -= count;
            nodes.add(new Node(stage, fail));
        }

        Collections.sort(nodes);
        int[] result = new int[n];

        for (int i = 0; i < nodes.size(); i++) {
            result[i] = nodes.get(i).stage;
        }

        return result;
    }

    private static class Node implements Comparable<Node> {
        private final int stage;
        private final double fail;

        public Node(int stage, double fail) {
            this.stage = stage;
            this.fail = fail;
        }

        @Override
        public int compareTo(Node o) {
            int compare = Double.compare(o.fail, this.fail);

            if (compare == 0) {
                return Integer.compare(this.stage, o.stage);
            }

            return compare;
        }
    }

    private int[] solution2(int n, int[] stages) {

        int[] currentStages = new int[n + 2];
        for (int stage : stages) {
            currentStages[stage] += 1;
        }

        int[] remaining = new int[n + 1];
        int playerCount = stages.length;
        for (int i = 1; i <= n; i++) {
            remaining[i] = playerCount;
            playerCount -= currentStages[i];
        }

        Integer[] result = new Integer[n];
        for (int i = 0; i < n; i++) {
            int stage = i + 1;
            result[i] = stage;
        }

        Arrays.sort(result, (a, b) -> {
            long left = (long) currentStages[a] * remaining[b];
            long right = (long) currentStages[b] * remaining[a];

            if (left == right) {
                return Integer.compare(a, b);
            }

            return Long.compare(right, left);
        });

        return Arrays.stream(result)
                .mapToInt(Integer::valueOf)
                .toArray();
    }
}
