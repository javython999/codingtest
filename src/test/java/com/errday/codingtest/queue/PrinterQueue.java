package com.errday.codingtest.queue;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;

public class PrinterQueue {

    @Test
    void case1() {
        int n = 1;
        int m = 0;
        int[] data = {5};
        int answer = 1;
        assertThat(solution(n, m, data)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int n = 4;
        int m = 2;
        int[] data = {1, 2, 3, 4};
        int answer = 2;
        assertThat(solution(n, m, data)).isEqualTo(answer);
    }

    @Test
    void case3() {
        int n = 6;
        int m = 0;
        int[] data = {1, 1, 9, 1, 1, 1};
        int answer = 5;
        assertThat(solution(n, m, data)).isEqualTo(answer);
    }

    private int solution(int n, int m, int[] data) {
        Queue<int[]> printerQueue = new ArrayDeque<>();
        int maxPriority = 0;
        int[] maxPriorities = new int[10];

        for (int i = 0; i < data.length; i++) {
            printerQueue.offer(new int[] {i, data[i]});
            maxPriority = Math.max(maxPriority, data[i]);
            maxPriorities[data[i]] += 1;
        }

        int sequence = 1;
        while (!printerQueue.isEmpty()) {
            int[] current = printerQueue.poll();
            int currentIndex = current[0];
            int currentPriority = current[1];

            if (currentPriority == maxPriority) {
                if (currentIndex == m) {
                    return sequence;
                }
                sequence += 1;

                maxPriorities[currentPriority] -= 1;
                if (maxPriorities[currentPriority]  == 0) {
                    for (int p = maxPriority-1; p > 0; p--) {
                        if (maxPriorities[p] > 0) {
                            maxPriority = p;
                            break;
                        }
                    }
                }
            } else {
                printerQueue.offer(current);
            }

        }
        return -1;
    }
}
