package com.errday.codingtest.bfs;

import org.junit.jupiter.api.Test;

import java.util.*;
import static org.assertj.core.api.Assertions.assertThat;

public class BlockMove {

    private final int EMPTY = 0;
    private final int WALL = 1;

    @Test
    void case1() {
        int[][] board = {
                {0, 0, 0, 1, 1},
                {0, 0, 0, 1, 0},
                {0, 1, 0, 1, 1},
                {1, 1, 0, 0, 1},
                {0, 0, 0, 0, 0}
        };

        int answer = 7;

        assertThat(solution(board)).isEqualTo(answer);
    }

    private int solution(int[][] board) {
        int GOAL = board.length;
        int[][] wrappedBoard = wrappingBoard(board);

        Node start = new Node(1, 1, 1, 2, 0);

        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(start);

        boolean[][][][] visited = new boolean[wrappedBoard.length][wrappedBoard.length][wrappedBoard.length][wrappedBoard.length];
        visited[start.row1][start.col1][start.row2][start.col2] = true;

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if ((current.row1 == GOAL && current.col1 == GOAL) || (current.row2 == GOAL && current.col2 == GOAL)) {
                return current.distance();
            }


            List<Node> nextPosition = getNextPosition(current, wrappedBoard);
            for (Node next : nextPosition) {

                if (!visited[next.row1][next.col1][next.row2][next.col2]) {
                    visited[next.row1][next.col1][next.row2][next.col2] = true;
                    queue.offer(next);
                }

            }

        }

        return 0;
    }


    private int[][] wrappingBoard(int[][] board) {
        int n = board.length;
        int[][] wrappedBoard = new int[n + 2][n + 2];
        for (int i = 0; i < wrappedBoard.length; i++) {
            Arrays.fill(wrappedBoard[i], WALL);
        }

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                wrappedBoard[row + 1][col + 1] = board[row][col];
            }
        }

        return wrappedBoard;
    }

    private List<Node> getNextPosition(Node pos, int[][] board) {
        List<Node> result = new ArrayList<>();

        int[] moveX = {0, 1, 0, -1};
        int[] moveY = {-1, 0, 1, 0};

        for (int i = 0; i < 4; i++) {
            int position1NextX = pos.row1() + moveX[i];
            int position1NextY = pos.col1() + moveY[i];
            int position2NextX = pos.row2() + moveX[i];
            int position2NextY = pos.col2() + moveY[i];
            int distance = pos.distance() + 1;

            if (board[position1NextX][position1NextY] == EMPTY && board[position2NextX][position2NextY] == EMPTY) {
                result.add(new Node(position1NextX, position1NextY, position2NextX, position2NextY, distance));
            }
        }

        int[] horizontalMove = {-1, 1};
        if (pos.row1 == pos.row2) {
            for (int move : horizontalMove) {
                if (board[pos.row1 + move][pos.col1] == EMPTY
                        && board[pos.row2 + move][pos.col2] == EMPTY) {
                    result.add(new Node(pos.row1, pos.col1, pos.row1 + move, pos.col2, pos.distance + 1));
                    result.add(new Node(pos.row2, pos.col2, pos.row2 + move, pos.col2, pos.distance + 1));
                }
            }
        }

        int[] verticalMove = {-1, 1};
        if (pos.col1 == pos.col2) {
            for (int move : verticalMove) {
                if (board[pos.row1][pos.col1 + move] == EMPTY && board[pos.row2][pos.col2 + move] == EMPTY) {
                    result.add(new Node(pos.row1, pos.col1, pos.row1, pos.col2 + move, pos.distance + 1));
                    result.add(new Node(pos.row2, pos.col2, pos.row2, pos.col2 + move, pos.distance + 1));
                }
            }
        }

        return result;
    }

    private record Node(
            int row1,
            int col1,
            int row2,
            int col2,
            int distance) {
    }

}
