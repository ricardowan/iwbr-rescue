package cn.iwbr.rescue.grammar.datastructure;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @description: 有向加权图（邻接表）
 * @author: <a href="mailto:wangbaorui@supermap.com">wangbaorui</a>
 * @date: 2025/02/26
 */
public class WeightedDigraph {

    // 存储相邻节点及边的权重
    public static class Edge {
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    // 图结构的 BFS 遍历，从节点 s 开始进行 BFS，且记录路径的权重和
    // 每个节点自行维护 State 类，记录从 s 走来的权重和
    class State {
        // 当前节点 ID
        int node;
        // 从起点 s 到当前节点的权重和
        int weight;

        public State(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    // 邻接表，graph[v] 存储节点 v 的所有邻居节点及对应权重
    private Map<Integer,List<Edge>> graph = new HashMap<>();

    // 增，添加一条带权重的有向边，复杂度 O(1)
    public void addEdge(int from, int to, int weight) {
        Edge edge = new Edge(to, weight);
        if (graph.containsKey(from)) {
            List<Edge> edgeList = graph.get(from);
            edgeList.add(edge);
        } else {
            graph.put(from, new ArrayList<>(Arrays.asList(edge)));
        }
    }

    // 删，删除一条有向边，复杂度 O(V)
    public void removeEdge(int from, int to) {
        for (int i = 0; i < graph.get(from).size(); i++) {
            if (graph.get(from).get(i).to == to) {
                graph.get(from).remove(i);
                break;
            }
        }
    }

    // 查，判断两个节点是否相邻，复杂度 O(V)
    public boolean hasEdge(int from, int to) {
        List<Edge> edgeList = graph.get(from);
        List<Edge> filterList = edgeList.stream().filter(e -> e.to == to).collect(Collectors.toList());
        return !filterList.isEmpty();
    }

    // 查，返回一条边的权重，复杂度 O(V)
    public int weight(int from, int to) {
        List<Edge> edgeList = graph.get(from);
        List<Edge> filterList = edgeList.stream().filter(e -> e.to == to).collect(Collectors.toList());
        if (filterList.isEmpty()) {
            throw new IllegalArgumentException("No such edge");
        }
        return filterList.get(0).weight;
    }

    public int size(){
        return graph.size();
    }

    // 上面的 hasEdge、removeEdge、weight 方法遍历 List 的行为是可以优化的
    // 比如用 Map<Integer, Map<Integer, Integer>> 存储邻接表
    // 这样就可以避免遍历 List，复杂度就能降到 O(1)

    // 查，返回某个节点的所有邻居节点，复杂度 O(1)
    public List<Edge> neighbors(int v) {
        return graph.get(v);
    }

    /**
     * 遍历节点(DFS)
     *
     * @param graph 图
     */
    public void traverse(WeightedDigraph graph, int s, boolean[] visited){
        if (s < 0 || s >= graph.size()) {
            return;
        }

        // 前序
        visited[s] = true;
        System.out.println("visit " + s);
        for (Edge e : graph.neighbors(s)) {
            traverse(graph, e.to, visited);
        }
        // 后序
    }

    /**
     * 遍历边（DFS）
     * 而对于图结构来说，由起点 src 到目标节点 dest 的路径可能不止一条。
     * 我们需要一个 onPath 数组，在进入节点时（前序位置）标记为正在访问，退出节点时（后序位置）撤销标记
     * 这样才能遍历图中的所有路径，从而找到 src 到 dest 的所有路径：
     * @param graph 图
     * @param src   src
     * @param dest  目
     */
    public void findPath(WeightedDigraph graph, int src, int dest, boolean[] onPath, List<Integer> path){
        if (src < 0 || src >= graph.size()) {
            return;
        }
        if (onPath[src]) {
            // 防止死循环
            return;
        }
        // 前序位置
        onPath[src] = true;
        path.add(src);
        if(src == dest){
            System.out.println("find path: " + path);
        }
        for (Edge edge : graph.neighbors(src)) {
            findPath(graph, edge.to, dest, onPath, path);
        }

        // 后序位置
        path.remove(path.size()-1);
        onPath[src] = false;
    }

    /**
     * 遍历图节点（BFS）
     * 从节点 s 开始进行 BFS
     * @param digraph 有向图
     * @param s       s
     */
    public static void traverseBfs(WeightedDigraph digraph, int s){
        boolean[] visited = new boolean[digraph.size()];
        ArrayDeque<Integer> deque = new ArrayDeque();

        deque.offer(s);
        visited[s] = true;

        int step = 0;
        while(!deque.isEmpty()){
            // 当前访问的记录
            int cru = deque.poll();
            System.out.println("visit " + cru + " at step " + step);
            // 当前访问记录的叶子节点
            for (Edge edge : digraph.neighbors(cru)) {
                if(!visited[edge.to]){
                    deque.offer(edge.to);
                    visited[edge.to] = true;
                }
            }
            step++;
        }
        System.out.println("step: " + step);
    }

    /**
     * 遍历图节点（BFS）
     * 从节点 s 开始进行 BFS
     * @param digraph 有向图
     * @param s       s
     */
    public static void traverseBfs1(WeightedDigraph digraph, int s){
        boolean[] visited = new boolean[digraph.size()];
        Queue<Integer> q = new LinkedList<>();
        q.offer(s);
        visited[s] = true;
        // 记录从 s 开始走到当前节点的步数
        int step = 0;
        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                int cur = q.poll();
                System.out.println("visit1 " + cur + " at step " + step);
                for (Edge e : digraph.neighbors(cur)) {
                    if (!visited[e.to]) {
                        q.offer(e.to);
                        visited[e.to] = true;
                    }
                }
            }
            step++;
        }
        System.out.println("step: " + step);
    }

    /**
     * 遍历图节点（BFS）
     * 从节点 s 开始进行 BFS
     * @param graph 图
     * @param s     s
     */
    public void traverseBfs2(WeightedDigraph graph, int s) {
        boolean[] visited = new boolean[graph.size()];
        Queue<State> q = new LinkedList<>();

        q.offer(new State(s, 0));
        visited[s] = true;

        while (!q.isEmpty()) {
            State state = q.poll();
            int cur = state.node;
            int weight = state.weight;
            System.out.println("visit " + cur + " with path weight " + weight);
            for (Edge e : graph.neighbors(cur)) {
                if (!visited[e.to]) {
                    q.offer(new State(e.to, weight + e.weight));
                    visited[e.to] = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        WeightedDigraph graph = new WeightedDigraph();
        graph.addEdge(0, 1, 1);
        graph.addEdge(1, 2, 2);
        graph.addEdge(2, 0, 3);
        graph.addEdge(2, 1, 4);
        graph.addEdge(3, 0, 3);
        graph.addEdge(3, 2, 4);
        graph.addEdge(4, 1, 3);
        graph.addEdge(0, 4, 4);

        traverseBfs(graph, 0);
        traverseBfs1(graph, 0);

        //System.out.println(graph.hasEdge(0, 1)); // true
        //System.out.println(graph.hasEdge(1, 0)); // false

//        graph.neighbors(2).forEach(edge -> {
//            System.out.println(2 + " -> " + edge.to + ", wight: " + edge.weight);
//        });
        // 2 -> 0, wight: 3
        // 2 -> 1, wight: 4

//        graph.removeEdge(0, 1);
//        System.out.println(graph.hasEdge(0, 1)); // false
    }
}
