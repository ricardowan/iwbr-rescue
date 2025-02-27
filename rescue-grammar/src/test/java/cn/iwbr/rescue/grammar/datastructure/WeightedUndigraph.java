package cn.iwbr.rescue.grammar.datastructure;

import java.util.List;

/**
 * @description: 无向加权图
 * @author: <a href="mailto:wangbaorui@supermap.com">wangbaorui</a>
 * @date: 2025/02/27
 */
public class WeightedUndigraph {

    private WeightedDigraph graph;

    public WeightedUndigraph(WeightedDigraph graph){
        this.graph = graph;
    }

    /**
     * 添加边
     * 无向就是两条边
     *
     * @param from   从
     * @param to     至
     * @param weight 重量
     */
    public void addEdge(int from, int to, int weight){
        graph.addEdge(from, to, weight);
        graph.addEdge(to, from, weight);
    }

    // 删除一条边
    public void removeEdge(int from, int to){
        graph.removeEdge(from, to);
        graph.removeEdge(to, from);
    }

    // 判断两个节点是否相邻
    public boolean hasEdge(int from, int to){
        return graph.hasEdge(from, to);
    }

    // 返回一条边的权重
    public int weight(int from, int to){
        return graph.weight(from, to);
    }

    // 返回某个节点的所有邻居节点和对应权重
    public List<WeightedDigraph.Edge> neighbors(int v){
        return graph.neighbors(v);
    }

    // 返回节点总数
    public int size(){
        return graph.size();
    }

    public static void main(String[] args) {
        WeightedUndigraph graph = new WeightedUndigraph(new WeightedDigraph());
        graph.addEdge(0, 1, 1);
        graph.addEdge(1, 2, 2);
        graph.addEdge(2, 0, 3);
        graph.addEdge(2, 1, 4);

        System.out.println(graph.hasEdge(0, 1)); // true
        System.out.println(graph.hasEdge(1, 0)); // true

        graph.neighbors(2).forEach(edge -> {
            System.out.println(2 + " <-> " + edge.to + ", wight: " + edge.weight);
        });
        // 2 <-> 0, wight: 3
        // 2 <-> 1, wight: 4

        graph.removeEdge(0, 1);
        System.out.println(graph.hasEdge(0, 1)); // false
        System.out.println(graph.hasEdge(1, 0)); // false
    }
}
