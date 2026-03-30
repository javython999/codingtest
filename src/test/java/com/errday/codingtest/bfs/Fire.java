package com.errday.codingtest.bfs;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;

public class Fire {

    @Test
    void case1() {
        int r = 4;
        int c = 4;
        String[][] map = {
                {"#", "#", "#", "#"},
                {"#", "J", "F", "#"},
                {"#", ".", ".", "#"},
                {"#", ".", ".", "#"}
        };
        String answer = "3";
        assertThat(solution(r, c, map)).isEqualTo(answer);
    }

    private String WALL = "#";
    private String EMPTY = ".";
    private String FIRE = "F";
    private String JIHOON = "J";
    private int[][] MOVES = {
            {-1, 0},
            {0 ,1},
            {1, 0},
            {0, -1}
    };


    private String solution(int r, int c, String[][] map) {

        int[][] distance = new int[r][c];
        for (int[] row : distance) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        boolean[][] visited = new boolean[r][c];
        Queue<int[]> fireQueue = new ArrayDeque<>();
        Queue<int[]> moveQueue = new ArrayDeque<>();

        for (int row = 0; row < r; row++) {
            for (int col = 0; col < c; col++) {

                String current = map[row][col];

                if (FIRE.equals(current)) {
                    fireQueue.offer(new int[]{row, col});
                    distance[row][col] = 0;
                } else if (JIHOON.equals(current)) {
                    moveQueue.offer(new int[]{row, col, 0});
                    visited[row][col] = true;
                }
            }
        }

        while (!fireQueue.isEmpty()) {
            int[] current = fireQueue.poll();
            int row = current[0];
            int col = current[1];

            for (int[] move : MOVES) {
                int nextRow = row + move[0];
                int nextCol = col + move[1];

                if (nextRow < 0 || nextRow >= r) {
                    continue;
                }

                if (nextCol < 0 || nextCol >= c) {
                    continue;
                }

                String nextType = map[nextRow][nextCol];
                if (nextType.equals(WALL)) {
                    continue;
                }

                if (distance[nextRow][nextCol] != Integer.MAX_VALUE) {
                    continue;
                }

                distance[nextRow][nextCol] = distance[row][col] + 1;
                fireQueue.offer(new int[]{nextRow, nextCol});
            }
        }

        while (!moveQueue.isEmpty()) {
            int[] current = moveQueue.poll();
            int row = current[0];
            int col = current[1];
            int time = current[2];

            for (int[] move : MOVES) {
                int nextRow = row + move[0];
                int nextCol = col + move[1];
                int nextTime = time + 1;

                if (nextRow < 0 || nextRow >= r) {
                    return String.valueOf(nextTime);
                }

                if (nextCol < 0 || nextCol >= c) {
                    return String.valueOf(nextTime);
                }

                if (distance[nextRow][nextCol] <= nextTime) {
                    continue;
                }

                String nextType = map[nextRow][nextCol];
                if (!nextType.equals(EMPTY)) {
                    continue;
                }

                if (visited[nextRow][nextCol]) {
                    continue;
                }

                visited[nextRow][nextCol] = true;
                moveQueue.offer(new int[]{nextRow, nextCol, nextTime});

            }

        }

        return "IMPOSSIBLE";
    }
}
