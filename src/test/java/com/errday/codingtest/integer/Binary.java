package com.errday.codingtest.integer;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class Binary {
    @Test
    void case1() {
        int number = 10;
        String answer = "1010";

        Assertions.assertThat(solution(number)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int number = 27;
        String answer = "11011";

        Assertions.assertThat(solution(number)).isEqualTo(answer);
    }

    @Test
    void case3() {
        int number = 12345;
        String answer = "11000000111001";

        Assertions.assertThat(solution(number)).isEqualTo(answer);
    }

    private String solution(int number) {
        return Integer.toBinaryString(number);
    }

    private String solution2(int number) {
        StringBuilder sb = new StringBuilder();

        while (number > 0) {
            sb.append(number % 2);

            number /= 2;
        }

        return sb.reverse().toString();
    }
}
