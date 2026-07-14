package com.errday.codingtest.greedy;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GasStation {

    @Test
    void case1() {
        int[] distances = {2, 3, 1};
        int[] cities = {5, 2, 4, 1};
        long answer = 18;
        assertThat(solution(distances, cities)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int[] distances = {3, 3, 4};
        int[] cities = {1, 1, 1, 1};
        long answer = 10;
        assertThat(solution(distances, cities)).isEqualTo(answer);
    }

    private long solution(int[] distances, int[] cities) {

        long sum = 0;
        long minValue = cities[0];

        for (int i = 0; i < distances.length; i++) {
            if (cities[i] < minValue) {
                minValue = cities[i];
            }

            sum += (minValue * distances[i]);
        }

        return sum;
    }
}
