package com.errday.codingtest.implementation;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class Time {

    private int n = 5;
    private int answer = 11475;

    @Test
    void solution() {

        int count = 0;

        for (int hour = 0; hour <= n; hour++) {

            for (int minute = 0; minute < 60; minute++) {

                for (int second = 0; second < 60; second++) {

                    if (String.valueOf(hour).contains("3") || String.valueOf(minute).contains("3") || String.valueOf(second).contains("3")) {
                        count += 1;
                    }

                }
            }
        }

        Assertions.assertThat(count).isEqualTo(answer);
    }
}
