package com.errday.codingtest.parametericsearch;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class Cutting {

    private int n = 6;
    private int[] data = {19, 15, 10, 17};
    private int answer = 15;


    @Test
    void solution() {
        Arrays.sort(data);
        int high = data[data.length - 1];
        int low = 0;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (simulation(data, mid) < n) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        Assertions.assertThat(high).isEqualTo(answer);

    }

    private int simulation(int[] data, int standard) {

        int total = 0;

        for (int item : data) {

            if (item > standard) {
                total += item - standard;
            }

        }

        return total;
    }
}
