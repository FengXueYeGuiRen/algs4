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
	 * 找到和起点 s 连通的所有顶点/找到从起点 s 可达的所有顶点
	 *
	 * @param G
	 * @param s
	 */
	public GraphSearch(Graph G, int s) {
	}

	/**
	 * v 和 s 是连通的吗/v 从 s(ss) 是可达的吗
	 *
	 * @param v
	 * @return
	 */
	public abstract boolean marked(int v);

	/**
	 * 与 s 连通(/从 s(/ss) 可达)的顶点总数
	 *
	 * @return
	 */
	public abstract int count();

}
