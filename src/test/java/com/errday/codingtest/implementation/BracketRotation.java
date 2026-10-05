package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class BracketRotation {
    @Test
    void case1() {
        String bracket = "[](){}";
        int answer = 3;

        assertThat(solution(bracket)).isEqualTo(answer);
    }

    @Test
    void case2() {
        String bracket = "}]()[{";
        int answer = 2;

        assertThat(solution(bracket)).isEqualTo(answer);
    }

    @Test
    void case3() {
        String bracket = "[)(]";
        int answer = 0;

        assertThat(solution(bracket)).isEqualTo(answer);
    }

    @Test
    void case4() {
        String bracket = "}}}";
        int answer = 0;

        assertThat(solution(bracket)).isEqualTo(answer);
    }

    private int solution(String input) {
        if (input.length() % 2 == 1) {
            return 0;
        }

        int answer = 0;

        Map<Character, Character> pairMap = new HashMap<>();
        pairMap.put(']', '[');
        pairMap.put('}', '{');
        pairMap.put(')', '(');

        String rotation = input + input;

        OUT_LOOP:for (int start = 0; start < input.length(); start++) {
            ArrayDeque<Character> stack = new ArrayDeque<>();

            for (int pointer = start; pointer < start + input.length(); pointer++) {
                char c = rotation.charAt(pointer);

                if (pairMap.containsKey(c)) {
                    if (stack.isEmpty() || stack.pop() != pairMap.get(c)) {
                        continue OUT_LOOP;
                    }
                }
                else {
                    stack.push(c);
                }

            }

            if (stack.isEmpty()) {
                answer += 1;
            }
        }




        return answer;
    }
}
