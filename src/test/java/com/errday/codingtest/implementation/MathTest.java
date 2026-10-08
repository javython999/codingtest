package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MathTest {

    @Test
    void case1() {
        int[] answers = {1, 2, 3, 4, 5};
        int[] answer = {1};
        assertThat(solution(answers)).containsExactly(answer);
    }

    @Test
    void case2() {
        int[] answers = {1, 3, 2, 4, 2};
        int[] answer = {1, 2, 3};
    }

    private int[] solution(int[] answers) {
        int[] scores = new int[4];
        scores[1] = getScore(new int[] {1, 2, 3, 4, 5}, answers);
        scores[2] = getScore(new int[] {2, 1, 2, 3, 2, 4, 2, 5}, answers);
        scores[3] = getScore(new int[] {3,  3, 1, 1, 2, 2, 4, 4, 5, 5}, answers);

        int maxScore = Arrays.stream(scores)
                .max()
                .getAsInt();

        List<Integer> maxScoredStudent = new ArrayList<>();
        for (int index = 0; index < scores.length; index++) {
            if (scores[index] == maxScore) {
                maxScoredStudent.add(index);
            }
        }

        return maxScoredStudent.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    private int getScore(int[] cycle, int[] answers) {
        int cycleLength = cycle.length;
        int answer = 0;
        for (int index = 0; index < answers.length; index++) {

            int pickAnswer = cycle[index % cycleLength];

            if (pickAnswer == answers[index]) {
                answer += 1;
            }

        }

        return answer;
    }

}
