package edu.princeton.cs.algs4.zh.cn.graph;

import edu.princeton.cs.algs4.zh.cn.graph.undirected.Graph;

/**
 * 图搜索 API
 *
 * @author FXYGR @date 2019-12-30
 */
public abstract class GraphSearch {

	public GraphSearch() {
	}

	/**
	 * 找到和起点 s 连通的所有顶点
	 *
	 * @param G
	 * @param s
	 */
	public GraphSearch(Graph G, int s) {
	}

	/**
	 * v 和 s 是连通的吗
	 *
	 * @param v
	 * @return
	 */
	public abstract boolean marked(int v);

	/**
	 * 与 s 连通的顶点总数
	 *
	 * @return
	 */
	public abstract int count();

}
