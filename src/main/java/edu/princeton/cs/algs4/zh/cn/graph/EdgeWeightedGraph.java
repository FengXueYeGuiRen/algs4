package edu.princeton.cs.algs4.zh.cn.graph;

/**
 * 加权边 API(4.3.2)
 * {@link edu.princeton.cs.algs4.EdgeWeightedGraph}
 *
 * @author FXYGR @date 2020-01-10
 */
public abstract class EdgeWeightedGraph extends Graph {

	/**
	 * 顶点数
	 *
	 * @return
	 */
	@Override
	public abstract int V();

	/**
	 * 边数
	 *
	 * @return
	 */
	@Override
	public abstract int E();

	/**
	 * 向图中添加一条边 v-w
	 *
	 * @param v
	 * @param w
	 */
	@Override
	public abstract void addEdge(int v, int w);

	/**
	 * 和 v 相邻的所有顶点
	 *
	 * @param v
	 * @return
	 */
	@Override
	public abstract Iterable<Integer> adj(int v);

}
