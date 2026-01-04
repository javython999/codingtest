package com.errday.codingtest.greedy;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class PlusOrMultiple {

    @Test
    void case1() {
        String input = "02984";
        int answer = 576;
        Assertions.assertThat(solution(input)).isEqualTo(answer);
    }

    @Test
    void case2() {
        String input = "567";
        int answer = 210;
        Assertions.assertThat(solution(input)).isEqualTo(answer);
    }

    @Test
    void case3() {
        String input = "123456789";
        int answer = 544320;
        Assertions.assertThat(solution(input)).isEqualTo(answer);
    }

    private int solution(String input) {
        int result = 0;

        int[] numbers = Arrays.stream(input.split(""))
                .mapToInt(Integer::parseInt)
                .toArray();

        for (int number : numbers) {
            if (result <= 1 || number <= 1) {
                result += number;
            } else {
                result *= number;
            }
        }

        return result;
    }
}
