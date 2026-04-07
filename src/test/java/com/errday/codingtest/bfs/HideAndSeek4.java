package com.errday.codingtest.bfs;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class HideAndSeek4 {

    @Test
    void case1() {
        int n = 5;
        int k = 17;

        Map<String, Object> result = solution(n, k);

        int answer = 4;
        int[] path = new int[] {5, 10, 9, 18, 17};

        assertThat(result.get("time")).isEqualTo(answer);
        assertThat((int[]) result.get("path")).hasSize(path.length);
    }

    private Map<String, Object> solution(int n, int k) {
        int BOUNDARY = 100_000 + 1;
        int[] prev = new int[BOUNDARY];
        int[] times = new int[BOUNDARY];
        Arrays.fill(times, Integer.MAX_VALUE);
        times[n] = 0;

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {n, 0});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentPosition = current[0];
            int currentTime = current[1];

            int[] positions = new int[] {currentPosition - 1, currentPosition + 1, currentPosition * 2};

            for (int nextPosition : positions) {

                if (nextPosition < 0 || nextPosition >= BOUNDARY) {
                    continue;
                }

                int nextTime = currentTime + 1;

                if (times[nextPosition] <= nextTime) {
                    continue;
                }

                prev[nextPosition] = currentPosition;
                times[nextPosition] = nextTime;
                queue.offer(new int[] {nextPosition, nextTime});
            }
        }

        List<Integer> path = new ArrayList<>();
        int current = k;
        while (current != n) {
            path.add(current);
            current = prev[current];
        }
        path.add(n);

        Collections.reverse(path);

        int[] resultPath = path.stream()
                .mapToInt(Integer::intValue)
                .toArray();

        return Map.of("time", times[k], "path", resultPath);
    }
}
