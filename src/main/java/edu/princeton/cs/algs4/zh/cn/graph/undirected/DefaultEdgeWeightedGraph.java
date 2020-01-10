package edu.princeton.cs.algs4.zh.cn.graph.undirected;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.zh.cn.graph.Edge;
import edu.princeton.cs.algs4.zh.cn.graph.EdgeWeightedGraph;

import java.util.Collections;
import java.util.Iterator;

/**
 * 加权无向图的数据类型(加权无向图的数据类型 4.3.2)
 * {@link edu.princeton.cs.algs4.EdgeWeightedGraph}
 * {@link AdjacencyListsGraph}
 *
 * @author FXYGR @date 2020-01-10
 */
public class DefaultEdgeWeightedGraph extends EdgeWeightedGraph {

	/**
	 * 顶点总数
	 */
	private int V;
	/**
	 * 边的总数
	 */
	private int E;
	/**
	 * 临接表
	 */
	private Bag<Edge>[] adjs;

	/**
	 * 创建一幅含有 V 个顶点的空图
	 *
	 * @param V
	 */
	public DefaultEdgeWeightedGraph(int V) {
		this.V = V;
		adjs = new Bag[V];
		for (int v = 0; v < V; ++v) {
			adjs[v] = new Bag<>();
		}
	}

	/**
	 * 从输入流中读取图
	 *
	 * @param in
	 */
	public DefaultEdgeWeightedGraph(In in) {
	}

	/**
	 * 图的顶点数
	 *
	 * @return
	 */
	@Override
	public int V() {
		return V;
	}

	/**
	 * 图的边数
	 *
	 * @return
	 */
	@Override
	public int E() {
		return E;
	}

	/**
	 * 向图中添加一条边 e
	 *
	 * @param e
	 */
	@Override
	public void addEdge(Edge e) {
		if (e == null) {
			throw new NullPointerException();
		}
		int v = e.either();
		int w = e.other(v);
		adjs[v].add(e);
		adjs[w].add(e);
		++this.E;
	}

	/**
	 * 向图中添加一条边 v-w
	 *
	 * @param v
	 * @param w
	 * @param weight
	 */
	@Override
	public void addEdge(int v, int w, double weight) {
		Edge e = new WeightedEdge(v, w, weight);
		this.addEdge(e);
	}

	/**
	 * 和 v 相关联的所有顶点
	 *
	 * @param v
	 * @return
	 */
	@Override
	public Iterable<Edge> adj(int v) {
		if (v < 0 || v >= adjs.length) {
			return emptyIterable();
		}
		return adjs[v];
	}

	private static Iterable<Edge> emptyIterable() {
		return new Iterable<Edge>() {
			@Override
			public Iterator<Edge> iterator() {
				return Collections.emptyIterator();
			}
		};
	}

	/**
	 * 图的所有边
	 *
	 * @return
	 */
	@Override
	public Iterable<Edge> edges() {
		Bag<Edge> bag = new Bag<>();
		for (int v = 0; v < V; ++v) {
			for (Edge e : adjs[v]) {
				if (e.other(v) > v) {
					bag.add(e);
				}
			}
		}
		return bag;
	}

	/**
	 * 对象的字符串表示
	 *
	 * @return
	 */
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (Edge e : edges()) {
			int v = e.either(), w = e.other(v);
			s.append(v + "-" + w + " ");
		}
		return s.toString();
	}

}
