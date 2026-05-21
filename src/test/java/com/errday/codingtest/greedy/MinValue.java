package com.errday.codingtest.greedy;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class MinValue {

    @Test
    void case1() {
        String input = "100-40+50+74-30+29-45+43+11";
        int answer = -222;
        assertThat(solution(input)).isEqualTo(answer);
    }

    private int solution(String input) {

        String[] parts = input.split("-");
        int[] numbers = new int[parts.length];

        for (int i = 0; i < parts.length; i++) {

            String current = parts[i];
            String[] split = current.split("[+]");

            int[] parseNumbers = Arrays.stream(split)
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int sum = Arrays.stream(parseNumbers)
                    .sum();

            numbers[i] = sum;
        }

        int result = numbers[0];

        for (int i = 1; i < numbers.length; i++) {
            result -= numbers[i];
        }

        return result;
    }

}
