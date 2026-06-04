# [◀ 목록](../README.md)
# LCA 알고리즘: 가장 가까운 공통 조상 찾기

## 기본 형태 O(n)
```
LCA(n, a, b, treeInfo) {
    // 트리 초기화
    int[] tree = new int[n + 1];
    for (int[] edge : input) {
        int parent = edge[0];
        int child = edge[1];
        tree[child] = parent;
    }
    
    // 레벨 초기화
    int[] levels = new int[n + 1];
    
    // BFS
    Queue<int[]> queue = new ArrayDeque<>();
    for (int i = 1; i < n + 1; i++) {
        if (tree[i] == 0) {
            queue.offer(new int[] {i, 1});
            levels[i] = 1;
        }
    }
    
    // 레벨 기록
    while (!queue.isEmpty()) {
        int[] current = queue.poll();
        int edge = current[0];
        int level = current[1];

        for (int next = 1; next < n + 1; next++) {
            if (next == edge) {
                continue;
            }

            if (tree[next] == edge) {
                queue.offer(new int[] {next, level + 1});
                levels[next] = level + 1;
            }
        }
    }
    
    // a, b 같은 레벨로 맞추기
    while (levels[a] > levels[b]) {
        a = tree[a];
    }

    while (levels[a] < levels[b]) {
        b = levels[b];
    }
    
    // 같은 부모가 나올 때까지 올라 가기
    while (a != b) {
        a = tree[a];
        b = tree[b];
    }
    
    return a;
}
```
## 고속 탐색 형태 O(NlogN)
```java
fastLca(int n, int[][] input, int a, int b) {
    // 1. log 상한선 구하기 (2^log > n인 최소 log 크기)
    int log = 0;
    while ((1 << log) <= n) {
        log++;
    }

    // 2. 그래프 생성
    int[] childCount = new int[n + 1];
    boolean[] hasParent = new boolean[n + 1];
    for (int[] edge : input) {
        childCount[edge[0]]++;
        hasParent[edge[1]] = true;
    }

    int[][] tree = new int[n + 1][];
    for (int i = 0; i <= n; i++) {
        tree[i] = new int[childCount[i]];
    }

    int[] idx = new int[n + 1];
    for (int[] edge : input) {
        int parent = edge[0];
        int child = edge[1];
        tree[parent][idx[parent]++] = child;
    }

    int[] levels = new int[n + 1];
    int[][] parents = new int[n + 1][log];

    // 3. 진입 차수(Parent 개수)가 0인 노드들을 찾아 루트로 지정하고 BFS 시작
    int[] bfsQueue = new int[n + 1];
    int head = 0, tail = 0;
    for (int i = 1; i <= n; i++) {
        if (!hasParent[i]) {
            bfsQueue[tail++] = i;
            levels[i] = 1;
        }
    }

    // 4. BFS 돌면서 levels 배열과 2^0(바로 위 부모) 채우기
    while (head < tail) {
        int current = bfsQueue[head++];

        for (int next : tree[current]) {
            levels[next] = levels[current] + 1;
            parents[next][0] = current;
            bfsQueue[tail++] = next;
        }
    }

    // 5. DP를 이용해 희소 배열(Sparse Table) 완성
    for (int k = 1; k < log; k++) {
        for (int i = 1; i < n + 1; i++) {
            if (parents[i][k - 1] != 0) {
                parents[i][k] = parents[parents[i][k - 1]][k - 1];
            }
        }
    }

    // LCA
    // 항상 b가 더 깊은 노드가 되도록 세팅
    if (levels[a] > levels[b]) {
        int temp = a;
        a = b;
        b = temp;
    }

    for (int k = log - 1; k >= 0; k--) {
        if (levels[b] - levels[a] >= (1 << k)) {
            b = parents[b][k];
        }
    }

    if (a == b) {
        return a;
    }

    for (int k = log - 1; k >= 0; k--) {
        if (parents[a][k] != parents[b][k]) {
            a = parents[a][k];
            b = parents[b][k];
        }
    }

    return parents[a][0];
}
```