package com.errday.codingtest.search;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.*;

public class FindPart {

    private int[] stockPartNumbers = {8, 3, 7, 9, 2, 12, 17, 15, 29, 30};
    private int[] requiredPartNumbers = {5, 7, 9};
    private String answer = "no yes yes";

    @Test
    void solution1() {

        StringJoiner joiner = new StringJoiner(" ");
        for (int partNumber : requiredPartNumbers) {
            String isExist = "no";

            if (isExist(stockPartNumbers, partNumber)) {
                isExist = "yes";
            }

            joiner.add(isExist);
        }

        Assertions.assertThat(joiner.toString()).isEqualTo(answer);
    }

    @Test
    void solution2() {

        StringJoiner joiner = new StringJoiner(" ");
        for (int partNumber : requiredPartNumbers) {
            joiner.add(binarySearch(stockPartNumbers, partNumber) ? "yes" : "no");
        }
    }

    private boolean isExist(int[] array, int target) {
        Arrays.sort(array);
        return Arrays.binarySearch(array, target) > -1;
    }

    private boolean binarySearch(int[] array, int target) {
        Arrays.sort(array);

        int start = 0;
        int end = array.length - 1;


        while (start <= end) {
            int mid = (start + end) / 2;

            if (array[mid] == target) {
                return true;
            }

            if (array[mid] > target) {
                end = mid - 1;
                continue;
            }

            if (array[mid] < target) {
                start = mid + 1;
                continue;
            }

        }
        return false;
    }
}
