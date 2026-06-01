package com.errday.codingtest.tree;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IndexTree {

    @Test
    void case1() {
        int[] input = {5, 8, 4, 3, 7, 2, 1, 6};
        int startIndex = 1;
        int endIndex = 5;
        int answer = 24;

        assertThat(solution(input, startIndex, endIndex)).isEqualTo(answer);
    }

    @Test
    void case2() {
        int[] input = {5, 8, 4, 3, 7, 2, 1, 6};
        int startIndex = 0;
        int endIndex = 3;
        int answer = 20;

        assertThat(solution(input, startIndex, endIndex)).isEqualTo(answer);
    }

    private int solution(int[] input, int startIndex, int endIndex) {

        int length = input.length;

        // 트리 길이 구하기
        int k = 0;
        while (Math.pow(2, k) < length) {
            k += 1;
        }

        // 트리 초기화
        int offset = (int) Math.pow(2, k);
        int[] tree = new int[(int) (offset * 2)];
        System.arraycopy(input, 0, tree, offset, length);

        // 구간합 계산
        for (int i = tree.length - 1; i > 1; i--) {
            int nextIndex = i / 2;
            tree[nextIndex] += tree[i];
        }

        // 질의 값 구하기
        // 1. 트리 인덱스로 변경
        int adjustStartIndex = startIndex + offset;
        int adjustEndIndex = endIndex + offset;

        int sum = 0;
        while (adjustStartIndex <= adjustEndIndex) {

            // 시작 인덱스가 우측 자식 노드
            if (adjustStartIndex % 2 == 1) {
                sum += tree[adjustStartIndex];
                adjustStartIndex += 1;
            }

            // 끝 인덱스가 좌측 자식 노드인 경우
            if (adjustEndIndex % 2 == 0) {
                sum += tree[adjustEndIndex];
                adjustEndIndex -= 1;
            }

            adjustStartIndex /= 2;
            adjustEndIndex /= 2;
        }

        return sum;
    }
}
