package com.errday.codingtest.greedy;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class ToOne {

    private long n = 17;
    private long k = 4;
    private final long ANSWER = 3;

    @Test
    void solution1() {
        LocalDateTime now = LocalDateTime.now();
        long count = 0;

        while (n >= k) {
            while (n % k != 0) {
                n -= 1;
                count += 1;
            }

            n = n / k;
            count += 1;
        }

        while (n > 1) {
            n -= 1;
            count += 1;
        }

        System.out.println(LocalDateTime.now().getNano() - now.getNano());
        assertThat(count).isEqualTo(ANSWER);
    }

    @Test
    void solution2() {
        LocalDateTime now = LocalDateTime.now();
        long count = 0;

        while (true) {
            long target = (n / k) * k;
            count += (n - target);
            n = target;

            if (n < k) {
                break;
            }

            count += 1;
            n /= k;
        }

        count += (n - 1);

        System.out.println(LocalDateTime.now().getNano() - now.getNano());
        assertThat(count).isEqualTo(ANSWER);
    }
}
