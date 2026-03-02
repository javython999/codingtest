package com.errday.codingtest.shortpath.floydwarshall;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class Rank {

    private int INF = Integer.MAX_VALUE / 2;

    @Test
    void case1() {
        int studentCount = 6;
        int[][] ranks = {
                {1, 5},
                {3, 4},
                {4, 2},
                {4, 6},
                {5, 2},
                {5, 4}
        };

        int answer = 1;

        assertThat(solution(studentCount, ranks)).isEqualTo(answer);
    }

    private int solution(int studentCount, int[][] ranks) {

        int[][] graph = new int[studentCount + 1][studentCount + 1];
        for (int[] row : graph) {
            Arrays.fill(row, INF);
        }

        for (int i = 1; i < studentCount + 1; i++) {
            graph[i][i] = 0;
        }

        for (int[] rank : ranks) {
            graph[rank[0]][rank[1]] = 1;
        }

        for (int stepOver = 1; stepOver < studentCount + 1; stepOver++) {
            for (int start = 1; start < studentCount + 1; start++) {
                for (int end = 1; end < studentCount + 1; end++) {
                    graph[start][end] = Math.min(graph[start][end], graph[start][stepOver] + graph[stepOver][end]);
                }
            }
        }

        int result = 0;

        for (int start = 1; start < studentCount + 1; start++) {
           int count = 0;

            for (int end = 1; end < studentCount + 1; end++) {

               if (graph[start][end] != INF || graph[end][start] != INF) {
                   count += 1;
               }
           }

            if (count == studentCount) {
                result += 1;
            }
        }


        return result;
    }




}
