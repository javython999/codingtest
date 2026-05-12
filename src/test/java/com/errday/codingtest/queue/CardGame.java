package com.errday.codingtest.queue;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;

public class CardGame {

    @Test
    void case1() {
        int n = 6;
        int answer = 4;
        assertThat(solution(n)).isEqualTo(answer);
    }

    private int solution(int n) {

        Queue<Integer> deque  = new ArrayDeque<>();

        for (int i = 1; i < n + 1; i++) {
            deque.offer(i);
        }

        int loop = 1;
        while (deque.size() > 1) {

            if (loop % 2 != 0) {
                deque.poll();
            } else {
                deque.add(deque.poll());
            }

            loop += 1;
        }

        return deque.poll();
    }
}
