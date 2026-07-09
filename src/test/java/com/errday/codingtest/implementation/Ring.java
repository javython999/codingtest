package com.errday.codingtest.implementation;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Ring {

    @Test
    void case1() {
        int[] rings = {8, 4, 2};
        String[] answer = {"2/1", "4/1"};
        Assertions.assertThat(solution(rings)).containsExactlyInAnyOrder(answer);
    }

    @Test
    void case2() {
        int[] rings = {12, 3, 8, 4};
        String[] answer = {"4/1", "3/2", "3/1"};
        Assertions.assertThat(solution(rings)).containsExactlyInAnyOrder(answer);
    }

    private String[] solution(int[] rings) {
        List<String> answer = new ArrayList<>();

        int firstValue = rings[0];
        for (int next = 1; next < rings.length; next++) {
            int gcdValue = gcd(Math.max(firstValue, rings[next]), Math.min(rings[next], firstValue));
            answer.add(firstValue / gcdValue + "/" + rings[next] / gcdValue);
        }

        return answer.toArray(new String[0]);
    }

    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
