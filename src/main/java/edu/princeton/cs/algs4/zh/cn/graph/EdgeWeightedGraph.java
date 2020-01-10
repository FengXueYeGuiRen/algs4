package edu.princeton.cs.algs4.zh.cn.graph;

import edu.princeton.cs.algs4.In;

/**
 * 加权无向图 API(加权无向图的数据类型 4.3.2)
 * {@link edu.princeton.cs.algs4.EdgeWeightedGraph}
 * {@link Graph}
 *
 * @author FXYGR @date 2020-01-10
 */
public abstract class EdgeWeightedGraph {

	public EdgeWeightedGraph() {
	}

	/**
	 * 创建一幅含有 V 个顶点的空图
	 *
	 * @param V
	 */
	public EdgeWeightedGraph(int V) {
	}

	/**
	 * 从输入流中读取图
	 *
	 * @param in
	 */
	public EdgeWeightedGraph(In in) {
	}

	/**
	 * 图的顶点数
	 *
	 * @return
	 */
	public abstract int V();

	/**
	 * 图的边数
	 *
	 * @return
	 */
	public abstract int E();

	/**
	 * 向图中添加一条边 e
	 *
	 * @param e
	 */
	public abstract void addEdge(Edge e);

	/**
	 * 向图中添加一条边 v-w
	 *
	 * @param v
	 * @param w
	 * @param weight
	 */
	public abstract void addEdge(int v, int w, double weight);

	/**
	 * 和 v 相关联的所有顶点
	 *
	 * @param v
	 * @return
	 */
	public abstract Iterable<Edge> adj(int v);

	/**
	 * 图的所有边
	 *
	 * @return
	 */
	public abstract Iterable<Edge> edges();

	/**
	 * 对象的字符串表示
	 * @return
	 */
	@Override
	public abstract String toString();

}
