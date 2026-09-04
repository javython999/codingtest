package com.errday.codingtest.greedy;

import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;

import static org.assertj.core.api.Assertions.assertThat;

public class CardSum {

    @Test
    void case1() {
        int m = 1;
        long[] cards = {3, 2, 6};
        int answer = 16;
        assertThat(solution(m, cards)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int m = 2;
        long[] cards = {4, 2, 3, 1};
        int answer = 19;
        assertThat(solution(m, cards)).isEqualTo(answer);
    }

    private long solution(int m, long[] cards) {

        PriorityQueue<Long> queue = new PriorityQueue<>();
        for (long card : cards) {
            queue.offer(card);
        }

        for (int loop = 0; loop < m; loop++) {
            long a = queue.poll();
            long b = queue.poll();
            queue.offer(a + b);
            queue.offer(a + b);
        }

        return queue.stream()
                .mapToLong(Long::longValue)
                .sum();
    }
}
