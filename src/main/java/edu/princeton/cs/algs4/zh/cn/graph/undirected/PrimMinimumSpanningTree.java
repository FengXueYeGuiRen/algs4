package edu.princeton.cs.algs4.zh.cn.graph.undirected;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.zh.cn.graph.Edge;
import edu.princeton.cs.algs4.zh.cn.graph.EdgeWeightedGraph;
import edu.princeton.cs.algs4.zh.cn.graph.MinimumSpanningTree;
import edu.princeton.cs.algs4.zh.cn.sorting.pq.IndexMinPriorityQueue;

/**
 * 最小生成树的 Prim 算法(即时版本)(4.3.5 算法4.7)
 * {@link edu.princeton.cs.algs4.PrimMST}
 * {@link LazyPrimMinimumSpanningTree}
 *
 * @author FXYGR @date 2020-01-14
 */
public class PrimMinimumSpanningTree implements MinimumSpanningTree {

	/**
	 * 距离树最近的边
	 */
	private Edge[] edgeTos;
	/**
	 * distTo[w] = edgeTos[w].weight()
	 */
	private double[] distTos;
	/**
	 * 如果 v 在树中则为 true
	 */
	private boolean[] markeds;
	/**
	 * 有效的横切边
	 */
	private IndexMinPriorityQueue<Double> minPQ;

	/**
	 * 构造函数
	 *
	 * @param G
	 */
	public PrimMinimumSpanningTree(EdgeWeightedGraph G) {
		edgeTos = new WeightedEdge[G.V()];
		markeds = new boolean[G.V()];
		minPQ = new IndexMinPriorityQueue<>(G.V());
		distTos = new double[G.V()];
		for (int i = 0; i < distTos.length; ++i) {
			distTos[i] = Double.POSITIVE_INFINITY;
		}
		distTos[0] = 0.0;
		//  用顶点 0 和权重 0 初始化 minPQ
		minPQ.insert(0, distTos[0]);
		while (!minPQ.isEmpty()) {
			//  将最近的顶点添加到树中
			visit(G, minPQ.delMin());
		}
	}

	private void visit(EdgeWeightedGraph G, int v) {
		//  将顶点 v 添加到树中，更新数据
		markeds[v] = true;
		for (Edge e : G.adj(v)) {
			int w = e.other(v);
			//  v-w 失效
			if (markeds[w]
					//  e 非连接 w 和树的最佳边
					|| e.weight() >= distTos[w]) {
				continue;
			}
			if (minPQ.contains(w)) {
				minPQ.change(w, e.weight());
			} else {
				minPQ.insert(w, e.weight());
			}
			//  连接 w 和树的最佳边 Edge 变为 e
			edgeTos[w] = e;
			distTos[w] = e.weight();
		}
	}

	/**
	 * 最小生成树的所有边
	 *
	 * @return
	 */
	@Override
	public Iterable<Edge> edges() {
		Queue<Edge> queue = new Queue<>();
		for (Edge e : edgeTos) {
			if (e != null) {
				queue.enqueue(e);
			}
		}
		return queue;
	}

	/**
	 * 最小生成树的权重
	 *
	 * @return
	 */
	@Override
	public double weight() {
		double weights = 0;
		for (double weight : distTos) {
			if (weight != Double.POSITIVE_INFINITY) {
				weights += weight;
			}
		}
		return weights;
	}

}
