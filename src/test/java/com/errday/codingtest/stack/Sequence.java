package com.errday.codingtest.stack;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.StringJoiner;

import static org.assertj.core.api.Assertions.assertThat;

public class Sequence {

    @Test
    void case1() {
        int n = 8;
        int[] input = {4, 3, 6, 8, 7, 5, 2, 1};
        String answer = "+ + + + - - + + - + + - - - - -";
        assertThat(solution(n, input)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int n = 5;
        int[] input = {1, 2, 5, 3, 4};
        String answer = "NO";
        assertThat(solution(n, input)).isEqualTo(answer);
    }

    private String solution(int n, int[] input) {

        StringJoiner sj = new StringJoiner(" ");
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int number = 1;
        for (int i = 0; i < n; i++) {

            int target = input[i];

            if (target >= number) {
                while (target >= number) {
                    stack.push(number);
                    number++;
                    sj.add("+");
                }

                stack.pop();
                sj.add("-");
            } else {
                int popped = stack.pop();

                if (popped > target) {
                    return "NO";
                }

                sj.add("-");
            }


        }

        return sj.toString();
    }
}
