package com.errday.codingtest.backtracking;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SumOfSequence {

    @Test
    void case1() {
        int[] seq = {-7, -3, -2, 5, 8};
        int s = 0;
        int answer = 1;
        assertThat(solution(seq, s)).isEqualTo(answer);
    }

    private int[] SEQ;
    private int TARGET;
    private int COUNT;
    private int solution(int[] seq, int s) {
        SEQ = seq;
        TARGET = s;
        backtracking(0, 0, 0);
        return COUNT;
    }

    private void backtracking(int position, int sum, int sequenceLength) {
        if (position == SEQ.length) {
            if (sum == TARGET && 0 < sequenceLength) {
                COUNT += 1;
            }
            return;
        }

        backtracking(position + 1, SEQ[position] + sum, sequenceLength + 1);
        backtracking(position + 1, sum, sequenceLength);
    }
}
