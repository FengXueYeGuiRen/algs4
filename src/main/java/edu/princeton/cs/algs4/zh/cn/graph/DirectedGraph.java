package edu.princeton.cs.algs4.zh.cn.graph;

import edu.princeton.cs.algs4.In;

/**
 * 有向图 API(4.2.2)
 *
 * @author FXYGR @date 2020-01-05
 */
public abstract class DirectedGraph extends Graph {

	public DirectedGraph() {
	}

	/**
	 * 创建一幅含有 v 个顶点但没有边的有向图
	 *
	 * @param v
	 */
	public DirectedGraph(int v) {
	}

	/**
	 * 从输入流 in 中读取一幅有向图
	 *
	 * @param in
	 */
	public DirectedGraph(In in) {
	}

	/**
	 * 顶点总数
	 *
	 * @return
	 */
	@Override
	public abstract int V();

	/**
	 * 边的总数
	 *
	 * @return
	 */
	@Override
	public abstract int E();

	/**
	 * 向有向图中添加一条边 v->w
	 *
	 * @param v
	 * @param w
	 */
	@Override
	public abstract void addEdge(int v, int w);

	/**
	 * 由 v 指出的边所连接的所有顶点
	 *
	 * @param v
	 * @return
	 */
	@Override
	public abstract Iterable<Integer> adj(int v);

	/**
	 * 该图的反向图
	 *
	 * @return
	 */
	public abstract DirectedGraph reverse();

	/**
	 * 对象的字符串表示
	 *
	 * @return
	 */
	@Override
	public abstract String toString();

}
