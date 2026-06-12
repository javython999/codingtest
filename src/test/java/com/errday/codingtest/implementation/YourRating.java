package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class YourRating {

    @Test
    void case1() {
        String[][] grade = {
                {"ObjectOrientedPrograming1", "3.0", "A+"},
                {"IntroductionComputerEngineering", "3.0", "A+"},
                {"ObjectOrientedPrograming2", "3.0", "A0"},
                {"CreativeComputerEngineeringDesign", "3.0", "A+"},
                {"AssemblyLanguage", "3.0", "A+"},
                {"InternetProgramming", "3.0", "B0"},
                {"ApplicationProgrammingJava", "3.0", "A0"},
                {"SystemProgramming", "3.0", "B0"},
                {"OperatingSystem", "3.0", "B0"},
                {"WirelessCommunicationsandNetworking", "3.0", "C+"},
                {"LogicCircuits", "3.0", "B0"},
                {"DataStructure", "4.0", "A+"},
                {"MicroprocessorApplication", "3.0", "B+"},
                {"EmbeddedSoftware", "3.0", "C0"},
                {"ComputerSecurity", "3.0", "D+"},
                {"Database", "3.0", "C+"},
                {"Algorithm", "3.0", "B0"},
                {"CapstoneDesigninCSE", "3.0", "B+"},
                {"CompilerDesign", "3.0", "D0"},
                {"ProblemSolving", "4.0", "P"},
        };
        double answer = 3.284483;
        assertThat(solution(grade)).isEqualTo(answer);
    }

    private double solution(String[][] grade) {
        List<Grade> grades = new ArrayList<>();
        for (String[] info : grade) {
            double courseScore = Double.parseDouble(info[1]);
            String letterGrade = info[2];

            if (letterGrade.equals("P")) {
                continue;
            }
            grades.add(new Grade(courseScore, letterGrade));
        }

        double totalScore = grades.stream()
                .mapToDouble(Grade::getScore)
                .sum();

        double sum = grades.stream()
                .mapToDouble(Grade::getGradeScore)
                .sum();

        return Math.round((sum / totalScore) * 1_000_000) / 1_000_000.0;
    }


    private static class Grade {
        double score;
        String letterGrade;

        public Grade(double score, String letterGrade) {
            this.score = score;
            this.letterGrade = letterGrade;
        }

        public double getScore() {
            return score;
        }

        public double getGrade() {
            switch (letterGrade) {
                case "A+":
                    return 4.5;
                case "A0":
                    return 4.0;
                case "B+":
                    return 3.5;
                case "B0":
                    return 3.0;
                case "C+":
                    return 2.5;
                case "C0":
                    return  2.0;
                case "D+":
                    return 1.5;
                case "D0":
                    return 1.0;
                case "F":
                    return 0.0;
            }
            return 0.0;
        }

        public double getGradeScore() {
            return score * getGrade();
        }


    }
}
