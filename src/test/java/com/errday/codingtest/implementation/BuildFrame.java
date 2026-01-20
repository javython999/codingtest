package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class BuildFrame {

    private final int PILLAR = 0;
    private final int BEAM = 1;
    private final int REMOVE = 0;
    private final int INSTALL = 1;

    @Test
    void case1() {
        int n = 5;
        int[][] buildFrame = {
                {1, 0, 0, 1},
                {1, 1, 1, 1},
                {2, 1, 0, 1},
                {2, 2, 1, 1},
                {5, 0, 0, 1},
                {5, 1, 0, 1},
                {4, 2, 1, 1},
                {3, 2, 1, 1}
        };
        int[][] answer = {
                {1, 0, 0},
                {1, 1, 1},
                {2, 1, 0},
                {2, 2, 1},
                {3, 2, 1},
                {4, 2, 1},
                {5, 0, 0},
                {5, 1, 0}
        };

        assertThat(solution(n, buildFrame)).isDeepEqualTo(answer);
    }

    @Test
    void case2() {
        int n = 5;
        int[][] buildFrame = {
                {0, 0, 0, 1},
                {2, 0, 0, 1},
                {4, 0, 0, 1},
                {0, 1, 1, 1},
                {1, 1, 1, 1},
                {2, 1, 1, 1},
                {3, 1, 1, 1},
                {2, 0, 0, 0},
                {1, 1, 1, 0},
                {2, 2, 0, 1}
        };
        int[][] answer = {
                {0, 0, 0},
                {0, 1, 1},
                {1, 1, 1},
                {2, 1, 1},
                {3, 1, 1},
                {4, 0, 0}
        };

        assertThat(solution(n, buildFrame)).isDeepEqualTo(answer);
    }

    private int[][] solution(int n, int[][] buildFrame) {

        boolean[][] pillar = new boolean[n + 1][n + 1];
        boolean[][] beam = new boolean[n + 1][n + 1];

        for (int[] build : buildFrame) {

            int x = build[0];
            int y = build[1];
            int type = build[2];
            int job = build[3];

            if (job == INSTALL) {

                if (type == PILLAR) {
                    pillar[x][y] = true;

                    if (isImpossible(pillar, beam)) {
                        pillar[x][y] = false;
                    }

                } else if (type == BEAM) {

                    beam[x][y] = true;

                    if (isImpossible(pillar, beam)) {
                        beam[x][y] = false;
                    }
                }

            } else if (job == REMOVE) {
                if (type == PILLAR) {
                    pillar[x][y] = false;

                    if (isImpossible(pillar, beam)) {
                        pillar[x][y] = true;
                    }

                } else if (type == BEAM) {

                    beam[x][y] = false;

                    if (isImpossible(pillar, beam)) {
                        beam[x][y] = true;
                    }
                }
            }

        }

        List<int[]> result = new ArrayList<>();
        for (int x = 0; x < n + 1; x++) {
            for (int y = 0; y < n + 1; y++) {
                if(pillar[x][y]) {
                    result.add(new int[]{x, y, PILLAR});
                }

                if (beam[x][y]) {
                    result.add(new int[]{x, y, BEAM});
                }
            }
        }

        return result.toArray(new int[0][]);
    }

    private boolean isImpossible(boolean[][] pillar, boolean[][] beam) {

        int size = pillar.length;

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {

                if (pillar[x][y]) {
                    if (!canPlacePillar(x, y, pillar, beam)) {
                        return true;
                    }
                }

                if (beam[x][y]) {
                    if (!canPlaceBeam(x, y, pillar, beam)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean canPlacePillar(int x, int y, boolean[][] pillar, boolean[][] beam) {

        if (y == 0) {
            return true;
        }

        if (beam[x - 1][y]) {
            return true;
        }

        if (beam[x][y]) {
            return true;
        }

        if (pillar[x][y - 1]) {
            return true;
        }

        return false;
    }

    private boolean canPlaceBeam(int x, int y, boolean[][] pillar, boolean[][] beam) {
        if (pillar[x][y - 1]) {
            return true;
        }

        if (pillar[x + 1][y - 1]) {
            return true;
        }

        if (beam[x - 1][y] && beam[x + 1][y]) {
            return true;
        }

        return false;
    }
}
