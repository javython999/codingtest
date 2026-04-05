package com.errday.codingtest.bfs;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class RobotVaccuum {

    @Test
    void case1() {
        int w = 7;
        int h = 5;
        char[][] room = {
                {'.', '.', '.', '.', '.', '.', '.'},
                {'.', 'o', '.', '.', '.', '*', '.'},
                {'.', '.', '.', '.', '.', '.', '.'},
                {'.', '*', '.', '.', '.', '*', '.'},
                {'.', '.', '.', '.', '.', '.', '.'}
        };

        int answer = 8;
        assertThat(solution(w, h, room)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int w = 15;
        int h = 13;
        char[][] room = {
                {'.', '.', '.', '.', '.', '.', '.', 'x', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', 'o', '.', '.', '.', 'x', '.', '.', '.', '.', '*', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', 'x', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', 'x', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', 'x', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'x', 'x', 'x', 'x', 'x', '.', '.', '.', '.', '.', 'x', 'x', 'x', 'x', 'x'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', 'x', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', 'x', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', 'x', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '*', '.', '.', '.', '.', 'x', '.', '.', '.', '.', '*', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', 'x', '.', '.', '.', '.', '.', '.', '.'}
        };

        int answer = 49;
        assertThat(solution(w, h, room)).isEqualTo(answer);
    }

    @Test
    void case3() {
        int w = 10;
        int h = 10;
        char[][] room = {
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', 'o', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', 'x', 'x', 'x', 'x', 'x'},
                {'.', '.', '.', '.', '.', 'x', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', 'x', '.', '*', '.', '.'},
                {'.', '.', '.', '.', '.', 'x', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', 'x', '.', '.', '.', '.'}
        };

        int answer = -1;
        assertThat(solution(w, h, room)).isEqualTo(answer);
    }

    private char CLEAN = '.';
    private char DUST = '*';
    private char VACCUM = 'o';
    private char FURNITURE = 'x';
    private int[][] MOVES = {
            {-1, 0},
            {0, 1},
            {1, 0},
            {0, -1}
    };
    private int positionCount;
    private int INF = Integer.MAX_VALUE / 2;

    private int solution(int w, int h, char[][] room) {

        List<int[]> positions = new ArrayList<>();
        int[] start = null;

        for (int row = 0; row < h; row++) {
            for (int col = 0; col < w; col++) {
                if (room[row][col] == VACCUM) {
                    start = new int[]{row, col};
                } else if (room[row][col] == DUST) {
                    positions.add(new int[]{row, col});
                }
            }
        }

        positions.add(0, start);
        positionCount = positions.size();

        int[][] distnaces = new int[positionCount][positionCount];
        for (int[] distance : distnaces) {
            Arrays.fill(distance, INF);
        }

        for (int i = 0; i < positionCount; i++) {
            bfsAll(i, positions, room, distnaces);
        }

        for (int[] distance : distnaces) {
            for (int col : distance) {
                if (col == INF) {
                    return -1;
                }
            }
        }

        int[][] dp = new int[positionCount][1 << (positionCount - 1)];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return dfs(0, 0, dp, distnaces);
    }

    private int bfsAll(int index, List<int[]> positions, char[][] room, int[][] distances) {

        int rowSize = room.length;
        int colSize = room[0].length;

        boolean[][] visited = new boolean[rowSize][colSize];
        Queue<int[]> queue = new ArrayDeque<>();

        int[] start = positions.get(index);
        queue.offer(new int[] {start[0], start[1], 0});
        visited[start[0]][start[1]] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];
            int distance = current[2];

            for (int i = 0; i < positionCount; i++) {
                int[] position = positions.get(i);
                if (position[0] == row && position[1] == col) {
                    distances[index][i] = distance;
                }
            }

            for (int[] move : MOVES) {
                int nextRow = row + move[0];
                int nextCol = col + move[1];

                if (nextRow < 0 || nextRow >= room.length) {
                    continue;
                }

                if (nextCol < 0 || nextCol >= room[nextRow].length) {
                    continue;
                }

                if (room[nextRow][nextCol] == FURNITURE) {
                    continue;
                }

                if (visited[nextRow][nextCol]) {
                    continue;
                }

                visited[nextRow][nextCol] = true;
                queue.offer(new int[]{nextRow, nextCol, distance + 1});
            }
        }

        return INF;
    }

    private int dfs(int current, int visited, int[][] dp, int[][] distances) {
        if (visited == (1 << (positionCount - 1)) - 1) {
            return 0;
        }

        if (dp[current][visited] != -1) {
            return dp[current][visited];
        }

        dp[current][visited] = INF;

        for (int next = 1; next < positionCount; next++) {

            if ((visited & (1 << (next-1))) != 0) {
                continue;
            }


            if (distances[current][next] == INF) {
                continue;
            }

            dp[current][visited] = Math.min(
                    dp[current][visited],
                    distances[current][next] + dfs(next, visited | (1 << (next-1)), dp, distances)
            );
        }

        return dp[current][visited];
    }

}
