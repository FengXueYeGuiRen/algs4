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

	private int V;
	private int E;
	private Bag<WeightedDirectedEdge>[] adjs;


	/**
	 * 含有 V 个顶点的空有向图
	 *
	 * @param V
	 */
	public EdgeWeightedDirectedGraph(int V) {
		this.V = V;
		this.E = 0;

		adjs = new Bag[V];
		for (int i = 0; i < V; ++i) {
			adjs[i] = new Bag<>();
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

	/**
	 * 将 e 添加到该有向图中
	 *
	 * @param e
	 */
	@Override
	public void addEdge(WeightedDirectedEdge e) {
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
		for (int i = 0; i < V; ++i) {
			for (WeightedDirectedEdge e : adjs[i]) {
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
		Iterable<WeightedDirectedEdge> iterable = edges();
		StringBuilder s = new StringBuilder();
		for (WeightedDirectedEdge e : iterable) {
			s.append(e.toString()).append(" ");
		}
		return s.toString();
	}

}
