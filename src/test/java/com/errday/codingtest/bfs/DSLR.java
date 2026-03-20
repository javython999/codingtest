package com.errday.codingtest.bfs;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
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

    private String EMPTY = "empty";
    private String solution(String input) {
        String[] split = input.split(" ");
        int start = Integer.parseInt(split[0]);
        int end = Integer.parseInt(split[1]);

        String[] memory = new String[10000];

        Arrays.fill(memory, EMPTY);

        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(start, ""));
        memory[start] = "";

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.value == end) {
                return current.command;
            }

            processD(current, memory, queue);
            processS(current, memory, queue);
            processL(current, memory, queue);
            processR(current, memory, queue);
        }


        return "ERROR";
    }

    private void processD(Node node, String[] memory, Queue<Node> queue) {
        int value = node.value * 2;
        if (10_000 < value) {
            value = value % 10_000;
        }

        if (EMPTY.equals(memory[value])) {
            String command = node.command + "D";
            memory[value] = command;
            queue.offer(new Node(value, command));
        }
    }

    private void processS(Node node, String[] memory, Queue<Node> queue) {
        int value = node.value - 1;
        if (value < 0) {
            value = 9999;
        }

        if (EMPTY.equals(memory[value])) {
            String command = node.command + "S";
            memory[value] = command;
            queue.offer(new Node(value, command));
        }
    }

    private void processL(Node node, String[] memory, Queue<Node> queue) {
        String strNumber = node.strValue;
        String first = strNumber.substring(0, 1);
        String second = strNumber.substring(1);

        int value = Integer.parseInt(second+first);

        if (EMPTY.equals(memory[value])) {
            String command = node.command + "L";
            memory[value] = command;
            queue.offer(new Node(value, command));
        }
    }

    private void processR(Node node, String[] memory, Queue<Node> queue) {
        String strNumber = node.strValue;
        String first = strNumber.substring(0, strNumber.length() - 2);
        String second = strNumber.substring(strNumber.length() - 1);

        int value = Integer.parseInt(second + first);

        if (EMPTY.equals(memory[value])) {
            String command = node.command + "R";
            memory[value] = command;
            queue.offer(new Node(value, command));
        }
    }

    private class Node {
        int value;
        String strValue;
        String command;

        public Node(int value, String command) {
            this.value = value;
            this.strValue = leftPadding(value);
            this.command = command;
        }

        private static String leftPadding(int value) {
            int loopCount = 4 - String.valueOf(value).length();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < loopCount; i++) {
                sb.append("0");
            }
            return sb.toString() + value;
        }
    }
}
