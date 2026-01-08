package com.errday.codingtest.greedy;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class Mukbang {

    @Test
    void case1() {
        long[] foodTimes = {1, 2, 3, 4};
        long networkErrorSecond = 1;
        int answer = 2;
        assertThat(solution(foodTimes, networkErrorSecond)).isEqualTo(answer);
    }

    @Test
    void case2() {
        long[] foodTimes = {3, 1, 2};
        long networkErrorSecond = 5;
        int answer = 1;
        assertThat(solution(foodTimes, networkErrorSecond)).isEqualTo(answer);
    }

    @Test
    void case3() {
        long[] foodTimes = {8, 6, 4};
        long networkErrorSecond = 15;
        int answer = 2;
        assertThat(solution(foodTimes, networkErrorSecond)).isEqualTo(answer);
    }

    @Test
    void case4() {
        long[] foodTimes = {
                100_000_000, 99_000_000, 88_000_000, 77_000_000, 66_000_000, 55_000_000, 44_000_000, 33_000_000, 22_000_000, 11_000_000,
                100_000_000, 99_000_000, 88_000_000, 77_000_000, 66_000_000, 55_000_000, 44_000_000, 33_000_000, 22_000_000, 11_000_000,
                100_000_000, 99_000_000, 88_000_000, 77_000_000, 66_000_000, 55_000_000, 44_000_000, 33_000_000, 22_000_000, 11_000_000,
                100_000_000, 99_000_000, 88_000_000, 77_000_000, 66_000_000, 55_000_000, 44_000_000, 33_000_000, 22_000_000, 11_000_000,
                100_000_000, 99_000_000, 88_000_000, 77_000_000, 66_000_000, 55_000_000, 44_000_000, 33_000_000, 22_000_000, 11_000_000,
                100_000_000, 99_000_000, 88_000_000, 77_000_000, 66_000_000, 55_000_000, 44_000_000, 33_000_000, 22_000_000, 11_000_000,
                100_000_000, 99_000_000, 88_000_000, 77_000_000, 66_000_000, 55_000_000, 44_000_000, 33_000_000, 22_000_000, 11_000_000,
                100_000_000, 99_000_000, 88_000_000, 77_000_000, 66_000_000, 55_000_000, 44_000_000, 33_000_000, 22_000_000, 11_000_000,
                100_000_000, 99_000_000, 88_000_000, 77_000_000, 66_000_000, 55_000_000, 44_000_000, 33_000_000, 22_000_000, 11_000_000,
                100_000_000, 99_000_000, 88_000_000, 77_000_000, 66_000_000, 55_000_000, 44_000_000, 33_000_000, 22_000_000, 11_000_000,
                100_000_000, 99_000_000, 88_000_000, 77_000_000, 66_000_000, 55_000_000, 44_000_000, 33_000_000, 22_000_000, 11_000_000,
                100_000_000, 99_000_000, 88_000_000, 77_000_000, 66_000_000, 55_000_000, 44_000_000, 33_000_000, 22_000_000, 11_000_000,
        };
        long networkErrorSecond = 1_000_000_000;
        int answer = 41;
        assertThat(solution(foodTimes, networkErrorSecond)).isEqualTo(answer);
    }

    @Test
    void case5() {
        long[] foodTimes = {1, 2, 3};
        long networkErrorSecond = 7;
        int answer = -1;
        assertThat(solution(foodTimes, networkErrorSecond)).isEqualTo(answer);
    }

    private int solution(long[] foodTimes, long networkErrorSecond) {

        int foodCount = foodTimes.length;

        long totalFoodQuantity = Arrays.stream(foodTimes).sum();
        if (totalFoodQuantity <= networkErrorSecond) {
            return -1;
        }

        PriorityQueue<long[]> queue = new PriorityQueue<>(Comparator.comparingLong(data -> data[1]));
        for (int i = 0; i < foodCount; i++) {
            queue.offer(new long[] {i + 1, foodTimes[i]});
        }

        long prev = 0;
        long remain = foodCount;
        long k = networkErrorSecond;

        while (!queue.isEmpty()) {
            // 가장 양이 작은 음식 정보
            long[] foodInfo = queue.peek();
            long foodTime = foodInfo[1];

            // 직전 순회에서 먹은 양을 제외하고 이번 순회때 이 음식을 없애기 위해 먹어야 되는 양
            long round = foodTime - prev;

            // 남아 있는 모든 음식을 같은 양으로 줄이기 위해 필요한 시간
            long need = round * remain;

            // 남아 있는 모든 음식을 같은 양으로 먹을 수 없는 경우
            if (k < need) {
                break;
            }

            // 남아 있는 모든 음식을 같은 양으로 줄이기 위해 소모한 시간 반영
            k -= need;

            // 가장 양이 작은 음식을 다 먹은 것으로 처리
            queue.poll();

            // 가장 양이 작은 음식의 양만큼 prev 갱신
            prev = foodTime;

            // 남아 있는 음식 개수 1개 감소
            remain -= 1;

        }

        if (queue.isEmpty()) {
            return -1;
        }

        List<long[]> rest = new ArrayList<>(queue);
        rest.sort(Comparator.comparingLong(data -> data[0]));

        int nextFoodIndex = (int) (k % remain);
        return (int) rest.get(nextFoodIndex)[0];
    }
}
