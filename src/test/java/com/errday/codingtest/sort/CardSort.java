package com.errday.codingtest.sort;

import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;

public class CardSort {

    @Test
    void case1() {
        int[] decks = {10, 40, 20};

        int answer = 100;

        assertThat(solution(decks)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int[] decks = {10};

        int answer = 0;

        assertThat(solution(decks)).isEqualTo(answer);
    }

    @Test
    void case3() {
        int[] decks = {10, 10, 10, 10};

        int answer = 80;

        assertThat(solution(decks)).isEqualTo(answer);
    }

    private int solution(int[] decks) {
        int result = 0;

        Queue<Integer> queue = new PriorityQueue<>();
        for (int deck : decks) {
            queue.offer(deck);
        }

        while (queue.size() != 1) {
            int first = queue.poll();
            int second = queue.poll();

            int sum = first + second;
            result += sum;
            queue.offer(sum);
        }

        return result;
    }


}
