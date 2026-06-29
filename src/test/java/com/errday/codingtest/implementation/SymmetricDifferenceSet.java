package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class SymmetricDifferenceSet {

    @Test
    void case1() {
        int[] a = {1, 2, 4};
        int[] b = {2, 3, 4, 5, 6};
        int answer = 4;
        assertThat(solution(a, b)).isEqualTo(answer);
    }

    private int solution(int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);

        int count = 0;
        int aPointer = 0;
        int bPointer = 0;

        while (aPointer < a.length && bPointer < b.length) {

            if (a[aPointer] < b[bPointer]) {
                count += 1;
                aPointer += 1;
            } else if (a[aPointer] > b[bPointer]) {
                count += 1;
                bPointer += 1;
            } else {
                aPointer += 1;
                bPointer += 1;
            }
        }

        count += a.length - aPointer;
        count += b.length - bPointer;

        return count;
    }
}
