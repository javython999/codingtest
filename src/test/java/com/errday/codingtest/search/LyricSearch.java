package com.errday.codingtest.search;

import java.util.*;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LyricSearch {

    @Test
    void case1() {
        String[] words = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
        String[] queries = {"fro??", "????o", "fr???", "fro???", "pro?"};

        int[] answer = {3, 2, 4, 1, 0};

        assertThat(solution(words, queries)).isEqualTo(answer);
    }

    private int[] solution(String[] words, String[] queries) {

        Map<Integer, List<String>> wordMap = new HashMap<>();
        Map<Integer, List<String>> reverseWordMap = new HashMap<>();
        for (String word: words) {
            int length = word.length();

            wordMap.computeIfAbsent(length, k -> new ArrayList<>()).add(word);

            String reverseWord = new StringBuilder(word).reverse().toString();
            reverseWordMap.computeIfAbsent(length, k -> new ArrayList<>()).add(reverseWord);
        }

        for (List<String> wordList : wordMap.values()) {
            Collections.sort(wordList);
        }

        for (List<String> wordList : reverseWordMap.values()) {
            Collections.sort(wordList);
        }


        int[] answer = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            String query = queries[i];
            int queryLength = query.length();

            List<String> searchList;

            if (query.charAt(0) == '?') {
                searchList = reverseWordMap.get(queryLength);
                query = new StringBuilder(query).reverse().toString();
            } else {
                searchList = wordMap.get(queryLength);
            }

            if (searchList == null) {
                answer[i] = 0;
                continue;
            }

            String lower = query.replace("?", "a");
            String upper = query.replace("?", "z");

            answer[i] = upperBound(searchList, upper) - lowerBound(searchList, lower);

        }

        return answer;
    }

    private int lowerBound(List<String> searchList, String target) {
        int left = 0;
        int right = searchList.size();

        while (left < right) {
            int mid = (left + right) / 2;

            if (searchList.get(mid).compareTo(target) >= 0) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }


    private int upperBound(List<String> searchList, String target) {
        int left = 0;
        int right = searchList.size();


        while (left < right) {
            int mid = (left + right) / 2;

            if (searchList.get(mid).compareTo(target) > 0) {
                right = mid;
            } else {
                left = mid + 1;
            }

        }

        return left;
    }


}
