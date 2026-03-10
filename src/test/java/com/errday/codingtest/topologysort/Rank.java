package com.errday.codingtest.topologysort;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class Rank {

    @Test
    void case1() {
        int[] lastYearRank = {5, 4, 3, 2, 1};
        int[][] changes = {
                {2, 4},
                {3, 4}
        };
        String answer = "5 3 2 4 1";
        assertThat(solution(lastYearRank, changes)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int[] lastYearRank = {2, 3, 1};
        int[][] changes = {};
        String answer = "2 3 1";
        assertThat(solution(lastYearRank, changes)).isEqualTo(answer);
    }

    @Test
    void case3() {
        int[] lastYearRank = {1, 2 ,3, 4};
        int[][] changes = {
                {1, 2},
                {3, 4},
                {2, 3}
        };
        String answer = "IMPOSSIBLE";
        assertThat(solution(lastYearRank, changes)).isEqualTo(answer);
    }

    private String solution(int[] lastYearRank, int[][] changes) {
        int[] indegree = new int[lastYearRank.length + 1];
        boolean[][] graph = new boolean[lastYearRank.length + 1][lastYearRank.length + 1];

        for (int i = 0; i < lastYearRank.length; i++) {
            for (int j = i + 1; j < lastYearRank.length; j++) {
                int upper = lastYearRank[i];
                int lower = lastYearRank[j];
                indegree[lower] += 1;
                graph[upper][lower] = true;
            }
        }

        for (int[] change : changes) {
            int a = change[0];
            int b = change[1];

            if (graph[a][b]) {
                graph[a][b] = false;
                graph[b][a] = true;
                indegree[a] += 1;
                indegree[b] -= 1;
            } else {
                graph[a][b] = true;
                graph[b][a] = false;
                indegree[a] -= 1;
                indegree[b] += 1;
            }
        }

        List<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new ArrayDeque<>();

        for (int team = 1; team < indegree.length; team++) {
            if (indegree[team] == 0) {
                queue.offer(team);
            }
        }

        for (int i = 1; i < indegree.length; i++) {
            if (queue.isEmpty()) {
                return "IMPOSSIBLE";
            }

            if (queue.size() > 1) {
                return "?";
            }

            int team = queue.poll();
            result.add(team);

            for (int lower = 1; lower < indegree.length; lower++) {
                if (graph[team][lower]) {
                    indegree[lower] -= 1;

                    if (indegree[lower] == 0) {
                        queue.offer(lower);
                    }

                }

            }

        }

        StringJoiner sj = new StringJoiner(" ");
        for (int team : result) {
            sj.add(String.valueOf(team));
        }

        return sj.toString();
    }
}
