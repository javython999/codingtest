package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class CompetitiveTransmission {

    private int CLEAN_AREA = 0;
    private int[][] map;
    private Queue<int[]> queue;

    @Test
    void case1() {
        int n = 3;
        int k = 3;
        map = new int[][] {
                {2, 0, 1},
                {0 ,0, 0},
                {3, 0, 0}
        };
        int second = 2;
        int[] position = {3, 2};
        int answer = 3;

        assertThat(solution(n, k, map, second, position)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int n = 3;
        int k = 3;
        map = new int[][] {
                {1, 0, 2},
                {0 ,0, 0},
                {3, 0, 0}
        };
        int second = 1;
        int[] position = {2, 2};
        int answer = 0;

        assertThat(solution(n, k, map, second, position)).isEqualTo(answer);
    }

    private int solution(int n, int k, int[][] map, int second, int[] position) {

        List<int[]> virus = new ArrayList<>();
        for (int row = 0; row < n; row++) {

            for (int col = 0; col < n; col++) {

                int cellValue = map[row][col];
                if (cellValue != CLEAN_AREA) {
                    int time = 0;
                    virus.add(new int[]{cellValue, row, col, time});
                }
            }

        }

        virus.sort(Comparator.comparingInt(v -> v[0]));

        queue = new ArrayDeque<>(virus);

        while (!queue.isEmpty()) {
            int[] virusCell = queue.poll();
            int x =  virusCell[1];
            int y = virusCell[2];
            int time = virusCell[3];

            if (time >= second) {
                continue;
            }

            infect(x, y, time);
        }


        return selection(position[0]-1, position[1]-1);
    }


    private void infect(int x, int y, int second) {
        int virusNumber = map[x][y];
        int xOutLine = map.length;
        int yOutLine = map[0].length;

        int[] directionX = {0, 1, 0, -1};
        int[] directionY = {-1, 0, 1, 0};

        for (int i = 0; i < 4; i++) {

            int nextX = x + directionX[i];
            int nextY = y + directionY[i];

            if (nextX < 0 || nextX >= xOutLine || nextY < 0 || nextY >= yOutLine) {
                continue;
            }

            if (map[nextX][nextY] != CLEAN_AREA) {
                continue;
            }

            map[nextX][nextY] = virusNumber;
            queue.offer(new int[] {virusNumber, nextX, nextY, second+1});
        }
    }


    private int selection(int row, int col) {
        return map[row][col];
    }

}
