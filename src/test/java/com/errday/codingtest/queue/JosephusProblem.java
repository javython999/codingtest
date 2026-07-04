package com.errday.codingtest.queue;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringJoiner;

import static org.assertj.core.api.Assertions.assertThat;

public class JosephusProblem {

    @Test
    void case1() {
        int n = 7;
        int m = 3;
        String answer = "3, 6, 2, 7, 5, 1, 4";
        assertThat(solution(n, m)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int n = 7;
        int m = 4;
        String answer = "4, 1, 6, 5, 7, 3, 2";
        assertThat(solution(n, m)).isEqualTo(answer);
    }

    private String solution(int n, int m) {
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i < n + 1; i++) {
            queue.offer(i);
        }

        StringJoiner sj = new StringJoiner(", ");
        int loopCount = 0;

        while (!queue.isEmpty()) {
            if (queue.size() == 1) {
                sj.add(String.valueOf(queue.poll()));
                break;
            }

            int current = queue.poll();
            loopCount += 1;

            if (loopCount == m) {
                sj.add(String.valueOf(current));
                loopCount = 0;
            } else {
                queue.offer(current);
            }
        }

        return sj.toString();
    }
}
