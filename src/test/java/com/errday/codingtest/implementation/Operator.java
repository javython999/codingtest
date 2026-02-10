package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Operator {

    private final int ADD = 0;
    private final int SUB = 1;
    private final int MUL = 2;
    private final int DIV = 3;
    private int[] NUMBERS;
    private int min;
    private int max;
    private int numberCount;

    @Test
    void case1() {
        int[] numbers = {5, 6};
        int[] operator = {0, 0, 1, 0};

        int[] answer = {30, 30};
        assertThat(solution(numbers, operator)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int[] numbers = {3, 4, 5};
        int[] operator = {1, 0, 1, 0};

        int[] answer = {35, 17};
        assertThat(solution(numbers, operator)).isEqualTo(answer);
    }

    @Test
    void case3() {
        int[] numbers = {1, 2, 3, 4, 5, 6};
        int[] operator = {2, 1, 1, 1};

        int[] answer = {54, -24};
        assertThat(solution(numbers, operator)).isEqualTo(answer);
    }

    private int[] solution(int[] numbers, int[] operators) {
        NUMBERS = numbers;
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
        numberCount = numbers.length;

        dfs(1, NUMBERS[0], operators);

        return new int[] {max, min};
    }

    private void dfs(int index, int acc, int[] operators) {
        if (index == numberCount) {
            min = Math.min(min, acc);
            max = Math.max(max, acc);
            return;
        }

        for (int operatorIndex = 0; operatorIndex < operators.length; operatorIndex++) {
            if (operators[operatorIndex] == 0) {
                continue;
            }

            operators[operatorIndex] -= 1;
            dfs(index + 1, calculate(operatorIndex, acc, NUMBERS[index]), operators);
            operators[operatorIndex] += 1;
        }

    }

    private int calculate(int operator, int a, int b) {
        return switch (operator) {
            case ADD -> a + b;
            case SUB -> a - b;
            case MUL -> a * b;
            case DIV -> a / b;
            default -> throw new IllegalArgumentException();
        };
    }
}
