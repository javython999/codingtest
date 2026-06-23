package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchForDocuments {

    @Test
    void case1() {
        String documents = "abababab";
        String find = "aba";
        int answer = 2;
        assertThat(solution(documents, find)).isEqualTo(answer);
    }

    @Test
    void case2() {
        String documents = "a a a a a";
        String find = "a a";
        int answer = 2;
        assertThat(solution(documents, find)).isEqualTo(answer);
    }

    @Test
    void case3() {
        String documents = "ababababa";
        String find = "ababa";
        int answer = 1;
        assertThat(solution(documents, find)).isEqualTo(answer);
    }

    private int solution(String documents, String find) {

        int findCount = 0;

        while (documents.contains(find)) {
            findCount += 1;
            documents = documents.replaceFirst(find, "_");
        }

        return findCount;
    }
}
