package com.errday.codingtest.implementation;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class WordSort {

    @Test
    void case1() {
        String[] words = {"but", "i", "wont", "hesitate", "no", "more", "no", "more", "it", "cannot", "wait", "im", "yours"};
        String[] answer = {"i", "im", "it", "no", "but", "more", "wait", "wont", "yours", "cannot", "hesitate"};
        Assertions.assertThat(solution(words)).containsExactly(answer);
    }

    private String[] solution(String[] words) {
        Set<String> set = new HashSet<>();
        Collections.addAll(set, words);

        String[] removeDuplicate = set.toArray(new String[0]);

        Arrays.sort(removeDuplicate, (a, b) -> {
            if (a.length() == b.length()) {
                return a.compareTo(b);
            }
            return a.length() - b.length();
        });

        return removeDuplicate;
    }


}
