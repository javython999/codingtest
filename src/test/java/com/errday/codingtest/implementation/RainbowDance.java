package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class RainbowDance {

    @Test
    void case1() {
        String[][] people = {
                {"bnb2011", "chansol"},
                {"chansol", "chogahui05"},
                {"chogahui05", "jthis"},
                {"jthis", "ChongChong"},
                {"jthis", "jyheo98"},
                {"jyheo98", "lms0806"},
                {"lms0806", "pichulia"},
                {"pichulia", "pjshwa"},
                {"pjshwa", "r4pidstart"},
                {"r4pidstart", "swoon"},
                {"swoon", "tony9402"},
                {"tony9402", "bnb2011"}
        };
        int answer = 10;
        assertThat(solution(people)).isEqualTo(answer);
    }

    private int solution(String[][] people) {
        Set<String> set = new HashSet<>();
        set.add("ChongChong");

        for (String[] p : people) {

            String p1 = p[0];
            String p2 = p[1];

            if (set.contains(p1) || set.contains(p2)) {
                set.add(p1);
                set.add(p2);
            }
        }

        return set.size();
    }
}
