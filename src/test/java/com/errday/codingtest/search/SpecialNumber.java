package com.errday.codingtest.search;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class SpecialNumber {

    @Test
    void case1() {
        int target = 2;
        int[] numbers = {1, 1, 2, 2, 2, 2, 3};

        int answer = 4;

        assertThat(solution(numbers, target)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int target = 4;
        int[] numbers = {1, 1, 2, 2, 2, 2, 3};

        int answer = -1;

        assertThat(solution(numbers, target)).isEqualTo(answer);
    }

    private int solution(int[] numbers, int target) {
        int count = upperBound(numbers, target) - lowerBound(numbers, target);
        return count == 0 ? -1 : count;
    }

    private int lowerBound(int[] array, int target) {
        int left = 0;
        int right = array.length;

        while (left < right) {
            int mid = (left + right) / 2;

            if (array[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private int upperBound(int[] array, int target) {
        int left = 0;
        int right = array.length;

        while (left < right) {
            int mid = (left + right) / 2;

            if (array[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }



}
