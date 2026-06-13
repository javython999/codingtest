package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class RoomNumber {

    @Test
    void case1() {
        int roomNumber = 9999;
        int answer = 2;
        assertThat(solution(roomNumber)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int roomNumber = 122;
        int answer = 2;
        assertThat(solution(roomNumber)).isEqualTo(answer);
    }

    @Test
    void case3() {
        int roomNumber = 888888;
        int answer = 6;
        assertThat(solution(roomNumber)).isEqualTo(answer);
    }

    private int solution(int roomNumber) {
        int[] numbers = new int[10];

        int temp = roomNumber;

        while (temp > 0) {
            int now = temp % 10;
            numbers[now] += 1;
            temp = temp / 10;
        }

        numbers[6] = (numbers[6] + numbers[9]) / 2;
        numbers[9] = 0;

        return Arrays.stream(numbers)
                .max()
                .getAsInt();
    }
}
