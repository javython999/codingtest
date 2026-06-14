package com.errday.codingtest.implementation;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class Remains {

    @Test
    void case1() {
        String[][] logs = {
                {"Baha", "enter"},
                {"Askar", "enter"},
                {"Baha", "leave"},
                {"Arthem", "enter"}
        };
        String[] answer = {"Askar", "Arthem"};
        assertThat(solution(logs)).isEqualTo(answer);
    }

    @Test
    void maxCase() {
        int n = 1_000_000;

        String[][] logs = new String[n][2];

        for (int i = 0; i < n; i++) {
            logs[i][0] = "user" + i;
            logs[i][1] = "enter";
        }

        long start = System.currentTimeMillis();
        solution(logs);
        System.out.println("elapsed = " + (System.currentTimeMillis() - start) + "ms");
    }


    private String[] solution(String[][] logs) {
        Set<String> company = new HashSet<>();

        for (String[] log : logs) {
            if ("enter".equals(log[1])) {
                company.add(log[0]);
            } else {
                company.remove(log[0]);
            }
        }

        List<String> result = new ArrayList<>(company);
        result.sort(Comparator.reverseOrder());
        return result.toArray(new String[0]);
    }

}
