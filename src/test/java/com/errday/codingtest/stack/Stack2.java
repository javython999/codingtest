package com.errday.codingtest.stack;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Stack2 {

    @Test
    void case1() {
        int[][] commands = {
                {4},
                {1, 3},
                {1, 5},
                {3},
                {2},
                {5},
                {2},
                {2},
                {5}
        };
        int[] answer = {1, 2, 5, 3, 3, -1, -1};
        assertThat(solution(commands)).containsExactly(answer);
    }

    private int[] solution(int[][] commands) {
        List<Integer> result = new ArrayList<>();
        MyStack stack = new MyStack();

        for (int[] command : commands) {
            if (command[0] == 1) {
                stack.push(command[1]);
                continue;
            }

            if (command[0] == 2) {
                result.add(stack.pop());
                continue;
            }

            if (command[0] == 3) {
                result.add(stack.size());
                continue;
            }

            if (command[0] == 4) {
                result.add(stack.isEmpty());
                continue;
            }

            if (command[0] == 5) {
                result.add(stack.peek());
            }

        }

        return result.stream().mapToInt(Integer::valueOf).toArray();
    }

    private class MyStack {

        private List<Integer> memory = new ArrayList<>();

        public void push(int x) {
            memory.addFirst(x);
        }

        public int pop() {
            if (memory.isEmpty()) {
                return -1;
            }
            return memory.removeFirst();
        }

        public int size() {
            return memory.size();
        }

        public int isEmpty() {
            return memory.isEmpty() ? 1 : 0;
        }

        public int peek() {
            if (memory.isEmpty()) {
                return -1;
            }
            return memory.getFirst();
        }

    }
}
