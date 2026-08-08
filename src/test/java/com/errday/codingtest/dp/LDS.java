package com.errday.codingtest.dp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LDS {

    @Test
    void case1() {
        assertThat(solution2(new int[] {10, 30, 10, 20, 20, 10})).isEqualTo(3);
    }

    @Test
    void case2() {
        assertThat(solution2(new int[]{5})).isEqualTo(1);
    }

    @Test
    void case3() {
        assertThat(solution2(new int[]{5,4,3,2,1})).isEqualTo(5);
    }

    @Test
    void case4() {
        assertThat(solution2(new int[]{1,2,3,4,5})).isEqualTo(1);
    }

    @Test
    void case5() {
        assertThat(solution2(new int[]{5,5,5,5})).isEqualTo(1);
    }

    @Test
    void case6() {
        assertThat(solution2(new int[]{10,30,10,20,20,10})).isEqualTo(3);
    }

    @Test
    void case7() {
        assertThat(solution2(new int[]{9,8,7,6,5,4,3,2,1})).isEqualTo(9);
    }

    private int solution2(int[] seq) {
        int[] lds = new int[seq.length];
        lds[0] = seq[0];
        int size = 1;

        for (int current = 1; current < seq.length; current++) {

            if (lds[size - 1] > seq[current]) {
                size += 1;
                lds[size - 1] = seq[current];
                continue;
            }

            int low = 0;
            int high = size;

            while (low < high) {
                int mid = low + (high - low) / 2;

                if (lds[mid] > seq[current]) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }

            lds[low] = seq[current];
        }

        return size;
    }

}
