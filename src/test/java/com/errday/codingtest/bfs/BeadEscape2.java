package com.errday.codingtest.bfs;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;

public class BeadEscape2 {

    @Test
    void case1() {
        int n = 5;
        int m = 5;
        String[][] map = {
                {"#", "#", "#", "#", "#"},
                {"#", ".", ".", "B", "#"},
                {"#", ".", "#", ".", "#"},
                {"#", "R", "O", ".", "#"},
                {"#", "#", "#", "#", "#"},
        };
        int answer = 1;
        assertThat(solution(n, m, map)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int n = 7;
        int m = 7;
        String[][] map = {
                {"#", "#", "#", "#", "#", "#", "#"},
                {"#", ".", ".", ".", "R", "B", "#"},
                {"#", ".", "#", "#", "#", "#", "#"},
                {"#", ".", ".", ".", ".", ".", "#"},
                {"#", "#", "#", "#", "#", ".", "#"},
                {"#", "O", ".", ".", ".", ".", "#"},
                {"#", "#", "#", "#", "#", "#", "#"},
        };
        int answer = 5;
        assertThat(solution(n, m, map)).isEqualTo(answer);
    }

    @Test
    void case3() {
        int n = 7;
        int m = 7;
        String[][] map = {
                {"#", "#", "#", "#", "#", "#", "#"},
                {"#", ".", ".", "R", "#", "B", "#"},
                {"#", ".", "#", "#", "#", "#", "#"},
                {"#", ".", ".", ".", ".", ".", "#"},
                {"#", "#", "#", "#", "#", ".", "#"},
                {"#", "O", ".", ".", ".", ".", "#"},
                {"#", "#", "#", "#", "#", "#", "#"}
        };
        int answer = 5;
        assertThat(solution(n, m, map)).isEqualTo(answer);
    }

    @Test
    void case4() {
        int n = 10;
        int m = 10;
        String[][] map = {
                {"#", "#", "#", "#", "#", "#", "#", "#", "#", "#"},
                {"#", "R", "#", ".", ".", ".", "#", "#", "B", "#"},
                {"#", ".", ".", ".", "#", ".", "#", "#", ".", "#"},
                {"#", "#", "#", "#", "#", ".", "#", "#", ".", "#"},
                {"#", ".", ".", ".", ".", ".", ".", "#", ".", "#"},
                {"#", ".", "#", "#", "#", "#", "#", "#", ".", "#"},
                {"#", ".", "#", ".", ".", ".", ".", "#", ".", "#"},
                {"#", ".", "#", ".", "#", ".", "#", ".", ".", "#"},
                {"#", ".", ".", ".", "#", ".", "O", "#", ".", "#"},
                {"#", "#", "#", "#", "#", "#", "#", "#", "#", "#"}
        };
        int answer = -1;
        assertThat(solution(n, m, map)).isEqualTo(answer);
    }

    @Test
    void case5() {
        int n = 3;
        int m = 7;
        String[][] map = {
                {"#", "#", "#", "#", "#", "#", "#"},
                {"#", "R", ".", "O", ".", "B", "#"},
                {"#", "#", "#", "#", "#", "#", "#"}
        };
        int answer = 1;
        assertThat(solution(n, m, map)).isEqualTo(answer);
    }

    @Test
    void case6() {
        int n = 10;
        int m = 10;
        String[][] map = {
                {"#", "#", "#", "#", "#", "#", "#", "#", "#", "#"},
                {"#", "R", "#", ".", ".", ".", "#", "#", "B", "#"},
                {"#", ".", ".", ".", "#", ".", "#", "#", ".", "#"},
                {"#", "#", "#", "#", "#", ".", "#", "#", ".", "#"},
                {"#", ".", ".", ".", ".", ".", ".", "#", ".", "#"},
                {"#", ".", "#", "#", "#", "#", "#", "#", ".", "#"},
                {"#", ".", "#", ".", ".", ".", ".", "#", ".", "#"},
                {"#", ".", "#", ".", "#", "#", ".", ".", ".", "#"},
                {"#", "O", ".", ".", "#", ".", ".", ".", ".", "#"},
                {"#", "#", "#", "#", "#", "#", "#", "#", "#", "#"}
        };
        int answer = 7;
        assertThat(solution(n, m, map)).isEqualTo(answer);
    }

    @Test
    void case7() {
        int n = 3;
        int m = 10;
        String[][] map = {
                {"#", "#", "#", "#", "#", "#", "#", "#", "#", "#"},
                {"#", ".", "O", ".", ".", ".", ".", "R", "B", "#"},
                {"#", "#", "#", "#", "#", "#", "#", "#", "#", "#"}
        };
        int answer = -1;
        assertThat(solution(n, m, map)).isEqualTo(answer);
    }


    private String WALL = "#";
    private String EMPTY = ".";
    private String HOLE = "O";
    private String RED = "R";
    private String BLUE = "B";
    private int[][] MOVES = {
            {-1, 0},
            {0, 1},
            {1, 0},
            {0, -1}
    };
    private class Result {
        int row;
        int col;
        boolean isHole;
        int moveCount;

        public Result(int row, int col, boolean isHole, int moveCount) {
            this.row = row;
            this.col = col;
            this.isHole = isHole;
            this.moveCount = moveCount;
        }
    }

    private int solution(int rowSize, int colSize, String[][] map) {

        int[] redPosition = new int[2];
        int[] bluePosition = new int[2];

        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                if (map[row][col].equals(RED)) {
                    redPosition = new int[]{row, col};
                } else if (map[row][col].equals(BLUE)) {
                    bluePosition = new int[]{row, col};
                }
            }
        }

        boolean[][][][] visited = new boolean[rowSize][colSize][rowSize][colSize];
        visited[redPosition[0]][redPosition[1]][bluePosition[0]][bluePosition[1]] = true;

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{redPosition[0], redPosition[1], bluePosition[0], bluePosition[1], 0});

        while (!queue.isEmpty()) {

            int[] current = queue.poll();

            int redRow = current[0];
            int redCol = current[1];
            int blueRow = current[2];
            int blueCol = current[3];
            int depth = current[4];

            if (depth >= 10) {
                continue;
            }

            for (int[] move : MOVES) {

                Result blueResult = role(blueRow, blueCol, move, map);
                if (blueResult.isHole) {
                    continue;
                }

                Result redResult = role(redRow, redCol, move, map);
                if (redResult.isHole) {
                    return depth + 1;
                }


                if (redResult.row == blueResult.row && redResult.col == blueResult.col) {

                    if (redResult.moveCount > blueResult.moveCount) {
                        redResult.row -= move[0];
                        redResult.col -= move[1];
                    } else {
                        blueResult.row -= move[0];
                        blueResult.col -= move[1];
                    }

                }

                if (visited[redResult.row][redResult.col][blueResult.row][blueResult.col]) {
                    continue;
                }

                visited[redResult.row][redResult.col][blueResult.row][blueResult.col] = true;
                queue.offer(new int[]{redResult.row, redResult.col, blueResult.row, blueResult.col, depth + 1});

            }

        }

        return -1;
    }

    private Result role(int startRow, int startCol, int[] move, String[][] map) {

        int currentRow = startRow;
        int currentCol = startCol;
        int moveCount = 0;
        while (true) {
            int nextRow = currentRow + move[0];
            int nextCol = currentCol + move[1];

            if (map[nextRow][nextCol].equals(WALL)) {
                return new Result(currentRow, currentCol, false, moveCount);
            }

            if (map[nextRow][nextCol].equals(HOLE)) {
                return new Result(currentRow, currentCol, true, moveCount + 1);
            }

            moveCount += 1;
            currentRow = nextRow;
            currentCol = nextCol;

        }

    }


}
