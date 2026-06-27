package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Unknown {

    @Test
    void case1() {
        String[] input1 = {"ohhenrie", "charlie", "baesangwook"};
        String[] input2 = {"obama", "baesangwook", "ohhenrie", "clinton"};
        String[] answer = {"baesangwook", "ohhenrie"};
        assertThat(solution(input1, input2)).containsExactly(answer);
    }

    private String[] solution(String[] input1, String[] input2) {
        Arrays.sort(input1);
        Arrays.sort(input2);

        List<String> answer = new ArrayList<>();
        for (String x : input1) {
            if (Arrays.binarySearch(input2, x) >= 0) {
                answer.add(x);
            }
        }

        return answer.toArray(String[]::new);
    }
}
