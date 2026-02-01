package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class Laboratory {

    private final int SAFE_AREA = 0;
    private final int WALL = 1;
    private final int INFECTED_AREA = 2;
    private int[][] map;
    private int[][] tempMap;
    private int result;

    @Test
    void case1() {
        int n = 7;
        int m = 7;
        result = 0;
        map = new int[][] {
                {2, 0, 0, 0, 1, 1, 0},
                {0, 0, 1, 0, 1, 2, 0},
                {0, 1, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 1},
                {0, 1, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0}
        };

        int answer = 27;
        solution();
        assertThat(result).isEqualTo(answer);
    }

    @Test
    void case2() {
        int n = 4;
        int m = 6;
        result = 0;
        map = new int[][] {
                {0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 2, 2},
                {1, 1, 1, 0, 0, 2},
                {0, 0, 0, 0, 0, 2}
        };

        int answer = 9;
        solution();
        assertThat(result).isEqualTo(answer);
    }

    @Test
    void case3() {
        int n = 8;
        int m = 8;
        result = 0;
        map = new int[][] {
                {2, 0, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 0, 0, 0, 0, 2},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };

        int answer = 3;
        solution();
        assertThat(result).isEqualTo(answer);
    }


    private void solution() {
        dfs(0);
    }

    private void dfs(int selectedCount) {

        if (selectedCount == 3) {

            // 원본 맵 복사
            tempMap = Arrays.stream(map)
                    .map(row -> Arrays.copyOf(row, row.length))
                    .toArray(int[][]::new);

            // 전염
            for (int row = 0; row < tempMap.length; row++) {
                for (int col = 0; col < tempMap[row].length; col++) {
                    if (tempMap[row][col] == INFECTED_AREA) {
                        infect(row, col);
                    }
                }
            }

            // 안전구역 확인
            result = Math.max(result, checkSafeArea(tempMap));
            return;
        }

        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                if (map[row][col] == SAFE_AREA) {

                    map[row][col] = WALL;
                    selectedCount += 1;

                    dfs(selectedCount);

                    map[row][col] = SAFE_AREA;
                    selectedCount -= 1;
                }
            }
        }
    }

    private void infect(int x, int y) {

        int maxRow = tempMap.length;
        int maxCol = tempMap[0].length;

        int[] moveX = {0, 1, 0, -1};
        int[] moveY = {-1, 0, 1, 0};

        for (int i = 0; i < 4; i++) {

            int nextX = moveX[i] + x;
            int nextY = moveY[i] + y;

            if (nextX < 0 || nextX >= maxRow || nextY < 0 || nextY >= maxCol) {
                continue;
            }

            if (tempMap[nextX][nextY] == SAFE_AREA) {
                tempMap[nextX][nextY] = INFECTED_AREA;
                infect(nextX, nextY);
            }
        }
    }

    private int checkSafeArea(int[][] map) {
        int safeAreaCount = 0;
        for (int[] row : map) {
            for (int col : row) {
                if (col == SAFE_AREA) {
                    safeAreaCount++;
                }
            }
        }
        return  safeAreaCount;
    }
}
