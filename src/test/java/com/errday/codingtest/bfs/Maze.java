package com.errday.codingtest.bfs;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;

public class Maze {

    private int[][] maze = {
            {1,0,1,0,1,0},
            {1,1,1,1,1,1},
            {0,0,0,0,0,1},
            {1,1,1,1,1,1},
            {1,1,1,1,1,1},
    };

    private int answer = 10;

    @Test
    void solution() {
        int answer = 1;

        boolean[][] visited = new boolean[maze.length][maze[0].length];

        // up right down left
        int[] directRow = {-1, 0, 1, 0};
        int[] directCol = {0, 1, 0, -1};

        ArrayDeque<int[]> queue = new ArrayDeque<>();
        visited[0][0] = true;
        queue.offer(new int[]{0, 0});

        while (!queue.isEmpty()) {

            int[] currentPosition = queue.poll();

            int currentRow = currentPosition[0];
            int currentCol = currentPosition[1];

            if (isExit(currentRow, currentCol)) {
                break;
            }

            int rightX = currentRow + directRow[1];
            int rightY = currentCol + directCol[1];

            if (isInbounds(rightX, rightY) && !visited[rightX][rightY]) {
                if (maze[rightX][rightY] == 1) {
                    visited[rightX][rightY] = true;
                    queue.offer(new int[]{rightX, rightY});
                    answer += 1;
                    continue;
                }
            }

            int downX = currentRow + directRow[2];
            int downY = currentCol + directCol[2];

            if (isInbounds(downX, downY) && !visited[downX][downY]) {
                if (maze[downX][downY] == 1) {
                    visited[downX][downY] = true;
                    queue.offer(new int[]{downX, downY});
                    answer += 1;
                    continue;
                }
            }

            int upX = currentRow + directRow[0];
            int upY = currentCol + directCol[0];

            if (isInbounds(upX, upY) && !visited[upX][upY]) {
                if (maze[upX][upY] == 1) {
                    visited[upX][upY] = true;
                    queue.offer(new int[]{upX, upY});
                    answer += 1;
                    continue;
                }
            }


            int leftX = currentRow + directRow[3];
            int leftY = currentCol + directCol[3];

            if (isInbounds(leftX, leftY) && !visited[leftX][leftY]) {
                if (maze[leftX][leftY] == 1) {
                    visited[leftX][leftY] = true;
                    queue.offer(new int[]{leftX, leftY});
                    answer += 1;
                }
            }

        }


        Assertions.assertThat(answer).isEqualTo(this.answer);
    }

    private boolean isInbounds(int x, int y) {
        return x >= 0 && x < maze.length && y >= 0 && y < maze[0].length;
    }

    private boolean isExit(int x, int y) {
        return x == maze.length - 1 && y == maze[0].length -1;
    }
}
