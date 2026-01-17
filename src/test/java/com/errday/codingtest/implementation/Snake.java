package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class Snake {

    private static final int UP = 0;
    private static final int RIGHT = 1;
    private static final int DOWN = 2;
    private static final int LEFT = 3;
    private static final int[] xMove = {-1, 0, 1, 0};
    private static final int[] yMove = {0, 1, 0, -1};
    private static final int EMPTY_CELL = 0;
    private static final int APPLE_CELL = 1;
    private static final int SNAKE_CELL = 2;

    @Test
    void case1() {
        int n = 6;
        int[][] appleLocation = {
                {3, 4},
                {2, 5},
                {5, 3}
        };
        String[][] turns = {
                {"3", "D"},
                {"15", "L"},
                {"17", "D"}
        };
        int answer = 9;

        assertThat(solution(n, appleLocation, turns)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int n = 10;
        int[][] appleLocation = {
                {1, 2},
                {1, 3},
                {1, 4},
                {1, 5},
        };
        String[][] turns = {
                {"8", "D"},
                {"10", "D"},
                {"11", "D"},
                {"13", "L"}
        };
        int answer = 21;

        assertThat(solution(n, appleLocation, turns)).isEqualTo(answer);
    }

    @Test
    void case3() {
        int n = 13;
        int[][] appleLocation = {
                {1, 5},
                {1, 3},
                {1, 2},
                {1, 6},
                {1, 7}
        };
        String[][] turns = {
                {"8", "D"},
                {"10", "D"},
                {"11", "D"},
                {"13", "L"}
        };
        int answer = 13;

        assertThat(solution(n, appleLocation, turns)).isEqualTo(answer);
    }

    private int solution(int n, int[][] appleLocation, String[][] turns) {
        int turnIndex = 0;
        int positionX = 0;
        int positionY = 0;
        int time = 0;

        Set<String> appleSet = new HashSet<>();
        for (int[] apple : appleLocation) {
            int x = apple[0] - 1;
            int y = apple[1] - 1;
            appleSet.add(x + "," + y);
        }

        ArrayDeque<int[]> snakePosition = new ArrayDeque<>();
        snakePosition.offer(new int[]{positionX, positionY});
        Set<String> snakeSet = new HashSet<>();

        snakeSet.add(positionX + "," + positionY);

        int direction = RIGHT;

        while (true) {
            int nextX = positionX + xMove[direction];
            int nextY = positionY + yMove[direction];

            time += 1;

            if (nextX >= n || nextX < 0 || nextY >= n || nextY < 0 || snakeSet.contains(nextX + "," + nextY)) {
                break;
            }

            String nextLocation = nextX + "," + nextY;

            snakeSet.add(nextLocation);
            snakePosition.offer(new int[]{nextX, nextY});

            if (appleSet.contains(nextLocation)) {
                appleSet.remove(nextLocation);
            } else {
                int[] tail = snakePosition.poll();
                snakeSet.remove(tail[0] + "," + tail[1]);
            }

            positionX = nextX;
            positionY = nextY;

            if (turnIndex < turns.length) {
                int turnTime = Integer.parseInt(turns[turnIndex][0]);

                if (time == turnTime) {
                    direction = getDirection(direction, turns[turnIndex][1]);
                    turnIndex += 1;
                }

            }

        }

        return time;
    }

    private int getDirection(int direction, String command) {
        if ("D".equals(command)) {
            return direction == 3 ? 0 : direction + 1;
        } else {
            return direction == 0 ? 3 : direction - 1;
        }
    }

}
