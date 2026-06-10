package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class AgeSort {

    @Test
    void case1() {
        String[][] users = {
                {"21", "Junkyu"},
                {"21", "Dohyun"},
                {"20", "Sunyoug"}
        };

        String[][] answer = {
                {"20", "Sunyoug"},
                {"21", "Junkyu"},
                {"21", "Dohyun"}
        };

        assertThat(solution(users)).isDeepEqualTo(answer);
    }

    @Test
    void case2() {
        String[][] users = {
                {"21", "kim"},
                {"21", "lee"},
                {"20", "park"}
        };

        String[][] answer = {
                {"20", "park"},
                {"21", "kim"},
                {"21", "lee"},
        };

        assertThat(solution(users)).isDeepEqualTo(answer);
    }

    private String[][] solution(String[][] users) {
        User[] userArray = new User[users.length];
        for (int i = 0; i < users.length; i++) {
            userArray[i] = new User(users[i][1], users[i][0], i);
        }

        Arrays.sort(userArray);

        String[][] result = new String[userArray.length][2];
        for (int i = 0; i < userArray.length; i++) {
            result[i] = new String[] {userArray[i].age, userArray[i].name};
        }

        return result;
    }

    private class User implements Comparable<User> {
        String name;
        String age;
        int sort;

        public User(String name, String age, int sort) {
            this.name = name;
            this.age = age;
            this.sort = sort;
        }

        @Override
        public int compareTo(User o) {
            int myAge = Integer.parseInt(this.age);
            int otherAge = Integer.parseInt(o.age);

            if (myAge == otherAge) {
                return this.sort - o.sort;
            }
            return myAge - otherAge;
        }
    }
}
