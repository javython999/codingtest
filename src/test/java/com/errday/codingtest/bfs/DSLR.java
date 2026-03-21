package com.errday.codingtest.bfs;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;

public class DSLR {

    @Test
    void case1() {
        String input = "1234 3412";
        String answer = "LL";
        assertThat(solution(input)).isEqualTo(answer);
    }

    @Test
    void case2() {
        String input = "1000 1";
        String answer = "L";
        assertThat(solution(input)).isEqualTo(answer);
    }

    @Test
    void case3() {
        String input = "1 16";
        String answer = "DDDD";
        assertThat(solution(input)).isEqualTo(answer);
    }

    private String solution(String input) {
        String[] split = input.split(" ");
        int start = Integer.parseInt(split[0]);
        int end = Integer.parseInt(split[1]);

        int[] parents = new int[10_000];
        char[] commands = new char[10_000];
        boolean[] visited = new boolean[10_000];

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            if (current == end) {
                break;
            }

            processD(current, visited, parents, commands, queue);
            processS(current, visited, parents, commands, queue);
            processL(current, visited, parents, commands, queue);
            processR(current, visited, parents, commands, queue);

        }

        return processResult(start, end, parents, commands);

    }

    private void processD(int register, boolean[] visited, int[] parents, char[] commands, Queue<Integer> queue) {
        int value = (register * 2) % 10_000;
        if (!visited[value]) {
            visited[value] = true;
            parents[value] = register;
            commands[value] = 'D';
            queue.offer(value);
        }
    }

    private void processS(int register, boolean[] visited, int[] parents, char[] commands, Queue<Integer> queue) {
        int value = register == 0 ? 9999 : register - 1;
        if (!visited[value]) {
            visited[value] = true;
            parents[value] = register;
            commands[value] = 'S';
            queue.add(value);
        }
    }

    private void processL(int register, boolean[] visited, int[] parents, char[] commands, Queue<Integer> queue) {
        int value = (register % 1000) * 10 + (register / 1000);
        if (!visited[value]) {
            visited[value] = true;
            parents[value] = register;
            commands[value] = 'L';
            queue.offer(value);
        }
    }

    private void processR(int register, boolean[] visited, int[] parents, char[] commands, Queue<Integer> queue) {
        int value = (register % 10) * 1000 + (register / 10);
        if (!visited[value]) {
            visited[value] = true;
            parents[value] = register;
            commands[value] = 'R';
            queue.add(value);
        }
    }

    private String processResult(int start, int end, int[] parents, char[] commands) {
        int current = end;
        StringBuilder sb = new StringBuilder();
        while (current != start) {
            sb.append(commands[current]);
            current = parents[current];
        }
        return sb.reverse().toString();
    }
}
