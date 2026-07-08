package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RepeatedPermutation {

    @Test
    void case1() {
        int a = 57;
        int p = 2;
        int answer = 4;
        assertThat(solution(a, p)).isEqualTo(answer);
    }

    private int solution(int a, int p) {
        List<Integer> list = new ArrayList<>();
        list.add(a);

        int before = a;
        int now;

        while (true) {
            char[] chars = String.valueOf(before).toCharArray();
            now = 0;
            for (char c : chars) {
                int temp = c - '0';
                now += (int) Math.pow(temp, p);
            }

            if (list.contains(now)) {
                break;
            }

            list.add(now);
            before = now;
        }

        return list.indexOf(now);
    }
}
