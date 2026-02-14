package com.errday.codingtest.dfs;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class PopulationMovement {

    private final int[] moveX = {0, 1, 0, -1};
    private final int[] moveY = {-1, 0, 1, 0};

    private int N;
    private int L;
    private int R;
    private int[][] MAP;

    private int[][] UNIONS;

    @Test
    void case1() {
        int n = 2;
        int l = 20;
        int r = 50;
        int[][] map = {
                {50, 30},
                {20, 40}
        };

        int answer = 1;

        assertThat(solution(n, l, r, map)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int n = 2;
        int l = 40;
        int r = 50;
        int[][] map = {
                {50, 30},
                {20, 40}
        };

        int answer = 0;

        assertThat(solution(n, l, r, map)).isEqualTo(answer);
    }

    @Test
    void case3() {
        int n = 2;
        int l = 20;
        int r = 50;
        int[][] map = {
                {50, 30},
                {30, 40}
        };

        int answer = 1;

        assertThat(solution(n, l, r, map)).isEqualTo(answer);
    }

    @Test
    void case4() {
        int n = 3;
        int l = 5;
        int r = 10;
        int[][] map = {
                {10, 15, 20},
                {20, 30, 25},
                {40, 22, 10}
        };

        int answer = 2;

        assertThat(solution(n, l, r, map)).isEqualTo(answer);
    }

    @Test
    void case5() {
        int n = 4;
        int l = 10;
        int r = 50;
        int[][] map = {
                {10, 100, 20, 90},
                {80, 100, 60, 70},
                {70, 20, 30, 40},
                {50, 20, 100, 10}
        };

        int answer = 3;

        assertThat(solution(n, l, r, map)).isEqualTo(answer);
    }

    private int solution(int n, int l, int r, int[][] map) {
        N = n;
        L = l;
        R = r;
        MAP = map;
        UNIONS = new int[map.length][map[0].length];

        int totalCount = 0;

        while (true) {
            boolean moved = false;

            for (int[] row : UNIONS) {
                Arrays.fill(row, -1);
            }

            int index = 0;

            for (int row = 0; row < MAP.length; row++) {
                for (int col = 0; col < MAP[row].length; col++) {
                    if (UNIONS[row][col] == -1) {
                        if (process(row, col, index) > 1) {
                            moved = true;
                        }
                        index += 1;
                    }
                }
            }

            if (!moved) {
                break;
            }

            totalCount += 1;
        }

        return totalCount;
    }

    private int process(int x, int y, int index) {
        List<int[]> united = new ArrayList<>();
        united.add(new int[]{x, y});

        UNIONS[x][y] = index;

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {x, y});

        while (!queue.isEmpty()) {
            int[] city = queue.poll();
            int cityX = city[0];
            int cityY = city[1];
            int cityPopulation = MAP[cityX][cityY];

            for (int direction = 0; direction < 4; direction++) {
                int nextX = cityX + moveX[direction];
                int nextY = cityY + moveY[direction];

                if (nextX < 0 || nextX >= MAP.length) {
                    continue;
                }

                if (nextY < 0 || nextY >= MAP[nextX].length) {
                    continue;
                }

                if (UNIONS[nextX][nextY] != -1) {
                    continue;
                }

                int gap = Math.abs(cityPopulation - MAP[nextX][nextY]);

                if (L <= gap && gap <= R) {
                    queue.offer(new int[] {nextX, nextY});
                    united.add(new int[] {nextX, nextY});
                    UNIONS[nextX][nextY] = index;
                }
            }
        }

        int sum = united.stream()
                .mapToInt(city -> MAP[city[0]][city[1]])
                .sum();

        int unionCityCount = united.size();

        int averagePopulation = sum / unionCityCount;

        for (int[] city : united) {
            int cityX = city[0];
            int cityY = city[1];
            MAP[cityX][cityY] = averagePopulation;
        }

        return unionCityCount;
    }

}
