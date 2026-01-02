package com.errday.codingtest.topologysort;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class Curriculum {

    private int n = 5;
    private int[][] datas = {
            {10, -1},
            {10, 1, -1},
            {4, 1, -1},
            {4, 3 , 1, -1},
            {3, 3, -1}
    };
    private int[] answer = {10, 20, 14, 18, 17};

    @Test
    void solution() {
        int[] indegree = new int[n + 1];
        int[] time = new int[n + 1];

        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 1; i < n + 1; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int row = 0; row < datas.length; row++) {
            int[] rowData = datas[row];

            time[row+1] = rowData[0];

            for (int col = 1; col < rowData.length - 1; col++) {
                indegree[row+1] += 1;
                graph.get(rowData[col]).add(row+1);
            }
        }

        int[] result = topologySort(indegree, time, graph);
        int[] myAnswer = Arrays.copyOfRange(result, 1, result.length);
        Assertions.assertThat(myAnswer).isEqualTo(answer);
    }

    private int[] topologySort(int[] indegree,  int[] time, Map<Integer, List<Integer>> graph) {
        int[] result = Arrays.copyOf(time, time.length);

        ArrayDeque<Integer> queue = new ArrayDeque<>();

        // 진입차수 0인 노드 queue에 offer
        for (int i = 1; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            Integer vertex = queue.poll();

            // 현재 노드를 선행 과목으로 가진 과목들
            List<Integer> tos = graph.get(vertex);
            for (Integer to : tos) {

                // 현재 노드를 선행 과목의 누적 시간 vs 현재 노드의 시간 + 후행 과목의 수강 시간 비교후 큰 값으로 수정
                result[to] = Math.max(result[to], result[vertex] + time[to]);
                indegree[to] -= 1;
                if (indegree[to] == 0) {
                    queue.offer(to);
                }
            }
        }

        return result;
    }
}
