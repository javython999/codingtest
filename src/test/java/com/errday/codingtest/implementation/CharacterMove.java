package com.errday.codingtest.implementation;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class CharacterMove {

    private int n = 4;
    private int m = 4;
    private int[] characterStatus = new int[] {1, 1, 0};
    private int[][] map = new int[][] {
            {1, 1, 1, 1},
            {1, 0, 0, 1},
            {1, 1, 0, 1},
            {1, 1, 1, 1}
    };
    private int answer = 3;

    @Test
    void solution() {

        int sea = 1;
        int land = 0;

        int[] nextMoveX = {0, 1, 0, -1};
        int[] nextMoveY = {1, 0, -1, 0};

        int currentPositionX = characterStatus[0];
        int currentPositionY = characterStatus[1];
        int currentDirection = characterStatus[2];

        boolean[][] visited = new boolean[map.length][map.length];

        // 최초 지점 방문 처리
        visited[currentPositionX][currentPositionY] = true;
        int totalMoveCount = 1;

        int turnCount = 0;

        while (true) {

            if (turnCount == 4) {
                int backDirection = turnLeft(currentDirection+1);
                int backX = currentPositionX + nextMoveX[backDirection];
                int backY = currentPositionY + nextMoveY[backDirection];

                int backArea = map[backX][backY];
                if (backArea == sea) {
                    break;
                }
                currentPositionX = backX;
                currentPositionY = backY;
                turnCount = 0;
            }



            // 1. 현재 위치에서 현재 방향을 기준으로 왼쪽 방향부터 차례대로 갈 곳을 정한다.
            int tempDirection = turnLeft(currentDirection);

            int nextX = currentPositionX + nextMoveX[tempDirection];
            int nextY = currentPositionY + nextMoveY[tempDirection];
            int nextArea = map[nextX][nextY];

            // 왼쪽 방향에 아직 가보지 않은 칸이 존재한다면, 왼쪽 방향으로 횐전한 다음 한
            if (nextArea == land && !visited[nextX][nextY]) {
                turnCount = 0;
                currentDirection = turnLeft(currentDirection);
                // 방문 기록
                visited[nextX][nextY] = true;

                // 이동
                currentPositionX += nextMoveX[currentDirection];
                currentPositionY += nextMoveY[currentDirection];

                // 이동 횟수 누적
                totalMoveCount += 1;
            } else {
                // 가보지 않은 칸이 없다면 왼쪽 방향으로만 회전하고 1단계로 돌아간다.
                currentDirection = turnLeft(currentDirection);
                turnCount += 1;
            }
        }

        Assertions.assertThat(totalMoveCount).isEqualTo(answer);
    }

    private int turnLeft(int currentDirection) {
        int nextDirection = currentDirection - 1;
        return nextDirection < 0 ? 3 : nextDirection;
    }


}
