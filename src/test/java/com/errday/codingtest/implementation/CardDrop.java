package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;

public class CardDrop {

    @Test
    void case1() {
        int n = 7;
        int[] answer = {1, 3, 5, 7, 4, 2, 6};
        assertThat(solution(n)).containsExactly(answer);
    }

    private int[] solution(int n) {
        List<Integer> result = new ArrayList<>();

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i < n + 1; i++) {
            queue.offer(i);
        }

        int loop = 0;
        while (!queue.isEmpty()) {
            int current = queue.poll();
            loop += 1;

            if (loop % 2 != 0) {
                result.add(current);
            } else {
                queue.offer(current);
            }
        }

        return result.stream()
                .mapToInt(Integer::valueOf)
                .toArray();
    }
}
