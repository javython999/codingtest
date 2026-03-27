package com.errday.codingtest.bfs;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;

public class BeadEscape {

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
    private String HOLE = "O";
    private String RED = "R";
    private String BLUE = "B";
    private int[][] MOVES = {
            {-1, 0},
            {0, 1},
            {1, 0},
            {0, -1}
    };

    private class State {
        int redRow;
        int redCol;
        int blueRow;
        int blueCol;
        int depth;

        public State(int redRow, int redCol, int blueRow, int blueCol, int depth) {
            this.redRow = redRow;
            this.redCol = redCol;
            this.blueRow = blueRow;
            this.blueCol = blueCol;
            this.depth = depth;
        }
    }

    private class Result {
        int row;
        int col;
        int moveCount;
        boolean isHole;

        public Result(int row, int col, int moveCount, String value) {
            this.row = row;
            this.col = col;
            this.moveCount = moveCount;
            this.isHole = HOLE.equals(value);
        }
    }


    private int solution(int n, int m, String[][] map) {

        int[] redPosition = find(RED, map);
        int[] bluePosition = find(BLUE, map);

        boolean[][][][] visited = new boolean[n][m][n][m];
        visited[redPosition[0]][redPosition[1]][bluePosition[0]][bluePosition[1]] = true;

        Queue<State> queue = new ArrayDeque<>();
        queue.add(new State(redPosition[0], redPosition[1], bluePosition[0], bluePosition[1], 0));

        while (!queue.isEmpty()) {

            State current = queue.poll();

            if (current.depth >= 10) {
                continue;
            }

            for (int[] move : MOVES) {

                Result blueResult = role(current.blueRow, current.blueCol, move, map);
                if (blueResult.isHole) {
                    continue;
                }

                Result redResult = role(current.redRow, current.redCol, move, map);
                if (redResult.isHole) {
                    return current.depth + 1;
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
                queue.offer(new State(redResult.row, redResult.col, blueResult.row, blueResult.col, current.depth + 1));

            }


        }

        return -1;
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

    private Result role(int startRow, int startCol, int[] direction, String[][] map) {
        int row = startRow;
        int col = startCol;
        int moveCount = 0;

        while (true) {
            int nextRow = row + direction[0];
            int nextCol = col + direction[1];

            if (map[nextRow][nextCol].equals(WALL)) {
                break;
            }

            row = nextRow;
            col = nextCol;
            moveCount += 1;

            if (map[row][col].equals(HOLE)) {
                break;
            }
        }

        return new Result(row, col, moveCount, map[row][col]);
    }
}
