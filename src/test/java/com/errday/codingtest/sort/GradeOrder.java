package com.errday.codingtest.sort;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class GradeOrder {

    private String[] studentsInfos = {"홍길동 95", "이순신 77"};
    private String answer = "이순신 홍길동";

    @Test
    void solution() {

        List<Student> students = new ArrayList<>();

        for (String studentInfo : studentsInfos) {
            String[] info = studentInfo.split(" ");
            students.add(new Student(info[0], Integer.parseInt(info[1])));
        }

        students.sort(Comparator.comparingInt(Student::getScore));

        String myAnswer = students.stream()
                .map(s -> s.getName())
                .collect(Collectors.joining(" "));

        Assertions.assertThat(myAnswer).isEqualTo(answer);
    }

    private static class Student {
        private String name;
        private int score;

        public Student(String name, int score) {
            this.name = name;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }

        @Override
        public String toString() {
            return "name: " + name + ", score: " + score;
        }
    }
}
