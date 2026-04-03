package com.errday.codingtest.bfs;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class Key {

    @Test
    void case1() {
        int h = 5;
        int w = 17;
        char[][] building = {
                {'*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*',},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '*', '*', '$', '*',},
                {'*', 'B', '*', 'A', '*', 'P', '*', 'C', '*', '*', 'X', '*', 'Y', '*', '.', 'X', '.'},
                {'*', 'y', '*', 'x', '*', 'a', '*', 'p', '*', '*', '$', '*', '$', '*', '*', '$', '*'},
                {'*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*'}
        };
        String keys = "cz";

        int answer = 3;
        assertThat(solution(h, w, building, keys)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int h = 5;
        int w = 11;
        char[][] building = {
                {'*', '.', '*', '*', '*', '*', '*', '*', '*', '*', '*'},
                {'*', '.', '.', '.', '*', '.', '.', '.', '*', 'x', '*'},
                {'*', 'X', '*', '.', '*', '.', '*', '.', '*', '.', '*'},
                {'*', '$', '*', '.', '.', '.', '*', '.', '.', '.', '*'},
                {'*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*'}
        };
        String keys = "";

        int answer = 1;
        assertThat(solution(h, w, building, keys)).isEqualTo(answer);
    }

    @Test
    void case3() {
        int h = 7;
        int w = 7;
        char[][] building = {
                {'*', 'A', 'B', 'C', 'D', 'E', '*'},
                {'X', '.', '.', '.', '.', '.', 'F'},
                {'W', '.', '$', '$', '$', '.', 'G'},
                {'V', '.', '$', '$', '$', '.', 'H'},
                {'U', '.', '$', '$', '$', '.', 'J'},
                {'T', '.', '.', '.', '.', '.', 'K'},
                {'*', 'S', 'Q', 'P', 'M', 'L', '*'}
        };
        String keys = "irony";

        int answer = 0;
        assertThat(solution(h, w, building, keys)).isEqualTo(answer);
    }

    private char EMPTY = '.';
    private char WALL = '*';
    private char DOCUMENT = '$';
    private String DOORS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private int[][] MOVES =  {
        {-1, 0}, {0, 1}, {1, 0}, {0, -1}
    };


    private int solution(int h, int w, char[][] building, String keys) {

        char[][] newMap = wappedMap(building);

        int newHeight = newMap.length;
        int newWidth = newMap[0].length;
        int documentCount = 0;
        boolean[] getKeys = new boolean[DOORS.length()];

        boolean[][] visited = new boolean[newHeight][newWidth];
        visited[0][0] = true;

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {0, 0});

        List<int[]>[] doorsPosition = new List[26];
        for (int i = 0; i < 26; i++) {
            doorsPosition[i] = new ArrayList<>();
        }

        for (char key : keys.toCharArray()) {
            getKeys[key - 'a'] = true;
        }

        while (!queue.isEmpty()) {

            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];


            for (int[] move : MOVES) {
                int nextRow = row + move[0];
                int nextCol = col + move[1];

                if (nextRow < 0 || nextRow >= newHeight) {
                    continue;
                }

                if (nextCol < 0 || nextCol >= newWidth) {
                    continue;
                }

                if (visited[nextRow][nextCol]) {
                    continue;
                }

                char nextType = newMap[nextRow][nextCol];

                if (nextType == EMPTY) {
                    visited[nextRow][nextCol] = true;
                    queue.offer(new int[] {nextRow, nextCol});
                    continue;
                }

                if (nextType == WALL) {
                    visited[nextRow][nextCol] = true;
                    continue;
                }

                if (nextType == DOCUMENT) {
                    visited[nextRow][nextCol] = true;
                    queue.offer(new int[] {nextRow, nextCol});
                    documentCount += 1;
                    continue;
                }

                if (DOORS.indexOf(nextType) != -1 && !getKeys[Character.toLowerCase(nextType) - 'a']) {
                    doorsPosition[nextType - 'A'].add(new int[] {nextRow, nextCol});
                    continue;
                }

                if (DOORS.indexOf(nextType) != -1 && getKeys[Character.toLowerCase(nextType) - 'a']) {
                    newMap[nextRow][nextCol] = EMPTY;
                    visited[nextRow][nextCol] = true;
                    queue.offer(new int[] {nextRow, nextCol});
                    continue;
                }

                if ('a' <= nextType && nextType <= 'z') {
                    int keyIndex = nextType - 'a';

                    if (!visited[nextRow][nextCol]) {
                        visited[nextRow][nextCol] = true;
                        queue.offer(new int[] {nextRow, nextCol});
                    }

                    if (!getKeys[keyIndex]) {
                        getKeys[keyIndex] = true;

                        for (int[] door : doorsPosition[keyIndex]) {

                            if (!visited[door[0]][door[1]]) {
                                visited[door[0]][door[1]] = true;
                                queue.offer(new int[] {door[0], door[1]});
                            }
                        }
                        doorsPosition[nextType - 'a'].clear();

                    }

                }

            }

        }

        return documentCount;
    }

    private char[][] wappedMap(char[][] map) {
        int height = map.length;
        int width = map[0].length;

        int newHeight = height + 2;
        int newWidth = width + 2;

        char[][] newMap = new char[newHeight][newWidth];
        for (char[] row : newMap) {
            Arrays.fill(row, EMPTY);
        }

        for (int row = 1; row < newHeight - 1; row++) {
            for (int col = 1; col < newWidth - 1; col++) {
                newMap[row][col] = map[row-1][col-1];
            }
        }


        return newMap;
    }

}
