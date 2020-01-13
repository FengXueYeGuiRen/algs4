package edu.princeton.cs.algs4.zh.cn.graph.undirected;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.zh.cn.graph.Edge;
import edu.princeton.cs.algs4.zh.cn.graph.EdgeWeightedGraph;
import edu.princeton.cs.algs4.zh.cn.graph.MinimumSpanningTree;
import edu.princeton.cs.algs4.zh.cn.sorting.pq.BinaryHeapMinPQ;

/**
 * 最小生成树的 Prim 算法的延时实现(4.3.4)
 * {@link edu.princeton.cs.algs4.LazyPrimMST}
 *
 * @author FXYGR @date 2020-01-13
 */
public class LazyPrimMinimumSpanningTree implements MinimumSpanningTree {

	/**
	 * 最小生成树的顶点
	 */
	private boolean[] markeds;
	/**
	 * 最小生成树的边
	 */
	private Queue<Edge> mstEdges;
	/**
	 * 横切边(包括失效的边)
	 */
	private BinaryHeapMinPQ<Edge> minPQ;

	/**
	 * 构造函数
	 *
	 * @param G
	 */
	public LazyPrimMinimumSpanningTree(EdgeWeightedGraph G) {
		markeds = new boolean[G.V()];
		mstEdges = new Queue<>();
		minPQ = new BinaryHeapMinPQ<>(G.V());
		//  假设 G 是连通的
		visit(G, 0);
		while (!minPQ.isEmpty()) {
			//  从 pq 中得到权重最小的边
			Edge e = minPQ.delMin();
			int v = e.either(), w = e.other(v);
			//  跳过失效的边
			if (markeds[v] && markeds[w]) {
				continue;
			}
			//  将边添加到树中
			mstEdges.enqueue(e);
			//  将顶点 v 添加到树种
			if (!markeds[v]) {
				visit(G, v);
			}
			//  将顶点 w 添加到树种
			if (!markeds[w]) {
				visit(G, w);
			}
		}
	}

	private void visit(EdgeWeightedGraph G, int v) {
		//  标记顶点 v 并将所有链接 v 和未被标记顶点的边加入 pq
		markeds[v] = true;
		for (Edge e : G.adj(v)) {
			if (!markeds[e.other(v)]) {
				minPQ.insert(e);
			}
		}
	}

	/**
	 * 最小生成树的所有边
	 *
	 * @return
	 */
	@Override
	public Iterable<Edge> edges() {
		return mstEdges;
	}

	/**
	 * 最小生成树的权重
	 *
	 * @return
	 */
	@Override
	public double weight() {
		double weights = 0;
		for (Edge e : mstEdges) {
			weights += e.weight();
		}
		return weights;
	}

}
