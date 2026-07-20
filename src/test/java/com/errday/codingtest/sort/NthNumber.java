package com.errday.codingtest.sort;

import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;

import static org.assertj.core.api.Assertions.assertThat;

public class NthNumber {

    @Test
    void case1() {
        int[][] input = {
                {12, 7, 9, 15, 5},
                {13, 8, 11, 19, 6},
                {21, 10, 26, 31, 16},
                {48, 14, 28, 35, 25},
                {52, 20, 32, 41, 49}
        };
        int n = 5;
        int answer = 35;
        assertThat(solution(input, n)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int[][] input = {
                {12, 7, 9, 15, 5},
                {13, 8, 11, 19, 6},
                {21, 10, 26, 31, 16},
                {48, 14, 28, 35, 25},
                {52, 20, 32, 41, 49}
        };
        int n = 5;
        int answer = 35;
        assertThat(solution(input, n)).isEqualTo(answer);
    }

    private int solution(int[][] input, int n) {
        PriorityQueue<Integer> sorted = new PriorityQueue<>();

        for (int row = 0; row < input.length; row++) {
            for (int col = 0; col < input[row].length; col++) {
                int value = input[row][col];

                if (sorted.size() < n) {
                    sorted.offer(value);
                } else if (sorted.peek() < value) {
                    sorted.poll();
                    sorted.offer(value);
                }
            }
        }

       return sorted.peek();
    }
}
