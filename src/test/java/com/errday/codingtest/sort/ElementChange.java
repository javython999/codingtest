package com.errday.codingtest.sort;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class ElementChange {

    private int k = 3;
    private int[] a = {1, 2, 5, 4, 3};
    private int[] b = {5, 5, 6, 6, 5};
    private int answer = 26;


    @Test
    void solution() {

        Arrays.sort(a);
        Arrays.sort(b);

        int minPointer = 0;
        int maxPointer = b.length - 1;

        for (int i = 0; i < k; i++) {

            int min = a[minPointer];
            int max = b[maxPointer];

            if (min < max) {
                a[minPointer] = max;
                b[maxPointer] = min;
                minPointer++;
                maxPointer--;
            }
        }

        Assertions.assertThat(Arrays.stream(a).sum()).isEqualTo(answer);


    }
}
