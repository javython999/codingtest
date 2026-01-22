package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ChickenFranchise {

    private int n = 0;
    private int m = 0;
    private List<int[]> houses = new ArrayList<>();
    private List<int[]> stores = new ArrayList<>();
    private List<int[]> selectedStores = new ArrayList<>();

    @Test
    void case1() {
        n = 5;
        m = 3;
        int[][] city = {
                {0, 0, 1, 0, 0},
                {0, 0, 2, 0, 1},
                {0, 1, 2, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 2}
        };

        int answer = 5;
        assertThat(solution(n, m, city)).isEqualTo(answer);
    }

    @Test
    void case2() {
        n = 5;
        m = 2;
        int[][] city = {
                {0, 2, 0, 1, 0},
                {1, 0, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {2, 0, 0, 1, 1},
                {2, 2, 0, 1, 2}
        };

        int answer = 10;
        assertThat(solution(n, m, city)).isEqualTo(answer);
    }

    @Test
    void case3() {
        n = 5;
        m = 1;
        int[][] city = {
                {1, 2, 0, 0, 0},
                {1, 2, 0, 0, 0},
                {1, 2, 0, 0, 0},
                {1, 2, 0, 0, 0},
                {1, 2, 0, 0, 0}
        };

        int answer = 11;
        assertThat(solution(n, m, city)).isEqualTo(answer);
    }

    @Test
    void case4() {
        n = 5;
        m = 1;
        int[][] city = {
                {1, 2, 0, 2, 1},
                {1, 2, 0, 2, 1},
                {1, 2, 0, 2, 1},
                {1, 2, 0, 2, 1},
                {1, 2, 0, 2, 1},
        };

        int answer = 32;
        assertThat(solution(n, m, city)).isEqualTo(answer);
    }


    private int solution(int n, int m, int[][] city) {

        for (int row = 0; row < n; row++) {

            for (int col = 0; col < n; col++) {

                int type = city[row][col];

                if (type == 1) {
                    houses.add(new int[] {row, col});
                } else if (type == 2) {
                    stores.add(new int[] {row, col});
                }

            }
        }

        return dfs(0, 0, selectedStores);
    }

    private int dfs(int start, int selectedCount, List<int[]> selectedStores) {
        if (selectedCount == m) {
            return calcScore(selectedStores);
        }

        int score = Integer.MAX_VALUE;

        for (int i = start; i < stores.size(); i++) {
            selectedStores.add(stores.get(i));
            score = Math.min(score, dfs(i + 1, selectedCount + 1, selectedStores));
            selectedStores.removeLast();
        }

        return score;
    }

    private int calcScore(List<int[]> selectedStores) {
        int result = 0;

        for (int[] house : houses) {

            int minDistance = Integer.MAX_VALUE;

            for (int[] store : selectedStores) {

                int distance = Math.abs(house[0] - store[0]) + Math.abs(house[1] - store[1]);

                minDistance = Math.min(minDistance, distance);
            }

            result += minDistance;
        }

        return result;
    }


}
