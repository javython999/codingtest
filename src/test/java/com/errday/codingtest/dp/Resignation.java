package com.errday.codingtest.dp;

import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

public class Resignation {

    @Test
    void case1() {
        int[] time = {3, 5, 1, 1, 2, 4, 2};
        int[] pay = {10, 20, 10, 20, 15, 40, 200};


        int answer = 45;

        assertThat(solution(time, pay)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int[] time = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        int[] pay = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        int answer = 55;

        assertThat(solution(time, pay)).isEqualTo(answer);
    }

    @Test
    void case3() {
        int[] time = {5, 5, 5, 5, 5, 5, 5, 5, 5, 5};
        int[] pay = {10, 9, 8, 7, 6, 10, 9, 8, 7, 6};

        int answer = 20;

        assertThat(solution(time, pay)).isEqualTo(answer);
    }

    @Test
    void case4() {
        int[] time = {5, 4, 3, 2, 1, 1, 2, 3, 4, 5};
        int[] pay = {50, 40, 30, 20, 10, 10, 20, 30, 40, 50};

        int answer = 90;

        assertThat(solution2(time, pay)).isEqualTo(answer);
    }


    private int solution(int[] time, int[] pay) {
        int workDay = time.length;
        int[] dp = new int[workDay + 1];

        for (int today = workDay - 1; today >= 0; today--) {
            int nextIndex = today + time[today];

            dp[today] = dp[today + 1];

            if (nextIndex <= workDay) {
                dp[today]= Math.max(dp[today], pay[today] + dp[nextIndex]);
            }
        }

        return dp[0];
    }

    private int solution2(int[] time, int[] pay) {

        int workDay = time.length;
        int[] dp = new int[workDay + 1];

        if (time[0] - 1 < workDay) {
            dp[time[0]-1] = Math.max(dp[time[0]-1], pay[0]);
        }

        for (int day = 1; day < workDay; day++) {
            dp[day] = Math.max(dp[day], dp[day - 1]);

            if (time[day] + day - 1 < workDay) {
                dp[time[day] + day - 1] = Math.max(dp[time[day] + day - 1], pay[day] + dp[day - 1]);
            }
        }

        int answer = 0;
        for (int result : dp) {
            answer = Math.max(answer, result);
        }

        return answer;
    }
}
