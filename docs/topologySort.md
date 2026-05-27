# [◀ 목록](../README.md)
# 위상 정렬 알고리즘: 선후 관계를 지키는 처리 순서를 만든다

```
topologySort(graph, numVertices):
    // 1. 모든 노드의 진입 차수(in-degree)를 0으로 초기화
    inDegrees = new int[graph.length + 1]
    
    // 2. 그래프를 순회하며 각 노드의 진입 차수 계산
    for (int[] node : graph) {
        int from = node[0];
        int to = node[1];
        inDegrees[to] += 1;
    }
    
    // 3. 진입 차수가 0인 노드를 찾아 큐에 삽입
    Queue<Integer> queue = new ArrayDeque<>();
    for (int i = 1; i < inDegrees.length; i++) {
        if (inDegrees[i] == 0) {
            queue.enqueue(i);
        }
    }
        
    result = empty list (정렬된 결과를 담을 배열)

    // 4. 큐가 빌 때까지 반복 처리
    while (!queue.isEmpty()) {
        int current = queue.poll();
        result.add(current); // 큐에서 꺼낸 순서가 위상 정렬 순서가 됨
                
        // 현재 노드와 연결된 인접 노드들의 진입 차수를 1씩 감소
        List<Integer> nexts = graph.get(current);
        for (int next : nexts) {
            inDegrees[next] -= 1;
            
            // 새롭게 진입 차수가 0이 된 노드가 있다면 큐에 삽입
            if (inDegrees[next] == 0) {
                queue.enqueue(next);
            }
        }
    }

    // 5. 사이클 발생 여부 체크
    // 정렬된 결과의 개수가 전체 노드 수와 다르다면 그래프에 사이클이 존재하는 것임
    if result.length != numVertices:
        return "Graph contains a cycle (위상 정렬 불가능)"
    else:
        return result
```

```java
public class TopologySort {

    @Test
    void case1() {
        int n = 7;
        int[][] datas = {
                {1, 2},
                {1, 5},
                {2, 3},
                {2, 6},
                {3, 4},
                {4, 7},
                {5, 6},
                {6, 4}
        };
        int[] answer = {1, 2, 5, 3, 6, 4, 7};
        assertThat(solution(n, datas)).isEqualTo(answer);
    }

    private int[] solution(int n, int[][] datas) {
        int[] indegrees = new int[n + 1];

        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 1; i < n + 1; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int[] data : datas) {
            int from = data[0];
            int to = data[1];
            graph.get(from).add(to);
            indegrees[to] += 1;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i < n + 1; i++) {
            if (indegrees[i] == 0) {
                queue.offer(i);
            }
        }

        int[] result = new int[n];
        int resultIndex = 0;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            result[resultIndex++] = current;

            List<Integer> nexts = graph.get(current);
            for (int next : nexts) {
                indegrees[next] -= 1;
                if (indegrees[next] == 0) {
                    queue.offer(next);
                }
            }
        }
        
        return result;
    }
}
```