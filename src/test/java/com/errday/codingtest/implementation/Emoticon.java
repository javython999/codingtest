package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class Emoticon {

    @Test
    void case1() {
        String[] chat = {
                "ENTER", "pjshwa", "chansol", "chogahui05", "lms0806", "pichulia", "r4pidstart", "swoon", "tony9402"
        };
        int answer = 8;
        assertThat(solution(chat)).isEqualTo(answer);
    }

    @Test
    void case2() {
        String[] chat = {"ENTER", "pjshwa", "chansol", "chogahui05",  "ENTER", "pjshwa", "chansol"};
        int answer = 5;
        assertThat(solution(chat)).isEqualTo(answer);
    }

    @Test
    void case3() {
        String[] chat = {"ENTER", "lms0806", "lms0806"};
        int answer = 1;
        assertThat(solution(chat)).isEqualTo(answer);
    }

    private int solution(String[] chat) {
        int emoticonCount = 0;
        Set<String> user = new HashSet<>();

        for (String c : chat) {
            if ("ENTER".equals(c)) {
                emoticonCount += user.size();
                user.clear();
            } else {
                user.add(c);
            }
        }

        emoticonCount += user.size();


        return emoticonCount;
    }
}
