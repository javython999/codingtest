package com.errday.codingtest.implementation;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.*;

public class StringSort {

    @Test
    void case1() {
        String input = "K1KA5CB7";
        String answer = "ABCKK13";
        Assertions.assertThat(solution(input)).isEqualTo(answer);
    }

    @Test
    void case2() {
        String input = "AJKDLSI412K4JSJ9D";
        String answer = "ADDIJJJKKLSS20";
        Assertions.assertThat(solution(input)).isEqualTo(answer);
    }

    private String solution(String source) {

        String letters = source.chars()
                .filter(c -> c < '0' || c > '9')
                .sorted()
                .mapToObj(c -> String.valueOf((char) c))
                .collect(Collectors.joining());

        int digit = source.chars()
                .filter(c -> c >= '0' && c <= '9')
                .map(c -> c - '0')
                .sum();

        return letters + (digit == 0 ? "" : digit);
    }

}
