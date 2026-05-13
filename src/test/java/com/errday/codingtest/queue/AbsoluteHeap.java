package com.errday.codingtest.queue;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import static org.assertj.core.api.Assertions.assertThat;

public class AbsoluteHeap {

    @Test
    void case1() {
        int n = 18;
        int[] input = {1, -1, 0, 0, 0, 1, 1, -1, -1, 2, -2, 0, 0, 0, 0, 0, 0, 0};
        int[] answer = {-1, 1, 0, -1, -1, 1, 1, -2, 2, 0};
        assertThat(solution(n, input)).containsExactly(answer);
    }

    private int[] solution(int n, int[] input) {

        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> {
            int aAbs = Math.abs(a);
            int bAbs = Math.abs(b);
            if (aAbs == bAbs) {
                return a > b ? 1 : -1;
            }
            return aAbs - bAbs;
        });


        List<Integer> result = new ArrayList<>();
        for (int i : input) {

            if (i == 0) {
                if (queue.isEmpty()) {
                    result.add(0);
                } else {
                    result.add(queue.poll());
                }
            } else {
                queue.add(i);
            }
        }


        return result.stream()
                .mapToInt(Integer::valueOf)
                .toArray();
    }



}
