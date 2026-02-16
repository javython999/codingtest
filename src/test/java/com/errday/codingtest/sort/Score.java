package com.errday.codingtest.sort;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class Score {

    @Test
    void case1() {
        String[][] input = {
                {"Junkyu", "50", "60", "100"},
                {"Sangkeun", "80", "60", "50"},
                {"Sunyoung", "80", "70", "100"},
                {"Soong", "50", "60", "90"},
                {"Haebin", "50", "60", "100"},
                {"Kangsoo", "60", "80", "100"},
                {"Donghyuk", "80", "60", "100"},
                {"Sei", "70", "70", "70"},
                {"Wonseob", "70", "70", "90"},
                {"Sanghyun", "70", "70", "80"},
                {"nsj", "80", "80", "80"},
                {"Taewhan", "50", "60", "90"}
        };

        String[] answer = {
                "Donghyuk",
                "Sangkeun",
                "Sunyoung",
                "nsj",
                "Wonseob",
                "Sanghyun",
                "Sei",
                "Kangsoo",
                "Haebin",
                "Junkyu",
                "Soong",
                "Taewhan"
        };

        assertThat(solution(input)).isEqualTo(answer);
    }

    private String[] solution(String[][] input) {

        List<Student> students = new ArrayList<>();
        for (String[] info : input) {
            students.add(new Student(info));
        }

        Collections.sort(students);

        return students.stream()
                .map(s -> s.name)
                .toArray(String[]::new);
    }

    private class Student implements Comparable<Student> {
        private String name;
        private int korean;
        private int english;
        private int math;

        public Student(String[] input) {
            this.name = input[0];
            this.korean = Integer.parseInt(input[1]);
            this.english = Integer.parseInt(input[2]);
            this.math = Integer.parseInt(input[3]);
        }

        @Override
        public int compareTo(Student o) {
            if (this.korean == o.korean && this.english == o.english && this.math == o.math) {
                return this.name.compareTo(o.name);
            }

            if (this.korean == o.korean && this.english == o.english) {
                return Integer.compare(o.math, this.math);
            }

            if (this.korean == o.korean) {
                return Integer.compare(this.english, o.english);
            }

            return Integer.compare(o.korean, this.korean);
        }
    }



}
