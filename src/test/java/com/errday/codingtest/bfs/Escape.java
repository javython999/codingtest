package com.errday.codingtest.bfs;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;

public class Escape {

    @Test
    void case1() {
        int r = 3;
        int c = 3;
        String[][] map = {
                {"D", ".", "*"},
                {".", ".", "."},
                {".", "S", "."}
        };
        String answer = "3";
        assertThat(solution(r, c, map)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int r = 3;
        int c = 3;
        String[][] map = {
                {"D", ".", "*"},
                {".", ".", "."},
                {".", ".", "S"}
        };
        String answer = "KAKTUS";
        assertThat(solution(r, c, map)).isEqualTo(answer);
    }

    @Test
    void case3() {
        int r = 3;
        int c = 6;
        String[][] map = {
                {"D", ".", ".", ".", "*", "."},
                {".", "X", ".", "X", ".", "."},
                {".", ".", ".", ".", "S", "."}
        };
        String answer = "6";
        assertThat(solution(r, c, map)).isEqualTo(answer);
    }

    @Test
    void case4() {
        int r = 5;
        int c = 4;
        String[][] map = {
                {".", "D", ".", "*"},
                {".", ".", ".", "."},
                {".", ".", "X", "."},
                {"S", ".", "*", "."},
                {".", ".", ".", "."}
        };
        String answer = "4";
        assertThat(solution(r, c, map)).isEqualTo(answer);
    }

    private String EMPTY = ".";
    private String WATER = "*";
    private String STONE = "X";
    private String DESTINATION = "D";
    private String START = "S";
    private int[][] MOVES = {
            {-1, 0},
            {0 ,1},
            {1, 0},
            {0, -1}
    };


    private String solution(int r, int c, String[][] map) {

        Queue<int[]> waterQueue = new ArrayDeque<>();
        int[][] waterTimes = new int[r][c];
        for (int[] row : waterTimes) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        Queue<int[]> moveQueue = new ArrayDeque<>();
        boolean[][] visited = new boolean[r][c];

        for (int row = 0; row < r; row++) {
            for (int col = 0; col < c; col++) {

                String current = map[row][col];

                if (current.equals(WATER)) {
                    waterQueue.offer(new int[] {row, col, 0});
                    waterTimes[row][col] = 0;
                } else if (current.equals(START)) {
                    moveQueue.offer(new int[] {row, col, 0});
                    visited[row][col] = true;
                }
            }
        }

        while (!waterQueue.isEmpty()) {
            int[] current = waterQueue.poll();
            int row = current[0];
            int col = current[1];
            int waterTime = current[2];

            for (int[] move : MOVES) {
                int nextRow = row + move[0];
                int nextCol = col + move[1];

                if (nextRow < 0 || nextRow >= r) {
                    continue;
                }

                if (nextCol < 0 || nextCol >= c) {
                    continue;
                }

                String next = map[nextRow][nextCol];

                if (next.equals(STONE) || next.equals(DESTINATION)) {
                    continue;
                }

                if (waterTimes[nextRow][nextCol] < waterTime + 1) {
                    continue;
                }

                waterQueue.offer(new int[] {nextRow, nextCol, waterTime + 1});
                waterTimes[nextRow][nextCol] = waterTime + 1;
            }
        }


        while (!moveQueue.isEmpty()) {
            int[] current = moveQueue.poll();
            int row = current[0];
            int col = current[1];
            int moveTime = current[2];

            for (int[] move : MOVES) {
                int nextRow = row + move[0];
                int nextCol = col + move[1];

                if (nextRow < 0 || nextRow >= r) {
                    continue;
                }

                if (nextCol < 0 || nextCol >= c) {
                    continue;
                }

                if (visited[nextRow][nextCol]) {
                    continue;
                }

                String next = map[nextRow][nextCol];

                if (next.equals(STONE)) {
                    continue;
                }

                if (next.equals(DESTINATION)) {
                    return String.valueOf(moveTime + 1);
                }

                if (waterTimes[nextRow][nextCol] <= moveTime + 1) {
                    continue;
                }

                visited[nextRow][nextCol] = true;
                moveQueue.offer(new int[] {nextRow, nextCol, moveTime + 1});
            }

        }


        return "KAKTUS";
    }

}
