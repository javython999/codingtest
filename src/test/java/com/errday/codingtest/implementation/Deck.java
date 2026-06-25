package com.errday.codingtest.implementation;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Deck {

    @Test
    void case1() {
        String[][] input = {
                {"push_back", "1"},
                {"push_front", "2"},
                {"front"},
                {"back"},
                {"size"},
                {"empty"},
                {"pop_front"},
                {"pop_back"},
                {"pop_front"},
                {"size"},
                {"empty"},
                {"pop_back"},
                {"push_front", "3"},
                {"empty"},
                {"front"}
        };
        int[] answer = {2, 1, 2, 0, 2, 1, -1, 0, 1, -1, 0, 3};
        assertThat(solution(input)).containsExactly(answer);
    }

    private int[] solution(String[][] input) {
        List<Integer> deck = new ArrayList<>();

        ArrayDeque<Integer> deque = new ArrayDeque<>();

        for (String[] order : input) {
            String command = order[0];

            switch (command) {
                case "push_front":
                    deque.addFirst(Integer.parseInt(order[1]));
                    break;
                case "push_back":
                    deque.addLast(Integer.parseInt(order[1]));
                    break;
                case "pop_front":
                    if (deque.isEmpty()) {
                        deck.add(-1);
                    } else {
                        deck.add(deque.pollFirst());
                    }
                    break;
                case "pop_back":
                    if (deque.isEmpty()) {
                        deck.add(-1);
                    } else {
                        deck.add(deque.pollLast());
                    }
                    break;
                case "size":
                    deck.add(deque.size());
                    break;
                case "empty":
                    if (deque.isEmpty()) {
                        deck.add(1);
                    } else {
                        deck.add(0);
                    }
                    break;
                case "front":
                    if (deque.isEmpty()) {
                        deck.add(-1);
                    } else {
                        deck.add(deque.peekFirst());
                    }
                    break;
                case "back":
                    if (deque.isEmpty()) {
                        deck.add(-1);
                    } else {
                        deck.add(deque.peekLast());
                    }
                    break;
            }
        }

        return deck.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
}
