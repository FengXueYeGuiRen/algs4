package edu.princeton.cs.algs4.zh.cn.graph.directed;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.zh.cn.graph.DirectedGraph;

import java.util.Collections;
import java.util.Iterator;

/**
 * 有向图数据类型(4.2.2)
 * {@link edu.princeton.cs.algs4.Digraph}
 * {@link edu.princeton.cs.algs4.zh.cn.graph.undirected.AdjacencyListsGraph}
 *
 * @author FXYGR @date 2020-01-06
 */
public class DefaultDirectedGraph extends DirectedGraph {

	private final int V;
	private int E;
	private Bag<Integer>[] adjs;

	/**
	 * 创建一幅含有 v 个顶点但没有边的有向图
	 *
	 * @param V
	 */
	public DefaultDirectedGraph(int V) {
		if (V < 0) {
			throw new IllegalArgumentException(
					"Number of vertices in a DirectedGraph must be nonnegative");
		}
		this.V = V;
		this.E = 0;
		adjs = new Bag[V];
		for (int v = 0; v < V; ++v) {
			adjs[v] = new Bag();
		}
	}

	/**
	 * 从输入流 in 中读取一幅有向图
	 *
	 * @param in
	 */
	public DefaultDirectedGraph(In in) {
		this(in.readInt());
		int E = in.readInt();
		if (E < 0) {
			throw new IllegalArgumentException(
					"number of edges in a DirectedGraph must be nonnegative");
		}
		for (int edge = 0; edge < E; ++edge) {
			addEdge(in.readInt(), in.readInt());
		}
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
	 * 向有向图中添加一条边 v->w
	 *
	 * @param v
	 * @param w
	 */
	@Override
	public void addEdge(int v, int w) {
		if (v < 0 || v > V || w < 0 || w > V) {
			return;
		}
		adjs[v].add(w);
		++this.E;
	}

	/**
	 * 由 v 指出的边所连接的所有顶点
	 *
	 * @param v
	 * @return
	 */
	@Override
	public Iterable<Integer> adj(int v) {
		if (v < 0 || v > V) {
			return emptyIterable();
		}
		return adjs[v];
	}

	private static Iterable<Integer> emptyIterable() {
		return new Iterable<Integer>() {
			@Override
			public Iterator<Integer> iterator() {
				return Collections.emptyIterator();
			}
		};
	}

	/**
	 * 该图的反向图
	 *
	 * @return
	 */
	@Override
	public DirectedGraph reverse() {
		DirectedGraph reverseDirectedGraph =
				new DefaultDirectedGraph(V);
		for (int v = 0; v < V; ++v) {
			for (int w : adjs[v]) {
				reverseDirectedGraph.addEdge(w, v);
			}
		}
		return reverseDirectedGraph;
	}

	/**
	 * 对象的字符串表示
	 *
	 * @return
	 */
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(V + " vertices, " + E + " edges.\n");
		for (int v = 0; v < V; ++v) {
			s.append(String.format("%d: ", v));
			for (int w : adjs[v]) {
				s.append(String.format("%d ", w));
			}
			s.append("\n");
		}
		return s.toString();
	}

}
