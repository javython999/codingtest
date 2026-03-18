package com.errday.codingtest.dfs;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class DfsAndBfs {

    @Test
    void case1() {
        int n = 4;
        int m = 5;
        int v = 1;
        String[] input = {"1 2", "1 3", "1 4", "2 4", "3 4"};

        String answer = "1 2 4 3\n1 2 3 4";

        assertThat(solution(n, m, v, input)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int n = 5;
        int m = 5;
        int v = 3;
        String[] input = {"5 4", "5 2", "1 2", "3 4", "3 1"};

        String answer = "3 1 2 5 4\n3 1 4 2 5";

        assertThat(solution(n, m, v, input)).isEqualTo(answer);
    }

    @Test
    void case3() {
        int n = 1000;
        int m = 1;
        int v = 1000;
        String[] input = {"999 1000"};

        String answer = "1000 999\n1000 999";

        assertThat(solution(n, m, v, input)).isEqualTo(answer);
    }


    private String solution(int n, int m, int v, String[] input) {

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(input[i]);
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        for (int i = 1; i < n + 1; i++) {
            Collections.sort(graph.get(i));
        }


        StringBuilder sb = new StringBuilder();
        sb.append(dfs(graph, v))
                .append("\n")
                .append(Bfs(graph, v));


        return sb.toString();
    }

    private String dfs(List<List<Integer>> graph, int start) {
        StringJoiner sj = new StringJoiner(" ");
        boolean[] visited = new boolean[graph.size()];
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(start);

        while (!stack.isEmpty()) {
            Integer current = stack.pop();

            if (visited[current]) {
                continue;
            }
            visited[current] = true;
            sj.add(String.valueOf(current));

            List<Integer> nexts = graph.get(current);
            for (int i = nexts.size() - 1; i >= 0; i--) {
                int next = nexts.get(i);

                if (!visited[next]) {
                    stack.push(nexts.get(i));
                }
            }

        }

        return sj.toString();
    }


    private String Bfs(List<List<Integer>> graph, int start) {
        StringJoiner sj = new StringJoiner(" ");
        boolean[] visited = new boolean[graph.size()];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            Integer current = queue.poll();
            sj.add(String.valueOf(current));

            List<Integer> nexts = graph.get(current);

            for (Integer next : nexts) {
                if (!visited[next]) {
                    queue.offer(next);
                    visited[next] = true;
                }
            }
        }

        return sj.toString();
    }
}
