package com.errday.codingtest.bfs;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BabyShark {
    private final int EMPTY = 0;
    private final int SHARK = 9;
    private int sharkSize = 2;
    private int[] sharkPosition;

    @Test
    void case1() {
        int[][] space = {{0, 0, 0}, {0, 0, 0}, {0, 9, 0}};
        int answer = 0;
        assertThat(solution(space)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int[][] space = {{0, 0, 1}, {0, 0, 0}, {0, 9, 0}};
        int answer = 3;
        assertThat(solution(space)).isEqualTo(answer);
    }

    @Test
    void case3() {
        int[][] space = {{4, 3, 2, 1}, {0, 0, 0, 0}, {0, 0, 9, 0}, {1, 2, 3, 4}};
        int answer = 14;
        assertThat(solution(space)).isEqualTo(answer);
    }

    private int solution(int[][] space) {
        sharkPosition = getSharkPosition(space);
        int result = 0;
        int ate = 0;
        while (true) {

            int[][] shortPath = shortPath(space);
            int[] target = targetPosition(shortPath, space);

            if (target[0] == -1) {
                return result;
            }

            int row = target[0];
            int col = target[1];
            int dist = target[2];

            result += dist;

            sharkPosition = new int[]{row, col};
            space[row][col] = EMPTY;

            ate++;

            if (ate == sharkSize) {
                sharkSize++;
                ate = 0;
            }
        }
    }

    private int[] getSharkPosition(int[][] space) {
        for (int row = 0; row < space.length; row++) {
            for (int col = 0; col < space[0].length; col++) {
                if (space[row][col] == SHARK) {
                    space[row][col] = EMPTY;
                    return new int[]{row, col};
                }
            }
        }
        throw new IllegalArgumentException("Shark not found");
    }

    private int[][] shortPath(int[][] space) {
        int[] moveRow = {-1, 0, 1, 0};
        int[] moveCol = {0, 1, 0, -1};
        int[][] result = new int[space.length][space[0].length];
        for (int[] row : result) {
            Arrays.fill(row, -1);
        }
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(sharkPosition);
        result[sharkPosition[0]][sharkPosition[1]] = 0;
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];
            for (int i = 0; i < 4; i++) {
                int nextRow = row + moveRow[i];
                int nextCol = col + moveCol[i];
                if (isOutbound(nextRow, nextCol, space.length)) {
                    continue;
                }
                if (result[nextRow][nextCol] == -1 && space[nextRow][nextCol] <= sharkSize) {
                    result[nextRow][nextCol] = result[row][col] + 1;
                    queue.offer(new int[]{nextRow, nextCol});
                }
            }
        }
        return result;
    }

    private boolean isOutbound(int row, int col, int size) {
        return row < 0 || row >= size || col < 0 || col >= size;
    }

    private int[] targetPosition(int[][] shortPath, int[][] space) {
        int row = 0;
        int col = 0;
        int min = Integer.MAX_VALUE / 2;
        for (int rowIndex = 0; rowIndex < shortPath.length; rowIndex++) {
            for (int colIndex = 0; colIndex < shortPath[0].length; colIndex++) {
                if (isReachable(shortPath, rowIndex, colIndex) && isExistFish(space, rowIndex, colIndex) && isSmallThanShark(space, rowIndex, colIndex)) {
                    if (shortPath[rowIndex][colIndex] < min) {
                        row = rowIndex;
                        col = colIndex;
                        min = shortPath[rowIndex][colIndex];
                    }
                }
            }
        }
        if (min == Integer.MAX_VALUE / 2) {
            return new int[]{-1, -1, -1};
        } else {
            return new int[]{row, col, min};
        }
    }

    private boolean isReachable(int[][] shortPath, int row, int col) {
        return shortPath[row][col] != -1;
    }

    private boolean isExistFish(int[][] space, int row, int col) {
        return 0 < space[row][col];
    }

    private boolean isSmallThanShark(int[][] space, int row, int col) {
        return space[row][col] < sharkSize;
    }
}
