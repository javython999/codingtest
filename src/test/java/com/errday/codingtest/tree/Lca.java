package com.errday.codingtest.tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;

public class Lca {

    @BeforeEach
    void warmup() {
        int n = 100;
        int[][] input = new int[n - 1][2];
        for (int i = 1; i < n; i++) {
            input[i - 1] = new int[]{i, i + 1};
        }
        for (int i = 0; i < 200; i++) {
            solution(n, input, 50, 100);
            fastLca(n, input, 50, 100);
        }
    }

    @Test
    void case1() {
        int n = 16;
        int[][] input = {
                {1, 14},
                {8, 5},
                {10, 16},
                {5, 9},
                {4, 6},
                {8, 4},
                {4, 10},
                {1, 13},
                {6, 15},
                {10, 11},
                {6, 7},
                {10, 2},
                {16, 3},
                {8, 1},
                {16, 12}
        };
        int a = 16;
        int b = 7;
        int answer = 4;
        assertThat(solution(n, input, a, b)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int n = 5;
        int[][] input = {
                {2, 3},
                {3, 4},
                {3, 1},
                {1, 5}
        };
        int a = 3;
        int b = 5;
        int answer = 3;
        assertThat(solution(n, input, a, b)).isEqualTo(answer);
    }

    @Test
    void case3() {
        int n = 50000;
        int[][] input = new int[n - 1][2];
        for (int i = 1; i < n; i++) {
            input[i - 1] = new int[]{i, i + 1};
        }
        int a = 25000;
        int b = 50000;
        int answer = 25000;
        assertThat(solution(n, input, a, b)).isEqualTo(answer);
    }

    @Test
    void case4() {
        int n = 50000;
        int[][] input = new int[n - 1][2];
        for (int i = 1; i < n; i++) {
            input[i - 1] = new int[]{i, i + 1};
        }
        int a = 25000;
        int b = 50000;
        int answer = 25000;
        assertThat(fastLca(n, input, a, b)).isEqualTo(answer);
    }


    private int solution(int n, int[][] input, int a, int b) {

        int[] tree = new int[n + 1];

        for (int[] edge : input) {
            int parent = edge[0];
            int child = edge[1];
            tree[child] = parent;
        }

        int[] levels = new int[n + 1];

        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 1; i < n + 1; i++) {
            if (tree[i] == 0) {
                queue.offer(new int[] {i, 1});
            }
        }

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int node = current[0];
            int level = current[1];
            levels[node] = level;

            for (int next = 1; next < n + 1; next++) {
                if (tree[next] == node) {
                    queue.offer(new int[] {next, level + 1});
                }
            }
        }

        while (levels[a] > levels[b]) {
            a = tree[a];
        }

        while (levels[a] < levels[b]) {
            b = tree[b];
        }

        while (a != b) {
            a = tree[a];
            b = tree[b];
        }

        return a;
    }

    private int fastLca(int n, int[][] input, int a, int b) {

        // 1. log 상한선 구하기 (2^log > n인 최소 log 크기)
        int log = 0;
        while ((1 << log) <= n) {
            log++;
        }

        // 2. 그래프 생성
        int[] childCount = new int[n + 1];
        boolean[] hasParent = new boolean[n + 1];
        for (int[] edge : input) {
            childCount[edge[0]]++;
            hasParent[edge[1]] = true;
        }

        int[][] tree = new int[n + 1][];
        for (int i = 0; i <= n; i++) {
            tree[i] = new int[childCount[i]];
        }

        int[] idx = new int[n + 1];
        for (int[] edge : input) {
            int parent = edge[0];
            int child = edge[1];
            tree[parent][idx[parent]++] = child;
        }

        int[] levels = new int[n + 1];
        int[][] parents = new int[n + 1][log];

        // 3. 진입 차수(Parent 개수)가 0인 노드들을 찾아 루트로 지정하고 BFS 시작
        int[] bfsQueue = new int[n + 1];
        int head = 0, tail = 0;
        for (int i = 1; i <= n; i++) {
            if (!hasParent[i]) {
                bfsQueue[tail++] = i;
                levels[i] = 1;
            }
        }

        // 4. BFS 돌면서 levels 배열과 2^0(바로 위 부모) 채우기
        while (head < tail) {
            int current = bfsQueue[head++];

            for (int next : tree[current]) {
                levels[next] = levels[current] + 1;
                parents[next][0] = current;
                bfsQueue[tail++] = next;
            }
        }

        // 5. DP를 이용해 희소 배열(Sparse Table) 완성
        for (int k = 1; k < log; k++) {
            for (int i = 1; i < n + 1; i++) {
                if (parents[i][k - 1] != 0) {
                    parents[i][k] = parents[parents[i][k - 1]][k - 1];
                }
            }
        }

        // LCA
        // 항상 b가 더 깊은 노드가 되도록 세팅
        if (levels[a] > levels[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        for (int k = log - 1; k >= 0; k--) {
            if (levels[b] - levels[a] >= (1 << k)) {
                b = parents[b][k];
            }
        }

        if (a == b) {
            return a;
        }

        for (int k = log - 1; k >= 0; k--) {
            if (parents[a][k] != parents[b][k]) {
                a = parents[a][k];
                b = parents[b][k];
            }
        }

        return parents[a][0];
    }
}
