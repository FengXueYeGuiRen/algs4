package edu.princeton.cs.algs4.zh.cn.graph;

/**
 * 图路径 API
 *
 * @author FXYGR @date 2019-12-31
 */
public abstract class GraphPaths {

	public GraphPaths() {
	}

	/**
	 * 在 G 中找出所有起点为 s 的路径
	 *
	 * @param G
	 * @param s
	 */
	public GraphPaths(Graph G, int s) {
	}

	/**
	 * 是否存在从 s 到 v 的路径
	 *
	 * @param v
	 * @return
	 */
	public abstract boolean hasPathTo(int v);

	/**
	 * s 到 v 的路径
	 *
	 * @param v
	 * @return
	 */
	public abstract Iterable<Integer> pathTo(int v);

}
