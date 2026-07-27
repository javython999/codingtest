package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class FileSort {

    @Test
    void case1() {
        int fileCount = 8;
        String[] files = {
                "sbrus.txt",
                "spc.spc",
                "acm.icpc",
                "korea.icpc",
                "sample.txt",
                "hello.world",
                "sogang.spc",
                "example.txt"
        };
        String[][] answer = {
                {"icpc", "2"},
                {"spc", "2"},
                {"txt", "3"},
                {"world", "1"}
        };

        assertThat(solution(fileCount, files))
                .isDeepEqualTo(answer);
    }

    private String[][] solution(int fileCount, String[] files) {
        Map<String, Integer> map = new HashMap<>();

        for (String file : files) {
            String[] split = file.split("[.]");
            String extension = split[1];
            map.merge(extension, 1, Integer::sum);
        }

        List<String> sorted = new ArrayList<>(map.keySet());
        sorted.sort(String::compareTo);

        String[][] answer = new String[map.size()][2];
        for (int i = 0; i < sorted.size(); i++) {
            answer[i] = new String[2];
            answer[i][0] = sorted.get(i);
            answer[i][1] = String.valueOf(map.get(sorted.get(i)));
        }

        return answer;
    }


}
