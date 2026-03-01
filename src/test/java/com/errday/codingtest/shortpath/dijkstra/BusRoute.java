package com.errday.codingtest.shortpath.dijkstra;

import java.util.*;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BusRoute {

    private int INF = Integer.MAX_VALUE / 2;

    @Test
    void case1() {
        int n = 5;
        int m = 14;
        int[][] path = {
                {1, 2, 2},
                {1, 3, 3},
                {1, 4, 1},
                {1, 5, 10},
                {2, 4, 2},
                {3, 4, 1},
                {3, 5, 1},
                {4, 5, 3},
                {3, 5, 10},
                {3, 1, 8},
                {1, 4, 2},
                {5, 1, 7},
                {3, 4, 2},
                {5, 2, 4}
        };

        int[][] answer = {
                {0 ,2, 3, 1, 4},
                {12, 0, 15, 2, 5},
                {8, 5, 0, 1, 1},
                {10, 7, 13, 0, 3},
                {7, 4, 10, 6, 0}
        };

        assertThat(solution(n, path)).isEqualTo(answer);
    }

    private int[][] solution(int cityCount, int[][] path) {

        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <cityCount; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] info : path) {
            int startCity = info[0];
            int endCity = info[1];
            int cost = info[2];
            graph.get(startCity-1).add(new int[] {endCity, cost});
        }

        int[][] costs = new int[cityCount][cityCount];
        for (int city = 1; city <= cityCount; city++) {
            costs[city-1] = dijkstra(cityCount, graph, city);
        }


        StringJoiner sj = new StringJoiner("\n");
        sj.add(String.valueOf(1));


        return costs;
    }

    private int[] dijkstra(int cityCount, List<List<int[]>> graph, int startCity) {
        int[] costs = new int[cityCount+1];
        Arrays.fill(costs, INF);
        costs[startCity] = 0;

        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt( row -> row[1]));
        queue.offer(new int[] {startCity, 0});

        while (!queue.isEmpty()) {

            int[] current = queue.poll();
            int cityNumber = current[0];
            int weight = current[1];

            if (costs[cityNumber] < weight) {
                continue;
            }

            List<int[]> nextCities = graph.get(cityNumber-1);

            for (int[] nextCityInfo :nextCities) {

                int nextCity = nextCityInfo[0];
                int nextCost = nextCityInfo[1];

                int newCost = weight + nextCost;

                if (newCost < costs[nextCity]) {
                    costs[nextCity] = newCost;
                    queue.offer(new int[] {nextCity, newCost});
                }
            }
        }

        return Arrays.copyOfRange(costs, 1, costs.length);
    }
}
