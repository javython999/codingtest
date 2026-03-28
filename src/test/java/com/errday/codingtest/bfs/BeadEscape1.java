package com.errday.codingtest.bfs;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;

public class BeadEscape1 {

    @Test
    void case1() {
        int n = 5;
        int m = 4;
        String[][] map = {
                {"#", "#", "#", "#", "#"},
                {"#", "R", ".", ".", "#"},
                {"#", ".", "#", "O", "#"},
                {"#", "#", "#", "#", "#"}
        };


        int answer = 2;
        assertThat(solution(m, n, map)).isEqualTo(answer);
    }

    private String WALL = "#";
    private String HOLE = "O";
    private String RED = "R";
    private int Is_NOT_HOEL = 0;
    private int IS_HOLE = 1;
    private int[][] MOVES = {
            {-1, 0},
            {0, 1},
            {1, 0},
            {0, -1}
    };

    private int solution(int m, int n, String[][] map) {

        int[] redPosition = find(RED, map);

        boolean[][] visited = new boolean[n][m];
        visited[redPosition[0]][redPosition[1]] = true;

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {redPosition[0], redPosition[1], 0});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];
            int roleCount = current[2];

            for (int[] move : MOVES) {
                int[] result = role(row, col, move, map);

                if (result[2] == IS_HOLE) {
                    return roleCount + 1;
                }

                if (!visited[result[0]][result[1]]) {
                    visited[result[0]][result[1]] = true;
                    queue.offer(new int[] {result[0], result[1], roleCount + 1});
                }

            }
        }


        return 0;
    }

    private int[] find(String target, String[][] map) {
        int rowSize = map.length;
        int colSize = map[0].length;

        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                if (target.equals(map[row][col])) {
                    return new int[]{row, col};
                }
            }
        }

        return new int[]{-1, -1};
    }

    private int[] role(int startRow, int startCol, int[] move, String[][] map) {
        int rowSize = map.length;
        int colSize = map[0].length;
        int row = startRow;
        int col = startCol;
        int isHole = 0;

        while (true) {
            int nextRow = row + move[0];
            int nextCol = col + move[1];

            if (nextRow < 0 || nextRow >= rowSize) {
                break;
            }

            if (nextCol < 0 || nextCol >= colSize) {
                break;
            }

            if (WALL.equals(map[nextRow][nextCol])) {
                break;
            }

            row = nextRow;
            col = nextCol;

            if (HOLE.equals(map[nextRow][nextCol])) {
                isHole = IS_HOLE;
                break;
            }
        }

        return new int[] {row, col, isHole};
    }

}
