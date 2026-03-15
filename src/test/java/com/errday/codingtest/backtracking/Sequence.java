package com.errday.codingtest.backtracking;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Sequence {

    @Test
    void case1() {
        int n = 3;

        int[][] answer = {
                {1, 2, 3},
                {1, 3, 2},
                {2, 1, 3},
                {2, 3, 1},
                {3, 1, 2},
                {3, 2, 1}
        };

        assertThat(solution(n)).isEqualTo(answer);
    }

    private boolean[] visited;
    private int[] current;
    private int N;

    private int[][] solution(int n) {
        N = n;
        visited = new boolean[N];
        current = new int[N];
        List<int[]> memory = new ArrayList<>();
        dfs(0, memory);
        return memory.toArray(new int[0][]);
    }

    private void dfs(int depth, List<int[]> memory) {

        if (depth == N) {
            memory.add(current.clone());
            return;
        }


        for (int i = 0; i < N; i++) {

            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            current[depth] = i + 1;

            dfs(depth + 1, memory);

            visited[i] = false;
        }


    }
}
