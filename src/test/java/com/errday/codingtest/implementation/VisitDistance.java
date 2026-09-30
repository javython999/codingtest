package com.errday.codingtest.implementation;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class VisitDistance {

    @Test
    void case1() {
        String commands = "ULURRDLLU";
        int answer = 7;

        Assertions.assertThat(solution(commands)).isEqualTo(answer);
    }

    @Test
    void case2() {
        String commands = "LULLLLLLU";
        int answer = 7;

        Assertions.assertThat(solution(commands)).isEqualTo(answer);
    }

    private int solution(String commands) {
        int x = 0;
        int y = 0;
        Map<Character, int[]> directions = initDirectionMap();
        Set<String> moveHistory = new HashSet<>();

        for (char command : commands.toCharArray()) {
            int[] direction = directions.get(command);
            int toX = x + direction[0];
            int toY = y + direction[1];

            if (toX < -5 || toX > 5 || toY < -5 || toY > 5) {
                continue;
            }

            moveHistory.add(visitLog(x, y, toX, toY));
            moveHistory.add(visitLog(toX, toY, x, y));

            x = toX;
            y = toY;
        }

        return moveHistory.size() / 2;
    }

    private Map<Character, int[]> initDirectionMap() {
        Map<Character, int[]> map = new HashMap<>();
        map.put('U', new int[] {0, 1});
        map.put('R', new int[] {1, 0});
        map.put('D', new int[] {0, -1});
        map.put('L', new int[] {-1, 0});

        return map;
    }

    private String visitLog(int fromX, int fromY, int toX, int toY) {
        return "(" + fromX + "," + fromY + ")->(" + toX + "," + toY + ")";
    }
}
