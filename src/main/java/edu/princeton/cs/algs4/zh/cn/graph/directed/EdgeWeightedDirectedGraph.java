package edu.princeton.cs.algs4.zh.cn.graph.directed;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.zh.cn.graph.EdgeWeightedDigraph;

/**
 * 加权有向图的数据类型(加权有向图的数据结构 4.4.2)
 * {@link edu.princeton.cs.algs4.EdgeWeightedDigraph}
 *
 * @author FXYGR @date 2020-02-10
 */
public class EdgeWeightedDirectedGraph implements EdgeWeightedDigraph {

	private static final String NEWLINE = System.getProperty("line.separator");

	/**
	 * 顶点总数
	 */
	private int V;
	/**
	 * 边的总数
	 */
	private int E;
	/**
	 * 邻接表
	 */
	private Bag<WeightedDirectedEdge>[] adjs;

	/**
	 * 含有 V 个顶点的空有向图
	 *
	 * @param V
	 */
	public EdgeWeightedDirectedGraph(int V) {
		if (V < 0) {
			throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
		}
		this.V = V;
		this.E = 0;

		adjs = new Bag[V];
		for (int v = 0; v < V; ++v) {
			adjs[v] = new Bag<>();
		}
	}

	/**
	 * 从输入流中读取图的构造函数
	 *
	 * @param in
	 */
	public EdgeWeightedDirectedGraph(In in) {
	}

	/**
	 * 顶点总数
	 *
	 * @return
	 */
	@Override
	public int V() {
		return V;
	}

	/**
	 * 边的总数
	 *
	 * @return
	 */
	@Override
	public int E() {
		return E;
	}

	private void validateVertex(int v) {
		if (v < 0 || v >= V) {
			throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
		}
	}

	/**
	 * 将 e 添加到该有向图中
	 *
	 * @param e
	 */
	@Override
	public void addEdge(WeightedDirectedEdge e) {
		validateVertex(e.from());
		validateVertex(e.to());

		adjs[e.from()].add(e);
		++E;
	}

	/**
	 * 从 from 指出的边
	 *
	 * @param from
	 * @return
	 */
	@Override
	public Iterable<WeightedDirectedEdge> adj(int from) {
		validateVertex(from);
		return adjs[from];
	}

	/**
	 * 该有向图中的所有边
	 *
	 * @return
	 */
	@Override
	public Iterable<WeightedDirectedEdge> edges() {
		Bag<WeightedDirectedEdge> bags = new Bag<>();
		for (int v = 0; v < V; ++v) {
			for (WeightedDirectedEdge e : adjs[v]) {
				bags.add(e);
			}
		}
		return bags;
	}

	/**
	 * 对象的字符串表示
	 *
	 * @return
	 */
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(V + " " + E + NEWLINE);
		for (int v = 0; v < V; v++) {
			s.append(v + ": ");
			for (WeightedDirectedEdge e : adjs[v]) {
				s.append(e + "  ");
			}
			s.append(NEWLINE);
		}
		return s.toString();
	}

}
