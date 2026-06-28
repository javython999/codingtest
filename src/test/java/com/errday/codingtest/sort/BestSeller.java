package com.errday.codingtest.sort;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class BestSeller {

    @Test
    void case1() {
        String[] items = {"top", "top", "top", "top", "kimtop"};
        String answer = "top";
        assertThat(solution(items)).isEqualTo(answer);
    }

    @Test
    void case2() {
        String[] items = {"table", "chair", "table", "table", "lamp", "door", "lamp", "table", "chair"};
        String answer = "table";
        assertThat(solution(items)).isEqualTo(answer);
    }

    @Test
    void case3() {
        String[] items = {"a", "a", "a", "b", "b", "b"};
        String answer = "a";
        assertThat(solution(items)).isEqualTo(answer);
    }

    @Test
    void case4() {
        String[] items = {"icecream", "peanuts", "peanuts", "chocolate", "candy", "chocolate", "icecream", "apple"};
        String answer = "chocolate";
        assertThat(solution(items)).isEqualTo(answer);
    }

    private String solution(String[] items) {
        Map<String, Integer> countMap = new HashMap<>();

        for (String book : items) {
            countMap.merge(book, 1, Integer::sum);
        }

        List<String> bestSeller = new ArrayList<>(countMap.keySet());
        bestSeller.sort((a, b) -> {
            int countA = countMap.get(a);
            int countB = countMap.get(b);

            if (countA == countB) {
                return a.compareTo(b);
            }
            return countB - countA;
        });

        return bestSeller.getFirst();
    }


}
