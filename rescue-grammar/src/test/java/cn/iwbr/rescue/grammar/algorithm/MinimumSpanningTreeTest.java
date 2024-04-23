package cn.iwbr.rescue.grammar.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinimumSpanningTreeTest {

    public static void main(String[] args) {
        int[][] graph = {
                {0, 1, 5},
                {0, 2, 7},
                {0, 6, 2},
                {1, 6, 3},
                {1, 3, 9},
                {2, 4, 8},
                {3, 5, 4},
                {4, 5, 5},
                {4, 6, 4},
                {5, 6, 6},
        };
        kruskalAlgorithm(graph);
    }

    /**
     * kruskal算法
     * 思路：
     * 1. 将边放到一个数组或者集合里面，然后按照边的权重排序
     * 2. 然后遍历边数组，将边一个个回填到图里面
     * 3. 判断回填后是够形成了环，如果形成了环则将该边遗弃，继续遍历剩下的边，如果没有则将该边加入到最小边数组中
     * 4. 以此类推遍历处理其他的边，直到最小边的个数达到了n-1时结束
     * 核心问题：一个新边加入后如何判断是否构成了环？
     */
    public static void kruskalAlgorithm(int [][] graph){
        List<Edge> edges = new ArrayList<>();
        for (int[] edge : graph) {
            edges.add(new Edge(edge[0], edge[1], edge[2]));
        }
        Collections.sort(edges);
        UnionFind uf = new UnionFind(graph.length);
        List<Edge> result = new ArrayList<>();
        for (Edge edge : edges) {
            if (uf.union(edge.from, edge.to)) {
                result.add(edge);
            }
        }
        System.out.println("最小生成树的边为：");
        for (Edge edge : result) {
            System.out.println(edge.from + " - " + edge.to + " : " + edge.weight);
        }
    }

    /**
     * prim算法
     * 思路：
     * 1. 从图中任一点开始，找到这个点的连接的所有边中，权重最小的边
     * 2. 然后将这个边连接的那个顶点标记为已访问，将该边加入到最小边集合
     * 3. 然后将
     */
    public static void primAlgorithm(){

    }


    static class UnionFind{
        int[] parent;//存储每个结点的前驱结点
        int[] rank;//树的高度
        public UnionFind(int n) {

            parent = new int[n];

            rank = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;//每个结点的上级都是自己
                rank[i] = 1;//每个结点构成的树的高度为1
            }
        }
        public int find(int x) {
            if (parent[x] == x) {//递归出口：x的上级为 x本身，即 x为根结点
                return x;
            }
            return parent[x] = find(parent[x]);//此代码相当于先找到根结点 rootx，然后 parent[x]=rootx
        }

        public boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return false;
            }
            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
            return true;
        }
    }

    static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int weight;
        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        @Override
        public int compareTo(Edge other) {
            return this.weight - other.weight;
        }
    }
}
