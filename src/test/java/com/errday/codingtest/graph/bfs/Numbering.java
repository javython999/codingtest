package com.errday.codingtest.graph.bfs;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Numbering {

    @Test
    void case1() {
        int[][] map = {
                {0, 1, 1, 0, 1, 0, 0},
                {0, 1, 1, 0, 1, 0, 1},
                {1, 1, 1, 0, 1, 0, 1},
                {0, 0, 0, 0, 1, 1, 1},
                {0, 1, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 0, 0, 0}
        };
        int[] answer = {7, 8, 9};

        assertThat(solution(map)).isEqualTo(answer);
    }

    private int EMPTY = 0;
    private int[][] MOVES = {
            {-1, 0},
            {0 , 1},
            {1, 0},
            {0, -1}
    };

    private int[] solution(int[][] map) {
        int rowSize = map.length;
        int colSize = map[0].length;

        List<Integer> buildings = new ArrayList<>();

        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {

                if (map[i][j] == EMPTY) {
                    continue;
                }

                int count = 1;
                ArrayDeque<int[]> queue = new ArrayDeque<>();
                queue.offer(new int[] {i, j});
                map[i][j] = EMPTY;

                while (!queue.isEmpty()) {
                    int[] current = queue.poll();
                    int row = current[0];
                    int col = current[1];

                    for (int[] move : MOVES) {
                        int nextRow = row + move[0];
                        int nextCol = col + move[1];

                        if (nextRow < 0 || nextRow >= rowSize) {
                            continue;
                        }

                        if (nextCol < 0 || nextCol >= colSize) {
                            continue;
                        }

                        if (map[nextRow][nextCol] == EMPTY) {
                            continue;
                        }

                        map[nextRow][nextCol] = EMPTY;
                        count += 1;
                        queue.offer(new int[] {nextRow, nextCol});
                    }
                }

                buildings.add(count);
            }
        }


        return buildings
                .stream()
                .sorted()
                .mapToInt(Integer::intValue)
                .toArray();
    }


}
