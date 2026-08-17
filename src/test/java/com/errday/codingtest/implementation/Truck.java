package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;

public class Truck {

    @Test
    void case1() {
        int w = 2;
        int l = 10;
        int[] trucks = {7, 4, 5, 6};
        int answer = 8;
        assertThat(solution(w, l, trucks)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int w = 100;
        int l = 100;
        int[] trucks = {10};
        int answer = 101;
        assertThat(solution(w, l, trucks)).isEqualTo(answer);
    }

    @Test
    void case3() {
        int w = 100;
        int l = 100;
        int[] trucks = {10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
        int answer = 110;
        assertThat(solution(w, l, trucks)).isEqualTo(answer);
    }


    private int solution(int w, int l, int[] trucks) {

       Queue<Integer> bridge = new ArrayDeque<>();
       for (int i = 0; i < w; i++) {
           bridge.offer(0);
       }

       int sumOfLoad = 0;
       int time = 0;
       int index = 0;

       while (index < trucks.length) {
           time += 1;

           sumOfLoad -= bridge.poll();

           if (sumOfLoad + trucks[index] <= l) {
               sumOfLoad += trucks[index];
               bridge.offer(trucks[index]);
               index += 1;
           } else {
               bridge.offer(0);
           }
       }

       return time + bridge.size();
    }


}
