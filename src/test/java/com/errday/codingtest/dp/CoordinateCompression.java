package com.errday.codingtest.dp;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class CoordinateCompression {

    @Test
    void case1() {
        int[] coordinates = {2, 4, -10, 4, -9};
        int[] answer = {2, 3, 0, 3, 1};
        assertThat(solution(coordinates)).containsExactly(answer);
    }

    @Test
    void case2() {
        int[] coordinates = {1000, 999, 1000, 999, 1000, 999};
        int[] answer = {1, 0, 1, 0, 1, 0};
        assertThat(solution(coordinates)).containsExactly(answer);
    }

    private int[] solution(int[] coordinates) {
        int[] temp = Arrays.copyOf(coordinates, coordinates.length);
        Arrays.sort(temp);
        Map<Integer, Integer> indexMap = new HashMap<>();
        int index = 0;
        for (int coordinate : temp) {
            if (!indexMap.containsKey(coordinate)) {
                indexMap.put(coordinate, index);
                index += 1;
            }
        }

        int[] result = new int[coordinates.length];
        for (int i = 0; i < coordinates.length; i++) {
            result[i] = indexMap.get(coordinates[i]);
        }

        return result;
    }
}
