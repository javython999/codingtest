package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;

import static org.assertj.core.api.Assertions.assertThat;

public class FailRate {
    @Test
    void case1() {
        int n = 5;
        int[] stages = {2, 1, 2, 6, 2, 4, 3, 3};
        int[] answer = {3, 4, 2, 1, 5};

        assertThat(solution(n, stages)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int n = 4;
        int[] stages = {4, 4, 4, 4, 4};
        int[] answer = {4, 1, 2, 3};

        assertThat(solution(n, stages)).isEqualTo(answer);
    }

    private int[] solution(int n, int[] stages) {
        int[] challenger = new int[n + 2];

        for (int stage : stages) {
            challenger[stage] += 1;
        }

        PriorityQueue<StageInfo> queue = new PriorityQueue<>((a, b) -> {
            int compare = Double.compare(b.failRate, a.failRate);

            if (compare == 0) {
                return Integer.compare(a.stage, b.stage);
            }

            return compare;
        });

        int playerCount = stages.length;
        for (int stage = 1; stage < n + 1; stage++) {
            double failRate = 0;

            if (playerCount != 0) {
                failRate = (double) challenger[stage] / playerCount;
            }

            queue.offer(new StageInfo(stage, failRate));
            playerCount -= challenger[stage];
        }

        return queue.stream()
                .mapToInt(stageInfo -> stageInfo.stage)
                .toArray();
    }

    private class StageInfo {
        int stage;
        double failRate;

        StageInfo(int stage, double failRate) {
            this.stage = stage;
            this.failRate = failRate;
        }
    }


}
