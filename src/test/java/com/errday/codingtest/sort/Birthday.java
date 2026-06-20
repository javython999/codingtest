package com.errday.codingtest.sort;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class Birthday {

    @Test
    void case1() {
        String[][] students = {
                {"Mickey", "1", "10", "1991"},
                {"Alice", "30", "12", "1990"},
                {"Tom", "15", "8", "1993"},
                {"Jerry", "18", "9", "1990"},
                {"Garfield", "20", "9", "1990"},
        };
        String[] answer = {"Tom", "Jerry"};
        Assertions.assertThat(solution(students)).containsExactly(answer);
    }

    private String[] solution(String[][] students) {

        Arrays.sort(students, (a, b) -> {
            if (a[3].equals(b[3])) {

                if (a[2].equals(b[2])) {
                    return b[1].compareTo(a[1]);
                }

                return a[2].compareTo(b[2]);
            }

            return b[3].compareTo(a[3]);
        });
        String[] answer = new String[2];
        answer[0] = students[0][0];
        answer[1] = students[students.length-1][0];

        return answer;
    }
}
