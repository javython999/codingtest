package com.errday.codingtest.dfs;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class Tps {

    @Test
    void case1() {
        int n = 4;
        int[][] map = {
                {0, 10, 15, 20},
                {5, 0, 9, 10},
                {6, 13, 0, 12},
                {8, 8, 9, 0}
        };

        int answer = 35;
        assertThat(solution(n, map)).isEqualTo(answer);
    }

    private int INF = Integer.MAX_VALUE / 2;

    private int solution(int n, int[][] map) {

        int[][] dp = new int[n][1 << n];
        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }

        int visitedState = 1 << 0;
        dp[0][visitedState] = 0;

        ArrayDeque<int[]> stack = new ArrayDeque<>();
        stack.push(new int[]{0, visitedState});

        while (!stack.isEmpty()) {
            int[] current = stack.pop();
            int start = current[0];
            int state = current[1];


            for (int next = 0; next < n; next++) {

                if (map[start][next] == 0) {
                    continue;
                }

                // 방문 체크
                if ((state & (1 << next)) != 0) {
                    continue;
                }

                // 방문 처리
                int newState = state | (1 << next);
                // 비용 계산 (현재 경로까지 비용 + 다음 경로 비용)
                int nextCost = dp[start][state] + map[start][next];

                // 해당 상태에 도달하는 최소 비용 발견
                if (dp[next][newState] > nextCost) {
                    dp[next][newState] = nextCost;
                    stack.push(new int[]{next, newState});
                }
            }
        }

        int answer = INF;
        int finishedState = (1 << n) - 1;

        for (int start = 0; start < n; start++) {

            // 방문 불가능한 경로 pass
            if (dp[start][finishedState] == INF) {
                continue;
            }

            // 시작점(0)으로 갈 수 없는 경우 pass
            if (map[start][0] == 0) {
                continue;
            }

            // 최소 비용 갱신
            answer = Math.min(answer, dp[start][finishedState] + map[start][0]);
        }

        return answer;
    }
}
