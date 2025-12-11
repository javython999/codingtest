package com.errday.codingtest.sort;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class ReverseOrder {

    private int[] array = {15, 27, 12};
    private String answer = "27 15 12";

    @Test
    void solution() {

        List<Integer> list = new ArrayList<>( Arrays.stream(array)
                .boxed()
                .toList()
        );

        list.sort(Collections.reverseOrder());


        String myAnswer = list.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" "));

        Assertions.assertThat(myAnswer).isEqualTo(answer);
    }
}
