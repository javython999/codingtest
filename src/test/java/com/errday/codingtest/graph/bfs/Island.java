package com.errday.codingtest.graph.bfs;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Queue;

public class Island {

    @Test
    void case1() {
        int w = 1;
        int h = 1;
        int[][] map = {
                {0}
        };
        int answer = 0;
        Assertions.assertThat(solution(w, h, map)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int w = 2;
        int h = 2;
        int[][] map = {
                {0, 1},
                {1, 0}
        };
        int answer = 1;
        Assertions.assertThat(solution(w, h, map)).isEqualTo(answer);
    }

    @Test
    void case3() {
        int w = 2;
        int h = 2;
        int[][] map = {
                {1, 1, 1},
                {1, 1, 1},
        };
        int answer = 1;
        Assertions.assertThat(solution(w, h, map)).isEqualTo(answer);
    }

    @Test
    void case4() {
        int w = 5;
        int h = 4;
        int[][] map = {
                {1, 0, 1, 0, 0},
                {1, 0, 0, 0, 0},
                {1, 0, 1, 0, 1},
                {1, 0, 0, 1, 0},
        };
        int answer = 3;
        Assertions.assertThat(solution(w, h, map)).isEqualTo(answer);
    }


    @Test
    void case5() {
        int w = 5;
        int h = 5;
        int[][] map = {
                {1, 0, 1, 0, 1},
                {0, 0, 0, 0, 0},
                {1, 0, 1, 0, 1},
                {0, 0, 0, 0, 0},
                {1, 0, 1, 0, 1},
        };
        int answer = 9;
        Assertions.assertThat(solution(w, h, map)).isEqualTo(answer);
    }

    private int SEA = 0;
    private int[][] moves = {
            {-1, -1},
            {-1, 0},
            {-1, 1},
            {0, -1},
            {0, 1},
            {1, -1},
            {1, 0},
            {1, 1},
    };

    private int solution(int w, int h, int[][] map) {
        Queue<int[]> queue = new ArrayDeque<>();

        int answer = 0;

        for (int row = 0; row < h; row++) {
            for (int col = 0; col < w; col++) {

                if (map[row][col] == SEA) {
                    continue;
                }

                answer += 1;
                map[row][col] = SEA;
                queue.offer(new int[] {row, col});

                while (!queue.isEmpty()) {
                    int[] current = queue.poll();
                    map[current[0]][current[1]] = SEA;
                    for (int[] move : moves) {
                        int newRow = current[0] + move[0];
                        int newCol = current[1] + move[1];

                        if (newRow < 0 || newRow >= h) {
                            continue;
                        }

                        if (newCol < 0 || newCol >= w) {
                            continue;
                        }

                        if (map[newRow][newCol] == SEA) {
                            continue;
                        }

                        queue.offer(new int[] {newRow, newCol});
                        map[newRow][newCol] = SEA;
                    }
                }
            }
        }

        return answer;
    }
}
