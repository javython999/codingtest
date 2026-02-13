package com.errday.codingtest.dfs;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class AvoidSurveillance {

    private final String STUDENT = "S";
    private final String TEACHER = "T";
    private final String BLOCK = "O";
    private final String EMPTY = "X";

    private int BLOCK_INSTALL_COUNT = 3;
    private String[][] map;
    private List<int[]> teacherLocations = new ArrayList<>();;
    private List<int[]> emptyLocations = new ArrayList<>();

    private boolean canAvoid = false;

    @Test
    void case1() {
        String[][] map = {
                {"X", "S", "X", "X", "T"},
                {"T", "X", "S", "X", "X"},
                {"X", "X", "X", "X", "X"},
                {"X", "T", "X", "X", "X"},
                {"X", "X", "T", "X", "X"},
        };

        String answer = "YES";

        assertThat(solution(map)).isEqualTo(answer);
    }

    @Test
    void case2() {
        String[][] map = {
                {"S", "S", "S", "T"},
                {"X", "X", "X", "X"},
                {"X", "X", "X", "X"},
                {"T", "T", "T", "X"},
        };

        String answer = "NO";

        assertThat(solution(map)).isEqualTo(answer);
    }

    @Test
    void case3() {
        String[][] map = {
                {"X", "X", "T", "X"},
                {"X", "X", "X", "X"},
                {"T", "X", "S", "T"},
                {"X", "X", "X", "X"},
                {"X", "X", "T", "X"},
        };

        String answer = "NO";

        assertThat(solution(map)).isEqualTo(answer);
    }

    private String solution(String[][] map) {
        this.map = map;
        initLocations(map);

        dfs(0, 0);

        return canAvoid ? "YES" : "NO";
    }

    private void initLocations(String[][] map) {
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {

                if (TEACHER.equals(map[row][col])) {
                    teacherLocations.add(new int[]{row, col});
                } else if (EMPTY.equals(map[row][col])) {
                    emptyLocations.add(new int[]{row, col});
                }
            }
        }
    }

    private void dfs(int start, int count) {
        if (canAvoid) {
            return;
        }

        if (BLOCK_INSTALL_COUNT == count) {
            if (isSafe()) {
                canAvoid = true;
            }
            return;
        }

        for (int i = start; i < emptyLocations.size(); i++) {
            int[] position = emptyLocations.get(i);
            int x = position[0];
            int y = position[1];
            map[x][y] = BLOCK;
            dfs(i + 1, count + 1);
            map[x][y] = EMPTY;
        }
    }

    private boolean isSafe() {
        int[] moveX = {0, 1, 0, -1};
        int[] moveY = {-1, 0, 1, 0};

        for (int[] teacherLocation : teacherLocations) {
            int x =  teacherLocation[0];
            int y = teacherLocation[1];

            for (int direction = 0; direction < 4; direction++) {
                int nextX = x;
                int nextY = y;

                while (true) {
                    nextX += moveX[direction];
                    nextY += moveY[direction];

                    if (nextX < 0 || nextX >= map.length) {
                        break;
                    }

                    if (nextY < 0 || nextY >= map[nextX].length) {
                        break;
                    }

                    if (BLOCK.equals(map[nextX][nextY])) {
                        break;
                    }

                    if (STUDENT.equals(map[nextX][nextY])) {
                        return false;
                    }
                }
            }
        }

        return true;
    }


}
