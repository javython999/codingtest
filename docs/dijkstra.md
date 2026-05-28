# [◀ 목록](../README.md)
# 다익스타라 알고리즘: 단일 출발점에서 가장 짧은 거리부터 확정해 나간다

```
dijkstra(Graph, source):
    // 1. 초기화
    PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(v -> v[1]));
    
    for each vertex v in Graph.Vertices:
        distance[v] ← infinity                  // 출발지점으로부터의 거리를 무한대로 초기화
        previous[v] ← undefined                 // 최단 경로 추적을 위한 이전 정점 초기화
        
    distance[source] ← 0                        // 출발지점의 거리는 0으로 설정
    Q.insert(source, 0)                         // 우선순위 큐에 (정점, 거리) 삽입

    // 2. 메인 루프
    while queue is not empty:
        current ← queue.extract_min()               // 거리가 가장 짧은 정점을 꺼냄
        
        // 현재 꺼낸 정점의 거리가 이미 알고 있는 거리보다 크면 건너넙니다 (최적화)
        if distance[current] < current.distance:
            continue
            
        // 3. 인접 정점 탐색 및 거리 갱신 (Relaxation)
        for each neighbor v of current:
            newDistance ← distance[current] + weight(current, v)
            
            // 더 짧은 경로를 발견한 경우
            if newDistance < distance[v]:
                distance[v] ← newDistance
                queue.insert(v, alt)                // 갱신된 거리를 우선순위 큐에 삽입

    return distance[]
```
```java
public int[] djikstra(int[][] graph, int start) {
    int length = graph.length;

    int[] cost = new int[length];
    Arrays.fill(cost, INF);
    cost[start] = 0;
    
    PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(data -> data[1]));
    queue.offer(new int[] {start, 0});

    while (!queue.isEmpty()) {
        int[] currentNodeInfo = queue.poll();
        int currentNodeNumber = currentNodeInfo[0];
        int currentNodeCost = currentNodeInfo[1];
        
        if (cost[currentNodeNumber] < currentNodeCost) {
            continue;
        }
        
        for (int next = 1; next < length; next++) {

            int newCost = currentNodeCost + graph[currentNodeNumber][next];

            if (cost[next] > newCost) {
                cost[next] = newCost;
                queue.offer(new int[] {next, newCost});
            }
        }
    }

    return cost;
}
```