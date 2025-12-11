package com.errday.codingtest.greedy;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class Atm {


    private int[] times = {3, 1, 4, 3, 2};

    private int answer = 32;

    @Test
    void solution1() {

        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(x -> x[1]));

        for (int i = 0; i < times.length; i++) {
            queue.offer(new int[] {i, times[i]});
        }

        int totalTime = 0;
        int remainPersonCount = times.length;

        while (!queue.isEmpty()) {
            int[] timeInfo = queue.poll();
            totalTime += remainPersonCount * timeInfo[1];
            remainPersonCount -= 1;
        }

        Assertions.assertThat(totalTime).isEqualTo(answer);
    }

    @Test
    void solution2() {

        int count = times.length;
        Arrays.sort(times);

        int totalTime = 0;
        
        for (int time : times) {
            totalTime += time * count;
            count -= 1;
        }

        Assertions.assertThat(totalTime).isEqualTo(answer);
    }
}
