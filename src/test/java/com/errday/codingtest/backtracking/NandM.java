package com.errday.codingtest.backtracking;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class NandM {

    @Test
    void case1() {
        int n = 3;
        int m = 1;
        solution(n, m);
    }

    @Test
    void case2() {
        int n = 4;
        int m = 2;
        solution(n, m);
    }

    @Test
    void case3() {
        int n = 4;
        int m = 4;
        solution(n, m);
    }


    private int N;
    private int M;
    private boolean[] visited;
    private int[] temp;
    private List<String> memory = new ArrayList<>();

    private void solution(int n, int m) {

        N = n;
        M = m;
        visited = new boolean[n];
        temp = new int[n];

        backtracking(0);

        for (String s : memory) {
            System.out.println(s);
        }
    }

    private void backtracking(int length) {

        if (length == M) {
            memory.add(output());
            return;
        }

        for (int i = 0; i < N; i++) {

            if (visited[i]) {
                continue;
            }

            visited[i] = true;

            temp[length] = i + 1;
            backtracking(length + 1);
            visited[i] = false;

        }
    }

    private String output() {
        StringJoiner joiner = new StringJoiner(" ");

        for (int i = 0; i < M; i++) {
            joiner.add(String.valueOf(temp[i]));
        }

        return joiner.toString();
    }
}
