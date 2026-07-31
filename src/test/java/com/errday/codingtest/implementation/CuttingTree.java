package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class CuttingTree {

    @Test
    void case1() {
        int require = 7;
        int[] trees = {20, 15, 10, 17};
        int answer = 15;
        assertThat(solution(require, trees)).isEqualTo(answer);
    }

    private int solution(int require, int[] trees) {

        int high = Arrays.stream(trees).max().getAsInt();
        int low = 0;
        while (low <= high) {
           int mid = low + (high - low) / 2;
           long sum = 0;

           for (int tree : trees) {
               if (tree > mid) {
                   sum += tree - mid;
               }
           }

           if (require <= sum) {
               low = mid + 1;
           } else {
               high = mid - 1;
           }
        }

        return high;
    }
}
