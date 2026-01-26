package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class InspectionExteriorWall {

    @Test
    void case1() {
        int n = 12;
        int[] weak = {1, 5, 6, 10};
        int[] dist = {1, 2, 3, 4};

        int answer = 2;

        assertThat(solution(n, weak, dist)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int n = 12;
        int[] weak = {1, 3, 4, 9, 10};
        int[] dist = {3, 5, 7};

        int answer = 1;

        assertThat(solution(n, weak, dist)).isEqualTo(answer);
    }

    private int solution(int n, int[] weak, int[] dist) {

        int WEAK_COUNT = weak.length;

        int[] inline = new int[WEAK_COUNT * 2];
        for (int i = 0; i < WEAK_COUNT; i++) {
            inline[i] = weak[i];
            inline[i + WEAK_COUNT] = n +  weak[i];
        }

        Arrays.sort(dist);
        int[] sortedFriend =  new int[dist.length];
        for (int i = dist.length - 1; i >= 0; i--) {
            sortedFriend[dist.length - 1 - i] = dist[i];
        }

        int answer = Integer.MAX_VALUE;

        for (int startIndex = 0; startIndex < WEAK_COUNT; startIndex++) {

            int idx = startIndex;
            int workFriendCount = 0;

            for (int friend : sortedFriend) {
                int cover = inline[idx] + friend;

                while (idx < startIndex + WEAK_COUNT && inline[idx] <= cover) {
                    idx++;
                }

                workFriendCount++;

                if (idx >= startIndex + WEAK_COUNT) {
                    answer = Math.min(answer, workFriendCount);
                    break;
                }
            }
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }


}
