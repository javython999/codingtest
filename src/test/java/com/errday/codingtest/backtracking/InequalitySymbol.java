package com.errday.codingtest.backtracking;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class InequalitySymbol {

    @Test
    void case1() {
        char[] symbols = {'<', '>'};
        String[] answer = {"897", "021"};
        assertThat(solution(symbols)).containsExactlyInAnyOrder(answer);
    }


    private int K;
    private char[] SYMBOLS;
    private boolean[] VISITED;
    private List<String> RESULT;
    private String[] solution(char[] symbols) {
        SYMBOLS = symbols;
        K = symbols.length;
        VISITED = new boolean[10];
        RESULT = new ArrayList<>();


        backtracking(0, "");
        Collections.sort(RESULT, Comparator.reverseOrder());

        return new String[] {RESULT.getFirst(), RESULT.getLast()};
    }

    private void backtracking(int position, String number) {
        if (position == K + 1) {
            RESULT.add(number);
            return;
        }

        for (int i = 0; i < 10; i++) {
            if (VISITED[i]) {
                continue;
            }

            if (number.isEmpty() || check(number.charAt(position - 1) - '0', i, SYMBOLS[position - 1])) {
                VISITED[i] = true;
                backtracking(position + 1, number + i);
                VISITED[i] = false;
            }

        }
    }

    private boolean check(int x, int y, char symbol) {
        if ('<' == symbol) {
            return  x < y;
        }

        return x > y;
    }
}
